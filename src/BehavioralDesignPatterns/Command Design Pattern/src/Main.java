//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Command.TurnOnCommand;
import Command.TurnOffCommand;
import Invoker.RemoteControl;
import Receiver.TV;

void main() {

    TV tv = new TV();
    TurnOffCommand turnOffCommand = new TurnOffCommand(tv);
    TurnOnCommand turnOnCommand = new TurnOnCommand(tv);
    RemoteControl remoteControl = new RemoteControl(turnOnCommand,turnOffCommand);

    remoteControl.turnOn();
    remoteControl.turnOff();
}
