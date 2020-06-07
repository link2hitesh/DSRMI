package SuppClasses;

import java.io.IOException;
        import java.util.logging.FileHandler;
        import java.util.logging.Level;
        import java.util.logging.Logger;

//Logger define
public class loggerC {

    /** fileHandler for logging to file */
    static private FileHandler fileHandler;

    public static void setup(final String logFile) throws IOException {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        FileHandler fileHandler;
        logger.setUseParentHandlers(false); // don't log the console output to file
        logger.setLevel(Level.INFO);
        fileHandler = new FileHandler(logFile, true);
        fileHandler.setFormatter(new formatter());
        logger.addHandler(fileHandler);
    }
}