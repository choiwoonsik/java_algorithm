package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 강의실배정_11000 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> meetings = new PriorityQueue<>(Comparator.comparingInt(o->o.start));
		PriorityQueue<Node> meetingRoom = new PriorityQueue<>(Comparator.comparingInt(o->o.end));

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings.add(new Node(start, end));
		}

		meetingRoom.add(meetings.poll());

		int room_cnt = 1;
		while (!meetings.isEmpty())
		{
			Node meet = meetings.poll();

			if (!meetingRoom.isEmpty()) {
				if (meet.start < meetingRoom.peek().end)
				{
					room_cnt++;
					meetingRoom.add(meet);
				}
				else {
					meetingRoom.poll();
					meetingRoom.add(meet);
				}
			}
		}
		System.out.println(room_cnt);
	}
	private static class Node
	{
		int start;
		int end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
