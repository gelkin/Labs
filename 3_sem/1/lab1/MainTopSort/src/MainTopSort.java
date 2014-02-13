import java.util.*;
import java.io.*;

public class MainTopSort {
    ArrayList< LinkedList<Integer> > graph;

    /** Colors:
     * 0 - WHITE
     * 1 - GRAY
     * 2 - BLACK
     */
    ArrayList<Integer> colors;
    LinkedList<Integer> res;

    FastScanner in;
    PrintWriter out;

    int dfs(int v) {
        colors.set(v, 1);

        if (graph.get(v).size() > 0) {
            ListIterator<Integer> it = graph.get(v).listIterator();
            int i = -1;
            while (it.hasNext()) {
                i++;
                int to = it.next();

                if (colors.get(to) == 1) {
                    return -1;
                }

                if (colors.get(to) == 0) {
                    int k = dfs(to);
                    if(k == -1) {
                        return -1;
                    }
                }
            }
        }

        colors.set(v, 2);
        res.add(v);

        return 0;
    }

    int topologicalSort() {
        for (int i = 0; i < graph.size(); i++) {
            if (colors.get(i) != 2) {
                int k = dfs(i);
                if (k == -1) {
                    return -1;
                }
            }
        }

        return 0;
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

        res = new LinkedList<Integer>();
        colors = new ArrayList<Integer>(n);

        for (int i = 0; i < n; i++) {
            colors.add(0);
        }

        int k = topologicalSort();

        if (k == 0) {
            Iterator<Integer> it = res.descendingIterator();
            while (it.hasNext()) {
                out.print(it.next() + 1);
                out.print(" ");
            }
        } else {
            out.println(-1);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("topsort.in"));
            out = new PrintWriter(new File("topsort.out"));

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
        new MainTopSort().run();
    }
}
