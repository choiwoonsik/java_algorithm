package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
4 6
mrvica
mrvica
marica
mateja
 */
public class 문자잘라내기_2866 {
	static int R, C;
	static char[][] words;
	static HashSet<String> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		words = new char[R][C];

		for (int i = 0; i < R; i++) {
			words[i] = br.readLine().toCharArray();
		}

		int l = 0;
		int r = R-1;


		binarySearch(l, r);
	}

	private static void binarySearch(int l, int r) {
		boolean check = false;
		int mid = 0;

		while (l <= r)
		{
			boolean isDupl = false;
			mid = (l + r) / 2;

			for (int x = 0; x < C; x++)
			{
				StringBuilder line = new StringBuilder();
				for (int y = mid; y < R; y++)
					line.append(words[y][x]);

				if (set.contains(line.toString())) {
					isDupl = true;
					break;
				}

				set.add(line.toString());
			}

			check = isDupl;
			if (isDupl) r = mid - 1;
			else l = mid + 1;
			set.clear();
		}

		if (check) System.out.println(mid - 1);
		else System.out.println(mid);
	}
}
