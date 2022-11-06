package eu.kartoffelquadrat.printer;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A simple demo program to illustrate how to replace System-out-printlns by proper logs. Logs are
 * in general slower, but allow a precise routing and support severeness levels.
 *
 * <p>Greatly inspired by: https://mkyong.com/logging/apache-log4j-2-tutorials/
 * https://stackoverflow.com/a/17424539
 *
 * @author Maximilian Schiedermeier
 */
public class PrinterVsLogger {

  // Initialize log4j2 logger.
  private static final Logger logger = LogManager.getLogger(PrinterVsLogger.class);

  // Set global amount of messages to log per experiment.
  private static final int AMOUNT = 10000;

  /**
   * Runs to logging procedures and compares performance. First operation is to log 10.000 numbers
   * via Sysout. Second operation is to log the same numbers using a logger that saves to disk.
   *
   * @param args not needed.
   */
  public static void main(String[] args) {

    // Procedure 1: logs to console using sysout.
    long printDiff = printManyNumbers(AMOUNT);


    // Procedure 2: Logs to File and console (if log level matches).
    long logDiff = logManyNumbers(AMOUNT);

    // Print time needed for individual experiments
    System.out.println("Total print time needed: " + printDiff + "[ms]");
    logger.info("Total log time needed: " + logDiff + "[ms]");

  }

  /**
   * Helper method to measure time required for printing requested amount of messages.
   *
   * @param amount as number of messages to be printed.
   * @return the time in milliseconds required for the entire printing.
   */
  protected static long printManyNumbers(int amount) {
    long printStartMoment = new Date().getTime();
    for (int i = 0; i < amount; i++) {
      System.out.println(i);
    }

    long printEndMoment = new Date().getTime();
    return printEndMoment - printStartMoment;
  }

  /**
   * Helper method to measure time required for logging requested amount of messages.
   *
   * @param amount as number of messages to be logged.
   * @return the time in milliseconds required for the entire logging.
   */
  protected static long logManyNumbers(int amount) {

    logger.info("Starting logging of numbers");
    long logStartMoment = new Date().getTime();

    for (int i = 0; i < amount; i++) {
      logger.debug(Integer.toString(i));
    }

    logger.info("Finished logging of numbers");
    long logEndMoment = new Date().getTime();
    return logEndMoment - logStartMoment;
  }
}
