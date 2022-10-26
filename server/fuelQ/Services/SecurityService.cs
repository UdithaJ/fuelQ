using fuelQ.Helpers;
using fuelQ.Models;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using MongoDB.Driver;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;
using System.Text;

namespace fuelQ.Services
{
    public class SecurityService : ISecurityService
    {
        private readonly IfuelQDatabaseSetupHelper _config;

        public SecurityService(IfuelQDatabaseSetupHelper config)
        {
            _config = config;
        }
        //public static readonly string Foo = ConfigurationManager.AppSettings["Foo"];
        public string GenerateHashPassword(string password)
        {
            // Generate a 128-bit salt using a sequence of
            // cryptographically strong random bytes.
            /*byte[] salt = RandomNumberGenerator.GetBytes(128 / 8); // divide by 8 to convert bits to bytes
            Console.WriteLine($"Salt: {Convert.ToBase64String(salt)}");*/
            byte[] salt = Encoding.ASCII.GetBytes(_config.Salt.ToString());

            // derive a 256-bit subkey (use HMACSHA256 with 100,000 iterations)
            string hashed = Convert.ToBase64String(KeyDerivation.Pbkdf2(
                password: password!,
                salt: salt,
                prf: KeyDerivationPrf.HMACSHA256,
                iterationCount: 100000,
                numBytesRequested: 256 / 8));

            return hashed;
        }
    }
}
