package core.basesyntax;

import core.basesyntax.operations.*;
import core.basesyntax.processing.FruitTransaction;
import core.basesyntax.processing.ShopService;
import core.basesyntax.processing.ShopServiceImpl;
import core.basesyntax.reading.from.file.FileReader;
import core.basesyntax.reading.from.file.FileReaderImpl;
import core.basesyntax.report.preparing.ReportGenerator;
import core.basesyntax.report.preparing.ReportGeneratorImpl;
import core.basesyntax.processing.DataConverter;
import core.basesyntax.processing.DataConverterImpl;
import core.basesyntax.writting.to.file.FileWriter;
import core.basesyntax.writting.to.file.FileWriterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1. Читання CSV
        FileReader fileReader = new FileReaderImpl();
        String filePath = ClassLoader.getSystemResource("reportToRead.csv").getPath();
        List<String> inputReport = fileReader.read(filePath);

        // 2. Конвертація
        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);

        // 3. Стратегія
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());
        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

        // 4. Обробка
        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.process(transactions);

        // 5. Звіт
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String resultingReport = reportGenerator.getReport();

        // 6. Запис у файл
        FileWriter fileWriter = new FileWriterImpl();
        fileWriter.write(resultingReport, "finalReport.csv");

        System.out.println("Report created: finalReport.csv");
    }
}