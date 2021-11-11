package com.github.pjfanning.xlsx.large;

public class Main {
    public static void main(String[] args) {
        CsvWrite.init();
        SxssfWrite.init();
        DeferredSxssfWrite.init();
        FastExcelWrite.init();
        CsvWrite.writeCSV();
        SxssfWrite.writeSxssf();
        DeferredSxssfWrite.writeSxssf(true);
        DeferredSxssfWrite.writeSxssf(false);
        FastExcelWrite.writeExcel();
    }
}
