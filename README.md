# Log4j-Rollbar

** Since rollbar now provides this functionality in [rollbar-log4j2](https://docs.rollbar.com/docs/log4j2), I am going to archive this. I am leaving this here in case in the future I want to do something similar.**

Library to allow logging to the [Rollbar Service](https://rollbar.com/) from Log4j 2.

The project uses an appender to send the logging details to the Rollbar service. The appender can be configured by the standard log4j config files.

_Currently only three parts can be used in the logging request, the level of the message, a logging message and a throwable._

## Config properties

* **accessToken:** the access token grenerated by the Rollbar service to provide access.
* **environment:** a tag to show what level of code usage i.e production, development
* **codeVersion:** a tag to show what version of the code is in use i.e 1.3.4, build number

Example log4j2.xml file

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration packages="nz.co.zerosandones.log4jRollbar">
  <Appenders>
    <Console name="Console">
      <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>
    <Rollbar name="Rollbar">
      <accessToken>example_token</accessToken>
      <environment>production</environment>
      <codeVersion>1.2.1</codeVersion>
    </Rollbar>
  </Appenders>
  <Loggers>
    <Root level="trace">
      <AppenderRef ref="Console"/>
      <AppenderRef ref="Rollbar"/>
    </Root>
  </Loggers>
</Configuration>
```
