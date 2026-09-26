package State;

public class RedLightState implements TrafficLightState {
    TrafficLightState nextTrafficLightState;
    String colour = Lights.RED.toString();

    public RedLightState(TrafficLightState nextTrafficLightState) {
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
