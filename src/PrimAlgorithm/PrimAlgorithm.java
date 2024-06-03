package PrimAlgorithm;

import java.util.Arrays;

public class PrimAlgorithm {

    private static final int INF = Integer.MAX_VALUE;

    public int[] primMST(int[][] graph) {
        int V = graph.length; // Số đỉnh
        int[] parent = new int[V]; // Mảng lưu đỉnh cha
        int[] key = new int[V]; // Mảng lưu trọng số nhỏ nhất
        boolean[] mstSet = new boolean[V]; // Mảng đánh dấu đỉnh đã thuộc MST

        // Khởi tạo key với giá trị vô cực và mstSet là false
        Arrays.fill(key, INF);
        Arrays.fill(mstSet, false);

        // Chọn đỉnh bắt đầu là đỉnh 0
        key[0] = 0;
        parent[0] = -1; // Đỉnh gốc

        // Lặp lại (V-1) lần
        for (int count = 0; count < V - 1; count++) {
            // Tìm đỉnh u chưa thuộc MST có key nhỏ nhất
            int u = minKey(key, mstSet);

            // Thêm đỉnh u vào MST
            mstSet[u] = true;

            // Cập nhật key và parent cho các đỉnh kề với u
            for (int v = 0; v < V; v++) {
                // Cập nhật nếu:
                // - Có cạnh nối u và v
                // - v chưa thuộc MST
                // - Trọng số cạnh (u, v) nhỏ hơn key[v] hiện tại
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        return parent; // Trả về mảng parent biểu diễn MST
    }

    // Tìm đỉnh có key nhỏ nhất mà chưa thuộc MST
    private int minKey(int[] key, boolean[] mstSet) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public static void main(String[] args) {
        PrimAlgorithm prim = new PrimAlgorithm();
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        int[] parent = prim.primMST(graph);

        System.out.println("Cạnh của cây khung nhỏ nhất:");
        for (int i = 1; i < parent.length; i++) {
            System.out.println(parent[i] + " - " + i + "\tTrọng số: " + graph[i][parent[i]]);
        }
    }
}
