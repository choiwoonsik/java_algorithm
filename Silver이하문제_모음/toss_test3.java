package Silver이하문제_모음;

import java.io.*;
import java.util.Arrays;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[][] timetable;
    static String[] today;
    static String[] today_day;
    static String[] today_time;
    public static void main(String[] args) throws Exception {
        int[] n = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int N = n[0];
        timetable = new String[N][];
        today = new String[2];
        today = Arrays.stream(br.readLine().split(" ")).toArray(String[]::new);
        if (!today[0].contains("-") || !today[1].contains(":")) {
            System.out.println("error");
        }
        today_day = Arrays.stream(today[0].split("-")).toArray(String[]::new);
        today_time = Arrays.stream(today[1].split(":")).toArray(String[]::new);

        for (int i = 0; i < N; i++) {
            timetable[i] = Arrays.stream(br.readLine().split(" ")).toArray(String[]::new);
            if (!timetable[i][0].contains("-") || !timetable[i][1].contains(":")) {
                System.out.println("error");
            }
            else {
                String[] next_day = Arrays.stream(timetable[i][0].split("-")).toArray(String[]::new);
                String[] next_time = Arrays.stream(timetable[i][1].split(":")).toArray(String[]::new);
                check_time(next_day, next_time);
            }
        }
    }
    private static void check_time(String[] next_day, String[] next_time)
    {
        int[] day_arr = {365, 30, 1};
        int today_day_sum = 0;
        int next_day_sum = 0;
        for (int i = 0; i < 3; i++)
            today_day_sum += Integer.parseInt(today_day[i]) * day_arr[i];
        for (int i = 0; i < 3; i++)
            next_day_sum += Integer.parseInt(next_day[i]) * day_arr[i];

        int calc_day = today_day_sum - next_day_sum;
        if (calc_day < 0) {
            System.out.println("error");
            return;
        }
        int year = 0;
        int month = 0;
        int day = 0;
        if (calc_day >= 365) {
            year = calc_day / 365;
            calc_day -= year * 365;
        }
        if (calc_day >= 30) {
            month = calc_day / 30;
            calc_day -= month * 30;
        }
        day = calc_day;

        int[] time_arr = {3600, 60, 1};
        int today_time_sum = 0;
        int next_time_sum = 0;
        for (int i = 0; i < 3; i++)
            today_time_sum += Integer.parseInt(today_time[i]) * time_arr[i];
        for (int i = 0; i < 3; i++)
            next_time_sum += Integer.parseInt(next_time[i]) * time_arr[i];

        int calc_time = today_time_sum - next_time_sum;
        if (calc_time < 0 && calc_day < 0) {
            System.out.println("error");
            return;
        }
        int hour = 0;
        int min = 0;
        int sec = 0;
        if (calc_time >= 3600) {
            hour = calc_time / 3600;
            calc_time -= hour * 3600;
        }
        if (calc_time >= 60) {
            min = calc_time / 60;
            calc_time -= min * 60;
        }
        sec = calc_time;

        if (year > 0) {
            int pr_year = Integer.parseInt(next_day[0]) % 100;
            int pr_month = 0;
            if (Integer.parseInt(next_day[1]) >= 10)
                pr_month = Integer.parseInt(next_day[1]);
            else
                pr_month = next_day[1].charAt(1) -'0';
            int pr_day = 0;
            if (Integer.parseInt(next_day[2]) >= 10)
                pr_day = Integer.parseInt(next_day[2]);
            else
                pr_day = next_day[2].charAt(1) - '0';
            System.out.println(pr_year + "년 " + pr_month + "월 " + pr_day + "일");
        }
        else if (month > 0)
            System.out.println(month+"개월 전");
        else if (day > 0)
            System.out.println(day+"일 전");
        else if (hour > 0)
            System.out.println(hour+"시간 전");
        else if (min > 0)
            System.out.println(min+"분 전");
        else if (sec > 0)
            System.out.println("방금전");
    }
}