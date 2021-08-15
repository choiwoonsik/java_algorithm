package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 문자열게임2_20437 {
    static int T;
    static ArrayList<Integer>[] alhpa = new ArrayList[26];
    static String s;
    static int n, Min, Max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            init(br);
            search();
            if (Min == 10001) answer.append(-1).append("\n");
            else {
                answer.append(Min).append(" ").append(Max).append("\n");
            }
        }
        System.out.print(answer);
    }

    private static void search() {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            alhpa[c - 'a'].add(i);
        }

        for (int i = 0; i < 26; i++) {
            if (alhpa[i].size() >= n) {
                twoPointer(i);
            }
        }
    }

    private static void twoPointer(int i) {
        ArrayList<Integer> list = alhpa[i];
        int left = 0;
        int right = 0;

        while (right < list.size()) {
            if (right - left + 1 == n) {
                Min = Math.min(Min, list.get(right) - list.get(left) + 1);
                Max = Math.max(Max, list.get(right) - list.get(left) + 1);
            }
            right++;
            while (right - left + 1 > n) left++;
        }
    }

    private static void init(BufferedReader br) throws IOException {
        for (int i = 0; i < 26; i++) {
            alhpa[i] = new ArrayList<>();
        }
        s = br.readLine();
        n = Integer.parseInt(br.readLine());
        Min = 10001;
        Max = 0;
    }
}
