package pccp.배열;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 거리두기확인하기 {
    public static void main(String[] args) {
        String[][] input = new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"},
        };
        int[] solution = new Solution_거리두기확인하기().solution(input);
        System.out.println(Arrays.toString(solution));
    }
}

class Solution_거리두기확인하기 {
    static int N = 5;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Queue<Dot> personQueue = new LinkedList<>();
    static String[][] map;
    static int OK = 1;
    static int NOT = 0;

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            answer[i] = isDivide(places[i]);
        }

        return answer;
    }

    private int isDivide(String[] place) {
        searchPerson(place);
        if (personQueue.isEmpty()) return OK;

        makeMap(place);
        while (!personQueue.isEmpty()) {
            Dot now = personQueue.poll();
            int result = checkPersonDistance(now);
            if (result == NOT) return NOT;
        }

        return OK;
    }

    private void makeMap(String[] place) {
        map = new String[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = String.valueOf(place[y].charAt(x));
            }
        }
    }

    private int checkPersonDistance(Dot now) {
        Queue<Dot> queue = new LinkedList<>();
        queue.add(now);
        boolean[][] visited = new boolean[N][N];
        visited[now.y][now.x] = true;
        int startY = now.y;
        int startX = now.x;
        boolean foundOtherPerson = false;

        while (!queue.isEmpty()) {
            Dot cur = queue.poll();

            if (isDistanced(startY, startX, cur.y, cur.x)) continue;
            if (foundOtherPerson) break;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx].equals("X")) continue;
                if (map[ny][nx].equals("P") && !isDistanced(startY, startX, ny, nx)) {
                    foundOtherPerson = true;
                    break;
                }

                visited[ny][nx] = true;
                queue.add(new Dot(ny, nx));
            }
        }

        if (foundOtherPerson) return NOT;
        else return OK;
    }

    private boolean isDistanced(int criterionY, int criterionX, int cy, int cx) {
        return Math.abs(criterionY - cy) + Math.abs(criterionX - cx) > 2;
    }

    private static void searchPerson(String[] place) {
        personQueue.clear();
        for (int y = 0; y < place.length; y++) {
            for (int x = 0; x < place[y].length(); x++) {
                char chair = place[y].charAt(x);
                if (chair == 'P') personQueue.add(new Dot(y, x));
            }
        }
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
