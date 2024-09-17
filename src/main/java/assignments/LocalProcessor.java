package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import assignments.annotations.FullNameProcessorGeneratorAnnotation;
import assignments.annotations.ListIteratorAnnotation;
import assignments.annotations.ReadFullProcessorNameAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalProcessor {
    private String processorName;
    private long period = 10_000_000_000_000L;
    private String processorVersion;
    private int valueOfChip;
    private static final Logger logger = Logger.getLogger(LocalProcessor.class.getName());

    public LocalProcessor(String processorName, long period, String processorVersion, int valueOfChip) {
        this.processorName = processorName;
        this.period = period;
        this.processorVersion = processorVersion;
        this.valueOfChip = valueOfChip;
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void iterateList(List<String> list) {
        list.forEach(s -> System.out.println(s.hashCode()));
    }

    @FullNameProcessorGeneratorAnnotation
    public String generateProcessorFullName(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder(processorName);
        list.forEach(s -> stringBuilder.append(s).append(" "));
        processorName = stringBuilder.toString();
        return processorName;
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorName(File file) {
        StringBuilder stringBuilder = new StringBuilder(processorVersion);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            logger.severe("Ошибка при чтении файла: " + file.getAbsolutePath() + " - " + e.getMessage());
        }
        processorVersion = stringBuilder.toString();
    }
}
