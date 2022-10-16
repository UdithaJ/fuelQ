using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace fuelQ.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class FuelInventoryController : Controller
    {
        private readonly IFuelInventoryService fuelInventoryService;

        public FuelInventoryController(IFuelInventoryService fuelInventoryService)
        {
            this.fuelInventoryService = fuelInventoryService;
        }
        // GET: FuelInventoryController
        [HttpGet("GetFuelInventories")]
        public ActionResult<List<FuelInventory>> Index()
        {
            return fuelInventoryService.Get();
        }

        // GET: FuelInventoryController/GetFuelInventoryById/5
        [HttpGet("{id}")]
        public ActionResult<FuelInventory> GetFuelInventoryById(string id)
        {
            var fuelInventory = fuelInventoryService.Get(id);
            if (fuelInventory == null)
            {
                return NotFound($"Fuel Inventory with id {id} not found.");
            }
            return fuelInventory;
        }

        // Post: FuelInventoryController/Create
        [HttpPost("AddFuelInventory")]
        public ActionResult Create([FromBody] FuelInventory fuelInventory)
        {
            fuelInventoryService.Create(fuelInventory);
            return CreatedAtAction(nameof(GetFuelInventoryById), new { id = fuelInventory.Id }, fuelInventory);
        }

        // Put: FuelInventoryController/Edit/5
        [HttpPut("{id}")]
        public ActionResult Edit(string id, FuelInventory fuelInventory)
        {
            var existingFuelInventory = GetFuelInventoryById(id);
            if (existingFuelInventory == null)
            {
                return NotFound($"Fuel Inventory with id {id} not found.");
            }
            fuelInventoryService.Update(id, fuelInventory);
            return NoContent();
        }

        // Delete: FuelInventoryController/Delete/5
        [HttpDelete("{id}")]
        public ActionResult Delete(string id)
        {
            var fuelInventory = GetFuelInventoryById(id);
            if (fuelInventory == null)
            {
                return NotFound($"Fuel Inventory with id {id} not found.");
            }
            fuelInventoryService.Remove(id);
            return Ok($"Fuel Inventory with id {id} is deleted.");
        }
    }
}
