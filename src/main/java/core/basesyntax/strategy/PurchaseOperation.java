package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class PurchaseOperation implements OperationHandler {
    @Override
    public void apply(FruitTransaction transaction) {
        String fruit = transaction.getFruit();
        int currentQuantity = Storage.fruits.getOrDefault(fruit, 0);
        int toBuy = transaction.getQuantity();

        if (currentQuantity < toBuy) {
            throw new RuntimeException("Not enough " + fruit
                    + " in shop. Available: " + currentQuantity
                    + ", requested: " + toBuy);
        }

        Storage.fruits.put(fruit, currentQuantity - toBuy);
    }
}