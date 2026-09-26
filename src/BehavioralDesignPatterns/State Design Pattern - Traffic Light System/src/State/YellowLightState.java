package State;

public class YellowLightState implements TrafficLightState{
    TrafficLightState nextTrafficLightState;
    String colour = Lights.YELLOW.toString();

    public YellowLightState(TrafficLightState nextTrafficLightState) {
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
