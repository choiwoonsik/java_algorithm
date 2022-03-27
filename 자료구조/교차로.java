package 자료구조;

import java.util.*;
import java.io.*;

/*
6
0 A
0 B
0 C
2 D
2 C
4 B
 */
public class 교차로 {
	static int N;
	static Queue<Car> aQueue;
	static Queue<Car> bQueue;
	static Queue<Car> cQueue;
	static Queue<Car> dQueue;
	static int startMinTime;
	static int startMaxTime;
	static int MAX = Integer.MAX_VALUE;
	static StringBuilder answer = new StringBuilder();
	static int[] carOutTimes;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		aQueue = new LinkedList<>();
		bQueue = new LinkedList<>();
		cQueue = new LinkedList<>();
		dQueue = new LinkedList<>();
		carOutTimes = new int[N];
		Arrays.fill(carOutTimes, -1);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int time = Integer.parseInt(st.nextToken());
			String line = st.nextToken();

			if (line.equals("A")) {
				aQueue.add(new Car(i, time));
			} else if (line.equals("B")) {
				bQueue.add(new Car(i, time));
			} else if (line.equals("C")) {
				cQueue.add(new Car(i, time));
			} else {
				dQueue.add(new Car(i, time));
			}

			if (i == 0) {
				startMinTime = time;
			} else if (i == N - 1) {
				startMaxTime = time;
			}
		}

		moveCar();
		for (int i = 0; i < N; i++) {
			answer.append(carOutTimes[i]).append("\n");
		}
		System.out.print(answer);
	}

	private static void moveCar() {

		int curTime = startMinTime;

		while (!aQueue.isEmpty() || !bQueue.isEmpty() || !cQueue.isEmpty() || !dQueue.isEmpty()) {

			// 일단 모든 라인의 입차 시간을 다 최대로 변경
			int aTime = MAX;
			int bTime = MAX;
			int cTime = MAX;
			int dTime = MAX;

			// 각 라인 가장 앞에서 대기중인 차량의 입차 시간을 확보
			if (!aQueue.isEmpty()) {
				aTime = aQueue.peek().time;
			}
			if (!bQueue.isEmpty()) {
				bTime = bQueue.peek().time;
			}
			if (!cQueue.isEmpty()) {
				cTime = cQueue.peek().time;
			}
			if (!dQueue.isEmpty()) {
				dTime = dQueue.peek().time;
			}

			// 입차 시간이 현재 카운트 시간을 모두 초과한다면 해당 시간대 중 가장 빠른 시간으로 빨기감기
			if (aTime > curTime && bTime > curTime && cTime > curTime && dTime > curTime) {
				curTime = Math.min(Math.min(Math.min(aTime, bTime), cTime), dTime);
			}

			// 각 차들이 갈 수 있는지
			boolean aCarGoPossible = false;
			boolean bCarGoPossible = false;
			boolean cCarGoPossible = false;
			boolean dCarGoPossible = false;

			// 교착 상태
			boolean aIsStruct = false;
			boolean bIsStruct = false;
			boolean cIsStruct = false;
			boolean dIsStruct = false;

			// a line car start
			if (!aQueue.isEmpty()) {
				// d 차 있나 봄
				if (!dQueue.isEmpty() && dQueue.peek().time > curTime && aQueue.peek().time <= curTime) {
					aCarGoPossible = true;
				}
				// d 차 있나 봄
				if (dQueue.isEmpty() && aQueue.peek().time <= curTime) {
					aCarGoPossible = true;
				}
				// 시간이 되는대도 못가는 경우 멈춤
				if (!aCarGoPossible && aQueue.peek().time <= curTime) {
					aIsStruct = true;
				}
			}

			// b line car start
			if (!bQueue.isEmpty()) {
				// a 차 있나 봄
				if (!aQueue.isEmpty() && aQueue.peek().time > curTime && bQueue.peek().time <= curTime) {
					bCarGoPossible = true;
				}
				// a 차 있나 봄
				if (aQueue.isEmpty() && bQueue.peek().time <= curTime) {
					bCarGoPossible = true;
				}
				// 시간이 되는대도 못가는 경우 멈춤
				if (!bCarGoPossible && bQueue.peek().time <= curTime) {
					bIsStruct = true;
				}
			}

			// c line car start
			if (!cQueue.isEmpty()) {
				// b 차 있나 봄
				if (!bQueue.isEmpty() && bQueue.peek().time > curTime && cQueue.peek().time <= curTime) {
					cCarGoPossible = true;
				}
				if (bQueue.isEmpty() && cQueue.peek().time <= curTime) {
					cCarGoPossible = true;
				}
				if (!cCarGoPossible && cQueue.peek().time <= curTime) {
					cIsStruct = true;
				}
			}

			// d line car start
			if (!dQueue.isEmpty()) {
				// c 차 있나 봄
				if (!cQueue.isEmpty() && cQueue.peek().time > curTime && dQueue.peek().time <= curTime) {
					dCarGoPossible = true;
				}
				if (cQueue.isEmpty() && dQueue.peek().time <= curTime) {
					dCarGoPossible = true;
				}
				if (!dCarGoPossible && dQueue.peek().time <= curTime) {
					dIsStruct = true;
				}
			}


			if (aIsStruct && bIsStruct && cIsStruct && dIsStruct) {
				break;
			}

			Car curCar;
			if (aCarGoPossible) {
				curCar = aQueue.poll();
				carOutTimes[curCar.index] = curTime;
			}
			if (bCarGoPossible) {
				curCar = bQueue.poll();
				carOutTimes[curCar.index] = curTime;
			}
			if (cCarGoPossible) {
				curCar = cQueue.poll();
				carOutTimes[curCar.index] = curTime;
			}
			if (dCarGoPossible) {
				curCar = dQueue.poll();
				carOutTimes[curCar.index] = curTime;
			}

			curTime++;
		}
	}

	private static class Car
	{
		int index;
		int time;

		public Car(int index, int time) {
			this.index = index;
			this.time = time;
		}
	}
}
