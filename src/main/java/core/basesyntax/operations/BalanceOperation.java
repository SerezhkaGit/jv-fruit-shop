package core.basesyntax.operations;

import core.basesyntax.processing.FruitTransaction;
import core.basesyntax.processing.Storage;

public class BalanceOperation implements OperationHandler {
    @Override
    public void apply(FruitTransaction transaction) {
        Storage.fruits.put(transaction.getFruit(), transaction.getQuantity());
    }
}
