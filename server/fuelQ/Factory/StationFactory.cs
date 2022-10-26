using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Mvc;
using MongoDB.Bson;
using Newtonsoft.Json;

namespace fuelQ.Factory
{
    //Station Factory
    //Crdinates The Fuel Station related Services
    public class StationFactory
    {
        private readonly IUserService userService;
        private readonly IFuelStatioService fuelStatioService;
        private readonly IFuelInventoryService fuelInventoryService;
        private readonly IFuelTypeService fuelTypeService;
        private readonly ISecurityService securityService;
        

        /// <summary>
        /// 
        /// </summary>
        /// <param name="userService"></param>
        /// <param name="fuelStatioService"></param>
        /// <param name="fuelInventoryService"></param>
        /// <param name="fuelTypeService"></param>
        /// <param name="securityService"></param>
        public StationFactory(IUserService userService, IFuelStatioService fuelStatioService, IFuelInventoryService fuelInventoryService, IFuelTypeService fuelTypeService, ISecurityService securityService)
        {
            this.userService = userService;
            this.fuelStatioService = fuelStatioService;
            this.fuelInventoryService = fuelInventoryService;
            this.fuelTypeService = fuelTypeService;
            this.securityService = securityService;
        }
        /// <summary>
        /// 
        /// </summary>
        /// <param name="stationId">Fuel Station Id</param>
        /// <returns></returns>
        internal ActionResult GetStationFuelInventories(string stationId)
        {
            //return JsonConvert.SerializeObject(fuelInventoryService.GetFuelInventoriesOfStation(stationId));
            //return fuelInventoryService.GetFuelInventoriesOfStation(stationId).ToJson();
            return new ContentResult { Content = fuelInventoryService.GetFuelInventoriesOfStation(stationId).ToJson(), ContentType = "application/json" };
        }
        /// <summary>
        /// 
        /// </summary>
        /// <param name="stationOwner">stationOwner Type Object</param>
        /// <returns></returns>
        internal string RegisterStation(StationOwner stationOwner)
        {
            User user = new User();
            FuelStation station = new FuelStation();
            user.NIC = stationOwner.NIC;
            user.Name = stationOwner.UserName;
            user.Password = securityService.GenerateHashPassword(stationOwner.Password);
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
