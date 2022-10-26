using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace fuelQ.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class VehicleController : Controller
    {
        private readonly IVehicleService vehicleService;

        public VehicleController(IVehicleService vehicleService)
        {
            this.vehicleService = vehicleService;
        }
        // GET: VehicleController
        [HttpGet("GetVehicles")]
        public ActionResult<List<Vehicle>> Index()
        {
            return vehicleService.Get();
        }

        // GET: VehicleController/GetVehicleById/5
        [HttpGet("{id}")]
        public ActionResult<Vehicle> GetVehicleById(string id)
        {
            var vehicle = vehicleService.Get(id);
            if (vehicle == null)
            {
                return NotFound($"Vehicle with id {id} not found.");
            }
            return vehicle;
        }

        // Post: VehicleController/Create
        [HttpPost("AddVehicle")]
        public ActionResult Create([FromBody] Vehicle vehicle)
        {
            vehicleService.Create(vehicle);
            return CreatedAtAction(nameof(GetVehicleById), new { id = vehicle.Id }, vehicle);
        }

        // Put: VehicleController/Edit/5
        [HttpPut("{id}")]
        public ActionResult Edit(string id, Vehicle vehicle)
        {
            var existingVehicle = GetVehicleById(id);
            if (existingVehicle == null)
            {
                return NotFound($"Vehicle with id {id} not found.");
            }
            else
            {
                vehicleService.Update(id, vehicle);
                return StatusCode(200, Json(new { status = "Success" }));
            }
        }

        // Delete: VehicleController/Delete/5
        [HttpDelete("{id}")]
        public ActionResult Delete(string id)
        {
            var vehicle = GetVehicleById(id);
            if (vehicle == null)
            {
                return NotFound($"Vehicle with id {id} not found.");
            }
            vehicleService.Remove(id);
            return Ok($"Vehicle with id {id} is deleted.");
        }
    }
}
