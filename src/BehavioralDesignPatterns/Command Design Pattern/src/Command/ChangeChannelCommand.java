package Command;

import Receiver.TV;

public class ChangeChannelCommand implements Command{
    private final TV tv;
    int channel;
    public ChangeChannelCommand(TV tv, int channel) {
        this.tv = tv;
        this.channel = channel;
    }

    @Override
    public void execute(){
        tv.changeChannel(channel);
    }

}
