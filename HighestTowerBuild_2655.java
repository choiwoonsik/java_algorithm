import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HighestTowerBuild_2655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        brick[] arr = new brick[N+1];

        arr[0] = new brick(0,0,0,0);
        StringTokenizer st;
        for (int i = 1; i  <= N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int kg = Integer.parseInt(st.nextToken());
            arr[i] = new brick(i, w, h, kg);
        }
        //무게 기준 정렬
        Arrays.sort(arr);

        int[] dp = new int[N+1];
        for (int i = 0; i <= N; i++)
            dp[i] = arr[i].height;

        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i].width > arr[j].width) {
                    dp[i] = Math.max(dp[j] + arr[i].height, dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        StringBuilder str = new StringBuilder();
        int count = 0;
        int i = N;
        while (i > 0)
        {
            if (dp[i] == max) {
                count++;
                str.append(arr[i].num).append("\n");
                max -= arr[i].height;
            }
            i--;
        }
        System.out.print(count);
        System.out.print(str.reverse().toString());
    }
}

class brick implements Comparable<brick>
{
    int num;
    int width;
    int height;
    int weight;

    public brick(int num, int width, int height, int weight) {
        this.num = num;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(brick o) {
        return this.weight > o.weight ? 1 : -1;
    }
}
