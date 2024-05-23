package projects.adrian.homebudget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.adrian.homebudget.model.entity.TransactionEntity;
import projects.adrian.homebudget.model.entity.UserEntity;
import projects.adrian.homebudget.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<TransactionEntity> getTransactionById(UUID uuid) {
        Optional<TransactionEntity> optionalTransactionEntity = transactionRepository.findById(uuid);
        return Optional.ofNullable(optionalTransactionEntity.orElseThrow(() -> new RuntimeException("Can not find user by given id " + uuid)));
    }

//
//        @PostMapping
//        public Transaction createTransaction(@RequestBody Transaction transaction) {
//            return transactionService.createTransaction(transaction);
//        }
//
//        @PutMapping("/{id}")
//        public Transaction updateTransaction(@PathVariable UUID id, @RequestBody Transaction transactionDetails) {
//            return transactionService.updateTransaction(id, transactionDetails);
//        }
//
//        @DeleteMapping("/{id}")
//        public void deleteTransaction(@PathVariable UUID id) {
//            transactionService.deleteTransaction(id);
//        }
    }

