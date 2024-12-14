package projects.adrian.homebudget.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "home.budget.report")
    public void listen(String message){
        log.info(message);
    }
}
