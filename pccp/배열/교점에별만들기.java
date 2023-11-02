package pccp.배열;

import java.util.*;
import java.util.stream.IntStream;

public class 교점에별만들기 {
    public static void main(String[] args) {
        String[] solution = new Solution_교점에별만들기().solution(new int[][]
                {
                        {2, -1, 4},
                        {2, -1, 4},
                        {0, -1, 1},
                        {5, -8, -12},
                        {5, 8, 12},
                }
        );
        System.out.println(Arrays.toString(solution));
    }
}

class Solution_교점에별만들기{
    static List<Dot> dots = new ArrayList<>();
    static String[][] map;

    public String[] solution(int[][] line) {
        setCrossDot(line);

        long maxY = dots.stream().mapToLong(d -> d.y).max().orElse(-1);
        long minY = dots.stream().mapToLong(d -> d.y).min().orElse(-1);
        long maxX = dots.stream().mapToLong(d -> d.x).max().orElse(-1);
        long minX = dots.stream().mapToLong(d -> d.x).min().orElse(-1);

        int height = (int) (maxY - minY + 1);
        int width = (int) (maxX - minX + 1);
        long plusY = -minY;
        long plusX = -minX;

        fillDotToMap(height, width, plusY, plusX);

        String[] answer;
        answer = Arrays.stream(map)
                .map(it -> String.join("", it))
                .toArray(String[]::new);
        return IntStream
                .range(0, answer.length)
                .mapToObj(i -> answer[answer.length - i - 1])
                .toArray(String[]::new);
    }

    private void setCrossDot(int[][] line) {
        int size = line.length;

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                int[] lineA = line[i];
                int[] lineB = line[j];

                Dot dot = getDot(lineA, lineB);
                if (dot != null) dots.add(dot);
            }
        }
    }

    private static void fillDotToMap(int height, int width, long plusY, long plusX) {
        map = new String[height][width];
        Arrays.stream(map).forEach(it -> Arrays.fill(it, "."));

        for (Dot dot : dots) {
            int y = (int) (dot.y + plusY);
            int x= (int) (dot.x + plusX);
            map[y][x] = "*";
        }
    }

    private Dot getDot(int[] lineA, int[] lineB) {
        double ax = lineA[0];
        double by = lineA[1];
        double e = lineA[2];

        double cx = lineB[0];
        double dy = lineB[1];
        double f = lineB[2];

        if (ax * dy - by * cx == 0) return null;

        double y = (e * cx - ax * f) / (ax * dy - by * cx);
        double x = (by * f - e * dy) / (ax * dy - by * cx);

        if (y % 1 != 0 || x % 1 != 0) return null;

        return new Dot((long) y, (long) x);
    }

    private static class Dot {
        long y, x;

        public Dot(long y, long x) {
            this.y = y;
            this.x = x;
        }
    }
}
