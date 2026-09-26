package ConcreteHandler;

import Handler.RequestHandler;

public class LevelOneRequestHandler extends RequestHandler{

    public LevelOneRequestHandler(RequestHandler nextRequestHandler) {
        super(nextRequestHandler);
    }

    public void handleRequest(int request){
        if(request <= 3){
            System.out.println("Supervisor approved the leave.");
            return;
        }
        super.next(request);
    }
}
