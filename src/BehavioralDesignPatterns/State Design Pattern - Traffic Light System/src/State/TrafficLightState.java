package State;

public interface TrafficLightState {
    public TrafficLightState next();
    public void color();
}
