package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

/*
3
4
7
10
 */
public class a123더하기_9095 {
	static int 개수;
	static int 숫자;
	static int[] 디피;
	static StringBuilder 결과 = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		개수 = Integer.parseInt(br.readLine());

		for (int 현재번호 = 0; 현재번호 < 개수; 현재번호++) {

			숫자 = Integer.parseInt(br.readLine());
			디피 = new int[숫자 + 1];
			디피[0] = 1;
			디피[1] = 1;

			for (int 현재 = 2; 현재 <= 숫자; 현재++) {
				for (int 수 = 1; 수 <= 3; 수++) {
					if (현재 >= 수) {
						디피[현재] += 디피[현재 - 수];
					}
				}
			}

			결과.append(디피[숫자]).append("\n");
		}

		System.out.println(결과);
	}
}
