package Gold이상문제_정리;

import java.io.*;
import java.util.*;

//aabbbaa

public class 행운의문자열_1342 {

	static String myS;
	static boolean[] visited = new boolean[10];
	static int[] alphabet = new int[26];
	static char[] myWord;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		myS = br.readLine();
		myWord = new char[myS.length()];

		for (int i = 0; i < myS.length(); i++) {
			char c = myS.charAt(i);
			alphabet[c  - 'a']++;
		}

		DFS(0);

		for (int alpha : alphabet) {
			if (alpha > 0)
				count /= factorial(alpha);
		}

		System.out.println(count);
	}

	private static int factorial(int alpaCnt) {
		int n = 1;
		for (int i = 1; i <= alpaCnt; i++) {
			n *= i;
		}
		return n;
	}

	private static void DFS(int depth) {

		if (depth == myS.length())
		{
			if (adjIsOk())
			{
				count++;
			}
		}

		for (int i = 0; i < myS.length(); i++) {
			if (!visited[i])
			{
				visited[i] = true;
				myWord[depth] = myS.charAt(i);
				DFS(depth+1);
				visited[i] = false;
			}
		}
	}

	private static boolean adjIsOk() {
		for (int i = 0; i < myWord.length-1; i++) {
			if (myWord[i] == myWord[i+1]) return false;
		}
		return true;
	}
}
