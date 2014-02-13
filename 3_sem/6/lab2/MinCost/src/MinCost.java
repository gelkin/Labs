import java.util.*;
import java.io.*;

public class MinCost {
    FastScanner in;
    PrintWriter out;

    public static final int INF = Integer.MAX_VALUE;

    int n; // number of vertices
    int m; // number of edges

    int s; // start vertex
    int t; // to vertex

    LinkedList<Edge>[] graph;

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();

        s = 0;
        t = n - 1;


        // TODO: LinkedList -> ArrayList (?)
        graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<Edge>();
        }

        int from;
        int to;
        int cap;
        int cost1;
        for (int i = 0; i < m; i++) {

            from = in.nextInt();
            to = in.nextInt();
            cap = in.nextInt();
            cost1 = in.nextInt();

            Edge a = new Edge(to - 1, cap, cost1, 0);
            Edge b = new Edge(from - 1, 0, -cost1, 0);
            a.back = b;
            b.back = a;
            graph[from - 1].add(a);
            graph[to - 1].add(b);
        }

        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                System.out.print(i + " " + j + " " + e.cap + " " + e.flow + "\n");
            }
        }*/

        // int flow = 0;
        long cost = 0;
        while (true) {
            int[] id = new int[n]; // 0 <-> dist[i] == INF; 1 <-> i in the queue; 2 <-> dist[i] < INF

            int[] dist = new int[n];
            Arrays.fill(dist, INF);

            int[] queue = new int[n];
            int[] prev = new int[n]; // previous vertex
            Edge[] prevEdge = new Edge[n]; // edge from previous vertex to current
            int qh = 0; // pointer in queue on HEAD-element
            int qt = 0; // pointer in queue on after TAIL-element

            queue[qt++] = s;
            dist[s] = 0;
            while (qh != qt) {
                int v = queue[qh++];
                id[v] = 2;
                if (qh == n) {
                    qh = 0;
                }

                // Start iterating by edges from vertex "v"
                Iterator<Edge> it = graph[v].listIterator();
                while (it.hasNext()) {
                    Edge edge = it.next();
                    if (edge.flow < edge.cap && (dist[v] + edge.cost < dist[edge.to])) {
                        // System.out.println((v + 1) + "->" + (edge.to + 1));
                        dist[edge.to] = dist[v] + edge.cost;
                        if (id[edge.to] == 0) {
                            queue[qt++] = edge.to;
                            if (qt == n)  {
                                qt = 0;
                            }
                        } else if (id[edge.to] == 2) {
                            if (--qh == -1)  {
                                qh = n - 1;
                            }
                            queue[qh] = edge.to;
                        }
                        id[edge.to] = 1;
                        prev[edge.to] = v;
                        prevEdge[edge.to] = edge;
                    }
                }
            }

            if (dist[t] == INF)  break;

            // Lets update flow
            int addFlow = INF;
            for (int v = t; v != s; v = prev[v]) {
                // int pv = prev[v];
                // int pr = prevEdgeOLD[v];
                // Edge x = graph[pv].get(pr);
                Edge x = prevEdge[v];
                addFlow = Math.min(addFlow, x.cap - x.flow);
            }

            // Lets update cost
            for (int v = t; v != s; v = prev[v]) {
                // int pv = prev[v];
                // int pr = prevEdge[v];
                // Edge e = graph[pv].get(pr);
                Edge e = prevEdge[v];
                e.flow += addFlow;
                e.back.flow -= addFlow;
                cost += ((long) e.cost) * ((long) addFlow);
            }

            // flow += addFlow;
        }

        out.print(cost);
        // System.out.println(flow);
    }

    class Edge {
        int to;
        int cap;
        int cost;
        int flow;

        Edge back;

        public Edge(int to, int cap, int cost, int flow, Edge back) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.flow = flow;
            this.back = back;
        }

        public Edge(int to, int cap, int cost, int flow) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.flow = flow;
            this.back = null;
        }

        public boolean isEqual(Edge e) {
            if (flow == e.flow && cost == e.cost &&
                cap == e.cap && to == e.to) {
                return true;
            }
            return false;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("mincost.in"));
            out = new PrintWriter(new File("mincost.out"));

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
        new MinCost().run();
    }
}




