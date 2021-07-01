package Gold이상문제_정리;

import java.io.*;

public class 홀수홀릭호석_20164 {
	static int N, Min = 100000000, Max = 0;
	static StringBuilder strN = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		strN.append(br.readLine());
		N = Integer.parseInt(strN.toString());

		int oddCount = 0;
		for (int i = 0; i < strN.length(); i++) {
			if ((strN.charAt(i) - '0') % 2 != 0)
				oddCount++;
		}
		findOdd(strN, oddCount);
		System.out.println(Min +" "+Max);
	}

	private static void findOdd(StringBuilder strN, int oddCount) {
		if (strN.length() == 1)
		{
			Min = Math.min(Min, oddCount);
			Max = Math.max(Max, oddCount);
		}
		else if (strN.length() == 2)
		{
			int tmpN = Integer.parseInt(strN.toString());
			tmpN = tmpN / 10 + tmpN % 10;

			strN = new StringBuilder();
			strN.append(tmpN);

			int tmpCount = 0;
			for (int i = 0; i < strN.length(); i++) {
				if ((strN.charAt(i) - '0') % 2 != 0)
					tmpCount++;
			}
			findOdd(strN, oddCount + tmpCount);
		}
		else if (strN.length() >= 3)
		{
			for (int front = 1; front <= strN.length() - 2; front++) {
				for (int mid = front+1; mid <= strN.length() - 1; mid++) {

					int frontN = Integer.parseInt(strN.substring(0, front));
					int midN =  Integer.parseInt(strN.substring(front, mid));
					int endN = Integer.parseInt(strN.substring(mid, strN.length()));
					int sumN = frontN + midN + endN;

					StringBuilder nextN = new StringBuilder();
					nextN.append(sumN);

					int tmpCount = 0;
					for (int i = 0; i < nextN.length(); i++) {
						if ((nextN.charAt(i) - '0') % 2 != 0)
							tmpCount++;
					}
					findOdd(nextN, oddCount + tmpCount);
				}
			}
		}
	}
}
