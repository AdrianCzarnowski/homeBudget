package projects.adrian.homebudget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projects.adrian.homebudget.mapper.TransactionMapper;
import projects.adrian.homebudget.model.dto.TransactionDto;
import projects.adrian.homebudget.model.entity.TransactionEntity;
import projects.adrian.homebudget.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(transactionMapper::toDto).toList();
    }

    public TransactionDto getTransactionById(UUID uuid) {
        Optional<TransactionEntity> optionalTransactionEntity = transactionRepository.findById(uuid);
        return optionalTransactionEntity.map(transactionMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find transaction by given id " + uuid));
    }

    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        TransactionEntity transactionEntity = transactionMapper.toEntity(transactionDto);
        TransactionEntity savedEntity = transactionRepository.save(transactionEntity);
        return transactionMapper.toDto(savedEntity);
    }

    public void deleteTransaction(UUID uuid) {
        transactionRepository.deleteById(uuid);
    }
}

