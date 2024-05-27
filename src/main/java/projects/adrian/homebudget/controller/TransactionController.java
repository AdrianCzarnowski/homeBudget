package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.TransactionDto;
import projects.adrian.homebudget.service.TransactionService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ApplicationConstants.TRANSACTION_API_URL)
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<TransactionDto> getById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(transactionService.getTransactionById(uuid));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.saveTransaction(transactionDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable UUID uuid, @RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.saveTransaction(transactionDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<TransactionDto> deleteTransaction(@PathVariable UUID uuid) {
        transactionService.deleteTransaction(uuid);
        return ResponseEntity.noContent().build();
    }

}
