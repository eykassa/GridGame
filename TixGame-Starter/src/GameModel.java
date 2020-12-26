/**
 * Class to model the play of the game
 * You should implement the public interface methods below
 */
public class GameModel {
    public int [][]grid;
    public int [][]playerGrid;
    public int size;
    /**
     * Construct a game with given sizexsize
     * @param sz the square size of the board
     */
    public GameModel(int sz) {
        grid=new int [sz][sz];
        playerGrid=new int[sz][sz];
        size=sz;

    }
    /**
     * Can a play be made at position row, col?
     * @param row the row in question
     * @param col the col in question
     * @return true if row, col is empty, false o.w.
     */
    public boolean canPlay(int row, int col) {
        return playerGrid[row][col]==0;
    }
    /**
     * play a piece at the specified spot by the specified player
     * @param row the row where a piece is played
     * @param col the col where a piece is played
     * @param player -1 for white and 1 for black
     * @return true if the game is over and false otherwise
     */
    public boolean makePlay(int row, int col, int player) {
        playerGrid[row][col] = player;
        int number = row*size + col + 1;
        grid[row][col] = number;
        if (notNeighbour(row, col + 1, player) && notNeighbour(row, col - 1, player) && notNeighbour(row - 1, col, player) &&
                notNeighbour(row - 1, col + 1, player) && notNeighbour(row + 1, col, player) && notNeighbour(row + 1, col - 1, player)) {
        } else {
            int top = 0, topR = 0, left = 0, right = 0, down = 0, downL = 0;

            if ((col + 1) < size && playerGrid[row][col + 1] == player) {
                right = grid[row][col + 1];
            }

            if ((col - 1) >= 0 && playerGrid[row][col - 1] == player) {
                left = grid[row][col - 1];
            }

            if ((row + 1) < size && playerGrid[row + 1][col] == player){
                down = grid[row + 1][col];
            }

            if ((row + 1) < size && (col - 1) >= 0 && playerGrid[row + 1][col - 1] == player) {
                downL = grid[row + 1][col - 1];
            }

            if ((row - 1) >= 0 && (col + 1) < size && playerGrid[row - 1][col + 1] == player) {
                topR = grid[row - 1][col + 1];
            }

            if ((row - 1) >= 0 && playerGrid[row-1][col] == player) {
                top = grid[row - 1][col];
            }

            boolean topAccess=false , bottomAccess=false , leftAccess=false , rightAccess=false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int next = grid[i][j];
                    if (next == left || next == right || next == top || next == topR || next == down || next == downL || next == number) {
                        grid[i][j] = number;

                        if (i == 0 && playerGrid[i][j] == -1) topAccess = true;
                        if (j == 0 && playerGrid[i][j] == 1) leftAccess = true;

                        if ((i == size - 1) && playerGrid[i][j] == -1) bottomAccess = true;
                        if ((j == size - 1) && playerGrid[i][j] == 1) rightAccess = true;

                        if((topAccess && bottomAccess) || (leftAccess && rightAccess)) return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * this method checks if the given row and column is a valid neighbour of given player.
     * @param row the given row
     * @param col the given column
     * @param player player -1 for white and 1 for black
     * @return true if the neighbour is valid, false otherwise
     */
    public boolean notNeighbour(int row, int col, int player){
        return row < 0 || row >= size || col < 0 || col >= size || playerGrid[row][col] != player;
    }
}