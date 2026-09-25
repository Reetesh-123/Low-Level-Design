//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

        Logger logger = Logger.getLoggerInstance();
        Logger logger1 = Logger.getLoggerInstance();

        if(logger == logger1){
            IO.println("both are same instance");
        }
}
