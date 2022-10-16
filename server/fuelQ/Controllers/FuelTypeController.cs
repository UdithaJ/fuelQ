using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.VisualBasic.FileIO;

namespace fuelQ.Controllers
{
    [ApiController]
    public class FuelTypeController : Controller
    {
        private readonly IFuelTypeService fuelTypeService;

        public FuelTypeController(IFuelTypeService fuelTypeService)
        {
            this.fuelTypeService = fuelTypeService;
        }
        // GET: FuelTypeController
        [HttpGet("GetFuelTypes")]
        public ActionResult<List<FuelType>> Index()
        {
            return fuelTypeService.Get();
        }

        // GET: FuelTypeController/GetFuelTypeById/5
        [HttpGet("{id}")]
        public ActionResult<FuelType> GetFuelTypeById(string id)
        {
            var fuelType = fuelTypeService.Get(id);
            if (fuelType == null) 
            {
                return NotFound($"Fuel Type with id {id} not found.");
            }
            return fuelType;
        }

        // Post: FuelTypeController/Create
        [HttpPost("AddFuelType")]
        public ActionResult Create([FromBody] FuelType fuelType)
        {
            fuelTypeService.Create(fuelType);
            return CreatedAtAction(nameof(GetFuelTypeById) , new { id = fuelType.Id} , fuelType);
        }

        // Put: FuelTypeController/Edit/5
        [HttpPut("{id}")]
        public ActionResult Edit(string id, FuelType fuelType)
        {
            var existingFuelType = GetFuelTypeById(id);
            if (existingFuelType == null)
            {
                return NotFound($"Fuel Type with id {id} not found.");
            }
            fuelTypeService.Update(id, fuelType);
            return NoContent();
        }

        // Delete: FuelTypeController/Delete/5
        [HttpDelete("{id}")]
        public ActionResult Delete(string id)
        {
            var fuelType = GetFuelTypeById(id);
            if (fuelType == null)
            {
                return NotFound($"Fuel Type with id {id} not found.");
            }
            fuelTypeService.Remove(id);
            return Ok($"Fuel Type with id {id} is deleted.");
        }
    }
}
