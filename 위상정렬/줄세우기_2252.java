package 위상정렬;

import java.io.*;
import java.util.*;

public class 줄세우기_2252 {
    static ArrayList<Integer>[] degree;
    static int[] degreeCont;
    static int N;
    static int M;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new ArrayList[N + 1];
        degreeCont = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            degree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            degree[u].add(v);
            degreeCont[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degreeCont[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int stu = queue.poll();
            answer.append(stu).append(" ");

            for (int behindStu : degree[stu]) {
                degreeCont[behindStu]--;
                if (degreeCont[behindStu] == 0) {
                    queue.add(behindStu);
                }
            }
        }

        System.out.println(answer);
    }
}
