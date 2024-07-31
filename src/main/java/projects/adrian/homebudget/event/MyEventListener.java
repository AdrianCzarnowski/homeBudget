package projects.adrian.homebudget.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyEventListener {

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent(){
        log.info("Application just started, message from listener");
    }

    @EventListener(BudgetChangeEvent.class)
    public void onBudgetChangeEvent(BudgetChangeEvent budgetChangeEvent){
        log.info("Budget change event, message from listener " + budgetChangeEvent.getBudgetDto().budgetId());
    }
}
