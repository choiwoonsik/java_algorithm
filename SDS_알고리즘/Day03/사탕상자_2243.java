package SDS_알고리즘.Day03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사탕상자_2243 {
    static int N;
    static int S = 1;
    static int MAX = 1000000;
    static int[] candyTree;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        while (S < MAX) {
            S *= 2;
        }
        candyTree = new int[S * 2];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            // 사탕을 꺼낸다.
            if (type == 1) {
                int candyNum = Integer.parseInt(st.nextToken());
                int candyIndex = calculate(1, S, 1, candyNum);
                update(1, S, 1, candyIndex, -1);
                answer.append(candyIndex).append("\n");
            }
            // 사탕을 넣는다.
            else if (type == 2) {
                int candyIndex = Integer.parseInt(st.nextToken());
                int candyDiff = Integer.parseInt(st.nextToken());
                update(1, S, 1, candyIndex, candyDiff);
            }
        }
        System.out.print(answer);
    }

    private static void update(int left, int right, int node, int candyIndex, int diff) {
        if (candyIndex >= left && candyIndex <= right) {
            candyTree[node] += diff;

            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, candyIndex, diff);
                update(mid + 1, right, node * 2 + 1, candyIndex, diff);
            }
        }
    }

    private static int calculate(int left, int right, int node, int candyNum) {
        if (left == right) return left;

        int mid = (left + right) / 2;

        if (candyTree[node * 2] < candyNum) {
            return calculate(mid + 1, right, node * 2 + 1, candyNum - candyTree[node * 2]);
        } else {
            return calculate(left, mid, node * 2, candyNum);
        }
    }
}
