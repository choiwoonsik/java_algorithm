package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class LCS_최장공통수열_9251 {
	static int[][] map = new int[1001][1001];
	static int Max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String strOne = br.readLine();
		String strTwo = br.readLine();

		char[] first = strOne.toCharArray();
		char[] second = strTwo.toCharArray();

		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < second.length; j++) {
				if (first[i] == second[j]) {
					map[i+1][j+1] = map[i][j] + 1;
					Max = Math.max(map[i+1][j+1], Max);
				}
				else
					map[i+1][j+1] = Math.max(map[i][j+1], map[i+1][j]);
			}
		}
		System.out.println(Max);
	}
}
