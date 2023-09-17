package 힣_코테.코테_스크2_2022_03;

import java.util.*;

class Solution2 {
	public static void main(String[] args) {
//		solution(
//				new String[]{"1", "2", "4", "3", "3", "4", "1", "5"},
//				new String[]{"read 1 3 1 2", "read 2 6 4 7", "write 4 3 3 5 2", "read 5 2 2 5", "write 6 1 3 3 9", "read 9 1 0 7"}
//		);

		solution(
				new String[]{"1", "1", "1", "1", "1", "1", "1"},
				new String[]{"write 1 12 1 5 8", "read 2 3 0 2", "read 5 5 1 2", "read 7 5 2 5", "write 13 4 0 1 3", "write 19 3 3 5 5", "read 30 4 0 6", "read 32 3 1 5"}
		);
	}

	static int totalWorkTime;
	static int readWaitCount;
	static int writeWaitCount;
	static String[] answer;
	static ArrayList<String> answerList = new ArrayList<>();
	static String[] array;
	static PriorityQueue<Process> processTimeQ = new PriorityQueue<>(Comparator.comparing(P -> P.startTime));

	public static String[] solution(String[] arr, String[] processes) {
		array = arr;
		for (String process : processes) {
			String[] processStr = process.split(" ");

			if (processStr[0].equals("read")) {
				int startTime = Integer.parseInt(processStr[1]);
				int duration = Integer.parseInt(processStr[2]);
				int A = Integer.parseInt(processStr[3]);
				int B = Integer.parseInt(processStr[4]);
				processTimeQ.add(new Process("read", startTime, duration, A, B));
			} else {
				int startTime = Integer.parseInt(processStr[1]);
				int duration = Integer.parseInt(processStr[2]);
				int A = Integer.parseInt(processStr[3]);
				int B = Integer.parseInt(processStr[4]);
				int C = Integer.parseInt(processStr[5]);
				processTimeQ.add(new Process("write", startTime, duration, A, B, C));
			}
		}
		processStart();
		answerList.add(totalWorkTime + "");
		answer = new String[answerList.size()];
		int i = 0;
		for (String s : answerList) {
			answer[i] = s;
			System.out.println(answer[i]);
			i++;
		}
		return answer;
	}

	private static void processStart() {

		Jop job = new Jop("");

		while (!processTimeQ.isEmpty() || !job.watingQueue.isEmpty()) {

			job.curTime++;
			if (job.curTime == job.openTime) job.curProcessType = "";

			// process 시작 시간
			if (!processTimeQ.isEmpty() && job.curTime == processTimeQ.peek().startTime) {

				if (job.watingQueue.isEmpty()
					|| (writeWaitCount == 0 && job.watingQueue.peek().type.equals("read") && processTimeQ.peek().type.equals("write"))) {
					Process nowProcess = processTimeQ.poll();

					if (job.curProcessType.equals("") && nowProcess.type.equals("read")) {
						processRead(nowProcess);
						totalWorkTime += nowProcess.duration;
						job.openTime = job.curTime + nowProcess.duration;
						job.curProcessType = "read";
					} else if (job.curProcessType.equals("") && nowProcess.type.equals("write")) {
						processWrite(nowProcess);
						totalWorkTime += nowProcess.duration;
						job.openTime = job.curTime + nowProcess.duration;
						job.curProcessType = "write";
					} else if (job.curProcessType.equals("read") && nowProcess.type.equals("read")) {
						processRead(nowProcess);
						int nextOpenTime = Math.max(job.openTime, job.curTime + nowProcess.duration);
						totalWorkTime += nextOpenTime - job.openTime;
						job.openTime = nextOpenTime;
					} else if (job.curProcessType.equals("read") && nowProcess.type.equals("write")) {
						job.watingQueue.add(nowProcess);
						writeWaitCount++;
					} else if (job.curProcessType.equals("write")) {
						job.watingQueue.add(nowProcess);
						if (nowProcess.type.equals("read")) readWaitCount++;
						if (nowProcess.type.equals("write")) writeWaitCount++;
					}
				} else {
					job.watingQueue.add(processTimeQ.poll());
				}
			}

			if (!job.watingQueue.isEmpty()) {
				Optional<Process> write = job.watingQueue.stream().filter(W -> W.type.equals("write")).findFirst();

				if (write.isPresent() && job.curProcessType.equals("")) {
					Process nowProcess = write.get();
					job.watingQueue.remove(write.get());
					processWrite(nowProcess);
					job.openTime = job.curTime + nowProcess.duration;
					totalWorkTime += nowProcess.duration;
					job.curProcessType = "write";
					writeWaitCount--;
				} else if (job.curProcessType.equals("") || job.curProcessType.equals("read")) {
					while (!job.watingQueue.isEmpty() && job.watingQueue.peek().type.equals("read")) {
						Process nowProcess = job.watingQueue.poll();
						processRead(nowProcess);
						int nextOpenTime = Math.max(job.openTime, job.curTime + nowProcess.duration);
						totalWorkTime += nextOpenTime - job.openTime;
						job.openTime = nextOpenTime;
						job.curProcessType = "read";
						readWaitCount--;
					}
				}
			}
		}
	}

	private static void processWrite(Process nowProcess) {
		for (int s = nowProcess.A; s <= nowProcess.B; s++) {
			array[s] = String.valueOf(nowProcess.C);
		}
	}

	private static void processRead(Process nowProcess) {
		StringBuilder readHistory = new StringBuilder();

		for (int s = nowProcess.A; s <= nowProcess.B; s++) {
			readHistory.append(array[s]);
		}

		answerList.add(readHistory.toString());
	}

	private static class Jop {
		String curProcessType;
		int curTime;
		int openTime;
		Queue<Process> watingQueue = new LinkedList<>();

		public Jop(String curProcessType) {
			this.curProcessType = curProcessType;
		}
	}

	private static class Process {
		String type;
		int startTime;
		int duration;
		int A;
		int B;
		int C;

		public Process(String type, int startTime, int duration, int a, int b, int c) {
			this.type = type;
			this.startTime = startTime;
			this.duration = duration;
			A = a;
			B = b;
			C = c;
		}

		public Process(String type, int startTime, int duration, int a, int b) {
			this.type = type;
			this.startTime = startTime;
			this.duration = duration;
			A = a;
			B = b;
		}
	}
}
