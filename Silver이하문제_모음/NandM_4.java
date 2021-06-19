package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class NandM_4 {
    static int N, M;
    static char[] arr;
    static boolean[] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[M*2];
        visited = new boolean[N + 1];
        for (int i = 1; i < M * 2; i += 2)
            arr[i] = ' ';
        recursive(0, 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static void recursive(int depth, int start)
    {
        if (depth == M)
        {
            sb.append(arr).append("\n");
            return;
        }
        for (int i = start; i <= N; i++)
        {
            arr[depth * 2] = (char) (start + '0');
            recursive(depth + 1, start++);
        }
    }
}
