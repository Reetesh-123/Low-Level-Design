package CarRentalSystem;

import CarRentalSystem.Product.Vehicle;

import java.util.List;

public class VehicleInventoryManagment extends Vehicle {
    List<Vehicle>vehicles;

    public VehicleInventoryManagment(List<Vehicle> list){
        vehicles=list;
    }

    public List<Vehicle> getVehicles(){
        return vehicles;
    }
    public void setVehicles(List<Vehicle>list){
        vehicles=list;
    }
}
