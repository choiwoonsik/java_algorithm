package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class PPAP_16120 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<pnum> stack = new Stack<>();

		char[] str = br.readLine().toCharArray();

		for (char c : str) {
			if (!stack.isEmpty()) {
				int peek = stack.peek().num;
				if (stack.peek().pa == 'P') {
					if (c == 'P')
						stack.push(new pnum(c, ++peek));
					else if (stack.peek().num >= 2)
						stack.push(new pnum(c, -1));
					else
						stack.push(new pnum(c, -99));
				} else {
					// PPAP!!
					if (c == 'P' && peek == -1) {
						stack.pop();
						int before = stack.peek().num;
						while (!stack.isEmpty() && stack.peek().num >= before - 1)
							stack.pop();
						if (stack.isEmpty())
							stack.push(new pnum(c, 1));
						else
							stack.push(new pnum(c, stack.peek().num + 1));
					} else if (c == 'P')
						stack.push(new pnum(c, 1));
					else
						stack.push(new pnum(c, -99));
				}
			} else {
				if (c == 'A')
					stack.push(new pnum(c, -99));
				else
					stack.push(new pnum(c, 1));
			}
		}

		if (stack.size() == 1 && stack.pop().pa == 'P')
			System.out.println("PPAP");
		else
			System.out.println("NP");
	}
	private static class pnum
	{
		char pa;
		int num;

		public pnum(char pa, int num) {
			this.pa = pa;
			this.num = num;
		}
	}
}
//PPPPPPPPPPPPPAPAPPAPAPAPAPPAPAPAPAPAPAPAPAP
