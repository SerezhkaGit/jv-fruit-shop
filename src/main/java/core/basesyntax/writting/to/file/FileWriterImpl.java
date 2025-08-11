package core.basesyntax.writting.to.file;

import java.io.IOException;

public class FileWriterImpl implements FileWriter {
    @Override
    public void write(String data, String fileName) {
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + fileName, e);
        }
    }
}
