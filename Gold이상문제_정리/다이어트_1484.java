package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 다이어트_1484 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder str = new StringBuilder();

		int G = Integer.parseInt(br.readLine());
		// G : 현재 ^ 2 - 전 ^ 2 = G
		// 4^2 - 1^2 = 15
		// 8^2 - 7^2 = 15
		boolean no = true;
		int[] arrG = new int[G/2+1];
		for (int i = 0; i < G/2+1; i++) arrG[i] = i+1;

		int L = 0, R = 0;
		while (R < G/2+1) {
			long current = (long) Math.pow(arrG[R], 2);
			long past = (long) Math.pow(arrG[L], 2);
			while (current - past > G && L <= R) {
				past = (long) Math.pow(arrG[L], 2);
				L++;
			}
			if (current - past == G) {
				no = false;
				str.append(R+1).append("\n");
			}
			R++;
		}
		if (no)
			System.out.println("-1");
		else
			System.out.print(str);
	}
}
