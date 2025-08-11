package core.basesyntax.operations;

import core.basesyntax.processing.FruitTransaction;
import core.basesyntax.processing.Storage;

public class SupplyOperation implements OperationHandler {
    @Override
    public void apply(FruitTransaction transaction) {
        Storage.fruits.merge(transaction.getFruit(), transaction.getQuantity(), Integer::sum);
    }
}
