package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class almost_short_distance_5719 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> list;
    static StringTokenizer st;
    static int N, M, start, dest, W;
    static int[][] board;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException
    {
        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0)
                break;
            board = new int[N][N];
            visited = new boolean[N][N];
            list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            //가중치 간선을 모두 기록하고 기록된 가중치를 바탕으로 start->dest로 가는 모든 길을 체크
            //체크가 안된 부분은 모두 0
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                board[s][d] = w;
            }
            //dfs를 통해 s->d의 모든 길을 확인하면서 가중치를 더한 것들을 기록
            DFS(start);
            Collections.sort(list);
            if(list.size() == 1 || list.size() == 0)
                System.out.println(-1);
            else
                System.out.println(list.get(1));
            System.out.println("\n=======\n");
        }
    }
    private static void DFS(int toGo)
    {
        if (toGo == dest)
        {
            System.out.println("저장되는 W의 값들...>"+W);
            if (!list.contains(W))
                list.add(W);
            return;
        }
        for (int i = 0; i < N; i++) {
            //가중치가 있고 && 아직 이용하지 않은 길일 경우
            if (board[toGo][i] > 0 && !visited[toGo][i]) {
                visited[toGo][i] = true;
                W += board[toGo][i];
                System.out.println("toGo >> "+toGo+"...Dest >> "+i);
                System.out.println(board[toGo][i] +" > +"+W);
                DFS(i);
                W -= board[toGo][i];
            }
        }
    }
}
