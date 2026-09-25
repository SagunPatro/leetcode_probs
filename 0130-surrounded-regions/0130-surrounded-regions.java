class Solution {
    public void solve(char[][] board) {

        int n = board.length;
        int m = board[0].length;

        int visited[][] = new int[n][m];

        int delrow[] = {0, 0, -1, 1};
        int delcol[] = {-1, 1, 0, 0};

        // Top and bottom rows
        for(int j = 0; j < m; j++) {

            // Top row
            if(visited[0][j] == 0 && board[0][j] == 'O') {
                dfs(0, j, visited, board, delrow, delcol);
            }

            // Bottom row
            if(visited[n-1][j] == 0 && board[n-1][j] == 'O') {
                dfs(n-1, j, visited, board, delrow, delcol);
            }
        }

        // Left and right columns
        for(int i = 0; i < n; i++) {

            // Left column
            if(visited[i][0] == 0 && board[i][0] == 'O') {
                dfs(i, 0, visited, board, delrow, delcol);
            }

            // Right column
            if(visited[i][m-1] == 0 && board[i][m-1] == 'O') {
                dfs(i, m-1, visited, board, delrow, delcol);
            }
        }

        // Convert surrounded O's to X
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                if(visited[i][j] == 0 && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }


    public void dfs(int row, int col, int visited[][],
                    char mat[][], int delrow[], int delcol[]) {

        visited[row][col] = 1;

        int n = mat.length;
        int m = mat[0].length;

        for(int i = 0; i < 4; i++) {

            int nrow = row + delrow[i];
            int ncol = col + delcol[i];

            if(nrow >= 0 && nrow < n &&
               ncol >= 0 && ncol < m &&
               visited[nrow][ncol] == 0 &&
               mat[nrow][ncol] == 'O') {

                dfs(nrow, ncol, visited, mat, delrow, delcol);
            }
        }
    }
}