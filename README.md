# Logger VS Printer

Just a little demo project to illustrate replacement of ```System.out.println``` statements by various logger levers.

## About

Tracing of program behaviour via ```System.out``` is [generally discouraged and considered bad coding style](https://stackoverflow.com/a/8601972).

 * It may potentially stall program execution, since ```system.out`` is a blocking command.
 * There is no mechanism to regulate logging severness, e.g. separate between debugging and error statements.
 * ```System.out``` is printed to console and hence does not survive e.g. a server crash and reboot.

A more sophisticated approach is the use of logging frameworks. This little demo project demonstrates how to integrate ```log4j2``` into a maven project and replace ```System.out``` statements by logger calls.

## Usage

 * Compile with:  
```mvn clean package```
 * Run with:  
```java -jar PrinterVsLogger.jar```

OR

 * Compile and run with:  
```mvn clean package exec:java```

## Enabling a logger

 * Add the logging framework to your pom.xml dependencies:  
```xml
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.11.2</version>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.11.2</version>
        </dependency>
```
 * Add a loggin configuration to your ```src/main/resources/log4j2.xml```:  
Note: You can select different logger levels for different logger destinations.  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration status="WARN">
<appenders>
    <Console name="Console" target="SYSTEM_OUT">
        <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>

    <File name="MyFile" fileName="logs/app.log">
        <PatternLayout pattern="%d{yyyy-mm-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </File>
</appenders>

<loggers>
    <root level="debug">
        <appender-ref ref="Console" level="info"/>
        <appender-ref ref="MyFile" level="debug"/>
    </root>
</loggers>
</configuration>
```
 * Import the logger where needed:  
```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
```
 * Create a logger instance:  
```java
  private static final Logger logger = LogManager.getLogger(PrinterVsLogger.class);
```
 * Call the logger with the desired severeness level:  
```
  logger.error("Something really bad happened.");
  logger.warning("Something troublesome happened.");
  logger.info("Something worth mentioning happened.");
  logger.debug("Something trivial happened.");
```

## Contact / Pull Requests

 * Author: Maximilian Schiedermeier ![email](email.png)
 * Github: Kartoffelquadrat
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)
