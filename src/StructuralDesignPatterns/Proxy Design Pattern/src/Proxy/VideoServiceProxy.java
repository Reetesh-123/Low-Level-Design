package Proxy;

import RealSubject.RealVideoService;
import Subject.VideoService;

public class VideoServiceProxy implements VideoService {
    private RealVideoService realVideoService;

    @Override
    public void playVideo(String userType, String videoName){
        if(!userType.equals("premium") && !userType.equals("free")){
            System.out.println("Access denied: Invalid user type.");
            return;
        }

        if(realVideoService == null){
            realVideoService = new RealVideoService();
        }
        realVideoService.playVideo(userType, videoName);
    }

}
