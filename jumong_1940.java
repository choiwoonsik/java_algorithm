import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class jumong_1940 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sum = 0;
        int R = arr.length - 1;
        int L = 0;
        int c = 0;
        Arrays.sort(arr);
        while(L < R){
            sum = arr[L] + arr[R];
            if (sum >= M){
                if (sum == M) {
                    c++;
                    R--;
                }
                else
                    R--;
            }
            else {
                L++;
            }
        }
        System.out.println(c);
    }
}
