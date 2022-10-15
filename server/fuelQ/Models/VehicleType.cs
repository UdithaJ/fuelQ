using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace fuelQ.Models
{
    [BsonIgnoreExtraElements]
    public class VehicleType
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = String.Empty;

        [BsonElement("name")]
        public string Name { get; set; } = String.Empty;
        
        [BsonElement("description")]
        public string Description { get; set; } = String.Empty;

        [BsonElement("maxAmmount")]
        public int MaxAmmount { get; set; }
    }
}
