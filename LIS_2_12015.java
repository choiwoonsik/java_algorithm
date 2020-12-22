import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LIS_2_12015 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] vector = new int[N+1];
        Arrays.fill(vector, 0);

        int v_idx = 0;
        vector[0] = -1;
        for (int i = 0; i < arr.length; i++)
        {
            // vector의 끝값이 배열의 현재 값보다 작다면 그냥 vector의 끝에 추가
            if (arr[i] > vector[v_idx])
                vector[++v_idx] = arr[i];
            // vector의 끝값이 배열의 현재 값보다 크다면 이분탐색해서 현재값으로 대체해준다
            else if (vector[v_idx] > arr[i]) {
                int left = 0;
                int right = v_idx;
                while (left < right) {
                    int mid = (left + right) / 2;

                    if (vector[mid] < arr[i])
                        left = mid + 1;
                    else
                        right = mid;
                }
                vector[right] = arr[i];
            }
        }

        System.out.println(v_idx);
    }
}
