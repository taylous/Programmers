package KAKAO.길찾기게임;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    private ArrayList<Node> nodes = new ArrayList<>();
    private Node root;

    public static void main(String[] args) {

        int[][] answers = new Solution().solution(new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}});

        for(int[] answer : answers) {
            for(int path : answer)
                System.out.print(path);
            System.out.println();
        }
    }

    public Node insert(Node node, Node newNode) {

        if(node == null)
            return newNode;

        if(node.x > newNode.x)
            node.left = insert(node.left, newNode);
        else
            node.right = insert(node.right, newNode);
        return node;
    }

    public void init(int[][] nodeinfo) {

        int id = 1;

        for (int[] node : nodeinfo)
            nodes.add(new Node(id++, node[0], node[1]));
        Collections.sort(nodes);

        Node root = nodes.remove(0);
        this.root = new Node(root.id, root.x, root.y);

        for(Node node : nodes) {
            this.root = insert(this.root, node);
        }
    }

    public void preorder(Node node, ArrayList<Integer> path) {

        if(node == null)
            return;

        path.add(node.id);
        preorder(node.left, path);
        preorder(node.right, path);
    }

    public void postorder(Node node, ArrayList<Integer> path) {

        if(node == null)
            return;

        postorder(node.left, path);
        postorder(node.right, path);
        path.add(node.id);
    }

    public int[][] solution(int[][] nodeinfo) {

        int[][] answer = new int[2][nodeinfo.length];
        ArrayList<Integer> paths = new ArrayList<>();
        init(nodeinfo);

        preorder(this.root, paths);
        answer[0] = paths.stream().mapToInt(i -> i).toArray();
        paths.clear();
        postorder(this.root, paths);
        answer[1] = paths.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}

class Node implements Comparable<Node> {

    int id;
    int x;
    int y;
    Node left, right;

    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.left = right = null;
    }

    @Override
    public int compareTo(Node other) {

        if(this.y == other.y)
            return this.x < other.x ? -1 : 1;
        return this.y < other.y ? 1 : -1;
    }
}