package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BestSeller_1302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Hashtable<String, Integer> bookTable = new Hashtable<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++)
        {
            String BookName = br.readLine();
            if (!bookTable.containsKey(BookName))
                bookTable.put(BookName, 1);
            else
                bookTable.put(BookName, bookTable.get(BookName)+1);
        }
        Iterator<String> iter = bookTable.keySet().iterator();
        int max = 0;
        while (iter.hasNext())
            max = Math.max(max, bookTable.get(iter.next()));

        iter = bookTable.keySet().iterator();
        List<String> list = new ArrayList<>();
        while (iter.hasNext())
        {
            String name = iter.next();
            int count = bookTable.get(name);
            if (count == max)
                list.add(name);
        }
        Collections.sort(list);
        System.out.println(list.get(0));
    }
}