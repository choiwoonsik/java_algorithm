package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
JA_BU_K_A

모음(A,E,I,O,U)이 연속해서 3번, 자음(모음을 제외한 나머지 알파벳)이 연속해서 3번 나오지 않아야 한다
L을 반드시 포함해야 한다
 */

public class 즐거운단어_2922 {
	static char[] myWord;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		myWord = br.readLine().toCharArray();

		System.out.println(makeComb(0, 0, 0, 0));
	}

	private static long makeComb(int mo, int ja, int isL, int idx) {
		if (mo >= 3) return 0;
		if (ja >= 3) return 0;

		if (idx == myWord.length) {
			if (isL == 0) return 0;
			else return 1;
		}

		long ret = 0;
		if (myWord[idx] != '_')
		{
			if (myWord[idx] == 'A' || myWord[idx] == 'E' || myWord[idx] == 'I' || myWord[idx] == 'O' || myWord[idx] == 'U') {
				ret = makeComb(mo + 1, 0, isL, idx + 1);
			} else {
				if (myWord[idx] == 'L') ret = makeComb(0, ja + 1, isL + 1, idx + 1);
				else ret = makeComb(0, ja + 1, isL, idx + 1);
			}
		} else {
			ret += (5 * makeComb(mo + 1, 0, isL, idx + 1));
			ret += (20 * makeComb(0, ja + 1, isL, idx + 1));
			ret += makeComb(0, ja + 1, isL + 1, idx + 1);
		}
		return ret;
	}
}
