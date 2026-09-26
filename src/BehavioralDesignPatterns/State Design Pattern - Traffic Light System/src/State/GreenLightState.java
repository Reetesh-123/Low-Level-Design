package State;

public class GreenLightState implements TrafficLightState{
    TrafficLightState nextTrafficLightState;
    String colour = Lights.GREEN.toString();

    public GreenLightState(TrafficLightState nextTrafficLightState) {
        this.nextTrafficLightState = nextTrafficLightState;
    }

    public void color(){
        IO.println("Current Traffic light colour is " + colour);
    }

    @Override
    public TrafficLightState next() {
        return nextTrafficLightState;
    }
}
