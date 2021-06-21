package Silver이하문제_모음;

import java.io.*;
import java.util.*;

public class 행복유치원_13164 {
	static int N, K;
	static int[] height;
	static int[] diff;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		height = new int[N];
		diff = new int[N - 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			height[i] = h;
		}

		Arrays.sort(height);

		for (int i = 0; i < N - 1; i++) {
			diff[i] = height[i + 1] - height[i];
		}

		Arrays.sort(diff);

		int sum = 0;
		for (int i = 0; i < diff.length - K + 1; i++) {
			sum += diff[i];
		}

		System.out.println(sum);
	}
}
