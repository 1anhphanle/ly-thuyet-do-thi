package GraphRepresentation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphRepresentation {
    static class Edge {
        int source;
        int destination;

        public Edge(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    // 1. Biểu diễn bằng Ma trận kề
    public static void adjacencyMatrixRepresentation(List<Edge> edges, int numVertices) {
        int[][] adjacencyMatrix = new int[numVertices][numVertices];

        for (Edge edge : edges) {
            adjacencyMatrix[edge.source][edge.destination] = 1;
            adjacencyMatrix[edge.destination][edge.source] = 1; // Đối với đồ thị vô hướng
        }

        System.out.println("Ma trận kề:");
        printMatrix(adjacencyMatrix);
    }

    // 2. Biểu diễn bằng Danh sách kề
    public static void adjacencyListRepresentation(List<Edge> edges, int numVertices) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new LinkedList<>());
        }

        for (Edge edge : edges) {
            adjacencyList.get(edge.source).add(edge.destination);
            adjacencyList.get(edge.destination).add(edge.source); // Đối với đồ thị vô hướng
        }

        System.out.println("\nDanh sách kề:");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (int neighbor : adjacencyList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    // 3. Biểu diễn bằng Danh sách cạnh
    public static void edgeListRepresentation(List<Edge> edges) {
        System.out.println("\nDanh sách cạnh:");
        for (Edge edge : edges) {
            System.out.println(edge.source + " - " + edge.destination);
        }
    }

    // Hàm in ma trận
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numVertices = 6; // Số đỉnh của đồ thị
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(1, 4));
        edges.add(new Edge(2, 4));
        edges.add(new Edge(3, 5));
        edges.add(new Edge(4, 5));

        adjacencyMatrixRepresentation(edges, numVertices);
        adjacencyListRepresentation(edges, numVertices);
        edgeListRepresentation(edges);
    }
}
