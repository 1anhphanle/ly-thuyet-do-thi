package HamiltonianCycles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HamiltonianCycles {

    static class Graph {
        private int numVertices;
        private int[][] adjacencyMatrix;

        public Graph(int numVertices) {
            this.numVertices = numVertices;
            this.adjacencyMatrix = new int[numVertices][numVertices];
        }

        public void addEdge(int source, int destination) {
            adjacencyMatrix[source][destination] = 1;
            adjacencyMatrix[destination][source] = 1; // Đồ thị vô hướng
        }

        public void findHamiltonianCycles(int startNode) {
            List<Integer> path = new ArrayList<>();
            path.add(startNode);
            boolean[] visited = new boolean[numVertices];
            visited[startNode] = true;

            System.out.println("Tất cả các chu trình Hamilton bắt đầu từ đỉnh " + startNode + ":");
            findHamiltonianCyclesUtil(startNode, path, visited);
        }

        private void findHamiltonianCyclesUtil(int currentNode, List<Integer> path, boolean[] visited) {
            if (path.size() == numVertices) {
                // Kiểm tra xem có cạnh nối đỉnh cuối cùng với đỉnh bắt đầu không
                if (adjacencyMatrix[currentNode][path.get(0)] == 1) {
                    System.out.println(path);
                }
                return;
            }

            for (int v = 0; v < numVertices; v++) {
                if (isSafe(v, currentNode, path, visited)) {
                    path.add(v);
                    visited[v] = true;
                    findHamiltonianCyclesUtil(v, path, visited);
                    visited[v] = false;
                    path.remove(path.size() - 1);
                }
            }
        }

        private boolean isSafe(int v, int currentNode, List<Integer> path, boolean[] visited) {
            return adjacencyMatrix[currentNode][v] == 1 && !visited[v];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Workspace\\lythuyetdothi\\src\\HamiltonianCycles\\matrix.txt"));
        int numVertices = scanner.nextInt();
        Graph graph = new Graph(numVertices);

        // Đọc ma trận kề từ file
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                int value = scanner.nextInt();
                if (value == 1) {
                    graph.addEdge(i, j);
                }
            }
        }
        scanner.close();

        // Tìm tất cả các chu trình Hamilton bắt đầu từ đỉnh 0
        graph.findHamiltonianCycles(0);
    }
}