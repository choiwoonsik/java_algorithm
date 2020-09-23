import java.util.ArrayList;
import java.util.Scanner;

public class be_women_president_2775 {
	static int	MAX = 15;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		int[][]map = new int[MAX][MAX];

		for (int i = 0; i < MAX; i++) {
			map[i][0] = 1;
			map[MAX - 1][i] = i + 1;
		}
		int	T = sc.nextInt();
		for (int i = 0; i < T; i++)
		{
			int	layer = sc.nextInt();
			int ho = sc.nextInt();
			for (int l = MAX - 1; l >= MAX - 1 - layer; l--) {
				for (int h = 0; h < ho; h++) {
					if (h > 0 && l < MAX - 1) {
						map[l][h] = map[l][h - 1] + map[l + 1][h];
					}
				}
			}
			list.add(map[MAX - 1 - layer][ho - 1]);
		}
		for (int l : list)
			System.out.println(l);
	}
}
