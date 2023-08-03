package com.ardadev.config;

public class FormatStringsColumns {
    public static String formatToColumns(String input, int columnWidth) {
        StringBuilder formatted = new StringBuilder();

        int index = 0;
        while (index < input.length()) {
            int endIndex = Math.min(index + columnWidth, input.length());
            String column = input.substring(index, endIndex);
            formatted.append(String.format("%-" + columnWidth + "s", column));

            if (endIndex < input.length()) {
                formatted.append("\n");
            }

            index += columnWidth;
        }

        return formatted.toString();
    }
}
