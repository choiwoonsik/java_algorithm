import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Tree_Round_1991 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        tree_1991[] map = new tree_1991[N];
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().toCharArray()[0];
            char left = st.nextToken().toCharArray()[0];
            char right = st.nextToken().toCharArray()[0];
            map[root-'A'] = new tree_1991(root, left, right);
        }
        preorder(map, 'A');
        System.out.println();
        midorder(map, 'A');
        System.out.println();
        postorder(map, 'A');
    }

    private static void postorder(tree_1991[] map, char node) {
        if (map[node-'A'].left != '.')
            postorder(map, map[node-'A'].left);
        if (map[node-'A'].right != '.')
            postorder(map, map[node-'A'].right);
        System.out.print(map[node-'A'].data);
    }

    private static void midorder(tree_1991[] map, char node) {
        if (map[node-'A'].left != '.')
            midorder(map, map[node-'A'].left);
        System.out.print(map[node-'A'].data);
        if (map[node-'A'].right != '.')
            midorder(map, map[node-'A'].right);
    }

    private static void preorder(tree_1991[] map, char node) {
        System.out.print(map[node-'A'].data);
        if (map[node-'A'].left != '.')
            preorder(map, map[node-'A'].left);
        if (map[node-'A'].right != '.')
            preorder(map, map[node-'A'].right);
    }

}
class tree_1991
{
    char data;
    char left;
    char right;
    tree_1991(char data, char left, char right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
