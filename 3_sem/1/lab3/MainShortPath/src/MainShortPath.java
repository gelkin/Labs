import java.util.*;
import java.io.*;

public class MainShortPath {
    ArrayList< LinkedList<Integer> > graph;

    public static final int MAX = 5000;
    /** Colors:
     * 0 - WHITE
     * 1 - GRAY
     * 2 - BLACK
     */
    int[] colors;
    LinkedList<Integer> res;

    FastScanner in;
    PrintWriter out;

    // for acyclic graph
    void topSort(int v) {
        colors[v] = 1;
        if (graph.get(v).size() > 0) {
            ListIterator<Integer> it = graph.get(v).listIterator();
            while (it.hasNext()) {
                int to = it.next();

                if (colors[to] == 0) {
                    topSort(to);
                }
            }
        }
        res.add(v);
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        if (m == 0) {
            out.print("Unreachable");
            return;
        }

        int s = in.nextInt();
        int t = in.nextInt();



        graph = new ArrayList<LinkedList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<Integer>());
        }

        int[][] w = new int[n][n];
        int x;
        int y;
        int z;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt(); // weight

            (graph.get(x - 1)).add(y - 1);
            w[x - 1][y - 1] = z;
        }

        res = new LinkedList<Integer>();
        colors = new int[n];

        for (int i = 0; i < n; i++) {
            colors[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                topSort(i);
            }
        }

        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = MAX;
        }


        d[s - 1] = 0;

        ListIterator<Integer> it1 = res.listIterator();
        int i;
        while (it1.hasNext()) {
            i = it1.next();
            if (i == s - 1) {
               break;
            }
        }

        ListIterator<Integer> it2 = it1;

        // System.out.println(i);

        int v;
        while(it2.hasPrevious()) {
            v = it2.previous();
            // System.out.println("v = " + v);

            if (graph.get(v).size() > 0) {
                ListIterator<Integer> it = graph.get(v).listIterator();
                while (it.hasNext()) {
                    int to = it.next();
                    d[to] = (d[to] > (d[v] + w[v][to]))? (d[v] + w[v][to]): d[to];

                    // System.out.print(" -->" + to);
                    // System.out.println(" & dist = " + d[to]);
                }
            }
        }

        /* System.out.println("Distances:");
        for (int i = 0; i < n; i++) {
            System.out.print(d[i] + " ");
        }
        */

        if (d[t - 1] == MAX) {
            out.print("Unreachable");
        } else {
            out.print(d[t - 1]);
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("shortpath.in"));
            out = new PrintWriter(new File("shortpath.out"));

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
        new MainShortPath().run();
    }
}
