using fuelQ.Models;

namespace fuelQ.Services
{
    public interface IVehicleQueueService
    {
        List<VehicleQueue> Get();
        VehicleQueue Get(string id);
        VehicleQueue Create(VehicleQueue vehicleQueue);
        void Update(string id, VehicleQueue vehicleQueue);
        void Remove(string id);
    }
}
