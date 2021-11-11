package com.github.pjfanning.xlsx.large;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.DeferredSXSSFSheet;
import org.apache.poi.xssf.streaming.DeferredSXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class DeferredSxssfWrite {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeferredSxssfWrite.class);
    private static final String DEFERRED_SXSSF_FILENAME = "deferred-sxssf.xlsx";

    public static void writeSxssf(boolean useTempFile) {
        LOGGER.info("writing Deferred SXSSF useTempFile={}, {}", useTempFile, DEFERRED_SXSSF_FILENAME);
        DeferredSXSSFWorkbook wb = new DeferredSXSSFWorkbook(10);
        try {
            DeferredSXSSFSheet sheet = wb.createSheet("large-sxssf");
            sheet.setRowGenerator((ssxSheet) -> {
                for (int r = 0; r < Common.ROW_COUNT; r++) {
                    Row row = ssxSheet.createRow(r);
                    for (int c = 0; c < Common.COLUMN_COUNT; c++) {
                        Cell cell = row.createCell(c);
                        cell.setCellValue(Common.generateValue(r, c));
                    }
                }
            });
            try (FileOutputStream fos = new FileOutputStream(DEFERRED_SXSSF_FILENAME)) {
                if (useTempFile) {
                    wb.write(fos);
                } else {
                    wb.writeAvoidingTempFiles(fos);
                }
            }
            LOGGER.info("finished writing Deferred SXSSF {}", DEFERRED_SXSSF_FILENAME);
        } catch (Throwable t) {
            LOGGER.error("failed to write Deferred SXSSF", t);
        } finally {
            IOUtils.closeQuietly(wb);
            wb.dispose();
        }
    }

    public static void init() {
        LOGGER.info("init Deferred SXSSF");
        DeferredSXSSFWorkbook wb = new DeferredSXSSFWorkbook(10);
        try {
            DeferredSXSSFSheet sheet = wb.createSheet("sxssf");
            sheet.setRowGenerator((ssxSheet) -> {
                Row row = ssxSheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue(Common.generateValue(0, 0));
            });
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                wb.write(bos);
            }
            wb.dispose();
            LOGGER.info("finished init");
        } catch (Throwable t) {
            LOGGER.error("failed to init SXSSF", t);
        } finally {
            IOUtils.closeQuietly(wb);
            wb.dispose();
        }
    }
}
