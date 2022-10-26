using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace fuelQ.Factory
{
    public class StationFactory
    {
        private readonly IUserService userService;
        private readonly IFuelStatioService fuelStatioService;
        private readonly IFuelInventoryService fuelInventoryService;
        private readonly IFuelTypeService fuelTypeService;

        public StationFactory(IUserService userService, IFuelStatioService fuelStatioService, IFuelInventoryService fuelInventoryService, IFuelTypeService fuelTypeService)
        {
            this.userService = userService;
            this.fuelStatioService = fuelStatioService;
            this.fuelInventoryService = fuelInventoryService;
            this.fuelTypeService = fuelTypeService;
        }

        internal ActionResult<string> GetStationFuelInventories(string stationId)
        {
            return JsonConvert.SerializeObject(fuelInventoryService.GetFuelInventoriesOfStation(stationId));
        }

        internal string RegisterStation(StationOwner stationOwner)
        {
            User user = new User();
            FuelStation station = new FuelStation();
            user.NIC = stationOwner.NIC;
            user.Name = stationOwner.UserName;
            user.Password = stationOwner.Password;
            //user.Email = stationOwner.Email;
            user.UserType = "Station Owner";
            userService.Create(user);
            station.StationOwnerId = user.Id;
            station.Address = stationOwner.StationAddress;
            station.Name = stationOwner.StationName;
            station.permitNumber = stationOwner.permitNumber;
            fuelStatioService.Create(station);
            List<FuelInventory> fuelInventories = new List<FuelInventory>();
            List<FuelType> fuelTypes = fuelTypeService.Get();
            foreach (FuelType fuelType in fuelTypes)
            {
                FuelInventory inventory = new FuelInventory() { StationId = station.Id , FuelTypeId= fuelType.Id, CurrentCapacirt = 0,FuelAvailability = false};
                FuelInventory newInventory = fuelInventoryService.Create(inventory);
                fuelInventories.Add(newInventory);
            }
            return JsonConvert.SerializeObject(new { user = user , station = station , fuelInventory = fuelInventories});
        }
    }
}
