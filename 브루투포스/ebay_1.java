package 브루투포스;

public class ebay_1 {

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(new String[][]{
                {"MO 12:00 WE 14:30", "MO 12:00", "MO 15:00", "MO 18:00"},
                {"TU 09:00", "TU 10:00", "TU 15:00", "TU 18:00"},
                {"WE 09:00", "WE 12:00", "WE 15:00", "WE 18:00"},
                {"TH 09:30", "TH 11:30", "TH 15:00", "TH 18:00"},
                {"FR 15:00", "FR 15:00", "FR 15:00", "FR 15:00"}});
    }

    private static class Solution {
        private static int count;
        private static int a;
        private static int b;
        private static final int fullHourLast = 6;
        private static final int halfHourLast = 3;
        private static final boolean[][][] timeTable = new boolean[5][13][2];
        private static final Class[][] subjectClass = new Class[5][4];
        private static final boolean[][] visited = new boolean[5][4];
        private static final int fullTime = 0;
        private static final int halfTime = 1;

        public int solution(String[][] schedule) {

            for (int i = 0; i < 5; i++) {
                String[] subjectInfo = schedule[i];

                for (int j = 0; j < 4; j++) {
                    String classInfo = subjectInfo[j];
                    String[] info = classInfo.split(" ");
                    int day = dayFunc(info[0]);
                    String[] time = info[1].split(":");
                    int hour = Integer.parseInt(time[0]);
                    int min = Integer.parseInt(time[1]);

                    // type a
                    if (classInfo.length() == 8) {
                        subjectClass[i][j] = new Class(day, hour, min, fullTime);
                    } else {
                        // type b
                        int day2 = dayFunc(info[2]);
                        time = info[3].split(":");
                        int hour2 = Integer.parseInt(time[0]);
                        int min2 = Integer.parseInt(time[1]);
                        subjectClass[i][j] = new Class(day, hour, min, day2, hour2, min2, halfTime);
                    }
                }
            }

            dfs(0, 0, 0, new Class[5]);
            System.out.println("count = " + count);
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            return count;
        }

        private static void dfs(int d, int c, int select, Class[] selected) {
            if (d == 5) {
                boolean isB = false;

                for (Class cl : selected) {
                    if (cl.type == 1) {
                        b++;
                        isB = true;
                    }
                }
                if (!isB) a++;
                count++;
                return;
            }

            for (int i = c; i < 4; i++) {
                if (!visited[d][i]) {
                    visited[d][i] = true;
                    Class nowClass = Solution.subjectClass[d][i];
                    if (register(nowClass)) {
                        selected[d] = nowClass;
//                        if (nowClass.type == 1) {
//                            for (int j = 0; j < 5; j++) {
//                                for (int k = 0; k < 13; k++) {
//                                    for (int l = 0; l < 2; l++) {
//                                        String s = "O";
//                                        if (!timeTable[j][k][l]) s = "X";
//                                        System.out.print((k + 9) + ":" + (l * 30) + " : " + s + ", ");
//                                    }
//                                }
//                                System.out.println();
//                            }
//                            System.out.println(nowClass.day1 + ", " + nowClass.hour1 + ":" + nowClass.min1);
//                        }
                        dfs(d + 1, 0, select + 1, selected);
                        cancel(nowClass);
                    } else {
//                        if (nowClass.type == 0) {
//                            for (int j = 0; j < 5; j++) {
//                                for (int k = 0; k < 13; k++) {
//                                    for (int l = 0; l < 2; l++) {
//                                        String s = "O";
//                                        if (!timeTable[j][k][l]) s = "X";
//                                        System.out.print((k + 9) + ":" + (l * 30) + " : " + s + ", ");
//                                    }
//                                }
//                                System.out.println();
//                            }
//                            System.out.println(nowClass.day1 + ", " + nowClass.hour1 + ":" + nowClass.min1);
//                        }
                    }
                    visited[d][i] = false;
                }
            }
        }

        private static void cancel(Class nowClass) {
            int count = 0;
            // type a 3 hour
            if (nowClass.type == fullTime) {
                int d = nowClass.day1;
                int h = nowClass.hour1 - 9;
                int m = nowClass.min1 == 30 ? 1 : 0;

                for (int hour = h; hour < h + 3; hour++) {
                    for (int min = m; min < 2; min++) {
                        timeTable[d][hour][min] = false;
                        count++;
                        if (count == fullHourLast) break;
                    }
                    m = 0;
                }
            }

            // type b 1hour 30min
            else {
                int d1 = nowClass.day1;
                int h1 = nowClass.hour1 - 9;
                int m1 = nowClass.min1 == 30 ? 1 : 0;
                int d2 = nowClass.day2;
                int h2 = nowClass.hour2 - 9;
                int m2 = nowClass.min2 == 30 ? 1 : 0;

                for (int hour = h1; hour < h1 + 3; hour++) {
                    for (int min = m1; min < 2; min++) {
                        timeTable[d1][hour][min] = false;
                        count++;
                        if (count == halfHourLast) break;
                    }
                    m1 = 0;
                }

                count = 0;
                for (int hour = h2; hour < h2 + 3; hour++) {
                    for (int min = m2; min < 2; min++) {
                        timeTable[d2][hour][min] = false;
                        count++;
                        if (count == halfHourLast) break;
                    }
                    m2 = 0;
                }
            }
        }

        private static boolean register(Class nowClass) {
            int count;
            // type 3hour
            if (nowClass.type == fullTime) {
                int d = nowClass.day1;
                int h = nowClass.hour1 - 9;
                int m = nowClass.min1 == 30 ? 1 : 0;

                // isClear
                if (isNotClearFull(d, h, m)) return false;

                count = 0;
                for (int hour = h; hour < h + 3; hour++) {
                    for (int min = m; min < 2; min++) {
                        if (!timeTable[d][hour][min]) {
                            timeTable[d][hour][min] = true;
                            count++;
                            if (count == fullHourLast) break;
                        } else return false;
                    }
                    m = 0;
                    if (count == fullHourLast) break;
                }
            }
            // type 1hour 30min
            else {
                int d1 = nowClass.day1;
                int h1 = nowClass.hour1 - 9;
                int m1 = nowClass.min1 == 30 ? 1 : 0;
                int d2 = nowClass.day2;
                int h2 = nowClass.hour2 - 9;
                int m2 = nowClass.min2 == 30 ? 1 : 0;

                // isClear one, two
                if (isNotClearHalf(d1, h1, m1)) return false;
                if (isNotClearHalf(d2, h2, m2)) return false;

                count = 0;
                for (int hour = h1; hour < h1 + 2; hour++) {
                    for (int min = m1; min < 2; min++) {
                        if (!timeTable[d1][hour][min]) {
                            timeTable[d1][hour][min] = true;
                            count++;
                            if (count == halfHourLast) break;
                        } else return false;
                        m1 = 0;
                    }
                    if (count == halfHourLast) break;
                }

                count = 0;
                for (int hour = h2; hour < h2 + 2; hour++) {
                    for (int min = m2; min < 2; min++) {
                        if (!timeTable[d2][hour][min]) {
                            timeTable[d2][hour][min] = true;
                            count++;
                            if (count == halfHourLast) break;
                        } else return false;
                    }
                    m2 = 0;
                    if (count == halfHourLast) break;
                }
            }
            return true;
        }

        private static boolean isNotClearFull(int d, int h, int m) {
            int count = 0;
            for (int hour = h; hour < h + 3; hour++) {
                for (int min = m; min < 2; min++) {
                    if (timeTable[d][hour][min]) {
                        return true;
                    }
                    count++;
                    if (count == fullHourLast) break;
                }
                m = 0;
                if (count == fullHourLast) break;
            }
            return false;
        }

        private static boolean isNotClearHalf(int d, int h, int m) {
            int count = 0;
            for (int hour = h; hour < h + 2; hour++) {
                for (int min = m; min < 2; min++) {
                    if (timeTable[d][hour][min]) {
                        return true;
                    }
                    count++;
                    if (count == halfHourLast) break;
                }
                m = 0;
                if (count == halfHourLast) break;
            }
            return false;
        }

        private int dayFunc(String day) {
            switch (day) {
                case "MO":
                    return 0;
                case "TU":
                    return 1;
                case "WE":
                    return 2;
                case "TH":
                    return 3;
                default:
                    return 4;
            }
        }
    }

    private static class Class {
        int day1;
        int hour1;
        int min1;
        int day2;
        int hour2;
        int min2;
        int type;

        public Class(int day, int hour, int min, int type) {
            this.day1 = day;
            this.hour1 = hour;
            this.min1 = min;
            this.type = type;
        }

        public Class(int day1, int hour1, int min1, int day2, int hour2, int min2, int type) {
            this.day1 = day1;
            this.hour1 = hour1;
            this.min1 = min1;
            this.day2 = day2;
            this.hour2 = hour2;
            this.min2 = min2;
            this.type = type;
        }
    }
}
