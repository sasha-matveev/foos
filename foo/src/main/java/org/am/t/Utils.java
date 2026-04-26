package org.am.t;

public class Utils {
    /**
     * @param matrix [[1,2,3],[4,5,6],[7,8,9]]
     */
    public static int[][] matrix(String matrix) {
        if (matrix == null || matrix.length() < 4) {
            return new int[0][0];
        }

        // убираем внешние [[ и ]]
        String content = matrix.substring(2, matrix.length() - 2);

        // делим на строки
        String[] rows = content.split("\\],\\[");

        int[][] result = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] nums = rows[i].split(",");
            result[i] = new int[nums.length];

            for (int j = 0; j < nums.length; j++) {
                result[i][j] = Integer.parseInt(nums[j].trim());
            }
        }

        return result;
    }
}
