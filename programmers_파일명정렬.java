import java.util.*;

public class programmers_파일명정렬 {
    static ArrayList<word> wordList = new ArrayList<>();
    public static void main(String[] args) {
        String[] test = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        test = new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat",
                "f-00070", "f--01010" , "f--1010", "img12.png", "img10.png", "img02.png",
                "img1.png", "IMG01.GIF", "img2.JPG"};
        solution(test);
    }
    private static String[] solution(String[] files)
    {
        String[] string = new String[files.length];
        trim_name(files);
        sortList();
        int i = 0;
        for (word w : wordList)
            string[i++] = w.origin;
        return string;
    }

    private static void sortList() {
        wordList.sort((o1, o2) -> {
            if (!o1.head.equals(o2.head))
                return o1.head.compareTo(o2.head);
            else
                return Integer.compare(o1.number, o2.number);
        });
    }

    private static void trim_name(String[] files) {
        for (String file : files) {
            String head;
            int number;
            int idxHead;
            for (idxHead = 0; idxHead < file.length(); idxHead++) {
                if (Character.isDigit(file.charAt(idxHead)))
                    break;
            }
            head = file.substring(0, idxHead).toLowerCase();
            int idxNumber;
            for (idxNumber = idxHead; idxNumber < file.length(); idxNumber++) {
                if (!Character.isDigit(file.charAt(idxNumber)))
                    break;
            }
            number = Integer.parseInt(file.substring(idxHead, idxNumber));
            wordList.add(new word(head, number, file));
        }
    }
    private static class word
    {
        String head;
        int number;
        String origin;
        public word(String head, int number, String origin) {
            this.head = head;
            this.number = number;
            this.origin = origin;
        }
    }
}
