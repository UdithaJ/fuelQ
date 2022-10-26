using fuelQ.Models;

namespace fuelQ.Services
{
    public interface IFuelTypeService
    {
        List<FuelType> Get();
        FuelType Get(string id);
        FuelType Create(FuelType fuelType);
        void Update(string id,FuelType fuelType);
        void Remove(string id);
    }
}
