package scrapper.csv;

import com.univocity.parsers.common.RowProcessorErrorHandler;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import scrapper.csv.model.CsvMatch;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class CsvFileParser {

    private BeanListProcessor<CsvMatch> rowProcessor;
    private CsvParserSettings parserSettings;
    private CsvParser csvParser;

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

        this.csvParser = new CsvParser(this.parserSettings);
    }

    public List<CsvMatch> parseAndMapCsvFileFromUrl(String url) {

        out.println("Parsing from url " + url);

        try {
            Thread.sleep(21000);
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
