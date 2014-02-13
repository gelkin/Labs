import java.util.*;
import java.io.*;

public class Minimax {
    FastScanner in;
    PrintWriter out;

    int[][] graph;
    Edge[] edges;

    boolean[] used;
    int[] mt;

    int minEdge;
    int minEdgeI;
    int minEdgeJ;

    int n; // number of vertices

    boolean tryKuhn (int v) {
        if (used[v])  return false;
        used[v] = true;
        for (int to = 0; to < n; to++) {
            if ((graph[v][to] >= minEdge) && (mt[to] == -1 || tryKuhn(mt[to]))) {
                mt[to] = v;
                return true;
            }
        }
        return false;
    }

    public void solve() throws IOException {
        n = in.nextInt();

        graph = new int[n][n];
        edges = new Edge[n * n];
        used = new boolean[n];
        mt = new int[n];

        int x;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x = in.nextInt();
                graph[i][j] = x;
                edges[i * n + j] = new Edge(x, i, j);
            }
        }

        Arrays.sort(edges);

        /*
        for (int i = 0; i < n * n; i++) {
            System.out.println((edges[i].left + 1) + " " + (edges[i].right + 1) + " " + edges[i].weight);
        }
        System.out.print("\n");
        */

        int neededEdge = 0;
        minEdge = 0;
        minEdgeI = 0;
        minEdgeJ = 0;

        int m;
        int l = 0;
        int r = n * n;

        mainLoop:
        while (l < r - 1) {
            m = l + (r - l) / 2;

            // System.out.println((edges[m].left + 1) + " " + (edges[m].right + 1) + " " + edges[m].weight);

            Arrays.fill(mt, -1);

            // mt[edges[m].right] = edges[m].left; // put needed edge to matching
            minEdge = edges[m].weight;

            for (int j = 0; j < n; j++) {
                Arrays.fill(used, false);
                tryKuhn(j);
            }

            // TODO: WHY WILL THE NEEDED EDGE BE IN MATCHING AFTER TRYKUHN?
            for (int i = 0; i < n; i++) {
                if (mt[i] == -1) {
                    r = m; // if we didn't find full matching, lets try find it in right part
                    continue mainLoop;
                }
            }
            neededEdge = edges[m].weight;
            l = m; // if we found full matching, lets try look for lower one in left part
            // System.out.println("l = " + l + "; r = " + r);
        }

        out.print(neededEdge);

    }

    // Edge in bipartite graph
    public class Edge implements Comparable<Edge> {
        int weight;
        int left;
        int right;

        public Edge(int weight, int left, int right) {
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Edge e) {
            if (weight < e.weight) {
                return -1;
            } else if (weight > e.weight) {
                return 1;
            }
            return 0;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("minimax.in"));
            out = new PrintWriter(new File("minimax.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        boolean Check;
        String line;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        boolean getCheck() {
            return Check;
        }

        void setCheck(boolean b) {
            Check = b;
        }

        String next() {
            try {
                if (st == null || !st.hasMoreTokens()) {
                    if ((line = br.readLine()) != null) {
                        st = new StringTokenizer(line);
                        setCheck(true);
                    } else {
                        setCheck(false);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (getCheck()) {
                return st.nextToken();
            } else {
                return "";
            }
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        new Minimax().run();
    }
}
