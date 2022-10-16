using fuelQ.Helpers;
using fuelQ.Models;
using MongoDB.Driver;

namespace fuelQ.Services
{
    public class FuelStatioService : IFuelStatioService
    {
        private readonly IMongoCollection<FuelStation> _fuelStations;

        public FuelStatioService(IfuelQDatabaseSetupHelper settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _fuelStations = database.GetCollection<FuelStation>(settings.FuelStationCollectionName);
        }
        public FuelStation Create(FuelStation fuelStation)
        {
            _fuelStations.InsertOne(fuelStation);
            return fuelStation;
        }

        public List<FuelStation> Get()
        {
            return _fuelStations.Find(fuelStation => true).ToList();
        }

        public FuelStation Get(string id)
        {
            return _fuelStations.Find(fuelStation => fuelStation.Id == id).FirstOrDefault();
        }

        public void Remove(string id)
        {
            _fuelStations.DeleteOne(fuelStation => fuelStation.Id == id);
        }

        public void Update(string id, FuelStation fuelStation)
        {
            _fuelStations.ReplaceOne(type => type.Id == id, fuelStation);
        }
    }
}
