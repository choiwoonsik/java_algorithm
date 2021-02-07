package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 커피숖2_1275 {
    static long[] A;
    static long[] tree;
    static int height, last;
    static int x, y, a ,b;
    static int N, Q;
    public static void main(String[] args) throws IOException {
        // 부분합 + 업데이트
        // x y a b -> x~y의 합을 구하고 change a번째 수 -> b로
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder str = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        height = (int) Math.ceil(Math.log10(N)/Math.log10(2));
        last = (int) Math.pow(2, height);

        A = new long[N + 1];
        long[] tmp = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        System.arraycopy(tmp, 0, A, 1, N);

        tree = new long[N*4];
        init(1, 1, last);
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // 구간합
            long sum_val = sum(1, 1, last, Math.min(x, y), Math.max(x, y));
            str.append(sum_val).append("\n");
            // 변경
            update(1, 1, last, a, b - A[a]);
            A[a] = b;
        }
        System.out.print(str);
    }

    private static long init(int index, int start, int end) {
        if (start > N) return Integer.MIN_VALUE;

        if (start == end)
            tree[index] = A[start];
        else {
            int mid = (start+end)/2;
            tree[index] = init(index*2, start, mid) + init(index*2+1, mid+1, end);
        }
        return tree[index];
    }

    private static long sum(int index, int start, int end, int left, int right) {
        if (right < start || end < left) return 0;
        else if (left <= start && end <= right)
            return tree[index];
        else {
            int mid = (start+end)/2;
            return sum(index*2, start, mid, left, right) + sum(index*2+1, mid+1, end, left, right);
        }
    }

    private static void update(int index, int start, int end, int changed_index, long diff)
    {
        if (changed_index > end || changed_index < start)
            return;

        tree[index] += diff;

        if (start != end) {
            int mid = (start + end) / 2;
            update(index*2, start, mid, changed_index, diff);
            update(index*2+1, mid+1, end, changed_index, diff);
        }
    }
}
