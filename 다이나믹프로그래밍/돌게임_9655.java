package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

public class 돌게임_9655 {
	static int N;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		if (N % 2 == 1) {
			System.out.println("SK");
		} else
			System.out.println("CY");
	}
}
