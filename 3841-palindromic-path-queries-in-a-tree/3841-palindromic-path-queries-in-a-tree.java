import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] tin, tout, depth, parent;
    private int[] mask, bit;
    private int timer;
    private int[][] up;
    private int LOG;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                         String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        mask = new int[n];
        timer = 0;

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        bit = new int[n + 2];

        dfs(0, -1, 0, s);

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int newMask = 1 << (c - 'a');
                int delta = mask[u] ^ newMask;

                if (delta != 0) {
                    update(tin[u], delta);
                    update(tout[u] + 1, delta);
                    mask[u] = newMask;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int l = lca(u, v);

                int pathMask = prefix(tin[u])
                             ^ prefix(tin[v])
                             ^ mask[l];

                // The LCA is included exactly once.
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, int d, String s) {
        parent[u] = (p == -1) ? u : p;
        up[0][u] = parent[u];
        depth[u] = d;

        mask[u] = 1 << (s.charAt(u) - 'a');

        tin[u] = ++timer;

        // Store root-to-node character parity using a difference
        // Fenwick tree: each node contributes at tin and tout + 1.
        update(tin[u], mask[u]);

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1, s);
            }
        }

        tout[u] = timer;
        update(tout[u] + 1, mask[u]);
    }

    private void update(int i, int delta) {
        while (i < bit.length) {
            bit[i] ^= delta;
            i += i & -i;
        }
    }

    private int prefix(int i) {
        int res = 0;
        while (i > 0) {
            res ^= bit[i];
            i -= i & -i;
        }
        return res;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }
}