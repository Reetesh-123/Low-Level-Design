package Context;

import State.TrafficLightState;

public class TrafficLight {
    TrafficLightState trafficLightState;


    public TrafficLight() {

    }

    public TrafficLightState getTrafficLightState() {
        return trafficLightState;
    }

    public void setTrafficLightState(TrafficLightState trafficLightState) {
        this.trafficLightState = trafficLightState;
    }

    public TrafficLightState getTrafficLightNextState(){
        return trafficLightState.next();
    }

    public void moveToNextState(){
        setTrafficLightState(getTrafficLightNextState());
    }


    public void color(){
        trafficLightState.color();
    }
}
