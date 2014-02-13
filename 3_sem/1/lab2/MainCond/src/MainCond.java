import java.util.*;
import java.io.*;

public class MainCond {
    ArrayList< LinkedList<Integer> > graph;
    ArrayList< LinkedList<Integer> > graphTransp;

    /** Colors:
     * 0 - WHITE
     * 1 - GRAY
     * 2 - BLACK
     */
    ArrayList<Integer> used;
    LinkedList<Integer> component;
    LinkedList<Integer> order;


    FastScanner in;
    PrintWriter out;

    void dfs1(int v) {
        used.set(v, 1);
        if (graph.get(v).size() > 0) {
            ListIterator<Integer> it = graph.get(v).listIterator();
            int i = -1;
            while (it.hasNext()) {
                i++;
                int to = it.next();
                if (used.get(to) == 0) {
                    dfs1(to);
                }
            }
        }

        order.add(v);
    }

    void dfs2(int v) {
        used.set(v, 1);
        component.add(v);
        if (graphTransp.get(v).size() > 0) {
            ListIterator<Integer> it = graphTransp.get(v).listIterator();
            // int i = -1;
            while (it.hasNext()) {
                // i++;
                int to = it.next();
                if (used.get(to) == 0) {
                    dfs2(to);
                }
            }
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new ArrayList<LinkedList<Integer>>(n);
        graphTransp = new ArrayList<LinkedList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<Integer>());
            graphTransp.add(new LinkedList<Integer>());
        }

        int x;
        int y;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();

            (graph.get(x - 1)).add(y - 1);
            (graphTransp.get(y - 1)).add(x - 1);
        }

        component = new LinkedList<Integer>();
        order = new LinkedList<Integer>();
        used = new ArrayList<Integer>(n);

        for (int i = 0; i < n; i++) {
            used.add(0);
        }

        for (int i = 0; i < n; i++) {
            if (used.get(i) == 0) {
                dfs1(i);
            }
        }

        for (int i = 0; i < n; i++) {
            used.set(i, 0);
        }

        ArrayList<Integer> res = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            res.add(0);
        }

        ListIterator<Integer> it;
        int numberOfCopms = 1;
        int v;
        for (int i = 0; i < n; i++) {
            v = order.get(n - 1 - i);
            if (used.get(v) == 0) {
                dfs2(v);

                it = component.listIterator();
                while (it.hasNext()) {
                    res.set(it.next(), numberOfCopms);
                }
                component.clear();
                numberOfCopms++;
            }
        }

        out.print(numberOfCopms - 1);
        out.print("\n");
        for (int i = 0; i < n; i++) {
            out.print(res.get(i) + " ");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("cond.in"));
            out = new PrintWriter(new File("cond.out"));

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
        new MainCond().run();
    }
}
