using fuelQ.Models;

namespace fuelQ.Services
{
    public interface IVehicleTypeService
    {
        List<VehicleType> Get();
        VehicleType Get(string id);
        VehicleType Create(VehicleType vehicleType);
        void Update(string id, VehicleType vehicleType);
        void Remove(string id);
    }
}
