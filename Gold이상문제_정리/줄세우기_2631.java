package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 줄세우기_2631 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		ArrayList<Integer> child = new ArrayList<>();
		child.add(-1);
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int pos = Collections.binarySearch(child, num);// -1
			if (pos < 0) {
				pos = Math.abs(pos + 1);
				if (pos < child.size())
					child.remove(pos);
				child.add(pos, num);
			}
			else {
				child.remove(pos);
				child.add(pos, num);
			}
		}
		System.out.println(N - (child.size()-1));
	}
}
