import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class wdding_5567 {
    static boolean[][] friend_map;
    static Queue<Integer> friend_que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        friend_map = new boolean[N+1][N+1];

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f_one = Integer.parseInt(st.nextToken());
            int f_two = Integer.parseInt(st.nextToken());
            friend_map[f_one][f_two] = true;
            friend_map[f_two][f_one] = true;
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++)
            if (friend_map[1][i] || friend_map[i][1])
                que.add(i);

        while (!que.isEmpty())
        {
            int friend = que.poll();
            if (!friend_que.contains(friend))
                friend_que.add(friend);
            DFS(friend, N);
        }
        System.out.println(friend_que.size());

    }

    private static void DFS(Integer poll, int N) {
        for (int i = 1; i <= N; i++)
        {
            if ((friend_map[poll][i] || friend_map[i][poll]) && !friend_que.contains(i) && i != 1) {
                friend_que.add(i);
            }
        }
    }
}
