package projects.adrian.homebudget.data;

import projects.adrian.homebudget.model.entity.TransactionEntity;
import projects.adrian.homebudget.model.entity.UserEntity;

import static projects.adrian.homebudget.data.TestData.TRANSACTION_ID;

public class TransactionMockFactory {
    public static TransactionEntity mockTransactionEntity() {
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionId(TRANSACTION_ID);
        return entity;
    }
}
