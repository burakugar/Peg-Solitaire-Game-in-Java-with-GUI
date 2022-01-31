import javax.management.remote.JMXConnectorFactory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.io.FileNotFoundException;  // Import this class to handle errors

public class PegSoliatire implements PegSolitaireGame {
    private grid_type[][] board1_;
    private grid_type[][] board2_;
    private grid_type[][] board3_;
    private grid_type[][] board4_;
    private grid_type[][] board5_;
    private grid_type[][] board6_;

    /**
     * Default constructor for cloning the grids from interface
     */
    PegSoliatire(){ /* default constuctor */
         board1_ = board1.clone();
         board2_ = board2.clone();
         board3_ = board3.clone();
         board4_ = board4.clone();
         board5_ = board5.clone();
         board6_ = board6.clone();
    }

    /**
     *
     * @param obj : grid_type obj which will be cloned to another grid
     * @return returns the grid type which is cloned
     * @throws CloneNotSupportedException
     */
    protected grid_type[][] clone(grid_type[][] obj) throws CloneNotSupportedException {
        grid_type[][] fin=null;
        for(int i=0;i<N1;i++){
            for(int j=0;j<N2;j++){
                fin[i][j]= obj[i][j];
            }
        }
        return fin;
    }

    /**
     *
     * @param obj
     * @param board_type: type of the board for indication as a string such as "board1"
     * @return returns the grid type which is cloned
     */
     public grid_type[][] equal(grid_type[][] obj,String board_type){
        if(board_type=="board1"){
            N1=7;
            N2=7;
        }
         else if(board_type=="board2"){
             N1=7;
             N2=9;
         }
         else if(board_type=="board3"){
             N1=8;
             N2=8;
         }
        else if(board_type=="board4"){
             N1=7;
             N2=7;
         }
        else if(board_type=="board5"){
             N1=9;
             N2=9;
         }
        else if(board_type=="board6"){
             N1=5;
             N2=9;
         }

        grid_type[][] fin = new grid_type[N1][N2];
        for(int i=0;i<N1;i++){
            for(int j=0;j<N2;j++){
                fin[i][j]= obj[i][j];
            }
        }
        return fin;
    }

    /* cloning objects to do implemantation */
    static grid_type[][] board1_temp = board1.clone();
    static grid_type[][] board2_temp = board2.clone();
    static grid_type[][] board3_temp = board3.clone();
    static grid_type[][] board4_temp = board4.clone();
    static grid_type[][] board5_temp = board5.clone();
    static grid_type[][] board6_temp = board6.clone();

    static grid_type[][] back_board_1;
    static grid_type[][] back_board_2;
    static grid_type[][] back_board_3;
    static grid_type[][] back_board_4;
    static grid_type[][] back_board_5;
    static grid_type[][] back_board_6;

    static int N1,N2;
    /* Button array, user will press buttons two times, one for choosing and one for the target */
    static JButton[] press_array = new JButton[2]; // new array with size 2 to hold understand what buttons that user pressed

    /**
     *
     * @param board : grid that will be controlled for legal left move
     * @param i : row number
     * @param j : col number
     * @param N1 i dimension of the grid
     * @param N2 j dimension of the grid
     * @return 1 if the given values on the given grid has a legal move for left direction
     */
    @Override
    public int check_left(grid_type[][] board, int i, int j,int N1,int N2) {
        if (j >= 2 && j <= N2 - 1 && i >= 0 && i <= N1 - 1) // a cell has at least this conditions to move in left direction
        {
            if (board[i][j - 1] == grid_type.peg && board[i][j - 2] == grid_type.space && board[i][j]== grid_type.peg)
            {
                return 1; // move is eligable
            }
            else
                return 0;
        }
        else
            return 0;
    }
    /**
     *
     * @param board : grid that will be controlled for legal right move
     * @param i : row number
     * @param j : col number
     * @param N1 i dimension of the grid
     * @param N2 j dimension of the grid
     * @return 1 if the given values on the given grid has a legal move for right direction
     */
    @Override
    public int check_right(grid_type[][] board, int i, int j,int N1,int N2) {
        if (j >= 0 && j <= N2 - 3 && i >= 0 && i <= N1 - 1)
        {
            if (board[i][j + 1] == grid_type.peg && board[i][j + 2] == grid_type.space && board[i][j]== grid_type.peg) // a cell has at least this conditions to move in right direction
            {
                return 1; // move is eligable
            }
            else
                return 0;
        }
        else
            return 0;
    }
    static int row_number=0;

    /**
     *
     * @param filename name of the file
     * @return returns the grid on the file
     */
    public grid_type[][] load_file(String filename){
        filename= filename+".txt";
        int r=0;
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                if(r==0){
                    String data = myReader.nextLine();
                    r++;
                    row_number=data.length()+1;
                }
                else{
                    String data = myReader.nextLine();
                    r++;
                }
            }
            r--;
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        grid_type[][] board= new grid_type[r][row_number];
        int v=0;
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                while(v!=r){
                    String data = myReader.nextLine();
                    N2=data.length();
                    for(int i=0;i<data.length();i++){
                        if(data.charAt(i)=='*'){
                            board[v][i]=grid_type.wall;
                        }
                        else if(data.charAt(i)==' '){
                            board[v][i]=grid_type.space;
                        }
                        if(data.charAt(i)=='P'){
                            board[v][i]=grid_type.peg;
                        }
                    }
                    v++;
                }
                check_string=  myReader.nextLine();
            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        N1=v;

        return board;
    }
    @Override
    /**
     *
     * @param board : grid that will be controlled for legal up move
     * @param i : row number
     * @param j : col number
     * @param N1 i dimension of the grid
     * @param N2 j dimension of the grid
     * @return 1 if the given values on the given grid has a legal move for up direction
     */
    public int check_up(grid_type[][] board, int i, int j,int N1,int N2) {
        if (j >= 0 && j <= N2 - 1 && i >= 2 && i <= N1 - 1)
        {
            if (board[i - 1][j] == grid_type.peg && board[i - 2][j] == grid_type.space && board[i][j]== grid_type.peg) // a cell has at least this conditions to move in up direction
            {
                return 1; // move is eligable
            }
            else
                return 0;
        }
        else
            return 0;
    }
    /**
     *
     * @param board : grid that will be controlled for legal down move
     * @param i : row number
     * @param j : col number
     * @param N1 i dimension of the grid
     * @param N2 j dimension of the grid
     * @return 1 if the given values on the given grid has a legal move for down direction
     */
    @Override
    public int check_down(grid_type[][] board, int i, int j,int N1,int N2) {
        if (j >= 0 && j <= N2 - 1 && i >= 0 && i <= N1 - 3) // a cell has at least this conditions to move in down direction
        {
            if (board[i + 1][j] == grid_type.peg && board[i + 2][j] == grid_type.space && board[i][j]== grid_type.peg)
            {
                return 1; // move is eligable
            }
            else
                return 0;
        }
        else
            return 0;
    }

    /**
     *
     * @param board grid type
     * @param first_row  row number of the first touched button
     * @param first_col  col number of the first touched button
     * @param sec_row  row number of the second touched button
     * @param sec_col col number of the second touched button
     * @return 1 if the given values on the given grid has a legal move for up direction
     */
    public int check_up_manuel(grid_type[][] board,int first_row,int first_col,int sec_row,int sec_col){
        if(first_col== sec_col && board[first_row][first_col]==grid_type.peg && board[first_row-1][first_col]==grid_type.peg && board[sec_row][sec_col]==grid_type.space && first_row>=2 && sec_row == first_row-2){
            board[first_row][first_col]=grid_type.space;
            board[first_row-1][first_col]=grid_type.space;
            board[sec_row][sec_col]=grid_type.peg;
            return 1;
        }
        return 0;
    }
    /**
     *
     * @param board grid type
     * @param first_row  row number of the first touched button
     * @param first_col  col number of the first touched button
     * @param sec_row  row number of the second touched button
     * @param sec_col col number of the second touched button
     * @return 1 if the given values on the given grid has a legal move for down direction
     */
    public int check_down_manuel(grid_type[][] board,int first_row,int first_col,int sec_row,int sec_col){
        if(first_col== sec_col && board[first_row][first_col]==grid_type.peg && board[first_row+1][first_col]==grid_type.peg && board[sec_row][sec_col]==grid_type.space && first_row <= N1-3 && sec_row == first_row+2 ){
            board[first_row][first_col]=grid_type.space;
            board[first_row+1][first_col]=grid_type.space;
            board[sec_row][sec_col]=grid_type.peg;
            return 1;
        }
        return 0;
    }
    /**
     *
     * @param board grid type
     * @param first_row  row number of the first touched button
     * @param first_col  col number of the first touched button
     * @param sec_row  row number of the second touched button
     * @param sec_col col number of the second touched button
     * @return 1 if the given values on the given grid has a legal move for left direction
     */
    public int check_left_manuel(grid_type[][] board,int first_row,int first_col,int sec_row,int sec_col){
        if(first_row == sec_row && board[first_row][first_col]==grid_type.peg && board[first_row][first_col-1]==grid_type.peg && board[sec_row][sec_col]==grid_type.space && first_col>=2 && sec_col+2 == first_col){
            board[first_row][first_col]=grid_type.space;
            board[first_row][first_col-1]=grid_type.space;
            board[sec_row][sec_col]=grid_type.peg;
            return 1;
        }
        return 0;
    }
    /**
     *
     * @param board grid type
     * @param first_row  row number of the first touched button
     * @param first_col  col number of the first touched button
     * @param sec_row  row number of the second touched button
     * @param sec_col col number of the second touched button
     * @return 1 if the given values on the given grid has a legal move for right direction
     */
    public int check_right_manuel(grid_type[][] board,int first_row,int first_col,int sec_row,int sec_col){
        if(first_row == sec_row && board[first_row][first_col]==grid_type.peg && board[first_row][first_col+1]==grid_type.peg && board[sec_row][sec_col]==grid_type.space && first_col<=N2-3 && sec_col == first_col+2 ){
            board[first_row][first_col]=grid_type.space;
            board[first_row][first_col+1]=grid_type.space;
            board[sec_row][sec_col]=grid_type.peg;
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param board grid type
     * @param N1 row dimension of the grid
     * @param N2 col dimension of the grid
     * @return 1 if the game is done else returns 0
     */
    @Override
    public int check_game_is_done(grid_type[][] board,int N1, int N2) {
        int row;
        int col;
        int number_of_pegs_has=0;
        for(int i=0;i<N1*N2;i++){
            if((Integer.parseInt(button_array[i].getName()) % N2 == 0)){
                row= (Integer.parseInt(button_array[i].getName())/N2)-1;
                col=N2-1;
            }
            else{
                row= (Integer.parseInt(button_array[i].getName()))/N2;
                col= (Integer.parseInt(button_array[i].getName()))%N2-1;
            }
            if(check_right(board,row,col,N1,N2)+check_left(board,row,col,N1,N2)+check_down(board,row,col,N1,N2)+check_up(board,row,col,N1,N2) !=0 ){
                number_of_pegs_has++;
            }
        }
        if(number_of_pegs_has != 0){
            return 1;
        }
        else
            return 0;
    }

    static grid_type[][] temp;
    static JButton[] button_array;
    static int press_array_index=0;
    static String check_string;
    static JFrame f=new JFrame("Peg Soliatire");
    static int total_var1=0;
    static grid_type[][] board_temp= null;
    /*********** Action Listeners ***********/
    static JLabel [] ar_b= new JLabel[1];
    static int w=0;
    /**
     * button listener for clicking to the peg buttons
     */
    ActionListener buttonListener= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(check_game_is_done(board_temp,N1,N2)==1){
                if(w==0){
                    JLabel label;
                    int num= calculateScore(board_temp);
                    label= new JLabel(String.valueOf(num));
                    ar_b[0]= label;
                    f.add(ar_b[0]);
                    w++;
                }
                else{
                    int num= calculateScore(board_temp);
                    ar_b[0].setText(String.valueOf(num));
                }


                if(check_string=="board1"){
                    back_board_1= equal(board_temp,"board1");
                }
                else if(check_string=="board2"){
                    back_board_2= equal(board_temp,"board2");
                }
                else if(check_string=="board3"){
                    back_board_3= equal(board_temp,"board3");
                }
                else if(check_string=="board4"){
                    back_board_4= equal(board_temp,"board4");
                }
                else if(check_string=="board5"){
                    back_board_5= equal(board_temp,"board5");
                }
                else if(check_string=="board6"){
                    back_board_6= equal(board_temp,"board6");
                }

                press_array_index = press_array_index %2;
                int number=0;
                press_array[press_array_index]= (JButton) e.getSource();

                int coordinate_button_previous_row;
                int coordinate_button_previous_col;
                int coordinate_button_new_row;
                int coordinate_button_new_col;

                if(press_array_index==1){
                    JButton button_previous= press_array[0];
                    if((Integer.parseInt(press_array[0].getName())%N2 == 0 && (Integer.parseInt(press_array[1].getName())%N2 != 0))){
                        coordinate_button_previous_row= (Integer.parseInt(press_array[0].getName())/N2)-1;
                        coordinate_button_previous_col=N2-1;
                        coordinate_button_new_row= (Integer.parseInt(press_array[1].getName())/ N2);
                        coordinate_button_new_col=Integer.parseInt(press_array[1].getName())% N2-1;
                    }
                    else if((Integer.parseInt(press_array[0].getName())%N2 != 0 && (Integer.parseInt(press_array[1].getName())%N2 == 0))){
                        coordinate_button_previous_row= (Integer.parseInt(press_array[0].getName())/N2);
                        coordinate_button_previous_col=Integer.parseInt(press_array[0].getName())%N2-1;
                        coordinate_button_new_row= (Integer.parseInt(press_array[1].getName())/ N2);
                        coordinate_button_new_col= N2-1;
                    }
                    else{
                        coordinate_button_previous_row= (Integer.parseInt(press_array[0].getName())/N2);
                        coordinate_button_previous_col=Integer.parseInt(press_array[0].getName())%N2-1;
                        coordinate_button_new_row= (Integer.parseInt(press_array[1].getName())/ N2);
                        coordinate_button_new_col=Integer.parseInt(press_array[1].getName())% N2-1;
                    }

                    if(coordinate_button_new_col == coordinate_button_previous_col ){
                        if(coordinate_button_previous_row < coordinate_button_new_row){
                            check_down_manuel(board_temp,coordinate_button_previous_row,coordinate_button_previous_col,coordinate_button_new_row,coordinate_button_new_col);
                        }
                        else if(coordinate_button_previous_row > coordinate_button_new_row){
                            check_up_manuel(board_temp,coordinate_button_previous_row,coordinate_button_previous_col,coordinate_button_new_row,coordinate_button_new_col);
                        }
                    }
                    else if(coordinate_button_new_row == coordinate_button_previous_row ){
                        if(coordinate_button_previous_col < coordinate_button_new_col){
                            check_right_manuel(board_temp,coordinate_button_previous_row,coordinate_button_previous_col,coordinate_button_new_row,coordinate_button_new_col);
                        }
                        else if(coordinate_button_previous_col > coordinate_button_new_col){
                            check_left_manuel(board_temp,coordinate_button_previous_row,coordinate_button_previous_col,coordinate_button_new_row,coordinate_button_new_col);
                        }
                    }

                    for(int i = 0; i < N1; i++) {
                        for (int j = 0; j < N2; j++) {
                            if (board_temp[i][j] == grid_type.peg) {
                                number= i*N2+j;
                                button_array[number].setText("P");

                            } else if (board_temp[i][j] == grid_type.space) {
                                number= i*N2+j;
                                button_array[number].setText(" ");

                            } else if (board_temp[i][j] == grid_type.wall) {
                                number= i*N2+j;
                                button_array[number].setText("*");
                            }
                        }

                    }
                }
                press_array_index++;
            }
            else{
                JOptionPane.showMessageDialog(null, "Game is done");
            }

        }

    };
    /**
     * button listener for clicking to close the file button
     */
    ActionListener close_the_file_listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
    /**
     * button listener for clicking to save the file button
     */
    ActionListener save_to_file_listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str=JOptionPane.showInputDialog("Enter the file name:");
            str = str + ".txt";
            try {
                FileWriter myWriter = new FileWriter(str);
                for (int i = 0; i < N1; i++) {
                    for (int j = 0; j < N2; j++) {
                        if (board_temp[i][j] == grid_type.peg) {
                            myWriter.write("P");
                        } else if (board_temp[i][j] == grid_type.space) {
                            myWriter.write(" ");
                        } else if (board_temp[i][j] == grid_type.wall) {
                            myWriter.write("*");
                        }
                    }
                    myWriter.write("\n");
                }
                if(check_string=="board1"){
                    myWriter.write("board1");
                }
                else if(check_string=="board2"){
                    myWriter.write("board2");
                }
                else if(check_string=="board3"){
                    myWriter.write("board3");
                }
                else if(check_string=="board4"){
                    myWriter.write("board4");
                }
                else if(check_string=="board5"){
                    myWriter.write("board5");
                }
                else if(check_string=="board6"){
                    myWriter.write("board6");
                }
                myWriter.close();
                JOptionPane.showMessageDialog(f,"Successfully wrote to file!");
            } catch (IOException t) {
                System.out.println("An error occurred.");
                t.printStackTrace();
            }
        }

    };
    /**
     * button listener for clicking to load the file button
     */
    ActionListener load_a_file_listener= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str=JOptionPane.showInputDialog("Enter the file name:");
            grid_type[][] board=load_file(str);
            for (int i = 0; i < N1; i++) {
                for (int j = 0; j < N2; j++) {
                    if (board[i][j] == grid_type.peg) {
                        System.out.print("P");
                    } else if (board[i][j] == grid_type.space) {
                        System.out.print(" ");
                    } else if (board[i][j] == grid_type.wall) {
                        System.out.print("*");
                    }
                }
                System.out.println("\n");
            }
            if(check_string=="board1"){
                N1=7;
                N2=7;
            }
            else if(check_string=="board2"){
                N1=7;
                N2=9;
            }
            else if(check_string=="board3"){
                N1=8;
                N2=8;
            }
            else if(check_string=="board4"){
                N1=7;
                N2=7;
            }
            else if(check_string=="board5"){
                N1=9;
                N2=9;
            }
            else if(check_string=="board6"){
                N1=5;
                N2=9;
            }
            System.out.println("n1"+N1+"N2"+N2);
                f.getContentPane().removeAll();
                manage_game(board,N1,N2,check_string,1);
        }
    };
    static int first1=0;
    /**
     * button listener for clicking to reset the grid button
     */
    ActionListener reset_listener= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String board_type=null;
            if(check_string=="board1"){
                board_type= "board1";
            }
            else if(check_string=="board2"){
                board_type= "board2";
            }
            else if(check_string=="board3"){
                board_type= "board3";
            }
            else if(check_string=="board4"){
                board_type= "board4";
            }
            else if(check_string=="board5"){
                board_type= "board5";
            }
            else if(check_string=="board6"){
                board_type= "board6";
            }
            f.getContentPane().removeAll();
            manage_game(board_temp,N1,N2,board_type,0);
        }
    };
    /**
     * button listener for clicking to one step back button
     */
    ActionListener back_listener= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(check_string == "board1"){
                f.getContentPane().removeAll();
                manage_game(back_board_1,N1,N2,"board1",1);
            }
            else if(check_string == "board2"){
                f.getContentPane().removeAll();
                manage_game(back_board_2,N1,N2,"board2",1);
            }
            else if(check_string == "board3"){
                f.getContentPane().removeAll();
                manage_game(back_board_3,N1,N2,"board3",1);
            }
            else if(check_string == "board4"){
                f.getContentPane().removeAll();
                manage_game(back_board_4,N1,N2,"board4",1);
            }
            else if(check_string == "board5"){
                f.getContentPane().removeAll();
                manage_game(back_board_5,N1,N2,"board5",1);
            }
            else if(check_string == "board6"){
                f.getContentPane().removeAll();
                manage_game(back_board_6,N1,N2,"board6",1);
            }
        }
    };
    ActionListener load_a_file_listener_computer= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str=JOptionPane.showInputDialog("Enter the file name:");
            grid_type[][] board=load_file(str);
            for (int i = 0; i < N1; i++) {
                for (int j = 0; j < N2; j++) {
                    if (board[i][j] == grid_type.peg) {
                        System.out.print("P");
                    } else if (board[i][j] == grid_type.space) {
                        System.out.print(" ");
                    } else if (board[i][j] == grid_type.wall) {
                        System.out.print("*");
                    }
                }
                System.out.println("\n");
            }
            if(check_string=="board1"){
                N1=7;
                N2=7;
            }
            else if(check_string=="board2"){
                N1=7;
                N2=9;
            }
            else if(check_string=="board3"){
                N1=8;
                N2=8;
            }
            else if(check_string=="board4"){
                N1=7;
                N2=7;
            }
            else if(check_string=="board5"){
                N1=9;
                N2=9;
            }
            else if(check_string=="board6"){
                N1=5;
                N2=9;
            }
            f.getContentPane().removeAll();
            f.repaint();
            do{
                computer_game1(board,N1,N2,check_string);

            }while(total_var1!=0);

            if(total_var1==0){
                JPanel buttonPanel = new JPanel();
                buttonPanel.setSize(500,500);
                buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
                f.setSize(500,500);
                f.setLayout(null);
                f.setVisible(true);
                f.setLayout(new GridLayout(N1,N2));
                f.getContentPane().removeAll();
                int d = 20;
                int l = 20;
                for (int i = 0; i < N1; i++) {
                    for (int j = 0; j < N2; j++) {
                        d += 30;
                        if (board_temp[i][j] == grid_type.peg) {
                            JButton b = new JButton("P");
                            b.setBounds(20 + d, 40 + l, 20, 20);
                            buttonPanel.add(b);

                        } else if (board_temp[i][j] == grid_type.space) {
                            JButton b = new JButton(" ");
                            buttonPanel.add(b);
                        } else if (board_temp[i][j] == grid_type.wall) {
                            JButton b = new JButton("*");
                            b.setBounds(20 + d, 40 + l, 20, 20);
                            buttonPanel.add(b);
                        }
                    }
                    f.revalidate();
                    l += 30;
                    d = 20;
                }
                f.setLayout(new BorderLayout());
                JPanel panel= new JPanel();
                panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
                JButton save= new JButton("Save to a file");
                save.addActionListener(save_to_file_listener);
                panel.add(save,BorderLayout.WEST);
                JButton close = new JButton("Close");
                panel.add(close,BorderLayout.EAST);
                close.addActionListener(close_the_file_listener);
                f.add(buttonPanel,BorderLayout.NORTH);
                f.add(panel,BorderLayout.CENTER);

            }
        }
    };
    static int first=0;

    /**
     * function prepares the user interface initially when the program starts
     */
    public void initial_choose_function(){
        f.setSize(1000,1000);
        JPanel jContentPane = new JPanel();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBounds(61, 11, 81, 140);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        jContentPane.setAlignmentX( Component.LEFT_ALIGNMENT );
        panel.setAlignmentX( Component.LEFT_ALIGNMENT );
        jContentPane.add(panel);
        JLabel label1 = new JLabel("Choose the Game Type");
        panel.add(label1);
        JRadioButton c1 = new JRadioButton("Human Game");
        panel.add(c1);
        /**
         * radio button listener - What will be happened after user will press the human game button
         */
        c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                f.setSize(1000,1000);
                f.getContentPane().removeAll();
                f.repaint();
                JPanel jContentPane = new JPanel();
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panel.setBounds(61, 11, 81, 140);
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                jContentPane.setAlignmentX( Component.LEFT_ALIGNMENT );
                panel.setAlignmentX( Component.LEFT_ALIGNMENT );
                jContentPane.add(panel);
                ButtonGroup g = new ButtonGroup();
                JLabel label1 = new JLabel("Choose the Game");
                panel.add(label1);
                JRadioButton c1 = new JRadioButton("Game 1");
                /**
                 * Radio button listener for game 1. Manages the game with giving necessary parameter for manage game function
                 */
                panel.add(c1);
                c1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 7;
                        N2= 7;
                        check_string= "board1";
                        manage_game(board1_temp,7,7,"board1",0);
                    }
                });
                g.add(c1);
                /**
                 * Radio button listener for game 2. Manages the game with giving necessary parameter for manage game function
                 */
                JRadioButton c2 = new JRadioButton("Game 2");
                c2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 7;
                        N2= 9;
                        check_string= "board2";
                        manage_game(board2_temp,7,9,"board2",0);
                    }
                });
                panel.add(c2);
                g.add(c2);
                /**
                 * Radio button listener for game 3. Manages the game with giving necessary parameter for manage game function
                 */
                JRadioButton c3 = new JRadioButton("Game 3");
                panel.add(c3);
                c3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 8;
                        N2= 8;
                        check_string= "board3";
                        manage_game(board3_temp,8,8,"board3",0);
                    }
                });
                g.add(c3);
                /**
                 * Radio button listener for game 4. Manages the game with giving necessary parameter for manage game function
                 */
                JRadioButton c4 = new JRadioButton("Game 4");
                panel.add(c4);
                c4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 7;
                        N2= 7;
                        check_string= "board4";
                        manage_game(board4_temp,7,7,"board4",0);
                    }
                });
                g.add(c4);
                /**
                 * Radio button listener for game 5. Manages the game with giving necessary parameter for manage game function
                 */
                JRadioButton c5 = new JRadioButton("Game 5");
                panel.add(c5);
                c5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 9;
                        N2= 9;
                        check_string= "board5";
                        manage_game(board5_temp,9,9,"board5",0);
                    }
                });
                g.add(c5);
                /**
                 * Radio button listener for game 6. Manages the game with giving necessary parameter for manage game function
                 */
                JRadioButton c6 = new JRadioButton("Game 6");
                panel.add(c6);
                c6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                    }
                });
                g.add(c6);

                f.setSize(300, 200);
                f.setContentPane(jContentPane);
                f.setVisible(true);
            }
        });
        ButtonGroup g = new ButtonGroup();
        g.add(c1);
        /* computer game action listener */
        JRadioButton c2 = new JRadioButton("Computer Game");
        c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                f.setSize(1000,1000);
                f.getContentPane().removeAll();
                f.repaint();
                JPanel jContentPane = new JPanel();
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panel.setBounds(61, 11, 81, 140);
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                jContentPane.setAlignmentX( Component.LEFT_ALIGNMENT );
                panel.setAlignmentX( Component.LEFT_ALIGNMENT );
                jContentPane.add(panel);
                ButtonGroup g = new ButtonGroup();
                JLabel label1 = new JLabel("Choose the Game");
                panel.add(label1);
                JRadioButton c1 = new JRadioButton("Game 1");
                panel.add(c1);
                c1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        N1= 7;
                        N2= 7;
                        first=0;
                        do{
                            computer_game(board1_temp,7,7,"board1");

                        }while(total_var!=0);

                        if(total_var==0){
                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setSize(500,500);
                            buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
                            f.setSize(500,500);
                            f.setLayout(null);
                            f.setVisible(true);
                            f.setLayout(new GridLayout(N1,N2));
                            f.getContentPane().removeAll();
                            int d = 20;
                            int l = 20;
                            for (int i = 0; i < N1; i++) {
                                for (int j = 0; j < N2; j++) {
                                    d += 30;
                                    if (board_temp[i][j] == grid_type.peg) {
                                        JButton b = new JButton("P");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);

                                    } else if (board_temp[i][j] == grid_type.space) {
                                        JButton b = new JButton(" ");
                                        buttonPanel.add(b);
                                    } else if (board_temp[i][j] == grid_type.wall) {
                                        JButton b = new JButton("*");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);
                                    }
                                }
                                f.revalidate();
                                l += 30;
                                d = 20;
                            }
                            f.setLayout(new BorderLayout());
                            JPanel panel= new JPanel();
                            panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
                            JButton save= new JButton("Save to a file");
                            save.addActionListener(save_to_file_listener);
                            panel.add(save,BorderLayout.WEST);
                            JButton close = new JButton("Close");
                            panel.add(close,BorderLayout.EAST);
                            close.addActionListener(close_the_file_listener);
                            f.add(buttonPanel,BorderLayout.NORTH);
                            f.add(panel,BorderLayout.CENTER);
                        }
                    }
                });
                g.add(c1);
                JRadioButton c2 = new JRadioButton("Game 2");
                c2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 7;
                        N2= 9;
                        check_string= "board2";
                        first=0;
                        do{
                            computer_game(board2_temp,7,9,"board2");

                        }while(total_var!=0);

                        if(total_var==0){
                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setSize(500,500);
                            buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
                            f.setSize(500,500);
                            f.setLayout(null);
                            f.setVisible(true);
                            f.setLayout(new GridLayout(N1,N2));
                            f.getContentPane().removeAll();
                            int d = 20;
                            int l = 20;
                            for (int i = 0; i < N1; i++) {
                                for (int j = 0; j < N2; j++) {
                                    d += 30;
                                    if (board_temp[i][j] == grid_type.peg) {
                                        JButton b = new JButton("P");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);

                                    } else if (board_temp[i][j] == grid_type.space) {
                                        JButton b = new JButton(" ");
                                        buttonPanel.add(b);
                                    } else if (board_temp[i][j] == grid_type.wall) {
                                        JButton b = new JButton("*");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);
                                    }
                                }
                                f.revalidate();
                                l += 30;
                                d = 20;
                            }
                            f.setLayout(new BorderLayout());
                            JPanel panel= new JPanel();
                            panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
                            JButton save= new JButton("Save to a file");
                            save.addActionListener(save_to_file_listener);
                            panel.add(save,BorderLayout.WEST);
                            JButton close = new JButton("Close");
                            panel.add(close,BorderLayout.EAST);
                            close.addActionListener(close_the_file_listener);
                            f.add(buttonPanel,BorderLayout.NORTH);
                            f.add(panel,BorderLayout.CENTER);
                        }
                    }
                });
                panel.add(c2);
                g.add(c2);
                JRadioButton c3 = new JRadioButton("Game 3");
                panel.add(c3);
                c3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 8;
                        N2= 8;
                        check_string= "board3";
                        first=0;
                        do{
                            computer_game(board3_temp,8,8,"board3");

                        }while(total_var!=0);

                        if(total_var==0){
                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setSize(500,500);
                            buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
                            f.setSize(500,500);
                            f.setLayout(null);
                            f.setVisible(true);
                            f.setLayout(new GridLayout(N1,N2));
                            f.getContentPane().removeAll();
                            int d = 20;
                            int l = 20;
                            for (int i = 0; i < N1; i++) {
                                for (int j = 0; j < N2; j++) {
                                    d += 30;
                                    if (board_temp[i][j] == grid_type.peg) {
                                        JButton b = new JButton("P");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);

                                    } else if (board_temp[i][j] == grid_type.space) {
                                        JButton b = new JButton(" ");
                                        buttonPanel.add(b);
                                    } else if (board_temp[i][j] == grid_type.wall) {
                                        JButton b = new JButton("*");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);
                                    }
                                }
                                f.revalidate();
                                l += 30;
                                d = 20;
                            }
                            f.setLayout(new BorderLayout());
                            JPanel panel= new JPanel();
                            panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
                            JButton save= new JButton("Save to a file");
                            save.addActionListener(save_to_file_listener);
                            JButton reset= new JButton("Reset");
                            panel.add(reset,BorderLayout.SOUTH);
                            //reset.addActionListener(reset_listener);
                            panel.add(save,BorderLayout.WEST);
                            JButton close = new JButton("Close");
                            panel.add(close,BorderLayout.EAST);
                            close.addActionListener(close_the_file_listener);
                            f.add(buttonPanel,BorderLayout.NORTH);
                            f.add(panel,BorderLayout.CENTER);
                        }
                    }
                });
                g.add(c3);
                JRadioButton c4 = new JRadioButton("Game 4");
                panel.add(c4);
                c4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 7;
                        N2= 7;
                        check_string= "board4";
                        first=0;
                        do{
                            computer_game(board4_temp,7,7,"board4");

                        }while(total_var!=0);

                        if(total_var==0){
                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setSize(500,500);
                            buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
                            f.setSize(500,500);
                            f.setLayout(null);
                            f.setVisible(true);
                            f.setLayout(new GridLayout(N1,N2));
                            f.getContentPane().removeAll();
                            int d = 20;
                            int l = 20;
                            for (int i = 0; i < N1; i++) {
                                for (int j = 0; j < N2; j++) {
                                    d += 30;
                                    if (board_temp[i][j] == grid_type.peg) {
                                        JButton b = new JButton("P");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);

                                    } else if (board_temp[i][j] == grid_type.space) {
                                        JButton b = new JButton(" ");
                                        buttonPanel.add(b);
                                    } else if (board_temp[i][j] == grid_type.wall) {
                                        JButton b = new JButton("*");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);
                                    }
                                }
                                f.revalidate();
                                l += 30;
                                d = 20;
                            }
                            f.setLayout(new BorderLayout());
                            JPanel panel= new JPanel();
                            panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
                            JButton save= new JButton("Save to a file");
                            save.addActionListener(save_to_file_listener);
                            panel.add(save,BorderLayout.WEST);
                            JButton close = new JButton("Close");
                            panel.add(close,BorderLayout.EAST);
                            close.addActionListener(close_the_file_listener);
                            f.add(buttonPanel,BorderLayout.NORTH);
                            f.add(panel,BorderLayout.CENTER);
                        }
                    }
                });
                g.add(c4);
                JRadioButton c5 = new JRadioButton("Game 5");
                panel.add(c5);
                c5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();
                        N1= 9;
                        N2= 9;
                        check_string= "board5";
                        first=0;
                        do{
                            computer_game(board5_temp,9,9,"board5");

                        }while(total_var!=0);

                        if(total_var==0){
                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setSize(500,500);
                            buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
                            f.setSize(500,500);
                            f.setLayout(null);
                            f.setVisible(true);
                            f.setLayout(new GridLayout(N1,N2));
                            f.getContentPane().removeAll();
                            int d = 20;
                            int l = 20;
                            for (int i = 0; i < N1; i++) {
                                for (int j = 0; j < N2; j++) {
                                    d += 30;
                                    if (board_temp[i][j] == grid_type.peg) {
                                        JButton b = new JButton("P");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);

                                    } else if (board_temp[i][j] == grid_type.space) {
                                        JButton b = new JButton(" ");
                                        buttonPanel.add(b);
                                    } else if (board_temp[i][j] == grid_type.wall) {
                                        JButton b = new JButton("*");
                                        b.setBounds(20 + d, 40 + l, 20, 20);
                                        buttonPanel.add(b);
                                    }
                                }
                                f.revalidate();
                                l += 30;
                                d = 20;
                            }
                            f.setLayout(new BorderLayout());
                            JPanel panel= new JPanel();
                            panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
                            JButton save= new JButton("Save to a file");
                            save.addActionListener(save_to_file_listener);
                            panel.add(save,BorderLayout.WEST);
                            JButton close = new JButton("Close");
                            panel.add(close,BorderLayout.EAST);
                            close.addActionListener(close_the_file_listener);
                            f.add(buttonPanel,BorderLayout.NORTH);
                            f.add(panel,BorderLayout.CENTER);
                        }
                    }
                });
                g.add(c5);
                JRadioButton c6 = new JRadioButton("Game 6");
                panel.add(c6);
                c6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        f.getContentPane().removeAll();
                        f.repaint();

                    }
                });
                g.add(c6);
                JButton load= new JButton("Load from a file");
                load.addActionListener(load_a_file_listener_computer);
                panel.add(load);
                f.setSize(300, 200);
                f.setContentPane(jContentPane);
                f.setVisible(true);
            }
        });

        g.add(c2);
        panel.add(c2);
        f.setSize(1000,1000);
        f.setContentPane(jContentPane);
        f.setVisible(true);
    }

    /**
     *
     * @param board1_temp : static board type.
     * @param N1 : row parameter for board
     * @param N2 : col parameter for board
     * @param board_type : type of the board as a string such as  "board1" for indication
     * @param z : initial parameter to understand if the game is just started or not
     * @return manages the game, makes the moves, creates the GridLayout, buttons inside it has action listeners to make the moves that user
     * selected
     */
    public grid_type[][] manage_game(grid_type[][] board1_temp,int N1,int N2,String board_type,int z) {
        if(z==0){
            if(board_type == "board1"){
                board_temp = equal(board1,"board1");
            }
            else if(board_type == "board2"){
                board_temp = equal(board2,"board2");
            }
            else if(board_type == "board3"){
                board_temp = equal(board3,"board3");
            }
            else if(board_type == "board4"){
                board_temp = equal(board4,"board4");
            }
            else if(board_type == "board5"){
                board_temp = equal(board5,"board5");
            }
            else if(board_type == "board6"){
                board_temp = equal(board6,"board6");
            }
        }
        else{
            board_temp=board1_temp.clone();
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(500,500);
        buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
        button_array = new JButton[N1*N2];
        int k=0;
        f.revalidate();
        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);
        f.setLayout(new GridLayout(N1,N2));
        int d = 20;
        int l = 20;
        for (int i = 0; i < N1; i++) {
            for (int j = 0; j < N2; j++) {
                d += 30;
                if (board_temp[i][j] == grid_type.peg) {
                    JButton b = new JButton("P");
                    b.setBounds(20 + d, 40 + l, 20, 20);
                    b.addActionListener(buttonListener);
                    b.setName(String.valueOf(k+1));
                    buttonPanel.add(b);
                    button_array[k] = b;
                    k++;
                } else if (board_temp[i][j] == grid_type.space) {
                    JButton b = new JButton(" ");
                    b.setBounds(20 + d, 40 + l, 20, 20);
                    b.addActionListener(buttonListener);
                    b.setName(String.valueOf(k+1));
                    buttonPanel.add(b);
                    button_array[k] = b;
                    k++;
                } else if (board_temp[i][j] == grid_type.wall) {
                    JButton b = new JButton("*");
                    b.setBounds(20 + d, 40 + l, 20, 20);
                    b.addActionListener(buttonListener);
                    b.setName(String.valueOf(k+1));
                    buttonPanel.add(b);
                    button_array[k] = b;
                    k++;
                }
            }
            f.revalidate();
            l += 30;
            d = 20;
        }
        f.setLayout(new BorderLayout());

        JButton back= new JButton("One Step Back");
        back.setSize(50,50);
        back.addActionListener(back_listener);
        JPanel panel= new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
        panel.add(back,BorderLayout.EAST);
        JButton save= new JButton("Save to a file");
        save.addActionListener(save_to_file_listener);
        panel.add(save,BorderLayout.WEST);
        JButton reset= new JButton("Reset");
        panel.add(reset,BorderLayout.SOUTH);
        reset.addActionListener(reset_listener);
        JButton load= new JButton("Load from a file");
        load.addActionListener(load_a_file_listener);
        panel.add(load,BorderLayout.CENTER);
        JButton close = new JButton("Close");
        panel.add(close,BorderLayout.CENTER);
        close.addActionListener(close_the_file_listener);
        f.add(buttonPanel,BorderLayout.NORTH);
        f.add(panel,BorderLayout.CENTER);
        return temp;
    }

    /**
     *
     * @param board board type, it's number of peg's left will be calculated inside the function
     * @return the number of peg left in the game
     */
    @Override
    public int calculateScore(grid_type[][] board) {
        int peg_number=0;
        for(int i=0;i<N1;i++){
            for(int j=0;j<N2;j++){
               if( board[i][j]==grid_type.peg){
                    peg_number++;
                }
            }
        }
        return peg_number;
    }

static int total_var=0;

    /**
     *
     * @param board board type
     * @param N1  : row dimension of the board
     * @param N2  : col dimension of the board
     * @param board_type type of the board as string
     * Manages the computer game. Makes the moves until end and finally shows the final board to screen.
     */
    @Override
    public void computer_game(grid_type[][] board,int N1,int N2,String board_type) {
        if(first==0){
            if(board_type == "board1"){
                board_temp= board1_temp.clone();
            }
            else if(board_type == "board2"){
                board_temp = board2_temp.clone();
            }
            else if(board_type == "board3"){
                board_temp = board3_temp.clone();
            }
            else if(board_type == "board4"){
                board_temp = board4_temp.clone();
            }
            else if(board_type == "board5"){
                board_temp = board5_temp.clone();
            }
            else if(board_type == "board6"){
                board_temp = board6_temp.clone();
            }
        }
        first++;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(500,500);
        buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
        f.revalidate();
        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);
        f.setLayout(new GridLayout(N1,N2));
        int [][] list = new int[N1*N2*4][2];
        int [][] direction_number_list= new int[N1*N2][1];
        int direction_number_list_index=0;
        total_var=0;
        int row_var=0;
        int flag=0,flag1=0,flag2=0,flag3=0;
        /*directions: 0: left 1:right 2:down 3: up */
        for(int i=0;i<N1*N2;i++){
            int row= i/ N2;
            int j= i% N2;
            if(check_left(board_temp,row,j,N1,N2)==1){
                list[total_var][0]= row_var;
                list[total_var][1]= 0;
                total_var++;
                flag=1;
            }
            if(check_right(board_temp,row,j,N1,N2)==1){
                list[total_var][0]= row_var;
                list[total_var][1]= 1;
                total_var++;
                flag1=1;
            }
            if(check_down(board_temp,row,j,N1,N2)==1){
                list[total_var][0]= row_var;
                list[total_var][1]= 2;
                total_var++;
                flag2=1;
            }
            if(check_up(board_temp,row,j,N1,N2)==1){
                list[total_var][0]= row_var;
                list[total_var][1]= 3;
                total_var++;
                flag3=1;
            }
            if(flag+flag1+flag2+flag3!=0)
                direction_number_list_index++;
            direction_number_list[i][0]= direction_number_list_index;
            flag=0;
            flag1=0;
            flag2=0;
            flag3=0;
            direction_number_list_index=0;
            row_var++;
        }
        int col,row,randomNum;
        if(total_var==0)
            return;
        int temp_number;
        do{
             randomNum = (int) ((Math.random() * (total_var - 0)) + 0)  ;
             temp_number= list[randomNum][0];
             col= temp_number % N2;
             row= temp_number / N2;

        } while(board_temp[row][col] == grid_type.space || board_temp[row][col] == grid_type.wall || direction_number_list[temp_number][0]==0);

        int k=0;
        int[] last_array = new int[4];
        for(int i=0;i<N1*N2;i++){
            if(list[i][0]==temp_number){
                last_array[k]=list[i][1];
                    k++;
                }
        }

        int index= (int) ((Math.random() * (k - 0)) + 0) ;
        int random_direction_index= last_array[index];
        if(random_direction_index == 0 && col>=2 && board_temp[row][col]== grid_type.peg && board_temp[row][col-1]==grid_type.peg &&  board_temp[row][col-2]==grid_type.space ){
            board_temp[row][col-2]=grid_type.peg;
            board_temp[row][col-1]=grid_type.space;
            board_temp[row][col]=grid_type.space;
        }
        else if(random_direction_index == 1 && col<=N2-3 &&  board_temp[row][col]== grid_type.peg && board_temp[row][col+1]==grid_type.peg &&  board_temp[row][col+2]==grid_type.space){
            board_temp[row][col+2]=grid_type.peg;
            board_temp[row][col+1]=grid_type.space;
            board_temp[row][col]=grid_type.space;
        }
        else if(random_direction_index == 2 && row <= N1-3 && board_temp[row][col]== grid_type.peg && board_temp[row+1][col]== grid_type.peg && board_temp[row+2][col]== grid_type.space){
            board_temp[row+2][col]=grid_type.peg;
            board_temp[row+1][col]=grid_type.space;
            board_temp[row][col]=grid_type.space;
        }
        else if(random_direction_index == 3 && row >=2 && board_temp[row][col]== grid_type.peg && board_temp[row-1][col]== grid_type.peg && board_temp[row-2][col]== grid_type.space){
            board_temp[row-2][col]=grid_type.peg;
            board_temp[row-1][col]=grid_type.space;
            board_temp[row][col]=grid_type.space;
        }

    }
    /**
     *
     * @param board board type
     * @param N1  : row dimension of the board
     * @param N2  : col dimension of the board
     * @param board_type type of the board as string
     * Manages the computer game but if it is loaded from file. Makes the moves until end and finally shows the final board to screen.
     */
    public void computer_game1(grid_type[][] board,int N1,int N2,String board_type) {
        if(first1==0){
            board_temp = equal(board,board_type);
            first1++;
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(500,500);
        buttonPanel.setLayout(new GridLayout(N1, N2, 5, 5));
        f.revalidate();
        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);
        f.setLayout(new GridLayout(N1,N2));
        int [][] list = new int[N1*N2*4][2];
        int [][] direction_number_list= new int[N1*N2][1];
        int direction_number_list_index=0;
        total_var1=0;
        int row_var=0;
        int flag=0,flag1=0,flag2=0,flag3=0;
        /*directions: 0: left 1:right 2:down 3: up */
        for(int i=0;i<N1*N2;i++){
            int row= i/ N2;
            int j= i% N2;
            if(check_left(board_temp,row,j,N1,N2)==1){
                list[total_var1][0]= row_var;
                list[total_var1][1]= 0;
                total_var1++;
                flag=1;
            }
            if(check_right(board_temp,row,j,N1,N2)==1){
                list[total_var1][0]= row_var;
                list[total_var1][1]= 1;
                total_var1++;
                flag1=1;
            }
            if(check_down(board_temp,row,j,N1,N2)==1){
                list[total_var1][0]= row_var;
                list[total_var1][1]= 2;
                total_var1++;
                flag2=1;
            }
            if(check_up(board_temp,row,j,N1,N2)==1){
                list[total_var1][0]= row_var;
                list[total_var1][1]= 3;
                total_var1++;
                flag3=1;
            }

            if(flag+flag1+flag2+flag3!=0)
                direction_number_list_index++;
            direction_number_list[i][0]= direction_number_list_index;
            flag=0;
            flag1=0;
            flag2=0;
            flag3=0;
            direction_number_list_index=0;
            row_var++;
        }

        int col,row,randomNum;
        if(total_var1==0)
            return;
        int temp_number;
        do{
            randomNum = (int) ((Math.random() * (total_var1 - 0)) + 0)  ;
            temp_number= list[randomNum][0];
            col= temp_number % N2;
            row= temp_number / N2;

        } while(board_temp[row][col] == grid_type.space || board_temp[row][col] == grid_type.wall || direction_number_list[temp_number][0]==0);

        int k=0;
        int[] last_array = new int[4];
        for(int i=0;i<N1*N2;i++){
            if(list[i][0]==temp_number){
                last_array[k]=list[i][1];
                k++;
            }
        }

        int index= (int) ((Math.random() * (k - 0)) + 0) ;
        int random_direction_index= last_array[index];
        if(random_direction_index == 0 && col>=2 && board_temp[row][col]== grid_type.peg && board_temp[row][col-1]==grid_type.peg &&  board_temp[row][col-2]==grid_type.space ){
            board_temp[row][col-2]=grid_type.peg;
            board_temp[row][col-1]=grid_type.space;
            board_temp[row][col]=grid_type.space;
        }
        else if(random_direction_index == 1 && col<=N2-3 &&  board_temp[row][col]== grid_type.peg && board_temp[row][col+1]==grid_type.peg &&  board_temp[row][col+2]==grid_type.space){
            board_temp[row][col+2]=grid_type.peg;
            board_temp[row][col+1]=grid_type.space;
            board_temp[row][col]=grid_type.space;
        }
        else if(random_direction_index == 2 && row <= N1-3 && board_temp[row][col]== grid_type.peg && board_temp[row+1][col]== grid_type.peg && board_temp[row+2][col]== grid_type.space){
            board_temp[row+2][col]=grid_type.peg;
            board_temp[row+1][col]=grid_type.space;
            board_temp[row][col]=grid_type.space;
        }
        else if(random_direction_index == 3 && row >=2 && board_temp[row][col]== grid_type.peg && board_temp[row-1][col]== grid_type.peg && board_temp[row-2][col]== grid_type.space){
            board_temp[row-2][col]=grid_type.peg;
            board_temp[row-1][col]=grid_type.space;
            board_temp[row][col]=grid_type.space;
        }

    }

}
