import java.util.*;
import java.io.*;

public class MainCirc {
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

        n += 2; // add s, t
        capacities = new int[n][n];
        flows = new int[n][n];

        LinkedList<FourInts> edges = new LinkedList<FourInts>();

        s = 0;
        t = n - 1;
        dist = new int[n];
        ptr = new int[n];
        queue = new int[n];

        int x;
        int y;
        int minCap;
        int maxCap;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            minCap = in.nextInt();
            maxCap = in.nextInt();

            edges.add(new FourInts(x, y, minCap, cnt));

            capacities[0][x] = minCap;
            capacities[y][n - 1] = minCap;
            capacities[x][y] = maxCap - minCap;
        }

        System.out.println("");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(capacities[i][j]);
            }
            System.out.println("");
        }

        while (true) {
            if (!bfs())  {
                break;
            }
            Arrays.fill(ptr, 0);
            int pushed;
            while ((pushed = dfs (s, INF)) > 0) {
                flow += pushed;
            }
        }

        System.out.print("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(flows[i][j]);
            }
            System.out.println("");
        }


        for (int i = 0; i < n; i++) {
            if ((capacities[0][i] - flows[0][i]) > 0) {
                out.print("NO");
                return;
            }
        }

        out.print("YES\n");
        Iterator<FourInts> it = edges.listIterator();
        FourInts tmp;
        while (it.hasNext()) {
            tmp = it.next();
            out.print((flows[tmp.from][tmp.to] + tmp.minCap) + "\n");
        }
    }

    class FourInts {
        int from;
        int to;
        int minCap;
        int number;

        public FourInts(int from,
                int to,
                int minCap,
                int number) {
            this.from = from;
            this.to = to;
            this.minCap = minCap;
            this.number = number;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("circulation.in"));
            out = new PrintWriter(new File("circulation.out"));

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
        new MainCirc().run();
    }
}



