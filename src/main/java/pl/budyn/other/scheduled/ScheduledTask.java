package pl.budyn.other.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hlibe on 17.12.2016.
 */
@Component
public class ScheduledTask {
    private static final SimpleDateFormat date =  new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 500000)
    public void reportCurrentTime(){
        System.out.println("The time is now: " + date.format(new Date()));
    }
}

