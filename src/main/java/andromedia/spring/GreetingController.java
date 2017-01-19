package andromedia.spring;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import andromedia.InputSender;
import andromedia.InputServer;
import andromedia.MethodPacketFactory;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
	private Object server;
	private InputSender sender;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/start")
    public String start() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, AWTException {
    	System.setProperty("java.awt.headless", "false");
        server = InputServer.create().start(1979);
        sender = InputSender.create().start("localhost", 1979);
        return "OK";
    }
    
    @RequestMapping("/send")
    public String send() throws IOException  {
        sender.send(MethodPacketFactory.getMouseMove(100, 100));
        return "OK";
    }
}