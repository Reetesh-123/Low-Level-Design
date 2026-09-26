//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Context.TrafficLight;
import State.GreenLightState;
import State.RedLightState;
import State.YellowLightState;

void main() {
        GreenLightState greenLightState = new GreenLightState(null);
        YellowLightState yellowLightState = new YellowLightState(greenLightState);
        RedLightState redLightState = new RedLightState(yellowLightState);

        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setTrafficLightState(redLightState);
        trafficLight.color();

        trafficLight.moveToNextState();
        trafficLight.color();

        trafficLight.moveToNextState();
        trafficLight.color();

}
