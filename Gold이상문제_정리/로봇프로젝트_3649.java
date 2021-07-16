package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 로봇프로젝트_3649 {
	static int X, N;
	static StringBuilder answer = new StringBuilder();
	static int[] block;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;
		while ((s = br.readLine()) != null)
		{
			X = Integer.parseInt(s) * 10000000;
			N = Integer.parseInt(br.readLine());
			block = new int[N];

			for (int i = 0; i < N; i++)
				block[i] = Integer.parseInt(br.readLine());
			Arrays.sort(block);

			int[] LR = binarySearch();

			if (LR == null)
				answer.append("danger\n");
			else
				answer.append("yes ").append(LR[0]).append(" ").append(LR[1]).append("\n");
			s = null;
		}
		System.out.println(answer);
	}

	private static int[] binarySearch() {
		int low = 0;
		int high = block.length - 1;
		int size;

		while (low < high) {

			size = block[low] + block[high];

			if (size > X)
				high--;
			else if (size < X)
				low++;
			else
				return new int[]{block[low], block[high]};
		}
		return null;
	}
}
