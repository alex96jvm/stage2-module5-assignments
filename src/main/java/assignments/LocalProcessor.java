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
    private List<String> stringArrayList;
    private StringBuilder stringBuilder;
    private static final Logger logger = Logger.getLogger(LocalProcessor.class.getName());

    public LocalProcessor(String processorName, long period, String processorVersion, int valueOfChip, List<String> stringArrayList) {
        this.processorName = processorName;
        this.period = period;
        this.processorVersion = processorVersion;
        this.valueOfChip = valueOfChip;
        this.stringArrayList = stringArrayList;
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void iterateList(List<String> list) {
        if (list == null) {
            String warningString = "The iteration list is null.";
            logger.warning(warningString);
            System.out.println(warningString);
            return;
        }
        list.forEach(s -> System.out.println(s != null ? s.hashCode() : "null"));
    }

    @FullNameProcessorGeneratorAnnotation
    public String generateProcessorFullName(List<String> list) {
        if (list == null) {
            String warningString = "The list for generating the full processor name is null.";
            logger.warning(warningString);
            return warningString;
        }
        stringBuilder = new StringBuilder(processorName);
        list.forEach(s -> stringBuilder.append(s != null ? s : "null").append(" "));
        processorName = stringBuilder.toString().trim();
        return processorName;
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
            logger.severe("Error reading file: " + file.getAbsolutePath() + " - " + e.getMessage());
        } finally {
            if (informationScanner != null) {
                informationScanner.close();
            }
        }
    }
}
