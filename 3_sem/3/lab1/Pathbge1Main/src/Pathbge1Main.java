import java.io.*;
import java.util.*;

public class Pathbge1Main {
    FastScanner in;
    PrintWriter out;

    ArrayList< LinkedList<Integer> > graph;
    boolean used[];
    int s = 0; // start vertex

    LinkedList<Integer> queue;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();


        // TODO!!!
        // Wrong Answer
        graph = new ArrayList<LinkedList<Integer>>(n);
        used = new boolean[n];
        queue = new LinkedList<Integer>();

        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<Integer>());
        }

        int x;
        int y;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            graph.get(x - 1).add(y - 1);
            graph.get(y - 1).add(x - 1);
        }

        queue.add(s);
        used[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.pop();
            if (graph.get(v).size() > 0) {
                // System.out.print("\nvertex = " + (v + 1) + " ");
                ListIterator<Integer> it = graph.get(v).listIterator();
                while (it.hasNext()) {
                    int to = it.next();
                    // System.out.print("\nto = " + (to + 1) + " ");

                    if (!used[to]) {
                        used[to] = true;
                        queue.add(to);
                        d[to] = d[v] + 1;
                    }
                    // System.out.print(" | d[to] = " + (d[to]) + " ");
                }
            }
        }

        for (int to = 0; to < n; to++) {
            out.print(d[to] + " ");
        }


    }



    public void run() {
        try {
            in = new FastScanner(new File("pathbge1.in"));
            out = new PrintWriter(new File("pathbge1.out"));

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
        new Pathbge1Main().run();
    }
}

