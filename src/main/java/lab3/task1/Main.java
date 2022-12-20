package lab3.task1;

import lab3.task1.bipartition.BipartiteGraph;
import lab3.task1.components.ConnectedComponents;
import lab3.task1.eulerian.EulerianCycle;
import lab3.task1.graph.Graph;
import lab3.task1.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n======= Connected components ========");
        findConnectedComponents();

        System.out.println("\n======= Eulerian cycle ========");
        eulerCycle();

        System.out.println("\n======= Bipartite graph ========");
        bipartiteGraph();

    }

    public static void findConnectedComponents() {
        int vertexCount = 7;
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            vertices.add(new Vertex(i));
        }
        Graph graph = new Graph(vertices);
        graph.addAdjacentVertices(vertices.get(0), 1, 2);
        graph.addAdjacentVertices(vertices.get(1), 0, 2);
        graph.addAdjacentVertices(vertices.get(2), 0, 1);
        graph.addAdjacentVertices(vertices.get(3), 5);
        graph.addAdjacentVertices(vertices.get(4), 5);
        graph.addAdjacentVertices(vertices.get(5), 3, 4);
        System.out.println("\nGiven graph: \n" + graph);
        List<Graph> components = ConnectedComponents.findConnectedComponents(graph);
        System.out.println("\nConnected components: ");
        components.forEach(System.out::println);

        System.out.println("\nIs this graph Eulerian? " + EulerianCycle.isEulerian(graph));
    }

    public static void eulerCycle() {
        int vertexCount = 8;
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            vertices.add(new Vertex(i));
        }
        Graph graph = new Graph(vertices);
        graph.addAdjacentVertices(vertices.get(0), 1, 2);
        graph.addAdjacentVertices(vertices.get(1), 0, 2, 7, 6);
        graph.addAdjacentVertices(vertices.get(2), 0, 1, 3, 4);
        graph.addAdjacentVertices(vertices.get(3), 2, 4);
        graph.addAdjacentVertices(vertices.get(4), 2, 3, 6, 5);
        graph.addAdjacentVertices(vertices.get(5), 4, 6);
        graph.addAdjacentVertices(vertices.get(6), 1, 7, 4, 5);
        graph.addAdjacentVertices(vertices.get(7), 1, 6);
        System.out.println("\nGiven graph: \n" + graph);
        System.out.println("\nIs this graph Eulerian? " + EulerianCycle.isEulerian(graph));
        System.out.println("Simple loops in this graph: " + EulerianCycle.findSimpleLoops(graph));
        System.out.println("Eulerian cycle in this graph: " + EulerianCycle.findEulerCycle(graph));

        List<Graph> components = ConnectedComponents.findConnectedComponents(graph);
        System.out.println("\nConnected components: ");
        components.forEach(System.out::println);
        System.out.println("\nIs this graph bipartite? " + BipartiteGraph.isBipartiteGraph(graph));
    }

    public static void bipartiteGraph() {
        int vertexCount = 4;
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            vertices.add(new Vertex(i));
        }
        Graph graph = new Graph(vertices);
        graph.addAdjacentVertices(vertices.get(0), 1, 3);
        graph.addAdjacentVertices(vertices.get(1), 0, 2);
        graph.addAdjacentVertices(vertices.get(2), 1, 3);
        graph.addAdjacentVertices(vertices.get(3), 0, 2);
        System.out.println("\nGiven graph: \n" + graph);
        System.out.println("\nIs this graph bipartite? " + BipartiteGraph.isBipartiteGraph(graph));
        System.out.println("Parts of this graph: " + BipartiteGraph.splitIntoParts(graph));
    }

}

