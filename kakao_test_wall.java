import java.util.ArrayList;

public class kakao_test_wall {
    public static void main(String[] args){
        String[] arr = {"test", "test", "args"};
        sol(3, arr);
    }
    public static int sol(int cacheSize, String[] cities) {
        int count = 0;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
            if (list.contains(cities[i])) {
                count += 1;
                list.remove(cities[i]);
                list.add(cities[i]);
            } else {
                count += 5;
                if (cacheSize == 0)
                    continue;
                else if (list.size() < cacheSize)
                    list.add(cities[i]);
                else {
                    list.remove(0);
                    list.add(cities[i]);
                }
            }
        }
        return count;
    }
}