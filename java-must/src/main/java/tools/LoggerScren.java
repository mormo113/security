package tools;


import javafx.scene.control.TextArea;
import org.apache.log4j.Logger;

/**
 * Created by b010cli on 19/09/2016.
 */
public class LoggerScren {

    private static TextArea PrintLog;

    private Logger LOGGER;

    //Bug qui fait pete l'ecran de log car trop de log d'un coup
    private static final int WAIT_TIME = 0;

    public static TextArea getPrintLog() {
        return PrintLog;
    }

    public static void setPrintLog(TextArea printLog) {
        PrintLog = printLog;
    }

    public LoggerScren(String ClassName) {
        LOGGER = Logger.getLogger(ClassName);
    }

    public void info(Object message) {
        LOGGER.info(message);
    }

    public synchronized void error(Object message) {

        if (PrintLog != null) {
            PrintLog.appendText(message + "\n");
        }
        LOGGER.error(message);

    }

    public synchronized void error(Object message, Throwable t) {
        if (PrintLog != null) {
            PrintLog.appendText(message + "\n");
        }
        LOGGER.error(message, t);
    }

}
