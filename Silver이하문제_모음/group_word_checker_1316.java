package Silver이하문제_모음;

import java.util.Scanner;

public class group_word_checker_1316 {
	static int			word_n;
	static boolean[]	word_check;
	static int			count = 0;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		word_n = Integer.parseInt(sc.nextLine());

		for (int i = 0; i < word_n; i++)
		{
			String str = sc.nextLine();
			word_check = new boolean[26];
			int	pos;
			for (pos = 0; pos < str.length(); pos++)
			{
				char	c = str.charAt(pos);
				if (!word_check[str.charAt(pos) - 'a'])
				{
					word_check[str.charAt(pos) - 'a'] = true;
					while (pos < str.length() - 1)
						if (str.charAt(pos + 1) == c)
							pos++;
						else
							break;
				}
				else
					break;
			}
			if (pos == str.length())
				count++;
		}
		System.out.println(count);
	}
}
