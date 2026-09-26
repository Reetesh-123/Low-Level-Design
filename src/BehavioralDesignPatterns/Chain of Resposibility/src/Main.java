import ConcreteHandler.LevelOneRequestHandler;
import ConcreteHandler.LevelTreeRequestHandler;
import ConcreteHandler.LevelTwoRequestHandler;
import Handler.RequestHandler;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    int request = 13;
    RequestHandler requestHandler = new LevelOneRequestHandler(new LevelTwoRequestHandler(new LevelTreeRequestHandler(null)));

    requestHandler.handleRequest(request);
    requestHandler.handleRequest(15);
}
