import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class over_average_4344 {
	static int C;
	static double N;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int c[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		C = c[0];
		for (int i = 0; i < C; i++) {
			int n[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N = n[0];
			calc_average(n);
		}
	}
	private static void calc_average(int[] grade)
	{
		double sum = 0;
		for(int i = 1; i < grade.length; i++) {
			sum += grade[i];
		}
		double avr = sum / N;
		double count = 0;
		for (int i = 1; i < grade.length; i++) {
			if (grade[i] > avr)
				count++;
		}
		String tmp = String.format("%.3f", count/N * 100000 / 1000);
		System.out.println(tmp+"%");
	}
}
