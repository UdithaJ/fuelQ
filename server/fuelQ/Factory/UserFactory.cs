using fuelQ.Models;
using fuelQ.Services;
using Microsoft.AspNetCore.Mvc;
using MongoDB.Bson.IO;
using System.Xml.Linq;
using JsonConvert = Newtonsoft.Json.JsonConvert;

namespace fuelQ.Factory
{
    public class UserFactory
    {
        private readonly IUserService userService;
        private readonly IVehicleService vehicleService;

        public UserFactory(IUserService userService , IVehicleService vehicleService)
        {
            this.userService = userService;
            this.vehicleService = vehicleService;
        }
        internal string RegisterDriver(Driver driver)
        {
            User user = new User();
            Vehicle vehicle = new Vehicle();
            user.NIC = driver.NIC;
            user.Name = driver.Name;
            user.Password = driver.Password;
            //user.Email = driver.Email;
            user.UserType = "Driver";
            userService.Create(user);
            vehicle.OwnerId = user.Id; 
            vehicle.VehicleNo = driver.VehicleNo;
            vehicle.VehicleType = driver.VehicleType;
            vehicle.FuelType = driver.FuelType;
            vehicleService.Create(vehicle);
            return JsonConvert.SerializeObject(new { user = user, vehicle = vehicle });
        }
    }
}
