# Log4j-Rollbar

Library to allow logging to the [Rollbar Service](https://rollbar.com/) from Log4j 2.

The project uses an appender to send the logging details to the Rollbar service. The appender can be configured by the standard log4j config files.

_Currently only three parts can be used in the logging request, the level of the message, a logging message and a throwable._

## Config properties

* **accessToken:** the access token grenerated by the Rollbar service to provide access.
* **environment:** a tag to show what level of code usage i.e production, development
* **codeVersion:** a tag to show what version of the code is in use i.e 1.3.4, build number


Currently only three parts of the logging request is taken in to account, the level, the message and the throwable.