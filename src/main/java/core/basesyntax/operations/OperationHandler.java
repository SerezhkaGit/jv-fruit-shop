package core.basesyntax.operations;

import core.basesyntax.processing.FruitTransaction;

public interface OperationHandler {
    void apply(FruitTransaction transaction);
}
