package SDS_알고리즘.Day06;

import java.io.*;
import java.util.*;

public class 게임개발_1516 {
    static int N;
    static int[] inDegree;
    static int[] buildTime;
    static int[] beforeTime;
    static ArrayList<Integer>[] dagList;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        buildTime = new int[N + 1];
        inDegree = new int[N + 1];
        beforeTime = new int[N + 1];
        dagList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            dagList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            buildTime[i] = time;

            int indegreeCount = 0;
            while(st.hasMoreTokens()) {
                int preJob = Integer.parseInt(st.nextToken());
                if (preJob == -1) break;
                dagList[preJob].add(i);
                indegreeCount++;
            }
            inDegree[i] = indegreeCount;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                beforeTime[i] = buildTime[i];
            }
        }

        while (!queue.isEmpty()) {
            int nowJob = queue.poll();

            for (Integer nextJob : dagList[nowJob]) {
                inDegree[nextJob]--;

                if (beforeTime[nextJob] < beforeTime[nowJob] + buildTime[nextJob]) {
                    beforeTime[nextJob] = beforeTime[nowJob] + buildTime[nextJob];
                }

                if (inDegree[nextJob] == 0) {
                    queue.add(nextJob);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            answer.append(beforeTime[i]).append("\n");
        }
        System.out.print(answer);
    }
}
