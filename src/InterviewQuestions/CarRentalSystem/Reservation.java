package InterviewQuestions.CarRentalSystem;
import InterviewQuestions.CarRentalSystem.Product.Vehicle;

public class Reservation {
    int reservationId;
    User user;
    Vehicle vehicle;
    Date bookedFrom;
    Date bookedTo;
    long fromTimeStamp;
    long toTimeStamp;
    ReservationStatus reservationStatus;
    Location location;
    ReservationType reservationType;

    public int createReserve(User user, Vehicle vehicle){
        reservationId = 12232;
        this.user=user;
        this.vehicle=vehicle;
        reservationType = ReservationType.DAILY;
        reservationStatus = ReservationStatus.SCHEDULED;

        return reservationId;
    }

}
