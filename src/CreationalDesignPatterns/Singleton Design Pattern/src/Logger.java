public class Logger {

    // The volatile keyword ensures that when one thread updates the instance, it’s visible to all other threads. This prevents any threads from getting an outdated version of the Logger object.
    private static volatile Logger logger;

    private Logger(){

    }
    public static Logger getLoggerInstance(){
          if(logger == null){
          synchronized (Logger.class){
              if(logger == null){
                  logger = new Logger();
                  return logger;
              }
          }
        }
        return logger;
    }
    public void log(){
        IO.println("Printing the logs");
    }
}
