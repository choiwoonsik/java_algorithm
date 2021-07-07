package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class AandB_12904 {

	static StringBuilder start = new StringBuilder();
	static StringBuilder end = new StringBuilder();
	static boolean flag;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		start.append(br.readLine());
		end.append(br.readLine());

		DFS(end);

		if (flag) System.out.println(1);
		else System.out.println(0);
	}

	private static void DFS(StringBuilder myS) {
//		System.out.println(myS+"\n");

		if (myS.length() < start.length())
			return;

		if (myS.toString().equals(start.toString())) {
			flag = true;
			return;
		}

		if(myS.charAt(myS.length()-1) == 'A') {
			myS.replace(myS.length() - 1, myS.length(), "");
			DFS(myS);
		}
		else if (myS.charAt(myS.length()-1) == 'B') {
			myS.replace(myS.length() - 1, myS.length(), "");
			myS.reverse();
			DFS(myS);
		}

	}
}
