import java.util.Scanner;

public class pronounce_pw_4659 {
	static Scanner sc = new Scanner(System.in);
	static char[] mo = {'a', 'e', 'i', 'o', 'u'};
	static boolean first;
	static boolean second;
	static boolean third;
	public static void main(String[] args) {
		while (true) {
			String str = sc.next();
			if (str.equals("end"))
				break;
			else if (str.length() >= 1 && str.length() <= 20) {
				if (check_pw(str)) {
					System.out.println("<"+str+"> is acceptable.");
				} else {
					System.out.println("<"+str+"> is not acceptable.");
				}
			}
			else
				System.out.println("<"+str+"> is acceptable.");
		}
	}
	private static boolean check_pw(String pw)
	{
		int mo_check = 0;
		int ja_check = 0;
		first = true;
		second = false;
		third = false;
		boolean not_mo;
		for (int i = 0; i < pw.length(); i++)
		{
			not_mo = true;
			for (int m = 0; m < 5; m++) {
				if (pw.charAt(i) == mo[m]) {
					first = false;
					mo_check++;
					ja_check = 0;
					not_mo = false;
				}
			}
			if (not_mo)
			{
				ja_check++;
				mo_check = 0;
			}
			if (i < pw.length() - 1) {
				if (pw.charAt(i) == pw.charAt(i + 1) && pw.charAt(i) != 'e' && pw.charAt(i) != 'o')
					third = true;
			}
			if (mo_check >= 3|| ja_check >= 3)
				second = true;
		}
		if (first || second || third)
			return false;
		return true;
	}
}