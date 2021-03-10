import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 하노이탑이동순서_11729 {
	static int count;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		move(n, '1', '2', '3');
		System.out.println(count);
		System.out.print(str);
	}
	private static void move (int n, char one, char two, char three) {
		if (n == 1) {
			count++;
			str.append(one).append(" ").append(three).append("\n");
		}
		else {
			move(n - 1, one, three, two);
			count++;
			str.append(one).append(" ").append(three).append("\n");
			move(n - 1, two, one, three);
		}
	}
}
