using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace fuelQ.Models
{
    public class Driver
    {
        public string Name { get; set; } = String.Empty;

        public string NIC { get; set; } = String.Empty;

        public string Email { get; set; } = String.Empty;

        public string Password { get; set; } = String.Empty;

        public string VehicleNo { get; set; } = String.Empty;

        public string VehicleType { get; set; } = String.Empty;

        public string FuelType { get; set; } = String.Empty;
    }
}
