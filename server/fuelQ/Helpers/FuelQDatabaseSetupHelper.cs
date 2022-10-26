namespace fuelQ.Helpers
{
    /// <summary>
    /// Helper for retrieve environment variables
    /// </summary>
    public class FuelQDatabaseSetupHelper : IfuelQDatabaseSetupHelper
    {
        public string DatabaseName { get; set; } = String.Empty;
        public string ConnectionString { get; set; } = String.Empty;
        public string UserCollectionName { get; set; } = String.Empty;
        public string FuelStationCollectionName { get; set; } = String.Empty;
        public string FuelTypeCollectionName { get; set; } = String.Empty;
        public string FuelInventoryCollectionName { get; set; } = String.Empty;
        public string VehicleQueueCollectionName { get; set; } = String.Empty;
        public string VehicleTypeCollectionName { get; set; } = String.Empty;
        public string VehicleCollectionName { get; set; } = String.Empty;
        public string Salt { get; set; } = String.Empty;
    }
}
