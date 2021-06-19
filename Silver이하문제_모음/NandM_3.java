package Silver이하문제_모음;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class NandM_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static char[] arr;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new char[M * 2];
        for (int i = 1; i < M * 2; i+=2)
            arr[i] = ' ';
        recursive(0, N, M);
        bw.write(sb.toString());
        bw.flush();
    }
    private static void recursive(int depth, int N, int M) {
        if (depth == M)
        {
            sb.append(arr).append("\n");
            return;
        }
        for (int i = 1; i <= N; i++)
        {
            arr[depth * 2] = (char)(i + '0');
            recursive(depth + 1, N, M);
        }
    }
}
