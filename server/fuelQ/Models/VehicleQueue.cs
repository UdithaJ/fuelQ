using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace fuelQ.Models
{
    [BsonIgnoreExtraElements]
    public class VehicleQueue
    {
        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("stationId")]
        public string StationId { get; set; } = String.Empty;

        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("fuelTypeId")]
        public string FuelTypeId { get; set; } = String.Empty;

        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("UserId")]
        public string userId { get; set; } = String.Empty;

        [BsonRepresentation(BsonType.DateTime)]
        [BsonElement("timeIn")]
        public DateTime TimeIn { get; set; }

        [BsonRepresentation(BsonType.DateTime)]
        [BsonElement("timeOut")]
        public DateTime TimeOut { get; set; }

        [BsonElement("isIn")]
        public bool IsIn { get; set; }
    }
}
