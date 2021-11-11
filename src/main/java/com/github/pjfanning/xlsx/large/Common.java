package com.github.pjfanning.xlsx.large;

public class Common {
    public static final int ROW_COUNT = 10000;
    public static final int COLUMN_COUNT = 1000;
    private static final String PADDED_NUM_FORMAT = "%06d";

    public static String generateValue(int row, int column) {
        return "Row" + String.format(PADDED_NUM_FORMAT, row) + " Column" + String.format(PADDED_NUM_FORMAT, column);
    }
}
