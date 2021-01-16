package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 개똥벌레_3020 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[] up = new int[N/2];
		int[] down = new int[N/2];
		for (int i = 0; i < N; i++)
		{
			int n = Integer.parseInt(br.readLine());
			if (i%2 == 0)
				down[i/2] = n;
			else
				up[i/2] = n;
		}

		Arrays.sort(down);
		Arrays.sort(up);

		int Min = 987654321;
		int count = 0;
		for (int i = 1; i <= H; i++)
		{
			int ans = find(down, i);
			ans += find(up, H - i + 1);

			if (Min > ans) {
				Min = ans;
				count = 1;
			}
			else if (Min == ans)
				count++;
		}
		System.out.println(Min + " " + count);
	}
	private static int find(int[] Arr, int H)
	{
		int start = 0;
		int end = Arr.length;

		while (start < end)
		{
			int middle = (start + end) / 2;
			if (Arr[middle] >= H)
				end = middle;
			else
				start = middle + 1;
		}

		return Arr.length - end;
	}
}
