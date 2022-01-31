import java.util.ArrayList;

public interface PegSolitaireGame{
    enum grid_type // grid type for boards
    {
        space,
        peg,
        wall
    };
    grid_type[][] board1 = { // 7x7 board
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.space, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall}
    };
    grid_type[][] board2 = { // 7x9 board
            {grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.space, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall}
    };
    //8x8 board
    grid_type[][] board3 = {{grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.space, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall}
    };
    // 7x7 board
    grid_type[][] board4 = {{grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.space, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall}
    };
     // 9x9 board
    grid_type[][] board5 = {{grid_type.wall, grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall},
            {grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.space, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg},
            {grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.peg, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall, grid_type.wall}};
    // 5x9 board
    grid_type[][] board6 = {{grid_type.wall, grid_type.wall, grid_type.wall, grid_type.wall, grid_type.space, grid_type.wall, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.wall},
            {grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall},
            {grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg, grid_type.wall, grid_type.peg}};

    public int check_left(grid_type[][] board, int i, int j,int N1,int N2);
    public int check_right(grid_type[][] board, int i, int j,int N1,int N2);
    public int check_up(grid_type[][] board, int i, int j,int N1,int N2);
    public int check_down(grid_type[][] board, int i, int j,int N1,int N2);
    public int check_game_is_done(grid_type[][] board,int N1, int N2);
    public grid_type[][] manage_game(grid_type[][] board1_temp,int N1,int N2,String board_type,int z);
    public int calculateScore(grid_type[][] board);
    public void computer_game(grid_type[][] board,int N1,int N2,String board_type);
    public grid_type[][] load_file(String filename);

}