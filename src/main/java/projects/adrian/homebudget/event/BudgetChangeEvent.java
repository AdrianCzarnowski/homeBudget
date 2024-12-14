package projects.adrian.homebudget.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import projects.adrian.homebudget.model.dto.BudgetDto;
@Getter
public class BudgetChangeEvent extends ApplicationEvent {
    private final BudgetDto budgetDto;
    public BudgetChangeEvent(Object source, BudgetDto budgetDto) {
        super(source);
        this.budgetDto = budgetDto;
    }
}
