package RealSubject;

import Subject.VideoService;

public class RealVideoService implements VideoService {

    @Override
    public void playVideo(String userType, String videoName){
        System.out.println("Streaming video: " + videoName);
    }
}
