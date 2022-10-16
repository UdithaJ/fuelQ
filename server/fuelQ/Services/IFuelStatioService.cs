using fuelQ.Models;

namespace fuelQ.Services
{
    public interface IFuelStatioService
    {
        List<FuelStation> Get();
        FuelStation Get(string id);
        FuelStation Create(FuelStation fuelStation);
        void Update(string id, FuelStation fuelStation);
        void Remove(string id);
    }
}
