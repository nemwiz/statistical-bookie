package csv;

import com.univocity.parsers.common.RowProcessorErrorHandler;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import csv.model.CsvMatch;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class CsvFileParser {

    private BeanListProcessor<CsvMatch> rowProcessor;
    private CsvParserSettings parserSettings;
    private CsvParser csvParser;
    private Random random;

    private static int MAX = 26000;
    private static int MIN = 21000;

    public CsvFileParser() {
        this.rowProcessor = new BeanListProcessor<>(CsvMatch.class);
        this.parserSettings = new CsvParserSettings();
        this.parserSettings.setRowProcessor(this.rowProcessor);
        this.parserSettings.setHeaderExtractionEnabled(true);
        this.parserSettings.setLineSeparatorDetectionEnabled(true);

        this.parserSettings.setProcessorErrorHandler((RowProcessorErrorHandler) (error, inputRow, context) -> {
            System.out.println("Error processing row: " + Arrays.toString(inputRow));
            System.out.println("Error details: column '" + error.getColumnName() + "' (index " + error.getColumnIndex() + ") has value '" + inputRow[error.getColumnIndex()] + "'");
        });
        this.random = new Random();

        this.csvParser = new CsvParser(this.parserSettings);
    }

    public List<CsvMatch> parseAndMapCsvFileFromUrl(String url) {

        out.println("Parsing from url " + url);

        try {
            int sleepTime = this.random.nextInt((MAX - MIN) + 3000) + MIN;
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            InputStream input = new URL(url).openStream();
            this.csvParser.parse(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowProcessor.getBeans();
    }

}
