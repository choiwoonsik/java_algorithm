package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Connet_Networt_1922 {
    /*
    컴퓨터의 수 N
    연결할 수 있는 선의 수 M
    a, b, c,  from , to , price
     */
    static Dictionary<Integer, Integer> rank = new Hashtable<>();
    static Dictionary<Integer, Integer> parent = new Hashtable<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] computers = new int[N+1];
        for (int i = 1; i  <= N; i++)
            computers[i] = i;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        List<computer_1922> list = new ArrayList<>();

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            list.add(new computer_1922(from, to, price));
        }
        
        List<computer_1922> coms = kruskal(computers, list);
        int sum = coms.stream().mapToInt(c -> c.price).sum();
        System.out.println(sum);
    }

    private static List<computer_1922> kruskal(int[] computers, List<computer_1922> list) {

        List<computer_1922> MST = new ArrayList<>();

        //각 컴퓨터 set만들기
        for (int computer : computers)
            make_set(computer);

        //비용 기준 정렬
        Collections.sort(list);

        //모든 컴퓨터를 최소비용으로 연결하기
        for (computer_1922 com : list){
            int from = com.from;
            int to = com.to;
            //no cycle 이면
            if (find(from) != find(to)){
                union(from, to);
                MST.add(com);
            }
        }

        return MST;
    }

    private static void union(int from, int to) {
        int rootCom1 = parent.get(from);
        int rootCom2 = parent.get(to);
        if (rank.get(rootCom1) < rank.get(rootCom2)){
            parent.put(rootCom1, rootCom2);
        }
        else {
            parent.put(rootCom2, rootCom1);
            if (rank.get(rootCom1) == rank.get(rootCom2)){
                rank.put(rootCom1, rank.get(rootCom1)+1);
            }
        }
    }

    private static int find(int com) {
        if (parent.get(com) != com){
            parent.put(com, find(parent.get(com)));
        }
        return parent.get(com);
    }

    private static void make_set(int computer) {
        //key : 자기 , value : 부모(자기자신으로 초기화)
        parent.put(computer, computer);
        rank.put(computer, 0);
    }
}

class computer_1922 implements Comparable<computer_1922>{
    int from;
    int to;
    int price;
    computer_1922 (int f, int t, int price){
        this.from = f;
        this.to = t;
        this.price = price;
    }

    @Override
    public int compareTo(computer_1922 o) {
        return this.price < o.price ? -1 : 1;
    }

    @Override
    public String toString() {
        return this.from +" - "+this.to+" : "+this.price;
    }
}
