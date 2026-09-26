package ConcreteHandler;

import Handler.RequestHandler;

public class LevelTwoRequestHandler extends RequestHandler {
    public LevelTwoRequestHandler(RequestHandler nextRequestHandler) {
        super(nextRequestHandler);
    }

    public void handleRequest(int request){
        if(request <= 7){
            System.out.println("Manager  approved the leave.");
            return;
        }
        super.next(request);
    }
}
