package org.am.t;

public class FiveInRow {
    public boolean fiveInRow(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                char c = array[i][j];
                if (charLookup(c, array, i, j) + 1 >= 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private int charLookup(char c, char[][] array, int row, int col) {
        return Math.max(
                Math.max(rowLookup(c, array, row, col),
                        colLookup(c, array, row, col)),
                Math.max(tlBrDiagLookup(c, array, row, col),
                        trBlDiagLookup(c, array, row, col))
        );
    }

    private int rowLookup(char c, char[][] array, int row, int col) {
        return lookup(c, array, row, col, 0, -1)
                + lookup(c, array, row, col, 0, +1);
    }

    private int colLookup(char c, char[][] array, int row, int col) {
        return lookup(c, array, row, col, -1, 0)
                + lookup(c, array, row, col, 1, 0);
    }

    private int tlBrDiagLookup(char c, char[][] array, int row, int col) {
        return lookup(c, array, row, col, -1, -1)
                + lookup(c, array, row, col, +1, +1);
    }

    private int trBlDiagLookup(char c, char[][] array, int row, int col) {
        return lookup(c, array, row, col, -1, +1)
                + lookup(c, array, row, col, +1, -1);
    }

    private int lookup(char c, char[][] array, int row, int col, int rowInc, int colInc) {
        int maxLookup = 5;
        int found = 0;
        int cRow = row;
        int cCol = col;
        for (int i = 0; i < maxLookup; i++) {
            cRow += rowInc;
            cCol += colInc;
            if (cRow < 0 || cRow >= array.length || cCol < 0 || cCol >= array[0].length || array[cRow][cCol] != c) {
                return found;
            }
            found++;
        }
        return found;
    }
}
