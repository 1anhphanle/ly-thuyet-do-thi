package BFSSpanningTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSpanningTree {

    static class Graph {
        int V; // Số đỉnh
        List<List<Integer>> adj; // Danh sách kề

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        // Thêm cạnh vào đồ thị
        public void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u); // Đồ thị vô hướng
        }

        // Thuật toán BFS để xây dựng cây khung
        public List<int[]> bfsSpanningTree(int start) {
            List<int[]> spanningTree = new ArrayList<>(); // Danh sách các cạnh của cây khung

            boolean[] visited = new boolean[V]; // Mảng đánh dấu các đỉnh đã duyệt
            Queue<Integer> queue = new LinkedList<>(); // Hàng đợi cho BFS

            visited[start] = true; // Đánh dấu đỉnh bắt đầu đã duyệt
            queue.offer(start); // Thêm đỉnh bắt đầu vào hàng đợi

            while (!queue.isEmpty()) {
                int u = queue.poll(); // Lấy đỉnh đầu tiên ra khỏi hàng đợi

                // Duyệt qua các đỉnh kề với đỉnh u
                for (int v : adj.get(u)) {
                    if (!visited[v]) {
                        visited[v] = true; // Đánh dấu đỉnh v đã duyệt
                        queue.offer(v); // Thêm đỉnh v vào hàng đợi
                        spanningTree.add(new int[]{u, v}); // Thêm cạnh (u, v) vào cây khung
                    }
                }
            }

            return spanningTree;
        }
    }

    public static void main(String[] args) {
        // Khởi tạo đồ thị
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);

        // Xây dựng cây khung bắt đầu từ đỉnh 0
        List<int[]> spanningTree = g.bfsSpanningTree(0);

        // In ra các cạnh của cây khung
        System.out.println("Các cạnh của cây khung:");
        for (int[] edge : spanningTree) {
            System.out.println(edge[0] + " - " + edge[1]);
        }
    }
}