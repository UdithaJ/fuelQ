using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace fuelQ.Models
{
    [BsonIgnoreExtraElements]
    public class User
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = String.Empty;

        [BsonElement("name")]
        public string Name { get; set; } = String.Empty;

        [BsonElement("nic")]
        public string NIC { get; set; } = String.Empty;

        [BsonElement("email")]
        public string Email { get; set; } = String.Empty;

        [BsonElement("password")]
        public string Password { get; set; } = String.Empty;

        [BsonElement("userType")]
        public string UserType { get; set; } = String.Empty;

        /*[BsonElement("vehicleType")]
        [BsonRepresentation(BsonType.ObjectId)]
        public string VehicleType { get; set; } = String.Empty;*/
    }
}
