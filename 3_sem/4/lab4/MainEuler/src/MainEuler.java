import java.io.*;
import java.util.*;

public class MainEuler {
    FastScanner in;
    PrintWriter out;

    int[][] graph;

    boolean[] usedV;

    int n;

    // first - weight; second - number of pair of vertices

    ArrayList<Integer> res;

    public void solve() throws IOException {
        n = in.nextInt();

        graph = new int[n][n];
        usedV = new boolean[n];
        res = new ArrayList<Integer>();

        int m;
        int u;
        for (int i = 0; i < n; i++) {
            m = in.nextInt();
            for (int j = 0; j < m; j++) {
                u = in.nextInt();
                if (u > i) {
                    graph[i][u - 1]++;
                    graph[u - 1][i]++;
                }
            }
        }

        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg[i] += graph[i][j];
            }
        }

        int first = 0;
        while (deg[first] == 0) {
            first++;
        }

        int v1 = -1;
        int v2 = -1;
        boolean bad = false;
        for (int i = 0; i < n; i++) {
            if (deg[i] % 2 == 1) {
                if (v1 == -1) {
                    v1 = i;
                } else if (v2 == -1) {
                    v2 = i;
                } else {
                    bad = true;
                }
            }
        }

        /*
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.print("\n");
        }
        */

        // System.out.print("v1 = " + v1 + ", v2 = " + v2 + "\n");

        if (v1 != -1) {
            graph[v1][v2]++;
            graph[v2][v1]++;
        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.add(first);
        while (!stack.isEmpty()) {
            int v = stack.peek();

            /*
            System.out.println("Peek = " + (v + 1));
            for (int i = 0; i < stack.size(); i++) {
                System.out.print((stack.get(i) + 1) + " ");
            }
            System.out.print("\n");
            */

            int i;
            for (i = 0; i < n; i++) {
                if (graph[v][i] != 0) {
                    break;
                }
            }

            if (i == n) {
                res.add(v);
                stack.pop();
            } else {
                graph[v][i]--;
                graph[i][v]--;
                stack.push(i);
            }
        }

        /*
        for (int i = 0; i < res.size(); i++) {
            System.out.print((res.get(i) + 1) + " ");
        }
        */


        if (v1 != -1)
            for (int i = 0; (i + 1) < res.size(); i++)
                if (res.get(i) == v1 && res.get(i + 1) == v2 || res.get(i) == v2 && res.get(i + 1) == v1) {
                    ArrayList<Integer> res2 = new ArrayList<Integer>();
                    for (int j = i + 1; j < res.size(); j++) {
                        res2.add(res.get(j));
                    }
                    for (int j = 1; j <= i; j++) {
                        res2.add(res.get(j));
                    }

                    res = res2;

                    break;
                }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != 0) {
                    bad = true;
                }
            }
        }

        if (bad) {
            out.print("-1");
        } else {
            out.print((res.size() - 1) + "\n");
            for (int i = 0; i < res.size(); i++) {
                out.print((res.get(i) + 1) + " ");
            }
        }
    }


    public void run() {
        try {
            in = new FastScanner(new File("euler.in"));
            out = new PrintWriter(new File("euler.out"));

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
        new MainEuler().run();
    }
}