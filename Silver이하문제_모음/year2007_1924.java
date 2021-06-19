package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class year2007_1924 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Month = Integer.parseInt(st.nextToken());
        int Day = Integer.parseInt(st.nextToken());

        int day = 0;
        while (--Month > 0) {
            switch (Month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    day += 31;
                    break;
                case 4: case 6: case 9: case 11:
                    day += 30;
                    break;
                case 2:
                    day += 28;
                    break;
            }
        }
        Day += day;

        day = Day % 7;
        if (day == 0)
            System.out.println("SUN");
        else if (day == 1)
            System.out.println("MON");
        else if (day == 2)
            System.out.println("TUE");
        else if (day == 3)
            System.out.println("WED");
        else if (day == 4)
            System.out.println("THU");
        else if (day == 5)
            System.out.println("FRI");
        else if (day == 6)
            System.out.println("SAT");
    }
}
