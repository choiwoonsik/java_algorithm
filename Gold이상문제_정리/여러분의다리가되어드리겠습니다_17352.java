package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여러분의다리가되어드리겠습니다_17352 {
    static int[] parents;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        // 남은 다리의 개수 = N - 1 - 1;
        for (int i = 0; i < N - 1-  1; i++)
        {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (find(a) != find (b)) {
                union(a, b);
            }
        }

        int A = find(parents[1]);
        int B = 0;
        for (int i = 2; i <= N; i++) {
            if (find(parents[i]) != A) {
                B = find(parents[i]);
                break;
            }
        }
        System.out.println(A + " " + B);
    }

    private static void union(int a, int b) {
        parents[parents[a]] = parents[b];
    }

    private static int find(int node) {
        if (parents[node] == node)
            return node;
        return parents[node] = find(parents[node]);
    }
}
