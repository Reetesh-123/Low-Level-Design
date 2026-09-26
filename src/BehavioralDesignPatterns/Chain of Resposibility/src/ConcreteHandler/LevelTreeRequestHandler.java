package ConcreteHandler;

import Handler.RequestHandler;

public class LevelTreeRequestHandler extends RequestHandler{

    public LevelTreeRequestHandler(RequestHandler nextRequestHandler) {
        super(nextRequestHandler);
    }

    public void handleRequest(int request){
        if(request <= 14){
            System.out.println("Director approved the leave.");
            return;
        }
        System.out.println("Leave request denied. Too many days!");
    }
}
