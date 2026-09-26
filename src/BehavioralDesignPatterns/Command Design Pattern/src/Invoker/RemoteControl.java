package Invoker;

import Command.Command;

public class RemoteControl {
    Command turnOffCommand;
    Command turnOnCommand;

    public RemoteControl(Command turnOnCommand, Command turnOffCommand) {
        this.turnOnCommand = turnOnCommand;
        this.turnOffCommand = turnOffCommand;
    }

    public void turnOff(){
        turnOffCommand.execute();
    }

    public void turnOn(){
        turnOnCommand.execute();
    }


}
