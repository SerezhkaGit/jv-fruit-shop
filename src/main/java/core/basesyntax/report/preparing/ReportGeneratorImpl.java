package core.basesyntax.report.preparing;

import core.basesyntax.processing.Storage;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String getReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("fruit,quantity\n");
        Storage.fruits.forEach((fruit, quantity) ->
                sb.append(fruit).append(",").append(quantity).append("\n")
        );
        return sb.toString();
    }
}
