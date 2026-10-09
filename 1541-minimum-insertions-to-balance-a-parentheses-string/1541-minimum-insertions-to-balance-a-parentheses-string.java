
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // If the next character is also ')',
                // we have a complete closing pair.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert one ')' to complete the pair.
                    insertions++;
                }

                // Match this closing pair with an opening '('.
                if (open > 0) {
                    open--;
                } else {
                    // No opening '(' exists, so insert one.
                    insertions++;
                }
            }
        }

        // Every remaining '(' needs two ')'.
        insertions += open * 2;

        return insertions;
    }
}
