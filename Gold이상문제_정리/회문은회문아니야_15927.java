package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 회문은회문아니야_15927 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String myS = br.readLine();
		StringBuilder builder = new StringBuilder(myS);

		if (isPallindrom(builder.toString(), 0)) {
			int R = 1;
			while (R <= builder.length() - 1) {
				if (!isPallindrom(builder.toString(), R)) {
					System.out.println(builder.length() - R);
					return;
				}
				while (R <= builder.length()-1 && builder.charAt(builder.length()-1) == builder.charAt(builder.length()-1-R))
					R++;
			}
			System.out.println(-1);
		} else System.out.println(myS.length());
	}

	private static boolean isPallindrom(String myS, int R) {
		int l = 0;
		int r = myS.length() - 1 - R;
		boolean flag = true;
		while (l <= r)
		{
			if (myS.charAt(l) != myS.charAt(r)) {
				flag = false;
				break;
			}
			l++;
			r--;
		}
		return flag;
	}
}
