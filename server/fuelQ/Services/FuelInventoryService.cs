using fuelQ.Helpers;
using fuelQ.Models;
using MongoDB.Bson;
using MongoDB.Driver;

namespace fuelQ.Services
{
    /// <summary>
    /// Nusiness logic for fuel inventory related operations
    /// </summary>
    public class FuelInventoryService : IFuelInventoryService
    {
        private readonly IMongoCollection<FuelInventory> _fuelInventory;

        public FuelInventoryService(IfuelQDatabaseSetupHelper settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _fuelInventory = database.GetCollection<FuelInventory>(settings.FuelInventoryCollectionName);
        }
        /// <summary>
        /// Creates FuelInventory
        /// </summary>
        /// <param name="fuelInventory">fuelInventory Type object</param>
        /// <returns></returns>
        public FuelInventory Create(FuelInventory fuelInventory)
        {
            _fuelInventory.InsertOne(fuelInventory);
            return fuelInventory;
        }
        /// <summary>
        /// Gets All fuel inventories
        /// </summary>
        /// <returns>List of fuel inventories</returns>
        public List<FuelInventory> Get()
        {
            return _fuelInventory.Find(fuelInventory => true).ToList();
        }

        /// <summary>
        /// Gets All fuel inventory by id
        /// </summary>
        /// <param name="id"></param>
        /// <returns>A FuelInventory object</returns>
        public FuelInventory Get(string id)
        {
            return _fuelInventory.Find(fuelInventory => fuelInventory.Id == id).FirstOrDefault();
        }

        /// <summary>
        /// Deletes a fuel inventory
        /// </summary>
        /// <param name="id">Id of the fuel inventory</param>
        public void Remove(string id)
        {
            _fuelInventory.DeleteOne(fuelInventory => fuelInventory.Id == id);
        }
        /// <summary>
        /// Updates a fuel inventory record
        /// </summary>
        /// <param name="id"></param>
        /// <param name="fuelInventory"></param>
        public void Update(string id, FuelInventory fuelInventory)
        {
            _fuelInventory.ReplaceOne(type => type.Id == id, fuelInventory);
        }

        public List<FuelInventory> GetFuelInventoriesOfStation(string stationId)
        {
            return _fuelInventory.Find(fuelInventory => fuelInventory.StationId == stationId).ToList();
        }

        public FuelInventory GetFuelInventoryByStationIdAndFuelTypeId(string stationId , string fuelTypeId)
        {
            return _fuelInventory.Find(fuelInventory => fuelInventory.StationId == stationId && fuelInventory.FuelTypeId == fuelTypeId).FirstOrDefault();
        }
    }
}
