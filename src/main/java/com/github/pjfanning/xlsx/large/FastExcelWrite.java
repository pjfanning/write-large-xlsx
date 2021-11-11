package com.github.pjfanning.xlsx.large;

import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class FastExcelWrite {
    private static final Logger LOGGER = LoggerFactory.getLogger(FastExcelWrite.class);
    private static final String XLSX_FILENAME = "large-fast-excel.xlsx";

    public static void writeExcel() {
        LOGGER.info("writing using fast-excel {}", XLSX_FILENAME);
        try (FileOutputStream fos = new FileOutputStream(XLSX_FILENAME)) {
            Workbook wb = new Workbook(fos, "MyApplication", "1.0");
            Worksheet ws = wb.newWorksheet("fast-excel");
            for (int r = 0; r < Common.ROW_COUNT; r++) {
                for (int c = 0; c < Common.COLUMN_COUNT; c++) {
                    ws.value(r, c, Common.generateValue(r, c));
                }
                ws.flush();
            }
            wb.finish();
            LOGGER.info("finished writing fast-excel {}", XLSX_FILENAME);
        } catch (Throwable t) {
            LOGGER.error("failed to write fast-excel", t);
        }
    }

    public static void init() {
        LOGGER.info("init fast-excel");
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Workbook wb = new Workbook(bos, "MyApplication", "1.0");
            Worksheet ws = wb.newWorksheet("fast-excel");
            ws.value(0, 0, Common.generateValue(0, 0));
            wb.finish();
        } catch (Throwable t) {
            LOGGER.error("failed to init fast-excel", t);
        }
    }
}
