package core.basesyntax.operations;

import core.basesyntax.processing.FruitTransaction;

public interface OperationStrategy {
    OperationHandler get(FruitTransaction.Operation operation);
}
