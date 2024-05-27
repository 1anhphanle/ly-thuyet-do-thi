package GraphConversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphConversion {
    static class Edge {
        int source;
        int destination;
        public Edge(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    // Chuyển đổi từ ma trận kề sang danh sách cạnh
    public static List<Edge> matrixToEdgeList(int[][] matrix) {
        List<Edge> edgeList = new ArrayList<>();
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // Chỉ xét nửa trên của ma trận (đồ thị vô hướng)
                if (matrix[i][j] == 1) {
                    edgeList.add(new Edge(i, j));
                }
            }
        }
        return edgeList;
    }

    // Chuyển đổi từ ma trận kề sang danh sách kề
    public static List<List<Integer>> matrixToAdjacencyList(int[][] matrix) {
        List<List<Integer>> adjList = new ArrayList<>();
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    neighbors.add(j);
                }
            }
            adjList.add(neighbors);
        }
        return adjList;
    }

    // Chuyển đổi từ danh sách cạnh sang ma trận kề
    public static int[][] edgeListToMatrix(List<Edge> edgeList, int n) {
        int[][] matrix = new int[n][n];
        for (Edge edge : edgeList) {
            matrix[edge.source][edge.destination] = 1;
            matrix[edge.destination][edge.source] = 1; // Đồ thị vô hướng
        }
        return matrix;
    }

    // Chuyển đổi từ danh sách cạnh sang danh sách kề
    public static List<List<Integer>> edgeListToAdjacencyList(List<Edge> edgeList, int n) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (Edge edge : edgeList) {
            adjList.get(edge.source).add(edge.destination);
            adjList.get(edge.destination).add(edge.source); // Đồ thị vô hướng
        }
        return adjList;
    }

    // Đọc ma trận kề từ file
    public static int[][] readMatrixFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        return matrix;
    }

    // Đọc danh sách cạnh từ file
    public static List<Edge> readEdgeListFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            edgeList.add(new Edge(source, destination));
        }
        scanner.close();
        return edgeList;
    }

    // Ghi ma trận kề vào file
    public static void writeMatrixToFile(int[][] matrix, String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(filename));
        int n = matrix.length;
        writer.println(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                writer.print(matrix[i][j] + " ");
            }
            writer.println();
        }
        writer.close();
    }

    // Ghi danh sách cạnh vào file
    public static void writeEdgeListToFile(List<Edge> edgeList, int n, String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(filename));
        writer.println(n + " " + edgeList.size());
        for (Edge edge : edgeList) {
            writer.println(edge.source + " " + edge.destination);
        }
        writer.close();
    }

    // Ghi danh sách kề vào file
    public static void writeAdjacencyListToFile(List<List<Integer>> adjList, String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(filename));
        int n = adjList.size();
        writer.println(n);
        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = adjList.get(i);
            writer.print((neighbors.size() + i) + " "); // Vị trí kết thúc của đoạn
            for (int neighbor : neighbors) {
                writer.print(neighbor + " ");
            }
            writer.println();
        }
        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Chuyển đổi từ ma trận kề sang danh sách cạnh và danh sách kề
        int[][] matrix = readMatrixFromFile("C:\\Workspace\\lythuyetdothi\\src\\GraphConversion\\matrix.txt");
        List<Edge> edgeListFromMatrix = matrixToEdgeList(matrix);
        List<List<Integer>> adjListFromMatrix = matrixToAdjacencyList(matrix);

        writeEdgeListToFile(edgeListFromMatrix, matrix.length, "C:\\Workspace\\lythuyetdothi\\src\\GraphConversion\\edgelist_from_matrix.txt");
        writeAdjacencyListToFile(adjListFromMatrix, "C:\\Workspace\\lythuyetdothi\\src\\GraphConversion\\adjlist_from_matrix.txt");

        // Chuyển đổi từ danh sách cạnh sang ma trận kề và danh sách kề
        List<Edge> edgeList = readEdgeListFromFile("C:\\Workspace\\lythuyetdothi\\src\\GraphConversion\\edgelist.txt");
        int n = edgeListToMatrix(edgeList, 6).length; // Lấy số đỉnh từ ma trận
        int[][] matrixFromEdgeList = edgeListToMatrix(edgeList, n);
        List<List<Integer>> adjListFromEdgeList = edgeListToAdjacencyList(edgeList, n);

        writeMatrixToFile(matrixFromEdgeList, "C:\\Workspace\\lythuyetdothi\\src\\GraphConversion\\matrix_from_edgelist.txt");
        writeAdjacencyListToFile(adjListFromEdgeList, "C:\\Workspace\\lythuyetdothi\\src\\GraphConversion\\adjlist_from_edgelist.txt");
    }
}