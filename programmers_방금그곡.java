import java.util.*;

public class programmers_방금그곡 {
    static ArrayList<musicInfo> musicList = new ArrayList<>();
    static String[] shap = {"C#", "D#", "F#", "G#", "A#"};
    static String[] noShap = {"c", "d", "f", "g", "a"};
    public static void main(String[] args)
    {
        String[] arr = {"12:00,12:14,HELLO,C#D#EF#GA#D#B", "13:00,13:15,WORLD,D#BBA#C#D#EGA#",
                "13:00,13:15,WORLD222,C#D#EF#GABABC#"};
        solution("GA#D#B", arr);
    }
    private static String solution(String m, String[] musicinfos)
    {
        // 먼저 음악 정보를 바탕으로 노래 만들기
        makeMusic(musicinfos);
        for (int i = 0; i < shap.length; i++)
            m = m.replaceAll(shap[i], noShap[i]);
        return find_just_that_music(m);
    }

    private static String find_just_that_music(String m) {
        String answer = "(None)";
        int playTime = 0;
        int order = 1499;
        for (musicInfo info : musicList) {
            System.out.println("m : " + m+", "+ "melody : "+ info.melody);
            if (info.melody.contains(m)) {
                //System.out.println("m : " + m+", "+ "melody : "+ info.melody);
                if (info.playTime > playTime) {
                    answer = info.name;
                    order = info.order;
                    playTime = info.playTime;
                }
                else if (info.playTime == playTime && info.order < order) {
                    answer = info.name;
                    order = info.order;
                }
            }
        }
        System.out.println(answer);
        return answer;
    }

    private static void makeMusic(String[] musicinfos) {
        StringBuffer sb;
        for (int music = 0; music < musicinfos.length; music++)
        {
            sb = new StringBuffer();
            String[] infos = musicinfos[music].split(",");
            int start_hour = Integer.parseInt(infos[0].split(":")[0]);
            int start_min = Integer.parseInt(infos[0].split(":")[1]);
            int end_hour = Integer.parseInt(infos[1].split(":")[0]);
            int end_min = Integer.parseInt(infos[1].split(":")[1]);
            int playTime = (end_hour - start_hour) * 60 + (end_min - start_min);

            for (int i = 0; i < shap.length; i++)
                infos[3] = infos[3].replaceAll(shap[i], noShap[i]);
            for (int time = 0; time < playTime; time++)
                sb.append(infos[3].charAt(time % infos[3].length()));
            musicList.add(new musicInfo(sb.toString(), playTime, music, infos[2]));
        }
    }
    private static class musicInfo
    {
        String melody;
        int playTime;
        int order;
        String name;
        public musicInfo(String melody, int playTime, int order, String name) {
            this.melody = melody;
            this.playTime = playTime;
            this.order = order;
            this.name = name;
        }
    }
}
