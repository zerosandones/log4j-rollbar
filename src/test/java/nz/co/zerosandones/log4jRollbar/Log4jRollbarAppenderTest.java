package nz.co.zerosandones.log4jRollbar;

import static org.mockito.Mockito.*;

import java.io.Serializable;

import com.rollbar.notifier.Rollbar;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Units tests for the Log4j appender. The tests are quite brittle due to the different ways the rollbar client
 * can be called and getting the same result i.e. log(...) vs error(...). The tests rely on a mock of the rollbar
 * client which breaks the idea of not mocking classes that don't belong to you, but as the mock is used only for 
 * verifying calls I think the ability to check the right calls to the rollbar client are made are worth it. 
 * 
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class Log4jRollbarAppenderTest{

    private Log4jRollbarAppender appender;

    @Mock
    private Rollbar rollbarClient;
    @Mock
    private Filter filter;
    @Mock
    private Layout<Serializable> layout;

    @Before
    public void init(){
        appender = new Log4jRollbarAppender("rollbar",
                filter,
                layout,
                false,
                rollbarClient);
    }

    @Test 
    public void sendDebugMessageTest(){
        
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("debug message");

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.DEBUG);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).debug("debug message");

    }

    @Test 
    public void sendDebugThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.DEBUG);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).debug(e);
        
    }

    @Test 
    public void sendDebugMesageAndThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("debug message");
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.DEBUG);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).debug(e, "debug message");
        
    }

    @Test 
    public void sendWarnMessageTest(){
        
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("warn message");

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.WARN);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).warning("warn message");

    }

    @Test 
    public void sendWarnThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.WARN);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).warning(e);
        
    }

    @Test 
    public void sendWarnMesageAndThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("warning message");
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.WARN);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).warning(e, "warning message");
        
    }

    @Test 
    public void sendErrorMessageTest(){
        
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("error message");

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.ERROR);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).error("error message");

    }

    @Test 
    public void sendErrorThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.ERROR);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).error(e);
        
    }

    @Test 
    public void sendErrorMesageAndThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("error message");
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.ERROR);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).error(e, "error message");
        
    }

    @Test 
    public void sendInfoMessageTest(){
        
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("info message");

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.INFO);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).info("info message");

    }

    @Test 
    public void sendInfoThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.INFO);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).info(e);
        
    }

    @Test 
    public void sendInfoMesageAndThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("info message");
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.INFO);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).info(e, "info message");
        
    }

    @Test 
    public void sendFatalMessageTest(){
        
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("fatal message");

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.FATAL);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).critical("fatal message");

    }

    @Test 
    public void sendFatalThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.FATAL);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).critical(e);
        
    }

    @Test 
    public void sendFatalMesageAndThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("fatal message");
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.FATAL);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).critical(e, "fatal message");
        
    }

    @Test 
    public void sendTraceMessageTest(){
        
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("trace message");

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.TRACE);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).info("trace message");

    }

    @Test 
    public void sendTraceThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.TRACE);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).info(e);
        
    }

    @Test 
    public void sendTraceMesageAndThrowableTest(){
        
        Throwable e = new NullPointerException();
        Message message = mock(Message.class);
        when(message.getFormattedMessage()).thenReturn("trace message");
        when(message.getThrowable()).thenReturn(e);

        LogEvent event = mock(LogEvent.class);
        when(event.getLevel()).thenReturn(Level.TRACE);
        when(event.getMessage()).thenReturn(message);

        appender.append(event);

        verify(rollbarClient).info(e, "trace message");
        
    }
    
}