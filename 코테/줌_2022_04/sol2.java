package 코테.줌_2022_04;

import java.util.*;

public class sol2 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.solution(new int[][]
				{
						{1, 0, 5},
						{2, 2, 2},
						{3, 3, 1},
						{4, 4, 1},
						{5, 10, 2}
				});

//		sol = new Solution();
//		sol.solution(new int[][]
//				{
//						{1, 2, 10}, // 2-> 12
//						{2, 5, 8}, // wating 12 -> 20
//						{3, 6, 9}, // wating
//						{4, 20, 6}, //
//						{5, 25, 5},
//				});

	}

	private static class Solution {
		static int[][] DATA;
		static Printer printer = new Printer();
		static ArrayList<Integer> result = new ArrayList<>();
		static int[] order;

		public int[] solution(int[][] data) {

			order = new int[data.length];
			DATA = data;
			printerFill();

			int i = 0;
			for (Integer docuNum : result) {
				order[i++] = docuNum;
			}

			return order;
		}

		private void printerFill() {
			for (int[] eachDocuInfo : DATA) {
				int num = eachDocuInfo[0];
				int time = eachDocuInfo[1];
				int len = eachDocuInfo[2];

				Docu docu = new Docu(num, time, len);

				// 문서 시간이 프린터 이용가능시간 이전
				if (docu.time < printer.openTime) {

					// 사용가능
					if (printer.printerQ.isEmpty()) {
						printer.printerQ.add(docu);
						printer.openTime = docu.time + docu.len;
						printer.curTime = printer.openTime;
					}
					// 사용중인경우
					else {
						printer.watingQ.add(docu);
					}
				}
				if (docu.time >= printer.openTime) {

					while (!printer.watingQ.isEmpty() && printer.watingQ.peek().time <= printer.openTime) {
						printer.printerQ.add(printer.watingQ.poll());
					}

					if (printer.printerQ.isEmpty() || docu.time == printer.openTime) {
						printer.printerQ.add(docu);
					} else {
						printer.watingQ.add(docu);
					}

					Docu nextDocu = printer.printerQ.poll();
					printer.openTime = Math.max(nextDocu.time, printer.openTime) + nextDocu.len;
					result.add(nextDocu.number);
				}
			}

			while (!printer.watingQ.isEmpty()) {
				printer.printerQ.add(printer.watingQ.poll());
			}

			while (!printer.printerQ.isEmpty()) {
				result.add(printer.printerQ.poll().number);
			}
		}

		private static class Printer {
			int openTime;
			int curTime;
			PriorityQueue<Docu> printerQ = new PriorityQueue<>((d1, d2) -> {
				if (d1.len == d2.len) {
					return Integer.compare(d1.time, d2.time);
				} else return Integer.compare(d1.len, d2.len);
			});
			PriorityQueue<Docu> watingQ = new PriorityQueue<>((d1, d2) -> {
				if (d1.len == d2.len) {
					return Integer.compare(d1.time, d2.time);
				} else return Integer.compare(d1.len, d2.len);
			});
		}

		private static class Docu {
			int number;
			int time;
			int len;

			Docu(int number, int time, int len) {
				this.number = number;
				this.time = time;
				this.len = len;
			}
		}
	}
}
