package Silver이하문제_모음;

import java.util.*;
import java.io.*;

public class castle_defence_17135 {
    static int[][] board;
    static int row, col, D, Killed;
    static ArrayList<Zom> zomList = new ArrayList<>();
    static ArrayList<Acher> acherList = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[row + 1][col];
        for (int y = 0; y < row; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < col; x++)
                board[y][x] = Integer.parseInt(st.nextToken());
        }

        // 모든 궁수의 경우
        all_acherBatch();

        int maxKill = -1;
        // 모든 궁수 위치에 대해서 좀비 사냥
        for (Acher acher : acherList) {
            initZom();
            Killed = 0;
            maxKill = Math.max(maxKill, catch_zom(acher));
        }
        System.out.println(maxKill);
    }

    private static int catch_zom(Acher acher) {
        while (!zomList.isEmpty())
        {
            // 활쏘기
            for (int eachA = 0; eachA < 3; eachA++) {
                int minDis = 100;
                Zom target = null;
                for (Zom zom : zomList) {
                    int dis = (Math.abs(zom.x - acher.x[eachA]) + Math.abs(zom.y - row));
                    if (dis <= D && dis < minDis) {
                        minDis = dis;
                        target = zom;
                    }
                }
                if (target != null)
                    target.die = true;
            }
            ArrayList<Zom> dieZom = new ArrayList<>();
            // 죽은 좀비 제거
            for (Zom zom : zomList)
                if (zom.die)
                    dieZom.add(zom);
            for (Zom die : dieZom) {
                zomList.remove(die);
                Killed++;
            }
            // 좀비 이동
            dieZom.clear();
            for (Zom zom : zomList) {
                if (zom.y < row) {
                    zom.y++;
                    if (zom.y == row)
                        dieZom.add(zom);
                }
            }
            for (Zom die : dieZom)
                zomList.remove(die);
        }
        return Killed;
    }

    private static void all_acherBatch() {
        boolean[] vst = new boolean[col];
        comb(vst, 0, 3);
    }

    private static void comb(boolean[] vst, int start, int r) {
        if (r == 0) {
            int[] x = new int[3];
            int idx = 0;
            for (int i = 0; i < vst.length; i++) {
                if (vst[i])
                    x[idx++] = i;
            }
            acherList.add(new Acher(x));
        } else {
            for (int i = start; i < col; i++) {
                vst[i] = true;
                comb(vst, i + 1, r - 1);
                vst[i] = false;
            }
        }
    }

    private static void initZom() {
        zomList.clear();
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                if (board[y][x] == 1)
                    zomList.add(new Zom(y, x, false));
            }
        }
        zomList.sort(Comparator.comparingInt(o -> o.x));
    }

    private static class Zom
    {
        int y;
        int x;
        boolean die;
        public Zom (int y, int x, boolean die)
        {
            this.y = y;
            this.x = x;
            this.die = die;
        }
    }
    private static class Acher
    {
        int[] x;

        public Acher(int[] x) {
            this.x = x;
        }
    }
}
