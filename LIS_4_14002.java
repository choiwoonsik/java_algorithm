import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LIS_4_14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] count = new int[N];

        Arrays.fill(count, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int MAX = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    count[i] = Math.max(count[j] + 1, count[i]);
                    MAX = Math.max(MAX, count[i]);
                }
            }
        }
        System.out.println(MAX);

        List<Integer> list = new ArrayList<>();
        int last = count.length - 1;
        for (int cnt = MAX; cnt > 0; cnt--)
        {
            for (int i = last; i >= 0; i--)
                if (count[i] == cnt) {
                    list.add(arr[i]);
                    last = i - 1;
                    break;
                }
        }
        Collections.sort(list);
        list.forEach(s -> System.out.print(s + " "));
    }
}
/*
7
1 6 2 4 5 3 7
 */