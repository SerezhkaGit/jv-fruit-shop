package core.basesyntax.operations;

import core.basesyntax.processing.FruitTransaction;
import java.util.Map;

public class OperationStrategyImpl implements OperationStrategy {
    private final Map<FruitTransaction.Operation, OperationHandler> operationHandlers;

    public OperationStrategyImpl(Map<FruitTransaction.Operation,
            OperationHandler> operationHandlers) {
        this.operationHandlers = operationHandlers;
    }

    @Override
    public OperationHandler get(FruitTransaction.Operation operation) {
        if (!operationHandlers.containsKey(operation)) {
            throw new RuntimeException("Unsupported operation: " + operation);
        }
        return operationHandlers.get(operation);
    }
}
