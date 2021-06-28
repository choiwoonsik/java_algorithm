package Gold이상문제_정리;

import java.io.*;

public class 회문_17609 {
	static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			String s = br.readLine();
			if (is_Pailindrom(s)) {
				System.out.println(0);
			} else if (is_CanPilindrom(s)) {
				System.out.println(1);
			} else {
				System.out.println(2);
			}
		}
	}

	private static boolean is_CanPilindrom(String s) {
		for (int i = 0; i <= s.length() / 2 - 1; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {

				StringBuilder tmpS = new StringBuilder(s);
				boolean a = is_Pailindrom(tmpS.deleteCharAt(i).toString());

				tmpS = new StringBuilder(s);
				boolean b = is_Pailindrom(tmpS.deleteCharAt(s.length() - 1 - i).toString());

				return a || b;
			}
		}
		return false;
	}

	private static boolean is_Pailindrom(String s) {
		boolean flag = true;
		for (int i = 0; i <= s.length() / 2 - 1; i++) {
			if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
