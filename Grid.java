public class Grid {
    private int numRows;
    private int numCols;
    private char[][] grid;
    private int[] height;

    public Grid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        initGrid();
        height = new int[numCols];
    }

    public void draw() {
        for (int row = numRows - 1; row >= 0; row--) {
            System.out.print("|");
            for (int col = 0; col < numCols; col++) {
                System.out.print(grid[row][col]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public boolean tryInsert(char disc, int col) {
        // Convert to zero-index
        int iCol = col - 1;
        if (iCol < 0 || iCol >= numCols || height[iCol] >= numRows) {
            return false;
        }

        grid[height[iCol]][iCol] = disc;
        height[iCol]++;

        return true;
    }

    public boolean isConnect4(char disc, int col) {
        int iCol = col - 1;
        int iRow = height[iCol] - 1;

        // Horizontal line
        int currCol = iCol - 1;
        int left = 0;
        while (currCol >= 0 && grid[iRow][currCol] == disc) {
            left++;
            currCol--;
        }
        int right = 0;
        currCol = iCol + 1;
        while (currCol < numCols && grid[iRow][currCol] == disc) {
            right++;
            currCol++;
        }
        if (left + right >= 3) {
            return true;
        }

        // Vertical line
        int down = 0;
        int currRow = iRow - 1;
        while (currRow >= 0 && grid[currRow][iCol] == disc) {
            down++;
            currRow--;
        }
        if (down >= 3) {
            return true;
        }

        // Left-right diagonal line
        int upLeft = 0;
        currCol = iCol - 1;
        currRow = iRow + 1;
        while (currCol >= 0 && currRow < numRows && grid[currRow][currCol] == disc) {
            upLeft++;
            currCol--;
            currRow++;
        }
        int downRight = 0;
        currCol = iCol + 1;
        currRow = iRow - 1;
        while (currCol < numCols && currRow >= 0 && grid[currRow][currCol] == disc) {
            downRight++;
            currCol++;
            currRow--;
        }
        if (upLeft + downRight >= 3) {
            return true;
        }

        // Right-left diagonal line
        int upRight = 0;
        currCol = iCol + 1;
        currRow = iRow + 1;
        while (currCol < numCols && currRow < numRows && grid[currRow][currCol] == disc) {
            upRight++;
            currCol++;
            currRow++;
        }
        int downLeft = 0;
        currCol = iCol - 1;
        currRow = iRow - 1;
        while (currCol >= 0 && currRow >= 0 && grid[currRow][currCol] == disc) {
            downLeft++;
            currCol--;
            currRow--;
        }
        if (upRight + downLeft >= 3) {
            return true;
        }

        return false;
    }

    private void initGrid() {
        this.grid = new char[numRows][numCols];
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                grid[row][col] = ' ';
            }
        }
    }
}
