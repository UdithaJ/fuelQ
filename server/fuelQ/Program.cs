using fuelQ.Helpers;
using fuelQ.Services;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.Configure<FuelQDatabaseSetupHelper>(builder.Configuration.GetSection("FuelQDatabaseSettings"));
builder.Services.AddSingleton<IfuelQDatabaseSetupHelper>(sp => sp.GetRequiredService<IOptions<FuelQDatabaseSetupHelper>>().Value);
builder.Services.AddSingleton<IMongoClient>(s => new MongoClient(builder.Configuration.GetValue<string>("FuelQDatabaseSettings:ConnectionString")));
builder.Services.AddScoped<IFuelTypeService, FuelTypeService>();
builder.Services.AddScoped<IVehicleTypeService, VehicleTypeService>();
builder.Services.AddScoped<IFuelStatioService, FuelStatioService>();
builder.Services.AddScoped<IUserService, UserService>();
builder.Services.AddScoped<IFuelInventoryService, FuelInventoryService>();
builder.Services.AddScoped<IVehicleQueueService, VehicleQueueService>();
builder.Services.AddScoped<IVehicleService, VehicleService>();
builder.Services.AddScoped<ISecurityService, SecurityService>();

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseCors("corsapp");

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
