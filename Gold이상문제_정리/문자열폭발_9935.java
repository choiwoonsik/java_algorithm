package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 문자열폭발_9935 {
	static char[] string;
	static char[] target;
	static int IDX = -1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<charNum> stack = new Stack<>();
		HashSet<Character> set = new HashSet<>();
		StringBuilder str = new StringBuilder();

		string = br.readLine().toCharArray();
		target = br.readLine().toCharArray();
		for (char c : target)
			set.add(c);

		for (char c : string) {
			// 첫글자가 같다 -> 시작
			if (c == target[0]) {
				IDX = 0;
				stack.push(new charNum(target[IDX], IDX++)); // 0번째, 0번
				// 번호가 다 찼다면
				if (IDX == target.length)
					doBoom(stack);
			}
			// 다른 글자가 같다 -> 제대로 오고있는거였는지 확인
			else if (set.contains(c) && IDX != -1) {
				if (target[IDX] == c) { // 다음번호의 target 과 맞다면
					stack.push(new charNum(target[IDX], IDX++));
					// 번호가 다 찼다면
					if (IDX == target.length)
						doBoom(stack);
				} else { // 다음번호의 target과 다르다면 땡!!!
					IDX = -1;
					stack.push(new charNum(c, IDX));
				}
			}
			// 상관없는 글자인 경우
			else {
				IDX = -1;
				stack.push(new charNum(c, IDX));
			}
		}

		if (stack.isEmpty())
			System.out.print("FRULA");
		else {
			stack.forEach(s -> str.append(s.c));
			System.out.print(str);
		}
	}

	private static void doBoom(Stack<charNum> stack) {
		while (IDX > 0 && stack.peek().num == IDX - 1) {
			stack.pop();
			IDX--;
		}
		if (!stack.isEmpty())
			IDX = stack.peek().num;
		else
			IDX = -1;
		if (IDX != -1) IDX++;
	}

	private static class charNum
	{
		char c;
		int num;
		charNum(char c, int num) {
			this.c = c;
			this.num = num;
		}
	}
}
