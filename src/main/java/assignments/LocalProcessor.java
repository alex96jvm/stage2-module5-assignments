package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    private Long period = 10_000_000_000_000L;
    protected String processorVersion;
    private Integer valueOfCheap;
    private Scanner informationScanner;
    private List<String> stringArrayList = new ArrayList<>();
    private StringBuilder stringBuilder;
    private static final Logger logger = Logger.getLogger(LocalProcessor.class.getName());

    public LocalProcessor(String processorName, long period, String processorVersion, int valueOfCheap, List<String> stringArrayList) {
        this.processorName = processorName;
        this.period = period;
        this.processorVersion = processorVersion;
        this.valueOfCheap = valueOfCheap;
        this.stringArrayList = stringArrayList;
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void iterateList(List<String> stringList) {
        if (stringList == null) {
            logger.warning("The iteration list is null.");
            return;
        }
        stringArrayList = stringList;
        stringList.forEach(s -> System.out.println(s != null ? s.hashCode() : "null"));
    }

    @FullNameProcessorGeneratorAnnotation
    public String generateProcessorFullName(List<String> stringList) {
        if (stringList == null) {
            String warningString = "The list for generating the full processor name is null.";
            logger.warning(warningString);
            return warningString;
        }
        stringBuilder = new StringBuilder(processorName);
        stringList.forEach(s -> stringBuilder.append(s != null ? s : "null").append(" "));
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
