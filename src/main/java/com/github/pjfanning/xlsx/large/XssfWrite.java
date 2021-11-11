package com.github.pjfanning.xlsx.large;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class XssfWrite {
    private static final Logger LOGGER = LoggerFactory.getLogger(XssfWrite.class);
    private static final String XSSF_FILENAME = "large-xssf.xlsx";

    public static void writeXssf() {
        LOGGER.info("writing XSSF {}", XSSF_FILENAME);
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("large-xssf");
            for (int r = 0; r < Common.ROW_COUNT; r++) {
                Row row = sheet.createRow(r);
                for (int c = 0; c < Common.COLUMN_COUNT; c++) {
                    Cell cell = row.createCell(c);
                    cell.setCellValue(Common.generateValue(r, c));
                }
            }
            try (FileOutputStream fos = new FileOutputStream(XSSF_FILENAME)) {
                wb.write(fos);
            }
            LOGGER.info("finished writing XSSF {}", XSSF_FILENAME);
        } catch (Throwable t) {
            LOGGER.error("failed to write XSSF", t);
        }
    }

    public static void init() {
        LOGGER.info("init XSSF");
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("xssf");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(Common.generateValue(0, 0));
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                wb.write(bos);
            }
            LOGGER.info("finished init");
        } catch (Throwable t) {
            LOGGER.error("failed to init XSSF", t);
        }
    }
}
