import Facade.MediaFacade;
import Multimedia.ImageViewer;
import Multimedia.MusicPlayer;
import Multimedia.VideoPlayer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
        MusicPlayer musicPlayer = new MusicPlayer();
        VideoPlayer videoPlayer = new VideoPlayer();
        ImageViewer imageViewer = new ImageViewer();

        MediaFacade mediaFacade = new MediaFacade(imageViewer, videoPlayer, musicPlayer);

        mediaFacade.playMusic();
        mediaFacade.playVideo();
        mediaFacade.viewImage();
}
