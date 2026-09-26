public class MediaFacade {
    private final ImageViewer imageViewer;
    private final MusicPlayer musicPlayer;
    private final VideoPlayer videoPlayer;

    public MediaFacade(ImageViewer imageViewer, VideoPlayer videoPlayer, MusicPlayer musicPlayer) {
        this.imageViewer = imageViewer;
        this.videoPlayer = videoPlayer;
        this.musicPlayer = musicPlayer;
    }

    public void playMusic(){
        musicPlayer.InitializeAudioDriver();
        musicPlayer.decodeAudio();
        musicPlayer.startPlayback();
    }

    public void viewImage(){
        imageViewer.loadImageFile();
        imageViewer.applyScaling();
        imageViewer.displayImage();
    }

    public void playVideo(){
        videoPlayer.setupRenderingEngine();
        videoPlayer.loadVideoFile();
        videoPlayer.playVideo();
    }
}
