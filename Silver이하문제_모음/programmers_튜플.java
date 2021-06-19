package Silver이하문제_모음;

import java.util.*;
public class programmers_튜플 {
    public static void main(String[] args)
    {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        solution(s);
    }
    private static int[] solution(String s) {
        ArrayList<ArrayList<Integer>> setList = new ArrayList<>();
        s = s.substring(2, s.length() - 2);

        String[] strSet = s.split("},\\{");

        for (String value : strSet) {
            String[] numbers = value.split(",");
            ArrayList<Integer> list = new ArrayList<>();
            for (String number : numbers)
                list.add(Integer.parseInt(number));
            setList.add(list);
        }
        setList.sort(Comparator.comparingInt(ArrayList::size));

        ArrayList<Integer> got = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        for (ArrayList<Integer> list : setList) {
            if (list.size() != 1) {
                for (Integer haveNum : got)
                    list.remove(haveNum);
            }
            str.append(list.get(0));
            got.add(list.get(0));
            str.append(" ");
        }
        String[] arr = str.toString().split(" ");
        int[] ret = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            ret[i] = Integer.parseInt(arr[i]);
        return ret;
    }
}
