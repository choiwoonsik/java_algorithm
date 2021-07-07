package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class contact_1013 {

	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			String contact = br.readLine();

			int state =  0;
			for (int i = 0; i < contact.length(); i++) {
				state = solve(contact.charAt(i), state);
				if (state == -1)
					break;
			}
			if (state == 0 || state == 2 || state == 6 || state == 7) answer.append("YES\n");
			else answer.append("NO\n");
		}
		System.out.print(answer);
	}

	//01 10001 01 100 1
	// 100 1 01 11
	private static int solve(char bit, int state) {
		if (bit == '1')
		{
			if (state == 0)
				return 3;
			if (state == 1)
				return 2;
			if (state == 2)
				return 3;
			if (state == 5)
				return 6;
			if (state == 6)
				return 7;
			if (state == 7)
				return 7;
			if (state == 8)
				return 0;
		}
		if (bit == '0') {
			if (state == 0)
				return 1;
			if (state == 2)
				return 1;
			if (state == 3)
				return 4;
			if (state == 4)
				return 5;
			if (state == 5)
				return 5;
			if (state == 6)
				return 1;
			if (state == 7)
				return 8;
			if (state == 8)
				return 5;
		}
		return -1;
	}
}
