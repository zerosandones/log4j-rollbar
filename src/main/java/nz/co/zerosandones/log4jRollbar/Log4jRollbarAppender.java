package nz.co.zerosandones.log4jRollbar;

import java.io.Serializable;

import com.rollbar.api.payload.data.Level;
import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;


@Plugin(name = "Rollbar", category = "Core", elementType = "appender", printObject = true)
public class Log4jRollbarAppender extends AbstractAppender {

  private Rollbar rollbarClient;

  protected Log4jRollbarAppender(String name, Filter filter, Layout<? extends Serializable> layout,
      boolean ignoreExceptions, Rollbar rollbarClient) {
    super(name, filter, layout, ignoreExceptions);
    this.rollbarClient = rollbarClient;
  }

  @PluginFactory
  public static Log4jRollbarAppender createAppender(@PluginAttribute("name") String name,
      @PluginElement("Layout") Layout<? extends Serializable> layout,
      @PluginElement("Filter") final Filter filter,
      @PluginAttribute("accessToken") String accessToken,
      @PluginAttribute("environment") String environment,
      @PluginAttribute("codeVersion") String codeVersion) {

    Config config = ConfigBuilder.withAccessToken(accessToken)
        .environment(environment)
        .codeVersion(codeVersion)
        .build();
        
    Rollbar rollbarClient = Rollbar.init(config);

    return new Log4jRollbarAppender(name, filter, layout, true, rollbarClient);
  }

  public void append(LogEvent event) {

    String messageText = null;
    if(event.getMessage() != null) messageText = event.getMessage().getFormattedMessage();

    Throwable error = null;
    if(event.getMessage().getThrowable() != null) error = event.getMessage().getThrowable();

    if(event.getLevel() == org.apache.logging.log4j.Level.FATAL){
      if(messageText != null && error != null){
        rollbarClient.critical(error, messageText);
      }else if(messageText != null){
        rollbarClient.critical(messageText);
      }else{
        rollbarClient.critical(error);
      }
    } else if(event.getLevel() == org.apache.logging.log4j.Level.ERROR){
      if(messageText != null && error != null){
        rollbarClient.error(error, messageText);
      }else if(messageText != null){
        rollbarClient.error(messageText);
      }else{
        rollbarClient.error(error);
      }
    } else if(event.getLevel() == org.apache.logging.log4j.Level.WARN){
      if(messageText != null && error != null){
        rollbarClient.warning(error, messageText);
      }else if(messageText != null){
        rollbarClient.warning(messageText);
      }else{
        rollbarClient.warning(error);
      }
    } else if(event.getLevel() == org.apache.logging.log4j.Level.DEBUG){
      if(messageText != null && error != null){
        rollbarClient.debug(error, messageText);
      }else if(messageText != null){
        rollbarClient.debug(messageText);
      }else{
        rollbarClient.debug(error);
      }
    } else if(event.getLevel() == org.apache.logging.log4j.Level.INFO || event.getLevel() == org.apache.logging.log4j.Level.TRACE){
      if(messageText != null && error != null){
        rollbarClient.info(error, messageText);
      }else if(messageText != null){
        rollbarClient.info(messageText);
      }else{
        rollbarClient.info(error);
      }
    } else {
      return;
    }
  }
}