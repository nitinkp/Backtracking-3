import java.util.ArrayList;
import java.util.List;

public class NQueens {
    List<List<String>> result;
    List<String> path;
    public List<List<String>> solveNQueens(int n) {
        //O(n!) T.C, where actually for each corresponding row, it should be n*(n-2)*(n-4)...
        //as we can eliminate both same column and adjacent column for each of the next row traversal
        result = new ArrayList<>();
        path = new ArrayList<>();

        //this grid is used to store places where it is safe to place Q
        boolean[][] grid = new boolean[n][n]; //O(n*n) S.C

        recursion(n,0, grid); //start recursion at 0th row
        return result;
    }

    void recursion(int n, int r, boolean[][] grid) {
        //base
        if(r == n) { //if current row reaches end of given input length
            List<String> path = new ArrayList<>(); //create a new list for each successful Q placements in board
            for(int i=0; i<n; i++) { //iterate over rows
                StringBuilder sb = new StringBuilder(); //create a new stringbuilder to store string path at current row
                for(int j=0; j<n; j++) { //iterate over columns
                    if(grid[i][j]) sb.append("Q"); //if this grid is found to be true, place a Q in the string
                    else sb.append("."); //else place a '.' in the string for rest of columns in same row
                }
                path.add(sb.toString()); //append the current string builder of each row to the path
            }
            result.add(path); //once a path is completed, append it to result
        }

        //logic
        for(int j=0; j < n; j++) { //at each row, check all columns
            if(isSafeGrid(r, j, grid)) { //if a grid is safe to place Q
                //action
                grid[r][j] = true; //mark that grid as true

                //recurse
                recursion(n, r+1, grid); //perform recursion on the next row

                //backtrack
                grid[r][j] = false; //once all the above stacked recursions are done, backtrack it
            }
        }
    }

    boolean isSafeGrid(int r, int c, boolean[][] grid) {
        //same col
        for(int i=0; i<r; i++) { //iterate over rows until current row (0->r)
            if(grid[i][c]) return false; //if any grid from same col is already true, it is not safe
        }

        int i = r; int j = c;

        //up left
        while(i > 0 && j > 0) { //diagonal left
            i--;
            j--;
            if(grid[i][j]) return false;
        }

        //up right
        i = r; j = c;
        while(i > 0 && j < grid.length-1) { //diagonal right
            i--;
            j++;
            if(grid[i][j]) return false;
        }
        return true; //if all above conditions pass, then it is safe to place a Q here
    }

    public static void main(String[] args) {
        int n = 5;
        NQueens nQueens = new NQueens();

        System.out.println("The ways queens can be adjusted in a " + n + " x " + n + " board is \n" +
                nQueens.solveNQueens(n));
    }
}