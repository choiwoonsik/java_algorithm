package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 선분교차1_17386 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Dot dot1 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Dot dot2 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		Dot dot3 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Dot dot4 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		int S1 = ccw(dot1, dot2, dot3);
		int S2 = ccw(dot1, dot2, dot4);
		int S3 = ccw(dot3, dot4, dot1);
		int S4 = ccw(dot3, dot4, dot2);

		if ((S1 * S2) <= 0 && (S3 * S4) <= 0)
			System.out.println(1);
		else
			System.out.println(0);
	}

	private static int ccw(Dot dot1, Dot dot2, Dot dot3) {
		long ret = (dot2.x - dot1.x) * (dot3.y - dot1.y) - (dot2.y - dot1.y) * (dot3.x - dot1.x);
		return Long.compare(ret, 0);
	}

	private static class Dot
	{
		long y, x;

		public Dot(long y, long x) {
			this.y = y;
			this.x = x;
		}
	}
}
