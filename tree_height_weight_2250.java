import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class tree_height_weight_2250 {
    static int[] nodeArr;
    static int[] depthArrMax;
    static int[] depthArrMin;
    static int pos = 1;
    static int nodeDepth;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        tree_node_2250[] tree = new tree_node_2250[N+1];

        for (int i = 1; i <= N; i++)
            tree[i] = new tree_node_2250(i, -1, -1);

        for (int i = 1; i <= N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int data = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree[data].left = left;
            tree[data].right = right;
            if (left != -1)
                tree[left].parent = data;
            if (right != -1)
                tree[right].parent = data;
        }

        int isRoot=0;
        for (int i = 1; i < N+1; i++) {
            if (tree[i].parent == -1)
                isRoot = i;
        }
        nodeArr = new int[N+1];
        depthArrMax = new int[N+1];
        depthArrMin = new int[N+1];

        Arrays.fill(depthArrMin, Integer.MAX_VALUE);

        inorder(tree, isRoot, 1);

        int level = 1;
        int ret_diff = depthArrMax[1] - depthArrMin[1] + 1;
        int diff;
        for (int i = 1; i <= nodeDepth; i++) {
            diff = depthArrMax[i] - depthArrMin[i] + 1;
            if (diff > ret_diff) {
                ret_diff = diff;
                level = i;
            }
        }
        System.out.println(level + " " + ret_diff);
    }

    private static void inorder(tree_node_2250[] tree, int data, int depth) {
        if (tree[data].left != -1)
            inorder(tree, tree[data].left, depth + 1);
        nodeArr[pos] = tree[data].data;
        depthArrMax[depth] = Math.max(depthArrMax[depth], pos);
        depthArrMin[depth] = Math.min(depthArrMin[depth], pos);
        pos++;
        nodeDepth = Math.max(depth, nodeDepth);
        if (tree[data].right != -1)
            inorder(tree, tree[data].right, depth + 1);
    }
}

class tree_node_2250
{
    int parent;
    int data;
    int left;
    int right;
    tree_node_2250(int data, int left, int right) {
        this.parent = -1;
        this.data = data;
        this.left = left;
        this.right = right;
    }
}