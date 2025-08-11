import core.basesyntax.operations.BalanceOperation;
import core.basesyntax.operations.OperationHandler;
import core.basesyntax.operations.OperationStrategy;
import core.basesyntax.operations.OperationStrategyImpl;
import core.basesyntax.operations.PurchaseOperation;
import core.basesyntax.operations.ReturnOperation;
import core.basesyntax.operations.SupplyOperation;
import core.basesyntax.processing.DataConverter;
import core.basesyntax.processing.DataConverterImpl;
import core.basesyntax.processing.FruitTransaction;
import core.basesyntax.processing.ShopService;
import core.basesyntax.processing.ShopServiceImpl;
import core.basesyntax.reading.from.file.FileReader;
import core.basesyntax.reading.from.file.FileReaderImpl;
import core.basesyntax.report.preparing.ReportGenerator;
import core.basesyntax.report.preparing.ReportGeneratorImpl;
import core.basesyntax.writting.to.file.FileWriter;
import core.basesyntax.writting.to.file.FileWriterImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        FileReader fileReader = new FileReaderImpl();
        String filePath = ClassLoader.getSystemResource("reportToRead.csv").getPath();
        List<String> inputReport = fileReader.read(filePath);

        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());
        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);

        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.process(transactions);

        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String resultingReport = reportGenerator.getReport();

        FileWriter fileWriter = new FileWriterImpl();
        fileWriter.write(resultingReport, "finalReport.csv");

        System.out.println("Report created: finalReport.csv");
    }
}
