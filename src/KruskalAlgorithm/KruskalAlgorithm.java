package KruskalAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static class Subset {
        int parent, rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    // Tìm nút đại diện của tập hợp chứa nút u
    private int find(Subset[] subsets, int u) {
        if (subsets[u].parent != u) {
            subsets[u].parent = find(subsets, subsets[u].parent);
        }
        return subsets[u].parent;
    }

    // Hợp nhất hai tập hợp chứa nút u và v
    private void union(Subset[] subsets, int u, int v) {
        int uRoot = find(subsets, u);
        int vRoot = find(subsets, v);

        if (subsets[uRoot].rank < subsets[vRoot].rank) {
            subsets[uRoot].parent = vRoot;
        } else if (subsets[uRoot].rank > subsets[vRoot].rank) {
            subsets[vRoot].parent = uRoot;
        } else {
            subsets[vRoot].parent = uRoot;
            subsets[uRoot].rank++;
        }
    }

    // Thuật toán Kruskal
    public List<Edge> kruskalMST(List<Edge> edges, int V) {
        List<Edge> result = new ArrayList<>();
        Subset[] subsets = new Subset[V];

        // Khởi tạo các tập hợp con (mỗi đỉnh là một tập hợp)
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Sắp xếp các cạnh theo trọng số tăng dần
        Collections.sort(edges);

        int e = 0; // Index của cạnh đang xét
        int i = 0; // Index của cạnh được thêm vào MST

        // Thêm các cạnh vào MST cho đến khi có đủ V-1 cạnh
        while (e < V - 1 && i < edges.size()) {
            Edge nextEdge = edges.get(i++);
            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            // Nếu thêm cạnh này không tạo chu trình, thêm nó vào MST
            if (x != y) {
                result.add(nextEdge);
                union(subsets, x, y);
                e++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int V = 7;  // Số đỉnh
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 251));
        edges.add(new Edge(0, 3, 249));
        edges.add(new Edge(0, 6, 274));
        edges.add(new Edge(1, 3, 245));
        edges.add(new Edge(1, 2, 255));
        edges.add(new Edge(3, 6, 251));
        edges.add(new Edge(3, 2, 253));
        edges.add(new Edge(6, 2, 264));
        edges.add(new Edge(6, 5, 242));
        edges.add(new Edge(2, 5, 256));
        edges.add(new Edge(2, 4, 259));
        edges.add(new Edge(4, 5, 262));

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        List<Edge> mst = kruskal.kruskalMST(edges, V);

        System.out.println("Cạnh của cây khung nhỏ nhất:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
        }
    }
}