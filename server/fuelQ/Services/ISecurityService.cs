using fuelQ.Models;

namespace fuelQ.Services
{
    public interface ISecurityService
    {
        string GenerateHashPassword(string password);
    }
}
