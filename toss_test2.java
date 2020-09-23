import java.io.*;
import java.util.*;

class Main2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[][] address;
    public static void main(String[] args) throws Exception {
        int[] n = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int N = n[0];
        if (N < 2) {
            System.out.println("없음");
            return ;
        }
        address = new String[N][];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            address[i] = tmp.split("\\.");
            address[i][address[i].length - 1] = null;
        }
        int last_common = find_union();
        if (last_common < 0)
            System.out.println("없음");
        else
        {
            for(int i = 0; i <= last_common; i++)
            {
                System.out.print(address[0][i]);
                if (i < last_common)
                    System.out.print(".");
            }
        }
    }
    private static int find_union()
    {
        int origin = 0;
        while (address[0][origin] != null)
        {
            String compare_str = address[0][origin];
            for (int i = 0; i < address.length; i++)
            {
                if (!address[i][origin].equals(compare_str))
                    return origin - 1;
            }
            origin++;
        }
        return origin - 1;
    }
}