import java.io.*;
import java.util.Arrays;

class Main1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String hex = "0123456789ABCDEF";
    static String str_al = "#";
    static boolean onechar;
    public static void main(String[] args) throws Exception {
        String input[] = br.readLine().split(",");
        char[] front = input[0].toCharArray();
        int[] back = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).toArray();
        sum(front, back[0]);
    }
    private static void sum(char[] front, int back)
    {
        int alpha = 255 * back / 100;
        if (alpha < 16)
            onechar = true;
        make_hex(alpha);
        for (int i = 1; i < front.length; i++) {
            str_al += String.valueOf(front[i]);
        }
        System.out.println(str_al);
    }
    private  static void make_hex(int alpha)
    {
        if(alpha < 16) {
            if (onechar)
                str_al += "0";
            str_al += String.valueOf(hex.charAt(alpha));
            return ;
        }
        else {
            int head = alpha / 16;
            str_al += String.valueOf(hex.charAt(head));
            alpha -= (head * 16);
            make_hex(alpha);
        }
    }
}