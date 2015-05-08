package org.iot.raspberry.examples;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.iot.raspberry.grovepi.GroveDigitalIn;
import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.pi.RaspberryPi;

public class ButtonListener implements Example {

  @Override
  public void run(RaspberryPi pi, GrovePi grovePi, Monitor monitor) throws Exception {
    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

    GroveDigitalIn button = grovePi.getDigitalIn(6);
    button.setListener((boolean oldValue, boolean newValue) -> {
      System.out.println("Button " + oldValue + "->" + newValue);
    });

    exec.scheduleAtFixedRate(button, 0, 200, TimeUnit.MILLISECONDS);
    monitor.waitForStop();
    exec.shutdown();
  }

}
