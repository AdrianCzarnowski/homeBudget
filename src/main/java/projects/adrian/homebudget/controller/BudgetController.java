package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.BudgetDto;
import projects.adrian.homebudget.service.BudgetService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ApplicationConstants.BUDGET_API_URL)
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<BudgetDto> getById(@PathVariable UUID uuid, @RequestBody BudgetDto budgetDto) {
        return ResponseEntity.ok(budgetService.findByBudgetId(uuid));
    }

    @GetMapping
    public ResponseEntity<List<BudgetDto>> getAllBudgets(@RequestBody BudgetDto budgetDto) {
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    @PostMapping
    public ResponseEntity<BudgetDto> createBudget(@RequestBody BudgetDto budgetDto) {
        return new ResponseEntity<>(budgetService.saveBudget(budgetDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<BudgetDto> updateBudget(@PathVariable UUID uuid, @RequestBody BudgetDto budgetDto) {
        return new ResponseEntity<>(budgetService.saveBudget(budgetDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<BudgetDto> deleteBudget(@PathVariable UUID uuid) {
        budgetService.deleteBudget(uuid);
        return ResponseEntity.noContent().build();
    }
}
