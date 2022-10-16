using fuelQ.Helpers;
using fuelQ.Models;
using MongoDB.Driver;

namespace fuelQ.Services
{
    public class UserService : IUserService
    {
        private readonly IMongoCollection<User> _user;

        public UserService(IfuelQDatabaseSetupHelper settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _user = database.GetCollection<User>(settings.UserCollectionName);
        }
        public User Create(User user)
        {
            _user.InsertOne(user);
            return user;
        }

        public List<User> Get()
        {
            return _user.Find(user => true).ToList();
        }

        public User Get(string id)
        {
            return _user.Find(user => user.Id == id).FirstOrDefault();
        }

        public void Remove(string id)
        {
            _user.DeleteOne(user => user.Id == id);
        }

        public void Update(string id, User user)
        {
            _user.ReplaceOne(type => type.Id == id, user);
        }
    }
}
