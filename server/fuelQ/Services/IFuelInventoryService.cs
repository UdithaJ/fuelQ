using fuelQ.Models;

namespace fuelQ.Services
{
    public interface IFuelInventoryService
    {
        List<FuelInventory> Get();
        FuelInventory Get(string id);
        FuelInventory Create(FuelInventory fuelInventory);
        void Update(string id, FuelInventory fuelInventory);
        void Remove(string id);
        List<FuelInventory> GetFuelInventoriesOfStation(string stationId);
        FuelInventory GetFuelInventoryByStationIdAndFuelTypeId(string stationId, string fuelTypeId);
    }
}
