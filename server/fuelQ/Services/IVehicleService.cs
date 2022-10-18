using fuelQ.Models;

namespace fuelQ.Services
{
    public interface IVehicleService
    {
        List<Vehicle> Get();
        Vehicle Get(string id);
        Vehicle Create(Vehicle Vehicle);
        void Update(string id, Vehicle Vehicle);
        void Remove(string id);
    }
}
