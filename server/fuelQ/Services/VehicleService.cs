using fuelQ.Helpers;
using fuelQ.Models;
using MongoDB.Driver;

namespace fuelQ.Services
{
    public class VehicleService : IVehicleService
    {
        private readonly IMongoCollection<Vehicle> _vehicle;

        public VehicleService(IfuelQDatabaseSetupHelper settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _vehicle = database.GetCollection<Vehicle>(settings.VehicleCollectionName);
        }
        public Vehicle Create(Vehicle vehicle)
        {
            _vehicle.InsertOne(vehicle);
            return vehicle;
        }

        public List<Vehicle> Get()
        {
            return _vehicle.Find(vehicle => true).ToList();
        }

        public Vehicle Get(string id)
        {
            return _vehicle.Find(vehicle => vehicle.Id == id).FirstOrDefault();
        }

        public Vehicle GetVehicleByOwnerId(string id)
        {
            return _vehicle.Find(vehicle => vehicle.OwnerId == id).FirstOrDefault();
        }

        public void Remove(string id)
        {
            _vehicle.DeleteOne(vehicle => vehicle.Id == id);
        }

        public void Update(string id, Vehicle vehicle)
        {
            _vehicle.ReplaceOne(type => type.Id == id, vehicle);
        }
    }
}
