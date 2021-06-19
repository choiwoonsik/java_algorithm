package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class order_by_age_10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<age_name> list = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new age_name(i, age, name));
        }
        Collections.sort(list);
        list.forEach(System.out::println);
    }
}
class age_name implements Comparable<age_name>
{
    int order;
    int age;
    String name;
    age_name (int order, int age, String name)
    {
        this.order = order;
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(age_name o) {
        if (this.age < o.age)
            return -1;
        else {
            if (this.age == o.age)
                return this.order < o.order ? -1 : 1;
            return 1;
        }
    }
    @Override
    public String toString() {
        return this.age +" "+this.name;
    }
}
