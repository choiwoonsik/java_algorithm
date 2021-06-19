package Silver이하문제_모음;

import java.util.Stack;

public class right_galho {
	public static void main(String[] args)
	{
		String str = "{()()()()()()()(())()(())(((()()()()))())}";
		solution(str);
		//{ [ (
	}
	static void solution(String problem)
	{
		Stack<Character> stack = new Stack<>();
		int pos = 0;
		while (pos < problem.length())
		{
			if (problem.charAt(pos) == '(')
			{
				stack.add(problem.charAt(pos));
				pos++;
			}
			else if(problem.charAt(pos) == ')')
			{
				if (stack.isEmpty())
				{
					System.out.println("wrong1");
					return ;
				}
				else
				{
					pos++;
					stack.pop();
				}
			}
			if (pos == problem.length() && !stack.empty())
			{
				System.out.println("Wrong2");
				return;
			}
		}
		System.out.println("correct");
	}
}
