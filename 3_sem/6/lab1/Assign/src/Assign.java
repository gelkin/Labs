import java.util.*;
import java.io.*;

public class Assign {
    FastScanner in;
    PrintWriter out;

    public static final int INF = 1000010;

    int[][] matrix;

    int n; // number of vertices
    int m; // number of edges

    public void solve() throws IOException {
        n = in.nextInt();

        n++;

        matrix = new int[n][n];

        int x;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                x = in.nextInt();
                matrix[i][j] = x;
            }
        }

        int[] potU = new int[n];
        int[] potV = new int[n];
        int[] matching = new int[n];
        int[] way = new int[n];

        int[] minV = new int[n];
        boolean[] used = new boolean[n];

        for (int i = 1; i < n; i++) {
            matching[0] = i;
            int j0 = 0;
            Arrays.fill(minV, INF);
            Arrays.fill(used, false);

            do {
                used[j0] = true;
                int i0 = matching[j0];
                int delta = INF;
                int j1 = 0;
                for (int j = 1; j < n; j++)
                    if (!used[j]) {
                        int cur = matrix[i0][j] - potU[i0] - potV[j];
                        if (cur < minV[j]) {
                            minV[j] = cur;
                            way[j] = j0;
                        }

                        if (minV[j] < delta) {
                            delta = minV[j];
                            j1 = j;
                        }
                    }

                for (int j = 0; j < n; j++) {
                    if (used[j]) {
                        potU[matching[j]] += delta;
                        potV[j] -= delta;
                    } else {
                        minV[j] -= delta;
                    }
                }
                j0 = j1;
            } while (matching[j0] != 0);
            do {
                int j1 = way[j0];
                matching[j0] = matching[j1];
                j0 = j1;
            } while (j0 != 0);
        }
        int[] ans = new int[n];
        for (int j = 1; j < n; j++) {
            ans[matching[j]] = j;
        }

        out.print(-potV[0] + "\n");

        for (int i = 1; i < n; i++) {
            out.print(i + " " + ans[i] + "\n");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("assignment.in"));
            out = new PrintWriter(new File("assignment.out"));

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
        new Assign().run();
    }
}




