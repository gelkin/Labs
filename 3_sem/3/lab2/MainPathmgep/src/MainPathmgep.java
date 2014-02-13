import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainPathmgep {
    FastScanner in;
    PrintWriter out;

    public static final double INF = Double.MAX_VALUE;

    IntPair[] vertices;
    double[][] graph;
    boolean[] used;
    // Edge with minimal weight
    double[] minEdge;
    // Second vertex of edge in "minEdge"
    int[] secVertex;

    int s = 0; // start vertex

    LinkedList<Integer> queue;

    public void solve() throws IOException {
        int n = in.nextInt();

        vertices = new IntPair[n];
        int x;
        int y;
        for (int i = 0; i < n; i++) {
            x = in.nextInt();
            y = in.nextInt();
            vertices[i] = new IntPair(x, y);
        }

        graph = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Calculate weights of edges between vertices
                graph[i][j] = Math.sqrt(Math.pow(vertices[i].first - vertices[j].first, 2) +
                                        Math.pow(vertices[i].second - vertices[j].second, 2));
            }
        }

        minEdge = new double[n];
        for (int i = 0; i < n; i++) {
            minEdge[i] = INF;
        }

        secVertex = new int[n];
        for (int i = 0; i < n; i++) {
            secVertex[i] = -1;
        }

        minEdge[0] = 0;
        Comparator comparator = new IntPairComparator();
        PriorityQueue<IntPair> queue = new PriorityQueue<IntPair>(0, comparator);
        queue.add(new IntPair(0, 0));

        for (int i = 0; i < n; i++) {
            int v = -1;
            for (int j = 0; j <n; ++j) {
                if (!used[j] && (v == -1 || min_e[j] < min_e[v]))
                    v = j;
            if (min_e[v] == INF) {
                cout << "No MST!";
                exit(0);
            }

            used[v] = true;
            if (sel_e[v] != -1)
                cout << v << " " << sel_e[v] << endl;

            for (int to=0; to<n; ++to)
                if (g[v][to] < min_e[to]) {
                    min_e[to] = g[v][to];
                    sel_e[to] = v;
                }
        }

    }

    class IntPair {
        int first;
        int second;

        IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    class IntPairComparator implements Comparator<IntPair> {
        public int compare(IntPair x, IntPair y) {
            if (x.second < y.second) {
                return -1;
            }
            if (x.second > y.second) {
                return 1;
            }
            return 0;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("pathmgep.in"));
            out = new PrintWriter(new File("pathmgep.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;
        private String line;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        String next() {
            try {
                if (st == null || !st.hasMoreTokens()) {
                    if ((line = br.readLine()) != null) {
                        st = new StringTokenizer(line);
                    } else {
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        new MainPathmgep().run();
    }
}


