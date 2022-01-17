package SDS_알고리즘.Day01.후보추천하기_1713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] recos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        recos = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            recos[i] = Integer.parseInt(st.nextToken());
        }

        start();
    }

    private static void start() {
        ArrayList<Dot> list = new ArrayList<>();
        int time = 0;

        for (int idx : recos) {
            time++;

            if (list.size() < N) {
                boolean isHave = false;
                for (Dot dot : list) {
                    if (dot.idx == idx) {
                        list.remove(dot);
                        list.add(new Dot(idx, dot.count + 1, dot.time));
                        isHave = true;
                        break;
                    }
                }
                if (!isHave) {
                    list.add(new Dot(idx, 1, time));
                }
            } else {
                boolean isHave = false;
                for (Dot dot : list) {
                    if (dot.idx == idx) {
                        list.remove(dot);
                        list.add(new Dot(idx, dot.count + 1, dot.time));
                        isHave = true;
                        break;
                    }
                }
                if (!isHave) {
                    list.sort((o1, o2) -> {
                        int compare = Integer.compare(o1.count, o2.count);
                        if (compare == 0) return Integer.compare(o1.time, o2.time);
                        else return compare;
                    });
                    list.remove(0);
                    list.add(new Dot(idx, 1, time));
                }
            }
        }

        list.sort(Comparator.comparingInt(d -> d.idx));
        for (Dot dot : list) {
            System.out.print(dot.idx+" ");
        }
    }

    private static class Dot{
        int idx;
        int count;
        int time;

        public Dot(int idx, int count, int time) {
            this.idx = idx;
            this.count = count;
            this.time = time;
        }
    }
}
