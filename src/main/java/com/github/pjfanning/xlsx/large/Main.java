package com.github.pjfanning.xlsx.large;

public class Main {
    public static void main(String[] args) {
        CsvWrite.init();
        XssfWrite.init();
        SxssfWrite.init();
        DeferredSxssfWrite.init();
        FastExcelWrite.init();
        CsvWrite.writeCSV();
        //XssfWrite is very slow and runs out of memory for large workbooks
        //XssfWrite.writeXssf();
        SxssfWrite.writeSxssf();
        DeferredSxssfWrite.writeSxssf(true);
        DeferredSxssfWrite.writeSxssf(false);
        FastExcelWrite.writeExcel();
    }
}
