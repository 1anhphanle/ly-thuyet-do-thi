package GraphTraversal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversal {

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

        public void printMatrix() {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        public void dfs(int startNode) {
            boolean[] visited = new boolean[numVertices];
            Stack<Integer> stack = new Stack<>();

            stack.push(startNode);
            visited[startNode] = true;

            System.out.print("DFS từ đỉnh " + startNode + ": ");
            while (!stack.isEmpty()) {
                int currentNode = stack.pop();
                System.out.print(currentNode + " ");

                for (int i = 0; i < numVertices; i++) {
                    if (adjacencyMatrix[currentNode][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        stack.push(i);
                    }
                }
            }
            System.out.println();
        }

        public void bfs(int startNode) {
            boolean[] visited = new boolean[numVertices];
            Queue<Integer> queue = new LinkedList<>();

            queue.offer(startNode);
            visited[startNode] = true;

            System.out.print("BFS từ đỉnh " + startNode + ": ");
            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                System.out.print(currentNode + " ");

                for (int i = 0; i < numVertices; i++) {
                    if (adjacencyMatrix[currentNode][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        queue.offer(i);
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Workspace\\lythuyetdothi\\src\\GraphTraversal\\matrix.txt"));
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

        System.out.println("Ma trận kề:");
        graph.printMatrix();
        System.out.println();

        graph.dfs(0); // Duyệt DFS từ đỉnh 0
        graph.bfs(0); // Duyệt BFS từ đỉnh 0
    }
}