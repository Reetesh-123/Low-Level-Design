package CreationalDesignPatterns.AbstractFactoryPattern;

import CreationalDesignPatterns.AbstractFactoryPattern.Cars.LuxuryV1;
import CreationalDesignPatterns.AbstractFactoryPattern.Cars.LuxuryV2;

public class  LuxuryFactory implements VehicleFactoryInterface {
    public Vehicle getVehicle(String input){

        switch (input) {
            case "LuxuryV1":
                    return new LuxuryV1();
            case "LuxuryV2":
                    return new LuxuryV2();
        
            default:
                return null;
        }
    }
}
