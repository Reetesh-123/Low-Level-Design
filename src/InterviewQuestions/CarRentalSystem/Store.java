package InterviewQuestions.CarRentalSystem;

import InterviewQuestions.CarRentalSystem.Product.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Store {
    int storeId;
    VehicleInventoryManagment inventoryManagment;
    Location location;
    List<Reservation>reservations=new ArrayList<>();

    public void setVehicle(List<Vehicle>list){
        inventoryManagment = new VehicleInventoryManagment(list);
    }

    public List<Vehicle> getVehicles(){
        return inventoryManagment.getVehicles();
    }

    public Reservation createReservation(Vehicle vehicle, User user){
        Reservation reservation= new Reservation();
        reservation.createReserve(user,vehicle);
        reservations.add(reservation);
        return reservation;
    }
    public void completeReseration(){
        //
    }
}
