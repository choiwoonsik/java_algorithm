package 힣_코테.카페_2021;

public class sol1 {

	public static void main(String[] args) {
		Sol sol = new Sol();
		int solution = sol.solution(12_345_678, 10, 20, 250_000, 10_000_000, 4);
		System.out.println(solution == 9000014);

		solution = sol.solution(1000000000, 50, 99, 100_000, 0, 6);
		System.out.println(solution == 6150);

		solution = sol.solution(123456789, 0, 0, 1, 0, 360);
		System.out.println(solution == 123456789);
	}

	private static class Sol {

		private int solution(int money, int minratio, int maxratio, int ranksize, int threshold, int months){
			if (minratio == 0 || maxratio == 0) {
				return money;
			}

			while (months-- > 0) {

				if (money < threshold) {
					break;
				}

				int multiple = 0;
				while (money >= threshold + (ranksize * multiple) && minratio + multiple <= maxratio) {
					multiple++;
				}
				multiple--;

				long tax;
				int ifMoney = money;
				if (money > 100) {
					ifMoney -= money % 100;
				} else {
					ifMoney = 0;
				}

				tax = (long) ifMoney * (minratio + multiple) / 100;
				money -= tax;
			}

			return money;
		}
	}
}
