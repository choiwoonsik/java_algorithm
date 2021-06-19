package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gearWheel_14891 {
    static int[][] wheel = new int[5][9];
    static int[][] copyArr = new int[5][9];
    static boolean[] checked = new boolean[5];
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i <= 4; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 1; j <= 8; j++)
                wheel[i][j] = Integer.parseInt(String.valueOf(s.charAt(j-1)));
        }
        N = Integer.parseInt(br.readLine());
        int ret = 0;
        for (int i = 0; i < N; i++)
        {
            checked = new boolean[5];
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            turn_wheel(w, dir);
            for (int l = 1; l < 5; l++) {
                if (!checked[l])
                    copyArr[l] = wheel[l].clone();
            }
            copy();
        }
        if (wheel[1][1] == 1)
            ret += 1;
        if (wheel[2][1] == 1)
            ret += 2;
        if (wheel[3][1] == 1)
            ret += 4;
        if (wheel[4][1] == 1)
            ret += 8;
        System.out.println(ret);
    }
    private static boolean[] isDifferent() {
        boolean[] isDiff = new boolean[3];
        if (wheel[1][3] != wheel[2][7]) isDiff[0] = true;
        if (wheel[2][3] != wheel[3][7]) isDiff[1] = true;
        if (wheel[3][3] != wheel[4][7]) isDiff[2] = true;

        return isDiff;
    }
    private static void turn_wheel(int w, int dir) {
        boolean[] isDiff = isDifferent();

        if (w == 1) {
            move(1, dir);
            if (isDiff[0]) {
                move(2, -dir);
                if (isDiff[1]){
                    move(3, dir);
                    if (isDiff[2])
                        move(4, -dir);
                }
            }
        }

        else if (w == 2){
            move(2, dir);
            if (isDiff[0])
                move(1, -dir);
            if (isDiff[1]) {
                move(3, -dir);
                if (isDiff[2])
                    move(4, dir);
            }
        }

        else if (w == 3) {
            move(3, dir);
            if (isDiff[2])
                move(4, -dir);
            if (isDiff[1]) {
                move(2, -dir);
                if (isDiff[0])
                    move(1, dir);
            }
        }

        else if (w == 4) {
            move(4, dir);
            if (isDiff[2]){
                move(3, -dir);
                if (isDiff[1]) {
                    move(2, dir);
                    if (isDiff[0])
                        move(1, -dir);
                }
            }
        }
    }

    private static void move(int w, int dir) {
        checked[w] = true;
        if (dir == 1) {
            System.arraycopy(wheel[w], 1, copyArr[w], 2, 7);
            copyArr[w][1] = wheel[w][8];
        }
        else if (dir == -1) {
            System.arraycopy(wheel[w], 2, copyArr[w], 1, 7);
            copyArr[w][8] = wheel[w][1];
        }
    }

    private static void copy()
    {
        for (int i = 1; i < 5; i++) {
            System.arraycopy(copyArr[i], 0, wheel[i], 0, 9);
        }
    }
}
