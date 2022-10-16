using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace fuelQ.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class VehicleTypeController : Controller
    {
        private readonly IVehicleTypeService vehicleTypeService;

        public VehicleTypeController(IVehicleTypeService vehicleTypeService)
        {
            this.vehicleTypeService = vehicleTypeService;
        }
        // GET: VehicleTypeController
        [HttpGet("GetVehicleTypes")]
        public ActionResult<List<VehicleType>> Index()
        {
            return vehicleTypeService.Get();
        }

        // GET: VehicleTypeController/GetVehicleTypeById/5
        [HttpGet("{id}")]
        public ActionResult<VehicleType> GetVehicleTypeById(string id)
        {
            var vehicleType = vehicleTypeService.Get(id);
            if (vehicleType == null)
            {
                return NotFound($"Vehicle Type with id {id} not found.");
            }
            return vehicleType;
        }

        // Post: VehicleTypeController/Create
        [HttpPost("AddVehicleType")]
        public ActionResult Create([FromBody] VehicleType vehicleType)
        {
            vehicleTypeService.Create(vehicleType);
            return CreatedAtAction(nameof(GetVehicleTypeById), new { id = vehicleType.Id }, vehicleType);
        }

        // Put: VehicleTypeController/Edit/5
        [HttpPut("{id}")]
        public ActionResult Edit(string id, VehicleType vehicleType)
        {
            var existingFuelStation = GetVehicleTypeById(id);
            if (existingFuelStation == null)
            {
                return NotFound($"Vehicle Type with id {id} not found.");
            }
            vehicleTypeService.Update(id, vehicleType);
            return NoContent();
        }

        // Delete: VehicleTypeController/Delete/5
        [HttpDelete("{id}")]
        public ActionResult Delete(string id)
        {
            var fuelStation = GetVehicleTypeById(id);
            if (fuelStation == null)
            {
                return NotFound($"Vehicle Type with id {id} not found.");
            }
            vehicleTypeService.Remove(id);
            return Ok($"Vehicle Type with id {id} is deleted.");
        }
    }
}
