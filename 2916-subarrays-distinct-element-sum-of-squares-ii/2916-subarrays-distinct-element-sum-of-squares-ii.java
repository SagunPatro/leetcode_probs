
class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] sum = new long[4 * n];
        long[] sq = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            int left = last[x]; // Previous occurrence, or 0
            update(1, 1, n, left + 1, i + 1, 1,
                   sum, sq, lazy);

            last[x] = i + 1;
            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    private void update(int node, int start, int end,
                        int l, int r, long val,
                        long[] sum, long[] sq, long[] lazy) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            long len = end - start + 1L;

            // (old distinct count + 1)^2
            sq[node] = (sq[node] + 2 * sum[node] + len) % MOD;
            sum[node] = (sum[node] + len) % MOD;
            lazy[node] = (lazy[node] + 1) % MOD;
            return;
        }

        push(node, start, end, sum, sq, lazy);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, l, r, val,
               sum, sq, lazy);
        update(node * 2 + 1, mid + 1, end, l, r, val,
               sum, sq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    private void push(int node, int start, int end,
                      long[] sum, long[] sq, long[] lazy) {
        long add = lazy[node];
        if (add == 0 || start == end) return;

        int mid = start + (end - start) / 2;

        apply(node * 2, start, mid, add, sum, sq, lazy);
        apply(node * 2 + 1, mid + 1, end, add, sum, sq, lazy);

        lazy[node] = 0;
    }

    private void apply(int node, int start, int end, long add,
                       long[] sum, long[] sq, long[] lazy) {
        long len = end - start + 1L;

        sq[node] = (sq[node] + 2 * add * sum[node]
                    + add * add % MOD * len) % MOD;

        sum[node] = (sum[node] + add * len) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }
}
