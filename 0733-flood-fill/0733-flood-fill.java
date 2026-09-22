class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int initColor = image[sr][sc];

        // If the starting color is already the target color
        // no need to do anything
        if (initColor == color) {
            return image;
        }

        int[][] ans = image;

        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        dfs(sr, sc, ans, image, color, delr, delc, initColor);

        return ans;
    }

    public void dfs(int row, int col, int[][] ans, int[][] image,
                    int color, int[] delr, int[] delc, int initColor) {

        ans[row][col] = color;

        int n = image.length;
        int m = image[0].length;

        for (int i = 0; i < 4; i++) {

            int nrow = row + delr[i];
            int ncol = col + delc[i];

            if (nrow >= 0 && nrow < n &&
                ncol >= 0 && ncol < m &&
                image[nrow][ncol] == initColor) {

                dfs(nrow, ncol, ans, image,
                    color, delr, delc, initColor);
            }
        }
    }
}