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
    private Scanner informationScanner;
    private StringBuilder stringBuilder;
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
        try {
            list.forEach(s -> System.out.println(s.hashCode()));
        } catch (NullPointerException e) {
            String warningString = "NullPointerException occurred while trying iterate over list";
            logger.warning(": " + e.getMessage());
            System.out.println(warningString);
        }
    }

    @FullNameProcessorGeneratorAnnotation
    public String generateProcessorFullName(List<String> list) {
        try {
            stringBuilder = new StringBuilder(processorName);
            list.forEach(s -> stringBuilder.append(s).append(" "));
            processorName = stringBuilder.toString().trim();
            return processorName;
        } catch (NullPointerException e) {
            String warningString = "NullPointerException occurred while trying generate processor name from list";
            logger.warning(": " + e.getMessage());
            return warningString;
        }

    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorName(File file) {
        try {
            informationScanner = new Scanner(file);
            stringBuilder = new StringBuilder(processorVersion);
            while (informationScanner.hasNextLine()) {
                stringBuilder.append(informationScanner.nextLine()).append("\n");
            }
            processorVersion = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            logger.severe("Ошибка при чтении файла: " + file.getAbsolutePath() + " - " + e.getMessage());
        } finally {
            if (informationScanner != null) {
                informationScanner.close();
            }
        }
    }
}
