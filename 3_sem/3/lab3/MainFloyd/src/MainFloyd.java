import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class MainFloyd {
    FastScanner in;
    PrintWriter out;

    public static final int INF = 100000;

    ArrayList< LinkedList<Integer> > graph;
    boolean used[];
    int s = 0; // start vertex

    LinkedList<Integer> queue;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] d = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                d[i][j] = INF;
            }
        }

        int x;
        int y;
        int z;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            d[x - 1][y - 1] = z;
        }
        for (int k=0; k<n; ++k)
            for (int i=0; i<n; ++i)
                for (int j=0; j<n; ++j)
                    d[i][j] = Math.min (d[i][j], d[i][k] + d[k][j]);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(d[i][j] + " ");
            }
            out.print("\n");
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("pathsg.in"));
            out = new PrintWriter(new File("pathsg.out"));

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
        new MainFloyd().run();
    }
}

