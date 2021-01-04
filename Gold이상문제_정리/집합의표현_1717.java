package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 집합의표현_1717 {
    static int[] p;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        p = new int[n + 1];
        for (int i = 0; i <= n; i++)
            p[i] = i;

        for (int calc = 0; calc < m; calc++)
        {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int third = Integer.parseInt(st.nextToken());

            // union
            if (first == 0)
            {
                union(second, third);
            }
            // find
            else if (first == 1)
            {
                if (find(second) == find(third))
                    sb.append("YES").append("\n");
                else
                    sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void union(int second, int third) {
        int rootA = find(second);
        int rootB = find(third);

        if (rootA != rootB)
            p[rootB] = rootA;
    }

    private static int find (int n)
    {
        if (p[n] == n) return p[n];
        return p[n] = find(p[n]);
    }
}
