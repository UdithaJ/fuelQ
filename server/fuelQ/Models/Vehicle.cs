using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace fuelQ.Models
{
    [BsonIgnoreExtraElements]
    public class Vehicle
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = String.Empty;

        [BsonElement("vehicleNo")]
        public string VehicleNo { get; set; } = String.Empty;

        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("ownerId")]
        public string OwnerId { get; set; } = String.Empty;
        
        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("vehicleType")]
        public string VehicleType { get; set; } = String.Empty;
        
        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("fuelType")]
        public string FuelType { get; set; } = String.Empty;

        
    }
}
