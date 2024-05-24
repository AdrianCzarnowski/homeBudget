package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.BudgetDto;
import projects.adrian.homebudget.service.BudgetService;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ApplicationConstants.BUDGET_API_URL)
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<BudgetDto> getById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(budgetService.findById(uuid));
    }
}
