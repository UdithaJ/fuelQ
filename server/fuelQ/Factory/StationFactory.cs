using fuelQ.Models;
using fuelQ.Services;
using Newtonsoft.Json;

namespace fuelQ.Factory
{
    public class StationFactory
    {
        private readonly IUserService userService;
        private readonly IFuelStatioService fuelStatioService;

        public StationFactory(IUserService userService, IFuelStatioService fuelStatioService)
        {
            this.userService = userService;
            this.fuelStatioService = fuelStatioService;
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
            fuelStatioService.Create(station);
            return JsonConvert.SerializeObject(new { user = user , station = station});
        }
    }
}
