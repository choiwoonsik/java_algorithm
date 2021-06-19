package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ggoong_pivonacci_9507 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long[] buf;
	public static void main(String[] args) throws IOException {
		int[] n = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = n[0];
		buf = new long[68];
		buf[0] = 1;
		buf[1] = 1;
		buf[2] = 2;
		buf[3] = 4;
		for (int i = 0; i < N; i++)
		{
			n = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			System.out.println(find_n(n[0]));
		}
	}
	private static long find_n(int n)
	{
		if (n < 2)
			return buf[n];
		else if (n == 2)
			return buf[n];
		else if (n == 3)
			return buf[n];
		else if (n > 3) {
			if (buf[n] == 0){
				buf[n] = find_n(n - 1) + find_n(n - 2) + find_n(n - 3) + find_n(n - 4);
				return buf[n];
			}
			else
				return buf[n];
		}
		return 0;
	}
}
