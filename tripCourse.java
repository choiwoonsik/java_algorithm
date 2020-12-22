import java.util.ArrayList;
import java.util.Collections;

public class tripCourse {
	static boolean[] visited;
	static ArrayList<String> list = new ArrayList<>();
	static String 	word;
	public static void main(String[] args)
	{
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		String[] re =  solution(tickets);
		for (String l : re)
			System.out.print(l + " ");
	}
	static String[] solution(String[][] tickets) {
		String[] answer = {};
		int		count;
		for (int i = 0; i < tickets.length; i++)
		{
			String go = tickets[i][0];
			String end = tickets[i][1];
			visited = new boolean[tickets.length];
			word = "";
			if (go.equals("ICN"))
			{
				visited[i] = true;
				word += go + ",";
				count = 0;
				dfs(end, tickets, count + 1);
				visited[i] = false;
			}
		}
		Collections.sort(list);
		return (list.get(0).split(","));
	}

	static void dfs(String end, String[][] tickets, int count)
	{
		word += end + ",";
		if (count == tickets.length)
		{
			list.add(word);
			return;
		}
		for (int i = 0; i < tickets.length; i++)
		{
			String NewGo = tickets[i][0];
			String NewEnd = tickets[i][1];
			if (end.equals(NewGo) && !visited[i]) {
				visited[i] = true;
				dfs(NewEnd, tickets, count + 1);
				visited[i] = false;
				word = word.substring(0, word.length() - 4);
			}
		}
	}
}
