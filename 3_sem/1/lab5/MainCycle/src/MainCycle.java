import java.util.*;
import java.io.*;

public class MainCycle {
    ArrayList< LinkedList<Integer> > graph;

    /** Colors:
     * 0 - WHITE
     * 1 - GRAY
     * 2 - BLACK
     */
    int[] colors;
    int[] parent;
    int cycleStart;
    int cycleEnd;

    FastScanner in;
    PrintWriter out;

    boolean dfs(int v) {
        colors[v] = 1;

        if (graph.get(v).size() > 0) {
            ListIterator<Integer> it = graph.get(v).listIterator();
            // int i = -1;
            while (it.hasNext()) {
                // i++;
                int to = it.next();

                if (colors[to] == 0) {
                    parent[to] = v;
                    if (dfs(to)) return true;
                } else if (colors[to] == 1) {
                    cycleEnd = v;
                    cycleStart = to;
                    return true;
                }
            }
        }

        colors[v] = 2;
        return false;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new ArrayList<LinkedList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<Integer>());
        }

        int x;
        int y;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();

            (graph.get(x - 1)).add(y - 1);
        }

        colors = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = 0;
            parent[i] = -1;
        }

        cycleStart = -1;

        for (int i = 0; i < n; i++) {
            if (dfs(i)) {
                break;
            }
        }

        if (cycleStart == -1)
            out.print("NO");
        else {
            out.print("YES\n");
            LinkedList<Integer> cycle = new LinkedList<Integer>();
            cycle.add(cycleStart);
            for (int v = cycleEnd; v != cycleStart; v = parent[v]) {
                cycle.add(v);
            }
            Iterator<Integer> it = cycle.descendingIterator();
            while (it.hasNext()) {
                out.print((it.next() + 1) + " ");
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("cycle.in"));
            out = new PrintWriter(new File("cycle.out"));

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
        new MainCycle().run();
    }
}
