package CreationalDesignPatterns.AbstractFactoryPattern;

public class Main {
    public static void main(String[] args) {
        VehicleFactory VehicleObject = new VehicleFactory();
        VehicleFactoryInterface  LuxuryVehicleObject = VehicleObject.getVehicle("Luxury");
        VehicleFactoryInterface  OrdinaryVehicleObject = VehicleObject.getVehicle("Ordinary");
        LuxuryVehicleObject.getVehicle("LuxuryV2").average();
        OrdinaryVehicleObject.getVehicle("OrdinaryV1").average();
    }
}
