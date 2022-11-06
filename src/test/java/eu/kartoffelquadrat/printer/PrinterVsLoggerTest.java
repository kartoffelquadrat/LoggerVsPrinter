package eu.kartoffelquadrat.printer;

import org.junit.Test;

/**
 * Simple test for the system out println based loop.
 *
 * @author Maximilian Schiedermeier
 */
public class PrinterVsLoggerTest {

  @Test
  public void testPrinter() {
    PrinterVsLogger.printManyNumbers(5);
  }

}