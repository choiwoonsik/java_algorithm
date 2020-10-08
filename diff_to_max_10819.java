import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class diff_to_max_10819 {
    static int N;
    static int max = Integer.MIN_VALUE;
    static boolean[] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        permutation(0, arr);
        System.out.println(max);
    }
    private static void permutation(int depth, int[] arr)
    {
        if (depth == N)
        {
            sum(arr);
            return;
        }
        for (int i = depth; i < N; i++)
        {
            swap(arr, i, depth);
            permutation(depth + 1, arr);
            swap(arr, i, depth);
        }
    }
    static void swap(int[] arr, int i, int depth)
    {
        int tmp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = tmp;
    }

    static void sum(int[] arr)
    {
        int sum = 0;
        for (int i = 2; i < N; i++)
        {
            sum += Math.abs(arr[i - 2] - arr[i - 1]);
        }
        if (max < sum)
            max = sum;
    }
}
