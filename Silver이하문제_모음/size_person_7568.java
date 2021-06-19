package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class size_person_7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        KgCm[] people = new KgCm[N];
        int[] count = new int[N];
        Arrays.fill(count, 1);

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int kg = Integer.parseInt(st.nextToken());
            int cm = Integer.parseInt(st.nextToken());
            people[i] = new KgCm(kg, cm);
        }

        int order = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (people[i].kg < people[j].kg && people[i].cm < people[j].cm)
                    count[i]++;
            }
        }
        Arrays.stream(count).forEach(s -> System.out.print(s+" "));
    }
}

class KgCm
{
    int kg;
    int cm;
    KgCm (int kg, int cm) {
        this.kg = kg;
        this.cm = cm;
    }
}
