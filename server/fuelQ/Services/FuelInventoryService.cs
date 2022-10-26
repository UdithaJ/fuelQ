using fuelQ.Helpers;
using fuelQ.Models;
using MongoDB.Bson;
using MongoDB.Driver;

namespace fuelQ.Services
{
    public class FuelInventoryService : IFuelInventoryService
    {
        private readonly IMongoCollection<FuelInventory> _fuelInventory;

        public FuelInventoryService(IfuelQDatabaseSetupHelper settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _fuelInventory = database.GetCollection<FuelInventory>(settings.FuelInventoryCollectionName);
        }
        public FuelInventory Create(FuelInventory fuelInventory)
        {
            _fuelInventory.InsertOne(fuelInventory);
            return fuelInventory;
        }

        public List<FuelInventory> Get()
        {
            return _fuelInventory.Find(fuelInventory => true).ToList();
        }

        public FuelInventory Get(string id)
        {
            return _fuelInventory.Find(fuelInventory => fuelInventory.Id == id).FirstOrDefault();
        }

        public void Remove(string id)
        {
            _fuelInventory.DeleteOne(fuelInventory => fuelInventory.Id == id);
        }

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
