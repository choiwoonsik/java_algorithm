package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

public class 설탕배달_2839 {
	static int N;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int min = Integer.MAX_VALUE;

		for (int i = 0; i <= N / 5; i++) {
			for (int j = 0; j <= N / 3; j++) {
				if (i * 5 + j * 3 == N) {
					min = Math.min(min, i + j);
				}
			}
		}

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
}
