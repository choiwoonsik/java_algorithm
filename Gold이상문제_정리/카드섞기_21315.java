package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 카드섞기_21315 {
	static int N;
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			list.add(Integer.parseInt(st.nextToken()));

		ArrayList<Integer> sort_list = new ArrayList<>(list);
		Collections.sort(sort_list);
		int max_k = (int) (Math.log10(sort_list.size()) / Math.log10(2));

		for (int i = 1; i <= max_k; i++) {
			ArrayList<Integer> shuffled = shuffle(sort_list, i, (int)(sort_list.size() - Math.pow(2, i)));
			for (int j = 1; j <= max_k; j++) {
				if (check(shuffle(shuffled, j, (int)(sort_list.size() - Math.pow(2, j))))) {
					System.out.println(i +" "+ j);
					return;
				}
			}
		}
	}

	private static boolean check(ArrayList<Integer> shuffle) {
		boolean flag = true;

		for (int j = 0; j < shuffle.size(); j++) {
			if (!list.get(j).equals(shuffle.get(j))) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	private static ArrayList<Integer> shuffle(ArrayList<Integer> list, int k, int start)
	{
		ArrayList<Integer> rm_list = new ArrayList<>(list);
		ArrayList<Integer> sub_list = new ArrayList<>();
		for (int i = 0; i < Math.pow(2, k); i++)
			sub_list.add(rm_list.remove(start));
		sub_list.addAll(rm_list);
		if (k == 0)
			return sub_list;
		int next_start = (int) (Math.pow(2, k) - Math.pow(2, k - 1));
		return shuffle(sub_list, k - 1, next_start);
	}
}
