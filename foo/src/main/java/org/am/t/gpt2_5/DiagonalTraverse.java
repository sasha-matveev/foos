package org.am.t.gpt2_5;

import org.am.t.Utils;

import java.util.Arrays;

public class DiagonalTraverse {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DiagonalTraverse().findDiagonalOrder(Utils.matrix("[[1,2,3],[4,5,6],[7,8,9]]"))));
    }

    public int[] findDiagonalOrder(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0) {
            return new int[0];
        }
        Direction dir = Direction.TR;
        Point pnt = new Point(0, 0);

        int[] res = new int[mat.length * mat[0].length];
        int pos = 0;

        while (pnt != null) {
            res[pos++] = pnt.value(mat);
            var nxt = dir.next(pnt);
            if (!nxt.isWithin(mat)) {
                nxt = dir.change(mat, pnt);
                dir = dir.opposite();
            }
            pnt = nxt;
        }
        return res;
    }

    enum Direction {
        TR, BL;

        Direction opposite() {
            return switch (this) {
                case BL -> TR;
                case TR -> BL;
            };
        }

        Point next(Point p) {
            return switch (this) {
                case TR -> p.topRight();
                case BL -> p.bottomLeft();
            };
        }

        Point change(int[][] m, Point p) {
            return switch (this) {
                case TR -> {
                    Point r = p.right();
                    if (r.isWithin(m)) {
                        yield r;
                    }
                    Point b = p.bottom();
                    if (b.isWithin(m)) {
                        yield b;
                    }
                    yield null;
                }
                case BL -> {
                    Point b = p.bottom();
                    if (b.isWithin(m)) {
                        yield b;
                    }
                    Point r = p.right();
                    if (r.isWithin(m)) {
                        yield r;
                    }
                    yield null;
                }
            };
        }
    }

    record Point(int r, int c) {
        boolean isWithin(int[][] map) {
            return r >= 0
                    && r < map.length
                    && c >= 0
                    && c < map[0].length;
        }

        int value(int[][] mat) {
            return mat[r][c];
        }

        Point topRight() {
            return top().right();
        }

        Point bottomLeft() {
            return bottom().left();
        }

        Point left () {
            return new Point(r, c - 1);
        }

        Point right() {
            return new Point(r, c + 1);
        }

        Point top() {
            return new Point(r - 1, c);
        }

        Point bottom() {
            return new Point(r + 1, c);
        }
    }
}
