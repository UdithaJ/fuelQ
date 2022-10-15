using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson;

namespace fuelQ.Models
{
    [BsonIgnoreExtraElements]
    public class FuelInventory
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = String.Empty;

        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("stationId")]
        public string StationId { get; set; } = String.Empty;

        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("fuelTypeId")]
        public string FuelTypeId { get; set; } = String.Empty;

        [BsonElement("currentCapacirt")]
        public float CurrentCapacirt { get; set; }

        [BsonElement("fuelAvailability")]
        public bool FuelAvailability { get; set; }
    }
}
