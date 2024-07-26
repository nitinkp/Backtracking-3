import java.util.Arrays;

public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        //O((mn)*3^(L)) T.C where m,n is dimensions of board and L is length of string
        //as at each further row apart from first, we can only traverse 3 more directions (excluding where it came from)

        //O(L) S.C, recursive stack space
        int[][] dirs = {{0,1}, {1,0}, {-1,0}, {0,-1}};
        int m = board.length;
        int n = board[0].length;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if (board[i][j] == word.charAt(0) && //proceed only if the first char of given word is found in board
                        backtrack(board, word, i, j, 0, dirs)) {//perform recursion starting from first char itself
                    return true; //if the word is found, exit early and return true;
                }
            }
        }
        return false; //if word is not found
    }

    static boolean backtrack(char[][] board, String word, int r, int c, int idx, int[][] dirs) {
        //base
        if(idx == word.length()) { //if all characters are found and index is at end of word
            return true; //return true
        }

        // check boundaries and if the character matches
        if(r < 0 || c < 0 || r >= board.length || c >= board[0].length ||
                board[r][c] != word.charAt(idx)) { //in recursion, if next char is not found, dont proceed further
            return false; //return false
        }

        // to avoid using the same cell twice
        char temp = board[r][c];
        board[r][c] = '#'; //temporary state change

        //logic
        for(int[] dir : dirs) { //traverse all directions from each cell
            int nr = dir[0] + r;
            int nc = dir[1] + c;
            //recurse
            if (backtrack(board, word, nr, nc, idx + 1, dirs)) { //if word is found in current recursive stack
                return true; //early exit and return true
            }
        }

        //backtrack
        board[r][c] = temp; //change the state of above cell to original
        return false; //if word is not found
    }

    public static void main(String[] args) {
        char[][] board = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'u'},
                {'m', 'n', 'i', 't'},
                {'o', 'n', 't', 'h'},
                {'r', 'i', 'h', 'j'},
                {'u', 'v', 'w', 'n'}
        };
        String word1 = "nithin";
        String word2 = "kumar";

        System.out.println("Is word " + word1 + " present in the board? " +
                Arrays.deepToString(board) + "\n" + exist(board, word1));

        System.out.println("Is word " + word2 + " present in the board? " +
                Arrays.deepToString(board) + "\n" + exist(board, word2));
    }
}