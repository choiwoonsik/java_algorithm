package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 여행가자_1976{
    static int[] p = new int[201];
    static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++)
            p[i] = i;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1 && i < j) {
                    if (find(i) != find(j))
                        union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int top = 0;
        int city;
        for (int i = 0; i < M; i++) {
            city = Integer.parseInt(st.nextToken());
            if (top == 0)
                top = find(city);
            else {
                if (top != find(city)) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    private static void union(int i, int j) {
        p[p[j]] = p[i];
    }

    private static int find(int city) {
        if (p[city] == city)
            return city;
        return p[city] = find(p[city]);
    }
}
