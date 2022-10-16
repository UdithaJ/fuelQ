﻿using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace fuelQ.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class FuelStationController : Controller
    {
        private readonly IFuelStatioService fuelStationService;

        public FuelStationController(IFuelStatioService fuelStationService)
        {
            this.fuelStationService = fuelStationService;
        }
        // GET: FuelStationController
        [HttpGet("GetFuelStations")]
        public ActionResult<List<FuelStation>> Index()
        {
            return fuelStationService.Get();
        }

        // GET: FuelStationController/GetFuelStationById/5
        [HttpGet("{id}")]
        public ActionResult<FuelStation> GetFuelStationById(string id)
        {
            var fuelStation = fuelStationService.Get(id);
            if (fuelStation == null)
            {
                return NotFound($"Fuel Station with id {id} not found.");
            }
            return fuelStation;
        }

        // Post: FuelStationController/Create
        [HttpPost("AddFuelStation")]
        public ActionResult Create([FromBody] FuelStation fuelStation)
        {
            fuelStationService.Create(fuelStation);
            return CreatedAtAction(nameof(GetFuelStationById), new { id = fuelStation.Id }, fuelStation);
        }

        // Put: FuelStationController/Edit/5
        [HttpPut("{id}")]
        public ActionResult Edit(string id, FuelStation fuelStation)
        {
            var existingFuelStation = GetFuelStationById(id);
            if (existingFuelStation == null)
            {
                return NotFound($"Fuel Station with id {id} not found.");
            }
            fuelStationService.Update(id, fuelStation);
            return NoContent();
        }

        // Delete: FuelStationController/Delete/5
        [HttpDelete("{id}")]
        public ActionResult Delete(string id)
        {
            var fuelStation = GetFuelStationById(id);
            if (fuelStation == null)
            {
                return NotFound($"Fuel Station with id {id} not found.");
            }
            fuelStationService.Remove(id);
            return Ok($"Fuel Station with id {id} is deleted.");
        }
    }
}