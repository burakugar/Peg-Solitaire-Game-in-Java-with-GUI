import javax.swing.*;

public class main {
    public static void main(String[] args) {
        {

            PegSoliatire pg= new PegSoliatire();
            PegSolitaireGame.grid_type[][] board = PegSolitaireGame.board1.clone();
            pg.initial_choose_function();
            //pg.manage_game(board,7);

        };
    }
}
