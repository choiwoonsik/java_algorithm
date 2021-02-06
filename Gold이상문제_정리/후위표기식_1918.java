package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 후위표기식_1918 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		StringBuilder str = new StringBuilder();

		char[] line = br.readLine().toCharArray();

		for (int i = 0; i < line.length; i++) {
			if (line[i] >= 'A' && line[i] <= 'Z')
				str.append(line[i]);
			else {
				// 곱셈, 나눗셈은 같은 경우 계속 빼주고 넣어주고 아니면 맨 위에 놔준다
				if (line[i] == '*' || line[i] == '/') {
					while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/'))
						str.append(stack.pop());
					stack.push(line[i]);
				}
				// 괄호 열기는 최우선순위를 갖는다
				else if (line[i] == '(') stack.push(line[i]);
				// +, -는 최하위 우선순위이므로 괄호 열기가 아니라면 끝까지 빼주고 넣는다
				else if (line[i] == '+' || line[i] == '-') {
					while (!stack.isEmpty() && stack.peek() != '(')
						str.append(stack.pop());
					stack.push(line[i]);
				}
				// 괄호닫기가 나온경우 괄호 열기까지 포함해서 다 빼준다
				else if (line[i] == ')') {
					while (stack.peek() != '(')
						str.append(stack.pop());
					stack.pop();
				}
			}
		}
		while (!stack.isEmpty())
			str.append(stack.pop());
		System.out.print(str);
	}
}
