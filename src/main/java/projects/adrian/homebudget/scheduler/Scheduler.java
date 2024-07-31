package projects.adrian.homebudget.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;

@Slf4j
public class Scheduler {

    @Scheduled(cron = "30 7 * * *")
    public void runEverydayAt730Am(){
        log.info("Execution every day at 07:30 AM");
    }
    @Scheduled(fixedRate = 10_000)
    public void runEvery10Sec(){
        log.info("Every 10 sec via fixedRate");
    }

    public void sjhsd(){
        log.info(String.valueOf(LocalDateTime.now()));
    }
    @Scheduled(fixedDelay = 10_000)
    @DependsOn({"sjhsd"})
    public void runEvery10SecAfterLastExecution(){
        log.info("After 10 sec via fixedDalay " +String.valueOf(LocalDateTime.now()));
    }
}
