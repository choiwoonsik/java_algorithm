import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
		절단기에 높이 h를 지정 => 높이를 지정하면 h미터 높이로 이동 한줄에 연속해있는 나무 절단
		즉 h보다 높은 나무는 h초과 부부이 잘리고 이하의 나무는 안잘린다
		필요한 나무 M
		M 20억 이하
		N 나무의 개수 백만개 이하 O(n) or O(nlongn)으로 풀어야댐 // 1000000 * 100  log2N == 100 -> 2^100 >= N

		4 7
		20 15 10 17
		나무 의 수 N = 4
		필요 나무 M = 7

		높이 H? => 15 (5 + 2)

		투포인터?

		나무의 첫높이와 끝높이

		ㄴㄴ

		정렬해서 이진탐색으로 가운데 나무 골라서 쭉자르고
		길면
		뒤에서 가운데 잡아서 자르고 부족하면 그전께 답길면 계속 재귀 진행
		짧으면
		앞에서 다시 가운데 잡아서 ㄱㄱ
		 */

public class cut_tree_2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N;
		int M;
		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			int tree = Integer.parseInt(st.nextToken());
			list.add(tree);
		}
		int LOW = 0;
		int HIGH = 1000000000;
		long sum;
		int answer = 0;
		while (LOW < HIGH)
		{
			int mid = (LOW + HIGH) / 2;
			sum = 0;
			//System.out.println("high>"+ HIGH + "  mid>" + mid +"  low>" + LOW);
			answer = mid;
			for (int i = 0; i < N; i++)
			{
				if (list.get(i) > mid)
					sum += (list.get(i) - mid);
			}
			if (sum > M) {
				if (LOW == mid)
					break;
				LOW = mid;
				//System.out.println(">>>"+sum);
			}
			else if (sum < M) {
				if (HIGH == mid)
					break;
				HIGH = mid;
			}
			else {
				answer = mid;
				break;
			}
		}
		System.out.println(answer);
	}
}
