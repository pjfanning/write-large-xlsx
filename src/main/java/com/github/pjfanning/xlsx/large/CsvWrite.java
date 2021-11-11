package com.github.pjfanning.xlsx.large;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class CsvWrite {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvWrite.class);
    private static final String CSV_FILENAME = "large.csv";

    public static void writeCSV() {
        LOGGER.info("writing CSV {}", CSV_FILENAME);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(new File(CSV_FILENAME), StandardCharsets.UTF_8)) {
            for (int r = 0; r < Common.ROW_COUNT; r++) {
                for (int c = 0; c < Common.COLUMN_COUNT; c++) {
                    printer.print(Common.generateValue(r, c));
                }
                printer.println();
            }
            LOGGER.info("finished writing CSV {}", CSV_FILENAME);
        } catch (Throwable t) {
            LOGGER.error("failed to write CSV", t);
        }
    }

    public static void init() {
        LOGGER.info("init CSV");
        try (
                StringWriter sw = new StringWriter();
                CSVPrinter printer = CSVFormat.DEFAULT.print(sw)
        ) {
            printer.print(Common.generateValue(0, 0));
            printer.println();
            LOGGER.info("finished init CSV");
        } catch (Throwable t) {
            LOGGER.error("failed to init CSV", t);
        }
    }
}
