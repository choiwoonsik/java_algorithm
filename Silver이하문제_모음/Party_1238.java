package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
N = 1 ~ 1000
N개의 숫자로 구분된 마을
N명의 학생

마을 사이에는 M개의 단방향 도로 , i번째 길을 지날때 t(i)의 시간을 소비한다
파티하는 마을에 갔다가 오는데 가장 많은 시간이 걸리는 학생은?
N, M , X

어느집이 x를 갔다 올때 가장 오래걸릴까?
 */
public class Party_1238 {
    static Node_1238[] GoNodes;
    static List<Node_1238>[] NodeList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        NodeList = new ArrayList[N+1];
        GoNodes = new Node_1238[N+1];
        visited = new boolean[N+1];

        for (int i = 1; i <= N; i++)
            NodeList[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            NodeList[S].add(new Node_1238(E,T));
        }

        int max = Integer.MIN_VALUE;
        int result[] = new int[N+1];
        for (int i = 1; i <= N; i++)
        {
            int Go = GoTime(i, X);
            int Back = GoTime(X, i);
            result[i] = Go + Back;
            max = Math.max(result[i], max);
        }
        System.out.println(max);
    }
    private static int GoTime(int start, int end)
    {
        PriorityQueue<Node_1238> pq = new PriorityQueue<>();
        for (int i = 0; i <GoNodes.length; i++)
            GoNodes[i] = new Node_1238(i, Integer.MAX_VALUE);

        GoNodes[start].time = 0;
        pq.add(GoNodes[start]);

        while (!pq.isEmpty())
        {
            Node_1238 now = pq.poll();
            for (Node_1238 next : NodeList[now.pos]) {
                if (GoNodes[next.pos].time > GoNodes[now.pos].time + next.time) {
                    GoNodes[next.pos].time = GoNodes[now.pos].time + next.time;
                    pq.add(GoNodes[next.pos]);
                }
            }
        }
        return GoNodes[end].time;
    }
}

class Node_1238 implements Comparable<Node_1238> {
    int pos;
    int time;
    Node_1238 (int pos, int time)
    {
        this.pos = pos;
        this.time = time;
    }
    @Override
    public int compareTo(Node_1238 o) {
        return o.time >= this.time ? -1 : 1;
    }
}
