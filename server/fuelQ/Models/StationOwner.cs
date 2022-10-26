using MongoDB.Bson.Serialization.Attributes;

namespace fuelQ.Models
{
    public class StationOwner
    {
        public string UserName { get; set; } = String.Empty;

        public string NIC { get; set; } = String.Empty;

        /*public string Email { get; set; } = String.Empty;*/

        public string Password { get; set; } = String.Empty;

        public string StationName { get; set; } = String.Empty;

        public string StationAddress { get; set; } = String.Empty;
        public string permitNumber { get; set; } = String.Empty;
    }
}
