package projects.adrian.homebudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.adrian.homebudget.model.entity.TransactionEntity;
import projects.adrian.homebudget.service.TransactionService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionEntity> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Optional<TransactionEntity> getTransactionById(@PathVariable UUID id) {
        return transactionService.getTransactionById(id);
    }

}
