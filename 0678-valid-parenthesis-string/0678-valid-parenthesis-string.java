class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                minOpen++;
                maxOpen++;
            }

            else if (ch == ')') {
                minOpen--;
                maxOpen--;
            }

            else { // '*'
                minOpen--;   // '*' acts as ')'
                maxOpen++;   // '*' acts as '('
            }

            // No possible interpretation remains
            if (maxOpen < 0) {
                return false;
            }

            // Minimum cannot go below 0
            minOpen = Math.max(0, minOpen);
        }

        return minOpen == 0;
    }
}