import java.util.*;
import java.io.*;

public class MainFlow {
    FastScanner in;
    PrintWriter out;

    public static final int INF = 100000;

    int capacities[][];
    int flows[][];
    int flow = 0;

    int n; // number of vertices
    int m; // number of edges
    int s; // start vertex
    int t; // end vertex
    int dist[];
    int ptr[];
    int queue[];

    boolean bfs() {
        int qh = 0;
        int qt = 0;

        queue[qt] = s;
        qt++;

        Arrays.fill(dist, -1);
        dist[s] = 0;

        while (qh < qt) {
            int v = queue[qh++];
            for (int to = 0; to < n; to++) {
                if (dist[to] == -1 && flows[v][to] < capacities[v][to]) {
                    queue[qt++] = to;
                    dist[to] = dist[v] + 1;
                }
            }
        }
        return (dist[t] != -1);
    }

    int dfs (int v, int flow) {
        if (flow == 0) {
            return 0;
        }

        if (v == t){
            return flow;
        }

        for (int to = ptr[v]; to < n; to++) {
            if (dist[to] != dist[v] + 1) {
                continue;
            }

            int pushed = dfs (to, Math.min(flow, capacities[v][to] - flows[v][to]));
            if (pushed > 0) {
                flows[v][to] += pushed;
                flows[to][v] -= pushed;
                return pushed;
            }
        }
        return 0;
    }

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();


        capacities = new int[n][n];
        flows = new int[n][n];

        s = 0;
        t = n - 1;
        dist = new int[n];
        ptr = new int[n];
        queue = new int[n];

        int x;
        int y;
        int z;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();

            capacities[x - 1][y - 1] = z;
        }

        while (true) {
            if (!bfs())  {
                break;
            }
            Arrays.fill(ptr, 0);
            // memset (ptr, 0, n * sizeof ptr[0]);
            int pushed = 0;
            while ((pushed = dfs (s, INF)) > 0) {
                flow += pushed;
            }
        }

        out.print(flow);
    }

    public void run() {
        try {
            in = new FastScanner(new File("maxflow.in"));
            out = new PrintWriter(new File("maxflow.out"));

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
        new MainFlow().run();
    }
}



