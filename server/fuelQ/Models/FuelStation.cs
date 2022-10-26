using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson.Serialization.Options;

namespace fuelQ.Models
{
    [BsonIgnoreExtraElements]
    public class FuelStation
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = String.Empty;
        [BsonElement("name")]
        public string Name { get; set; } = String.Empty;
        [BsonElement("address")]
        public string Address { get; set; } = String.Empty;
        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("stationOwnerId")]
        public string StationOwnerId { get; set; } = String.Empty;
        
        [BsonElement("permitNumber")]
        public string permitNumber { get; set; } = String.Empty;
    }
}
