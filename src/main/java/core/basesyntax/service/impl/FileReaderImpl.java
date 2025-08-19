package core.basesyntax.service.impl;

import core.basesyntax.service.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class FileReaderImpl implements FileReader {
    @Override
    public List<String> read(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path path = Paths.get(Objects.requireNonNull(classLoader.getResource(fileName))
                    .toURI());
            return Files.readAllLines(path);
        } catch (Exception e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }
}
