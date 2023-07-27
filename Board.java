public class Board {

    public Tile[][] board;
    int grids = 4;
    int border = 0;
    public int score = 0;

    public Board(){ //creates a board that is 4x4 matrix,
        board = new Tile[4][4];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                board[i][j] = new Tile();
            }
        }
    }
    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public int getScore (){
        return score;
    }

    public int getHighestTile(){
        int highestTile = board[0][0].getValue();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j].getValue() > highestTile) {
                    highestTile = board[i][j].getValue();
                }
            }
        }
        return highestTile;
    }

    public void print (){  //This prints the board into the console just to see it
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                String tile = board[i][j].toString() + " ";
                System.out.println(tile);
            }
            System.out.println("");
        }
        System.out.println("Score: " + score);
    }

    @Override
    public String toString() {  //Same as print() method, but doesn't sout it into the console, stores the value as a String;
        String stringValueOfBoard = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                stringValueOfBoard += board[i][j].toString() + " ";
            }
            stringValueOfBoard += "/n";
        }
        return stringValueOfBoard;
    }

    public void spawn (){  //randomly spawn a tile with value 2 (66%) or with value 4 (33%)
        boolean isEmpty = true;
        while (isEmpty){
            int row = (int) (Math.random() * 4);
            int column = (int)  (Math.random() *4);
            double spawnChance = Math.random();
            if (board[row][column].getValue() == 0){ //it checks if the tile is empty (0)
                if (spawnChance > 0.33){
                    board[row][column] = new Tile(4);
                    isEmpty = false;
                } else {
                    board[row][column] = new Tile(2);
                    isEmpty = false;
                }
            }
        }
    }

    public boolean isBoardFullBoolean(){
        int numberOfFullTiles = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() > 0){
                    numberOfFullTiles++;
                }
            }
            if (numberOfFullTiles == 16){
                return true;
            }
        }
        return false;
    }

    public boolean isGameOver(){
        int counterOfUnmovableTiles = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j].getValue() > 0){
                    if (i == 0 && j == 0){
                        if (board[i][j].getValue() != board[i+1][j].getValue() &&
                                board[i][j].getValue() != board[i][j+1].getValue()){
                            counterOfUnmovableTiles++;
                        }
                    }else if (i == 3 && j == 0){
                        if (board[i][j].getValue() != board[i-1][j].getValue() &&
                        board[i][j].getValue() != board[i][j+1].getValue()){
                            counterOfUnmovableTiles++;
                        }
                    }else if (i == 3 && j == 3){
                        if (board[i][j].getValue() != board[i-1][j].getValue() &&
                                board[i][j].getValue() != board[i][j-1].getValue()){
                            counterOfUnmovableTiles++;
                        }
                    }else if (i == 0 && j == 3){
                        if (board[i][j].getValue() != board[i+1][j].getValue() &&
                                board[i][j].getValue() != board[i][j-1].getValue()){
                            counterOfUnmovableTiles++;
                        }
                    }else if (i == 0 && (j == 1 || j == 2)){
                        if (board[i][j].getValue() != board[i+1][j].getValue() &&
                                board[i][j].getValue() != board[i][j-1].getValue() &&
                                board[i][j].getValue() != board[i][j+1].getValue()){
                            counterOfUnmovableTiles++;
                        }
                    }else if (i == 3 && (j == 1 || j == 2)){
                        if (board[i][j].getValue() != board[i-1][j].getValue() &&
                                board[i][j].getValue() != board[i][j-1].getValue() &&
                                board[i][j].getValue() != board[i][j+1].getValue()){
                            counterOfUnmovableTiles++;
                        }
                    }else if (j == 0 && (i == 1 || i == 2)){
                        if (board[i][j].getValue() != board[i][j+1].getValue() &&
                                board[i][j].getValue() != board[i-1][j].getValue() &&
                                board[i][j].getValue() != board[i+1][j].getValue()){
                            counterOfUnmovableTiles++;
                        }
                    }else if (j == 3 && (i == 1 || i == 2)){
                        if (board[i][j].getValue() != board[i][j-1].getValue() &&
                                board[i][j].getValue() != board[i-1][j].getValue() &&
                                board[i][j].getValue() != board[i+1][j].getValue()){
                            counterOfUnmovableTiles++;
                        }
                    }
                }
            }
        }
        if (counterOfUnmovableTiles == 16){
            return true;
        }
        return false;
    }

    public void verticalMove(int row, int column, String direction){
        Tile initial = board[border][column];
        Tile compare = board[row][column];
        if (initial.getValue() == 0 || initial.getValue() == compare.getValue()){
            if (row > border || (direction.equals("down") && (row < border))){
                int addScore = initial.getValue() + compare.getValue();
                if (initial.getValue() != 0){
                    score += addScore;
                }
                initial.setValue(addScore);
                compare.setValue(0);
            }
        }else{
            if (direction.equals("down")){
                border--;
            }else{
                border++;
            }
            verticalMove(row,column,direction);
        }
    }

    public void upMove(){
        for (int i = 0; i < grids; i++){
            border = 0;
            for (int j = 0; j < grids; j++){
                if (board[j][i].getValue() != 0){
                    if (border <= j){
                        verticalMove(j,i, "up");
                    }
                }
            }
        }
    }

    public void downMove(){
        for (int i = 0; i < grids; i++){
            border = grids -1;
            for (int j = grids; j >= 0; j--){
                if (board[j][i].getValue() != 0){
                    if (border >= j){
                        verticalMove(j, i, "down");
                    }
                }
            }
        }
    }

    public void horizontalMove(int row, int column, String direction){
        Tile initial = board[border][column];
        Tile compare = board[row][column];
        if(initial.getValue() ==0 || initial.getValue() == compare.getValue()){
            if (column > border || (direction.equals("right") && (column < border))){
                int addScore = initial.getValue()+compare.getValue();
                if (initial.getValue() != 0){
                    score = score + addScore;
                }
                initial.setValue(addScore);
                compare.setValue(0);
            }
        }else {
         if (direction.equals("right")){
             border--;
         }else {
             border++;
         }
         horizontalMove(row, column, direction);
        }
    }

    public void leftMove(){
        for (int i = 0; i < grids; i++){
            border = 0;
            for (int j = 0; j < grids; j++){
                if (board[i][j].getValue() != 0){
                    if (border <= j){
                        horizontalMove(i, j, "left");
                    }
                }
            }
        }
    }

    public void rightMove(){
        for (int i = 0; i < grids; i++){
            border = (grids -1);
            for (int j = grids-1; j >=0; j--){
                if (board[i][j].getValue() != 0){
                    if (border >=j){
                        horizontalMove(i, j, "right");
                    }
                }
            }
        }
    }


}
