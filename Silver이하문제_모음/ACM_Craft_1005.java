package Silver이하문제_모음;/*
T번 반복

건물의 개수 N , 건설 순서규칙의 개수 K
각 건물당 건설에 걸리는 시간 D
K개의 건설 순서 x - > y
목표 건물 W
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ACM_Craft_1005 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0)
            Build();
    }
    private static void Build() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<BT> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pq.add(new BT(i, Integer.parseInt(st.nextToken())));
        }
        List<Integer>[] list = new List[N+1];
        for (int i = 1; i < list.length; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list[from].add(end);
        }
        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int Time = 0;
        while (!pq.isEmpty())
        {
            BT now = pq.poll();
            int maxT = Integer.MIN_VALUE;
            Time += now.time;
            for (int next : list[now.n]){
                BT nextT = pq.poll();
                maxT = Math.max(maxT, nextT.time);
                //pq.add(new BT(2, ));
            }
            Time += maxT;
        }
        System.out.println(Time);
    }
}

class BT implements Comparable<BT>
{
    int n;
    int time;
    BT (int n, int time)
    {
        this.n = n;
        this.time = time;
    }

    @Override
    public int compareTo(BT o) {
        return this.n > o.n ? 1 : -1;
    }
}
