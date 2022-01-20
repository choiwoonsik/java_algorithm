package SDS_알고리즘.Day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
4 2
1 1 1 1
 */
public class 수들의합2 {
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int l = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        nums = new int[l];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < l; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int left = 0;
        int right = 0;
        int count = 0;

        while (right < l) {
            sum += nums[right];

            while (sum > goal) {
                sum -= nums[left++];
            }
            if (sum == goal) count++;
            right++;
        }
        System.out.println(count);
    }
}
