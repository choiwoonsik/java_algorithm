package Silver이하문제_모음;

import java.util.Scanner;

public class croatia_alphabet_2941 {
	static String[] change = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String str = sc.next();

		str = check(str);
		System.out.println(str.length());
	}
	static String check(String str)
	{
		for (int i = 0; i < change.length; i++)
		{
			if (str.contains(change[i]))
				str = str.replaceAll(change[i], " ");
		}
		return (str);
	}
}
