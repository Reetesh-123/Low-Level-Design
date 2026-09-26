import Proxy.VideoServiceProxy;
import Subject.VideoService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    VideoService videoService = new VideoServiceProxy();

    videoService.playVideo("premium","Premium video 1");
    videoService.playVideo("free","Free video 1");
    videoService.playVideo("guest","video 1");


}
