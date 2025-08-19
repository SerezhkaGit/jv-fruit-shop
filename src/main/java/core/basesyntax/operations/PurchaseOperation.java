package core.basesyntax.operations;

import core.basesyntax.processing.FruitTransaction;
import core.basesyntax.processing.Storage;

public class PurchaseOperation implements OperationHandler {
    @Override
    public void apply(FruitTransaction transaction) {
        if (transaction.getFruit() == null || transaction.getFruit().isEmpty()) {
            throw new RuntimeException("Fruit name cannot be null or empty");
        }
        if (transaction.getQuantity() < 0) {
            throw new RuntimeException("Quantity cannot be negative: " + transaction.getQuantity());
        }
        int current = Storage.fruits.getOrDefault(transaction.getFruit(), 0);
        if (current < transaction.getQuantity()) {
            throw new RuntimeException("Not enough " + transaction.getFruit()
                    + " in stock. Current: " + current
                    + ", requested: " + transaction.getQuantity());
        }
        Storage.fruits.put(transaction.getFruit(), current - transaction.getQuantity());
    }
}
