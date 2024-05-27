package EulerianCycle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class EulerianCycle {

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

        public List<Integer> findEulerianCycle(int startNode) {
            List<List<Integer>> adjList = matrixToAdjList(adjacencyMatrix);
            Stack<Integer> stack = new Stack<>();
            List<Integer> eulerianCycle = new ArrayList<>();

            stack.push(startNode);
            while (!stack.isEmpty()) {
                int currentNode = stack.peek();
                if (adjList.get(currentNode).isEmpty()) {
                    stack.pop();
                    eulerianCycle.add(0, currentNode); // Thêm vào đầu danh sách
                } else {
                    int neighbor = adjList.get(currentNode).get(0);
                    stack.push(neighbor);
                    adjList.get(currentNode).remove(0);
                    adjList.get(neighbor).remove((Integer) currentNode); // Xóa cạnh (vô hướng)
                }
            }

            return eulerianCycle;
        }

        // Chuyển đổi từ ma trận kề sang danh sách kề
        private List<List<Integer>> matrixToAdjList(int[][] matrix) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                List<Integer> neighbors = new ArrayList<>();
                for (int j = 0; j < numVertices; j++) {
                    if (matrix[i][j] == 1) {
                        neighbors.add(j);
                    }
                }
                adjList.add(neighbors);
            }
            return adjList;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Workspace\\lythuyetdothi\\src\\EulerianCycle\\matrix.txt"));
        int numVertices = scanner.nextInt();
        Graph graph = new Graph(numVertices);

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                int value = scanner.nextInt();
                if (value == 1) {
                    graph.addEdge(i, j);
                }
            }
        }
        scanner.close();

        // Tìm chu trình Euler bắt đầu từ đỉnh 0
        List<Integer> cycle = graph.findEulerianCycle(0);
        System.out.println("Chu trình Euler: " + cycle);
    }
}