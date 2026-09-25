package CreationalDesignPatterns.AbstractFactoryPattern;

public class VehicleFactory {
    
    public VehicleFactoryInterface getVehicle(String input){
        
        switch (input) {
            case "Luxury":
                    return new LuxuryFactory();
                
            case "Ordinary":
                    return new OrdinaryFactory();
            default:
                return null;
        }
    }
}
