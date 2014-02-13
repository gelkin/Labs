
import java.util.*;
import java.io.*;

public class MainBipartite {
    FastScanner in;
    PrintWriter out;

    ArrayList< LinkedList<Integer> > graph;

    int part[];
    int q[];
    boolean ok = true;


    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();


        graph = new ArrayList<LinkedList<Integer>>(n);
        part = new int[n];
        q = new int[n];;

        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<Integer>());
            part[i] = -1;
        }

        int x;
        int y;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            graph.get(x - 1).add(y - 1);
            graph.get(y - 1).add(x - 1);
        }

        for (int st = 0; st < n; st++) {
            if (part[st] == -1) {
                int h = 0;
                int t = 0;
                q[t++] = st;
                part[st] = 0;
                while (h < t) {
                    int v = q[h++];
                    if (graph.get(v).size() > 0) {
                        ListIterator<Integer> it = graph.get(v).listIterator();
                        while (it.hasNext()) {
                            int to = it.next();
                            if (part[to] == -1) {
                                part[to] = (part[v] == 0)? 1: 0;
                                q[t++] = to;
                            } else {
                                ok &= (part[to] != part[v]);
                            }
                        }
                    }
                }
            }
        }
        /*
        System.out.println("\n");
        for (int i = 0; i < tin.length; i++) {
            System.out.print(tin[i] + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < fup.length; i++) {
            System.out.print(fup[i] + " ");
        }
        System.out.println("\n");
        */

        if (ok) {
            out.print("YES");
        } else {
            out.print("NO");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("bipartite.in"));
            out = new PrintWriter(new File("bipartite.out"));

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
        new MainBipartite().run();
    }
}

