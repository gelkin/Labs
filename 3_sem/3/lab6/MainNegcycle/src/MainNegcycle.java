import java.io.*;
import java.util.*;

public class MainNegcycle {
    FastScanner in;
    PrintWriter out;

    public static final int INF = 1000000000;

    ArrayList<Edge> edges;

    LinkedList<Integer> queue;

    public void solve() throws IOException {
        int n = in.nextInt();

        // TODO!!!
        // Wrong Answer
        edges = new ArrayList<Edge>();
        queue = new LinkedList<Integer>();

        int[] d = new int[n];
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = INF;
            p[i] = -1;
        }

        int m = 0;
        for (int i = 0; i < n; i++) {
            // System.out.print("\n");
            for (int j = 0; (j < n); j++) {
                if (j == i) {
                    in.nextInt();
                    continue;
                }
                Edge edge = new Edge();
                edge.cost = in.nextInt();
                if (edge.cost == INF) continue;
                edge.a = i;
                edge.b = j;
                edges.add(edge);
                m++;
            }
        }

        int x = 0;
        for (int i = 0; i < n; i++) {
            x = -1;
            for (int j = 0; j < m; j++)
                if (d[edges.get(j).b] > (d[edges.get(j).a] + edges.get(j).cost) ) {
                    d[edges.get(j).b] = Math.max(-INF, d[edges.get(j).a] + edges.get(j).cost);
                    p[edges.get(j).b] = edges.get(j).a;
                    x = edges.get(j).b;
                }
        }

        if (x == -1) {
            out.print("NO");
        } else {
            int y = x;
            for (int i = 0; i < n; i++)
                y = p[y];

            int len = 0;
            LinkedList<Integer> path = new LinkedList<Integer>();
            for (int cur = y; ; cur = p[cur]) {
                path.add(cur);
                len++;
                if (cur == y && path.size() > 1)  break;
            }
            out.print("YES\n" + len + "\n");
            Iterator it = path.descendingIterator();
            while (it.hasNext()) {
                out.print((((Integer) it.next()) + 1) + " ");
            }
        }

    }

    class Edge {
        int a, b, cost;
    }


    public void run() {
        try {
            in = new FastScanner(new File("negcycle.in"));
            out = new PrintWriter(new File("negcycle.out"));

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
        new MainNegcycle().run();
    }
}


