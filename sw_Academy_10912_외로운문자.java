import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class sw_Academy_10912_외로운문자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer ret_str = new StringBuffer();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            str = pair_char(str);
            if (str.length() <= 0)
                ret_str.append("#").append(i+1).append(" Good");
            else
                ret_str.append("#").append(i+1).append(" ").append(str);
            if (i < N - 1)
                ret_str.append("\n");
        }
        System.out.println(ret_str);
    }

    private static String pair_char(String str) {
        ArrayList<Character> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <str.length(); i++)
        {
            if (list.size() > 0)
            {
                boolean removed = false;
                for (Character character : list) {
                    if (character == str.charAt(i)) {
                        list.remove(character);
                        removed = true;
                        break;
                    }
                }
                if (removed)
                    continue;
            }
            list.add(str.charAt(i));
        }
        Collections.sort(list);
        while (!list.isEmpty()) {
            sb.append(list.get(0));
            list.remove(0);
        }
        return sb.toString();
    }
}
