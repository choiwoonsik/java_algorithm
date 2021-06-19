package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ProblemBook_1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();

        int[] count_connect = new int[N+1];
        Arrays.fill(count_connect, 0);

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());
            //먼저해야하는 거 뒤에 나중에 할것들을 쌓고
            //쌓인 나중에 해야하는 것들의 개수를 센다
            list[pre].add(post);
            count_connect[post]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++)
            if (count_connect[i] == 0)
                pq.add(i);

        StringBuilder str = new StringBuilder();
        while (!pq.isEmpty()) {
            int now_problem = pq.poll();
            for (int next_problem : list[now_problem]) {
                count_connect[next_problem]--;
                if (count_connect[next_problem] == 0)
                    pq.add(next_problem);
            }
            str.append(now_problem).append(" ");
        }
        System.out.print(str);
    }
}
