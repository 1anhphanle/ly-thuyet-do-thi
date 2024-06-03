package DFSSpanningTree;

import java.util.ArrayList;
import java.util.List;

public class DFSSpanningTree {

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

        // Thuật toán DFS để xây dựng cây khung
        public List<int[]> dfsSpanningTree(int start) {
            List<int[]> spanningTree = new ArrayList<>(); // Danh sách các cạnh của cây khung
            boolean[] visited = new boolean[V]; // Mảng đánh dấu các đỉnh đã duyệt

            dfsUtil(start, visited, spanningTree);

            return spanningTree;
        }

        // Hàm đệ quy cho DFS
        private void dfsUtil(int u, boolean[] visited, List<int[]> spanningTree) {
            visited[u] = true; // Đánh dấu đỉnh u đã duyệt

            // Duyệt qua các đỉnh kề với đỉnh u
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    spanningTree.add(new int[]{u, v}); // Thêm cạnh (u, v) vào cây khung
                    dfsUtil(v, visited, spanningTree); // Gọi đệ quy cho đỉnh v
                }
            }
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
        List<int[]> spanningTree = g.dfsSpanningTree(0);

        // In ra các cạnh của cây khung
        System.out.println("Các cạnh của cây khung:");
        for (int[] edge : spanningTree) {
            System.out.println(edge[0] + " - " + edge[1]);
        }
    }
}
