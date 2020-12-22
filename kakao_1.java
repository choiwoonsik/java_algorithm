import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class kakao_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String new_id = br.readLine();
		StringBuilder sb = new StringBuilder();
		String answer = "";
		new_id = new_id.toLowerCase();
		sb.append(new_id);
		sb = make_list(sb);
		answer = sb.toString();
		System.out.println(answer);
		//return answer;
	}
	private static StringBuilder make_list(StringBuilder sb)
	{
		StringBuilder new_sb = new StringBuilder();
		int i = 0;
		while(i < sb.length()) {
			if ((sb.charAt(i) <= 'z' && sb.charAt(i) >= 'a')  || Character.isDigit(sb.charAt(i)) || sb.charAt(i) == '-'
					|| sb.charAt(i) == '_' || sb.charAt(i) == '.') {
				new_sb.append(sb.charAt(i));
			}
			i++;
		}
		System.out.println(new_sb);
		sb = new_sb;
		i = 0;
		while (i < sb.length()) {
			if (sb.charAt(i) == '.') {
				while (i < sb.length() - 1 && sb.charAt(i + 1) == '.') {
					sb.deleteCharAt(i);
				}
			}
			i++;
		}
		if (sb.charAt(0) == '.' )
			sb.deleteCharAt(0);
		if (sb.length() >= 1 && sb.charAt(sb.length() - 1) == '.')
			sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
		if (sb.length() == 0)
			sb.append('a');
		else if (sb.length() >= 16) {
			String str =  sb.toString();
			str = str.substring(0, 15);
			if (str.charAt(14) == '.')
				str = str.substring(0, 14);
			sb.delete(0, sb.length());
			sb.append(str);
		}
		else {
			while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.')
				sb.deleteCharAt(sb.length() - 1);
			if (sb.length() == 0)
				sb.append('a');
		}
		if (sb.length() <= 2)
			while(sb.length() < 3)
				sb.append(sb.charAt(sb.length() - 1));
		return sb;
	}
}