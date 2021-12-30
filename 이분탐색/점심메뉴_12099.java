package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 점심메뉴_12099 {
    static int N;
    static int Q;
    static Menu[] menus;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        menus = new Menu[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int spicy = Integer.parseInt(st.nextToken());
            int sweet = Integer.parseInt(st.nextToken());
            menus[i] = new Menu(spicy, sweet);
        }

        Arrays.sort(menus, Comparator.comparing(m -> m.spicy));
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            findMenuCount(u, v, x, y);
        }
        System.out.print(answer);
    }

    private static void findMenuCount(int u, int v, int x, int y) {
        int[] bound = spicyMenuFind(u, v);
        int count = 0;

        for (int i = bound[0]; i < bound[1]; i++) {
            if (menus[i].sweet <= y && menus[i].sweet >= x) {
                count++;
            }
        }

        answer.append(count).append("\n");
    }

    private static int[] spicyMenuFind(int u, int v) {
        int start, end;
        int left = 0;
        int right = N;
        int mid;

        // lower bound
        while (left < right) {
            mid = (left + right) / 2;

            if (menus[mid].spicy < u)
                left = mid + 1;
            else
                right = mid;
        }
        start = right;

        left = start;
        right = N;
        // upper bound
        while (left < right) {
            mid = (left + right) / 2;

            if (menus[mid].spicy <= v)
                left = mid + 1;
            else
                right = mid;
        }
        end = right;

        return new int[]{start, end};
    }

    private static class Menu {
        int spicy;
        int sweet;

        public Menu(int spicy, int sweet) {
            this.spicy = spicy;
            this.sweet = sweet;
        }
    }
}
