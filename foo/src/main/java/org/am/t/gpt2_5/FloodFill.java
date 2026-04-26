package org.am.t.gpt2_5;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image.length == 0 || image[0].length == 0) {
            return image;
        }
        int origColor = image[sr][sc];
        if (origColor == color) {
            return image;
        }
        fill(image, sr, sc, origColor, color);
        return image;
    }

    private void fill(int[][] image, int sr, int sc, int origColor, int targetColor) {
        // check boundaries
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != origColor) {
            return;
        }
        image[sr][sc] = targetColor;
        fill(image, sr - 1, sc, origColor, targetColor);
        fill(image, sr + 1, sc, origColor, targetColor);
        fill(image, sr, sc - 1, origColor, targetColor);
        fill(image, sr, sc + 1, origColor, targetColor);
    }
}
