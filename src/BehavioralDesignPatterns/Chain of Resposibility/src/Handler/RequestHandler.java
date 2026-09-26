package Handler;

public abstract class RequestHandler {
    protected RequestHandler nextRequestHandler;

    public RequestHandler(RequestHandler nextRequestHandler) {
        this.nextRequestHandler = nextRequestHandler;
    }

    public abstract void handleRequest(int request);

    public void next(int request){
        if(nextRequestHandler != null){
            nextRequestHandler.handleRequest(request);
        }
    }
}
