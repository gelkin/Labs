
import java.util.*;
import java.io.*;

public class MainBicone {
    FastScanner in;
    PrintWriter out;

    ArrayList<LinkedList<Integer>> graph;
    boolean used[];
    LinkedList<Integer> vertices;
    int c = 0;
    int timer;
    int tin[];
    int fup[];
    int color[];

    void dfs(int v, int p) {
        used[v] = true;
        vertices.add(v);
        tin[v] = timer;
        fup[v] = timer;
        timer++;
        // System.out.print("fup[" + v + "] = " + fup[v] + "\n");
        if (graph.get(v).size() > 0) {
            // System.out.print("vertex = " + v + " ");
            ListIterator<Integer> it = graph.get(v).listIterator();
            while (it.hasNext()) {
                int to = it.next();
                // System.out.print("to = " + to + " ");
                if (to == p)
                    continue;
                if (used[to]) {
                    fup[v] = Math.min(fup[v], tin[to]);
                    // System.out.println("\nfup[" + v + "] = " + fup[v] + " ");
                } else {
                    // System.out.print(" Arrrr ");
                    dfs(to, v);
                    fup[v] = Math.min(fup[v], fup[to]);
                    /*
                    System.out.print("fup[" + v + "] = " + fup[v] + "\n");
                    System.out.print("fup[" + to + "] = " + fup[to] + "\n");
                    System.out.print(fup[to] + " " + tin[v] + "\n");
                    */
                    // TODO: x = vertices.removeLast()) != to !!!! +++ use bridge search
                    if (fup[to] > tin[v]) {
                        // System.out.print("v =" + v + "\n");
                        c++;
                        int x;
                        while ((!vertices.isEmpty()) && ((x = vertices.removeLast()) != to)) {
                            color[x] = c;
                        }
                        color[to] = c;
                    }
                }
            }
        }
    }


    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();


        // TODO!!!
        // Wrong Answer
        graph = new ArrayList<LinkedList<Integer>>(n);
        used = new boolean[n];
        vertices = new LinkedList<Integer>();
        tin = new int[n];
        fup = new int[n];
        color = new int[n];

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

        timer = 0;
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                dfs(i, -1);
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

        out.print((c + 1) + "\n");
        for (int i = 0; i < color.length; i++) {
            out.print((color[i] + 1) + " ");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("bicone.in"));
            out = new PrintWriter(new File("bicone.out"));

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
        new MainBicone().run();
    }
}
