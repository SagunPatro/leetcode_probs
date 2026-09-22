class Solution {

    ArrayList<int[]> nodes = new ArrayList<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        dfs(root, 0, 0);

        // Sort by: column -> row -> value
        nodes.sort((a, b) -> {

            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);

            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);

            return Integer.compare(a[2], b[2]);
        });

        List<List<Integer>> ans = new ArrayList<>();

        int prevcol = Integer.MIN_VALUE;

        for (int[] node : nodes) {

            int col = node[0];
            int row = node[1];
            int val = node[2];

            // New column
            if (col != prevcol) {
                ans.add(new ArrayList<>());
                prevcol = col;
            }

            // Add value to current column
            ans.get(ans.size() - 1).add(val);
        }

        return ans;
    }

    void dfs(TreeNode root, int row, int col) {

        // Important!
        if (root == null)
            return;

        nodes.add(new int[]{col, row, root.val});

        dfs(root.left, row + 1, col - 1);
        dfs(root.right, row + 1, col + 1);
    }
}