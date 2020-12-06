public class programmers_비밀지도 {
    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {1, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        String[] str = solution(n, arr1, arr2);
        System.out.println(str);
    }

    private static String[] solution(int n, int[] arr1, int[] arr2) {
        char[][] map1 = new char[n][n];
        char[][] map2 = new char[n][n];
        String[] realMap = new String[n];
        String wallSpace;
        StringBuilder str = new StringBuilder();
        int len;

        for (int i = 0; i < n; i++){
            wallSpace = Integer.toBinaryString(arr1[i]);
            len = wallSpace.length();
            while (len < n) {
                str.append('0');
                len++;
            }
            str.append(wallSpace);
            for (int j = 0; j < n; j++) {
                map1[i][j] = str.charAt(j);
            }
        }

        str = new StringBuilder();
        for (int i = 0; i < n; i++){
            wallSpace = Integer.toBinaryString(arr2[i]);
            len = wallSpace.length();
            while (len < n) {
                str.append('0');
                len++;
            }
            str.append(wallSpace);
            for (int j = 0; j < n; j++) {
                map2[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; i++){
            str = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (map1[i][j] == '1' || map2[i][j] == '1')
                    str.append('#');
                else
                    str.append(' ');
            }
            realMap[i] = str.toString();
        }
        return realMap;
    }
}
