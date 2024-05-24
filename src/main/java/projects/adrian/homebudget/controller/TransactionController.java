package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.TransactionDto;
import projects.adrian.homebudget.model.entity.TransactionEntity;
import projects.adrian.homebudget.service.TransactionService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ApplicationConstants.TRANSACTION_API_URL)
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionEntity> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<TransactionDto> getById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(transactionService.getTransactionById(uuid));
    }
}
