package Gold이상문제_정리;

public class N으로표현_프로그래머스 {
	public static void main(String[] args) {
		solution(2, 11);
	}

	static int Min = Integer.MAX_VALUE;
	static int target, n;

	public static int solution(int N, int number) {
		n = N;
		target = number;

		recursive(0, 0);

		Min = Min == Integer.MAX_VALUE ? -1 : Min;
		System.out.println(Min);
		return Min;
	}

	private static void recursive(int num, int count) {
		if (count > 8) {
			Min = -1;
			return;
		}
		if (num == target) {
			Min = Math.min(Min, count);
			return;
		}

		int tmp = n;
		for (int i = 0; i < 8 - count; i++) {
			int now_cnt = count + i + 1;
			recursive(num + tmp, now_cnt);
			recursive(num - tmp, now_cnt);
			recursive(num / tmp, now_cnt);
			recursive(num * tmp, now_cnt);
			tmp = tmp * 10 + n; // 5 55 555 5555 ...
		}
	}
}
