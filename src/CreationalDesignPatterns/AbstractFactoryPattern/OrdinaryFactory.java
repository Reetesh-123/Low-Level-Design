package CreationalDesignPatterns.AbstractFactoryPattern;

import CreationalDesignPatterns.AbstractFactoryPattern.Cars.OrdinaryV1;
import CreationalDesignPatterns.AbstractFactoryPattern.Cars.OrdinaryV2;

public class OrdinaryFactory implements VehicleFactoryInterface {
    public Vehicle getVehicle(String input){

        switch (input) {
            case "OrdinaryV1":
                    return new OrdinaryV1();
            case "OrdinaryV2":
                    return new OrdinaryV2();    
        
            default:
                return null;
        }
    }
}
