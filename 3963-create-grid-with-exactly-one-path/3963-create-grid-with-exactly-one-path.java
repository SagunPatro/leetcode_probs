
class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        // Initialize all cells as obstacles
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '#';
            }
        }

        // First row is free
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }

        // Last column is free
        for (int i = 1; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        // Convert char[][] to String[]
        String[] result = new String[m];

        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }

        return result;
    }
}
