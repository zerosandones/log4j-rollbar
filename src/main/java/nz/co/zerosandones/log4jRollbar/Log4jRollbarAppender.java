package nz.co.zerosandones.log4jRollbar;

import java.io.Serializable;

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

  protected Log4jRollbarAppender(String name, Filter filter, Layout<? extends Serializable> layout,
      boolean ignoreExceptions) {
    super(name, filter, layout, ignoreExceptions);
  }

  @PluginFactory
  public static Log4jRollbarAppender createAppender(@PluginAttribute("name") String name,
      @PluginElement("Layout") Layout<? extends Serializable> layout,
      @PluginElement("Filter") final Filter filter,
      @PluginAttribute("accessToken") String accessToken,
      @PluginAttribute("environment") String environment) {


    return new Log4jRollbarAppender(name, filter, layout, true);
  }

  public void append(LogEvent event) {
   
  }
}