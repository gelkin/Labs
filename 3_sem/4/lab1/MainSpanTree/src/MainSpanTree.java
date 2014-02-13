import java.io.*;
import java.util.StringTokenizer;

public class MainSpanTree {
    FastScanner in;
    PrintWriter out;

    public static final int INF = 1000000000;

    int[] verticesX;
    int[] verticesY;

    boolean[] used;

    // Edge with minimal weight
    int[] minEdge;

    // Second vertex of edge in "minEdge"
    int[] secVertex;

    // Sum of weights in MST
    double res = 0.0;

    public void solve() throws IOException {
        int n = in.nextInt();

        verticesX = new int[n];
        verticesY = new int[n];
        int x;
        int y;
        for (int i = 0; i < n; i++) {
            x = in.nextInt();
            y = in.nextInt();
            verticesX[i] = x;
            verticesY[i] = y;
        }

        /*
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = distance(i, j);
            }
        }
        */

        used = new boolean[n];
        minEdge = new int[n];
        secVertex = new int[n];
        for (int i = 0; i < n; i++) {
            minEdge[i] = INF;
            secVertex[i] = -1;
        }

        minEdge[0] = 0;
        int tmp;
        for (int i = 0; i < n; i++) {
            int v = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && (v == -1 || minEdge[j] < minEdge[v]))
                    v = j;
            }

            if (minEdge[v] == INF) {
                out.print("NO MST! =( Sad but true...");
                return;
            }

            used[v] = true;
            if (secVertex[v] != -1) {
                res += Math.sqrt(minEdge[v]);
            }

            for (int to = 0; to < n; to++) {
                
                tmp = distance(v, to);

                if (tmp < minEdge[to]) {
                    minEdge[to] = tmp;
                    secVertex[to] = v;
                }
            }
        }

        out.print(res);

    }

    int distance(int i, int j) {
        return ((verticesX[i] - verticesX[j]) * (verticesX[i] - verticesX[j]) +
                (verticesY[i] - verticesY[j]) * (verticesY[i] - verticesY[j]));
    }

    public void run() {
        try {
            in = new FastScanner(new File("spantree.in"));
            out = new PrintWriter(new File("spantree.out"));

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
        new MainSpanTree().run();
    }
}



