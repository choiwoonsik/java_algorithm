package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_2042 {
    static int N, M, K;
    static int last, height;
    static long[] tree;
    static long[] A;
    public static void main(String[] args) throws IOException {
        // 어떠한 N개의 수가 주어졌다. 그 수의 변경이 번번히 일어나고 중간에 부분합을 구한다
        // 데이터가 상당히 많고 부분합을 구하는데 값의 변경까지 일어난다면 -> O(logN)의 세그먼트 트리를 이용하자!!

        // N개의 수, M번의 변경 횟수, K번의 구간 합을 구하는 횟수
        // N줄에 걸쳐서 수가 주어지고 M줄 + K줄에 걸쳐서 변경과 구간합을 묻는 수가 주어진다

        // a가 1이면 b를 c로 바꾸고, a가 2이면 b~c의 합을 구햐여 출력하면된다

        // 합이 매우 크므로 long 으로 받자

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder str = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 트리를 위한 배열
        tree = new long[N * 4];
        // 주어진 수 들
        A = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
        }

        // 먼저 트리를 만들자
        height = (int) Math.ceil(log(N, 2));
        last = (int) Math.pow(2, height);
        init(1, 1, last);

        for (int i = 0; i < M + K; i++)
        {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // 변경
            if (a == 1) {
                // 특정 구간의 수를 변경시키는 update 함수
                update(b, c - A[b], 1, 1, last);
                A[b] = c;
            }

            // 구간합
            else if (a == 2) {
                // 특정 구간의 합을 반환하는 sum함수
                long ret = sum(1, 1, last, b, (int)c);
                str.append(ret).append("\n");
            }
        }
        System.out.print(str);
    }

    private static void update(int changed_index, long diff, int index, int start, int end) {
        if (changed_index < start || changed_index > end)
            return;

        // 범위 내에 있으면 일단 변경
        tree[index] += diff;

        // 자식 노드가 더 있는 경우
        if (start != end) {
            int mid = (start + end) / 2;
            update(changed_index, diff, index * 2, start, mid);
            update(changed_index, diff, index * 2 + 1, mid + 1, end);
        }
    }

    private static long sum(int index, int start, int end, int left, int right) {
        if (start > right || end < left)
            return 0;
        else if (left <= start && end <= right)
            return tree[index];
        else {
            int mid = (start+end)/2;
            return sum(index*2, start, mid, left, right) + sum(index*2+1, mid+1, end, left, right);
        }
    }

    private static double log(int n, int base) {
        return (Math.log10(n) / Math.log10(base));
    }

    private static long init(int index, int start, int end) {
        if (start > N)
            return 0;
        if (start == end)
            tree[index] = A[start];
        else {
            int mid = (start + end) / 2;
            tree[index] = init(index * 2, start, mid) + init(index * 2 + 1, mid + 1, end);
        }
        return tree[index];
    }
}

