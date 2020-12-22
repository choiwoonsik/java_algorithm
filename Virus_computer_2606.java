import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.*;

public class Virus_computer_2606 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int pariCnt = Integer.parseInt(br.readLine());

        boolean[][] map = new boolean[N + 1][N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i <pariCnt; i++)
        {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start][end] = true;
            map[end][start] = true;
        }
        int attackedCnt = -1;
        Queue<Integer> virus = new LinkedList<>();
        virus.add(1);
        while (!virus.isEmpty())
        {
            int com = virus.poll();
            if (!visited[com]) {
                visited[com] = true;
                attackedCnt++;
                for (int i = 1; i <= N; i++)
                {
                    if (!visited[i] && (map[com][i] || map[i][com]))
                        virus.add(i);
                }
            }
        }
        System.out.println(attackedCnt);
    }
}
