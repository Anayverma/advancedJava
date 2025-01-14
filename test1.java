import java.io.*;
import java.util.*;

public class test1 {
    static class Edge implements Comparable<Edge> {
        int from, to;
        long cost;
        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        public int compareTo(Edge other) {
            return Long.compare(this.cost, other.cost);
        }
    }
    static class DisjointSet {
        int[] parent;
        int[] rank;
        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;
            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());            
            long[] positions = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                positions[i] = Long.parseLong(st.nextToken());
            }
            long[] costs = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                costs[i] = Long.parseLong(st.nextToken());
            }
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    long dist = Math.abs(positions[i] - positions[j]);
                    long minCost = Math.min(costs[i], costs[j]);
                    edges.add(new Edge(i, j, dist * minCost));
                }
            }
            Collections.sort(edges);
            DisjointSet ds = new DisjointSet(n);
            long totalCost = 0;
            int edgesUsed = 0;
            for (Edge edge : edges) {
                if (ds.find(edge.from) != ds.find(edge.to)) {
                    ds.union(edge.from, edge.to);
                    totalCost += edge.cost;
                    edgesUsed++;
                    
                    if (edgesUsed == n - 1) {
                        break;
                    }
                }
            }
            pw.println(totalCost);
        }
        pw.close();
        br.close();
    }
}