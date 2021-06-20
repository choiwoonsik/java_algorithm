package Gold이상문제_정리;

public class 약수의개수와덧셈_프로그래머스 {
	public static void main(String[] args) {
		int a = solution(13, 17);
		System.out.println(a);
	}
	public static int solution(int left, int right) {
		int answer = 0;

		for (int i = left; i <= right; i++)
		{
			if (count_div(i)) answer += i;
			else answer -= i;
		}

		return answer;
	}

	private static boolean count_div(int n)
	{
		int div = 1;
		int count = 0;
		while (div <= Math.sqrt(n))
		{
			if (n % div == 0) {
				if (div != Math.sqrt(n)) count += 2;
				else count++;
			}
			div++;
		}

		if (count % 2 == 0) return true;
		return false;
	}
}

