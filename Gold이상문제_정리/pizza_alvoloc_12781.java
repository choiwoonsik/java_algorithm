package Gold이상문제_정리;

/*
0 0 6 2 5 -4 2 2
*/
import java.io.*;
import java.util.*;

public class pizza_alvoloc_12781 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Dot dot1 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Dot dot2 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Dot dot3 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Dot dot4 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		// 선분 교차 판정
		//1-2선분에 대해서 dot3, dot4를 검사
		int S1 = ccw(dot1.x, dot1.y, dot2.x, dot2.y, dot3.x, dot3.y);
		int S2 = ccw(dot1.x, dot1.y, dot2.x, dot2.y, dot4.x, dot4.y);

		//3-4선분에 대해서 dot1, dot2를 검사
		int S3 = ccw(dot3.x, dot3.y, dot4.x, dot4.y, dot1.x, dot1.y);
		int S4 = ccw(dot3.x, dot3.y, dot4.x, dot4.y, dot2.x, dot2.y);

		// 완전 교차
		if  ((S1 * S2) < 0 && (S3 * S4) < 0)
			System.out.println(1);
		else { //아닌 경우
			if ((S1 * S2) == 0 && (S3 * S4) == 0) { // 완전 평행 - 겹치거나 아니거나
				if (dot1.x == dot3.x && dot2.x == dot4.x) { // y축 나열
					if (dot1.y <= dot4.y && dot2.y >= dot3.y) {
						System.out.println(1);
						return;
					}
				}
				if (dot1.y == dot3.y && dot2.y == dot4.y) { // x축 나열
					if (dot1.x <= dot4.x && dot2.x >= dot3.x) {
						System.out.println(1);
						return;
					}
				}
			}
			// 교차하지 않는 경우
			System.out.println(0);
		}
	}

	private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		int ret = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
		return Integer.compare(ret, 0);
	}

	private static class Dot
	{
		int x, y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
