using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace fuelQ.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class VehicleQueueController : Controller
    {
        private readonly IVehicleQueueService vehicleQueueService;

        public VehicleQueueController(IVehicleQueueService vehicleQueueService)
        {
            this.vehicleQueueService = vehicleQueueService;
        }
        // GET: VehicleQueueController
        [HttpGet("GetVehicleQueues")]
        public ActionResult<List<VehicleQueue>> Index()
        {
            return vehicleQueueService.Get();
        }

        // GET: VehicleQueueController/GetVehicleQueueById/5
        [HttpGet("{id}")]
        public ActionResult<VehicleQueue> GetVehicleQueueById(string id)
        {
            var vehicleQueue = vehicleQueueService.Get(id);
            if (vehicleQueue == null)
            {
                return NotFound($"Vehicle Queue with id {id} not found.");
            }
            return vehicleQueue;
        }

        // Post: VehicleQueueController/Create
        [HttpPost("AddVehicleQueue")]
        public ActionResult Create([FromBody] VehicleQueue vehicleQueue)
        {
            vehicleQueueService.Create(vehicleQueue);
            return CreatedAtAction(nameof(GetVehicleQueueById), new { id = vehicleQueue.Id }, vehicleQueue);
        }

        // Put: VehicleQueueController/Edit/5
        [HttpPut("{id}")]
        public ActionResult Edit(string id, VehicleQueue vehicleQueue)
        {
            var existingVehicleQueue = GetVehicleQueueById(id);
            if (existingVehicleQueue == null)
            {
                return NotFound($"Vehicle Queue with id {id} not found.");
            }
            else
            {
                vehicleQueueService.Update(id, vehicleQueue);
                return StatusCode(200, Json(new { status = "Success" }));
            }
        }

        // Delete: VehicleQueueController/Delete/5
        [HttpDelete("{id}")]
        public ActionResult Delete(string id)
        {
            var vehicleQueue = GetVehicleQueueById(id);
            if (vehicleQueue == null)
            {
                return NotFound($"Vehicle Queue with id {id} not found.");
            }
            vehicleQueueService.Remove(id);
            return Ok($"Vehicle Queue with id {id} is deleted.");
        }
    }
}
