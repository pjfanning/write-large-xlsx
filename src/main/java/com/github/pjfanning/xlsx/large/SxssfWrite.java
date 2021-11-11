package com.github.pjfanning.xlsx.large;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class SxssfWrite {
    private static final Logger LOGGER = LoggerFactory.getLogger(SxssfWrite.class);
    private static final String SXSSF_FILENAME = "large-sxssf.xlsx";

    public static void writeSxssf() {
        LOGGER.info("writing SXSSF {}", SXSSF_FILENAME);
        try (SXSSFWorkbook wb = new SXSSFWorkbook(10)) {
            Sheet sheet = wb.createSheet("large-sxssf");
            for (int r = 0; r < Common.ROW_COUNT; r++) {
                Row row = sheet.createRow(r);
                for (int c = 0; c < Common.COLUMN_COUNT; c++) {
                    Cell cell = row.createCell(c);
                    cell.setCellValue(Common.generateValue(r, c));
                }
            }
            try (FileOutputStream fos = new FileOutputStream(SXSSF_FILENAME)) {
                wb.write(fos);
            }
            LOGGER.info("finished writing SXSSF {}", SXSSF_FILENAME);
            wb.dispose();
        } catch (Throwable t) {
            LOGGER.error("failed to write SXSSF", t);
        }
    }

    public static void init() {
        LOGGER.info("init SXSSF");
        try (SXSSFWorkbook wb = new SXSSFWorkbook(10)) {
            Sheet sheet = wb.createSheet("sxssf");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(Common.generateValue(0, 0));
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                wb.write(bos);
            }
            wb.dispose();
            LOGGER.info("finished init");
        } catch (Throwable t) {
            LOGGER.error("failed to init SXSSF", t);
        }
    }
}
