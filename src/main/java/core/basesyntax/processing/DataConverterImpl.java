package core.basesyntax.processing;

import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    @Override
    public List<FruitTransaction> convertToTransaction(List<String> lines) {
        List<FruitTransaction> transactions = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            try {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    throw new RuntimeException("Invalid line format: " + line);
                }
                FruitTransaction.Operation operation = FruitTransaction
                        .Operation.fromCode(parts[0]);
                String fruit = parts[1];
                if (fruit == null || fruit.isEmpty()) {
                    String message = "Fruit name cannot be null or empty in line: " + line;
                    throw new RuntimeException(message);
                }
                int quantity = Integer.parseInt(parts[2]);
                if (quantity < 0) {
                    throw new RuntimeException("Quantity cannot be negative in line: "
                            + line);
                }
                transactions.add(new FruitTransaction(operation, fruit, quantity));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid quantity format in line: " + line, e);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid operation in line: " + line, e);
            }
        }
        return transactions;
    }
}
