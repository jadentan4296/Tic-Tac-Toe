import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
        };
        
        printBoard(board);
        System.out.println();

        int first = first();

        do {
            if(first==0){
                computer(board);
                if(done(board)) {
                    break;
                }
                player(board);
            } else {
                player(board);
                if(done(board)) {
                    break;
                }
                computer(board);
            }
        } while(!done(board));

    }
    
    private static int first() {
        return new Random().nextInt(2);
    }

    private static void printBoard(char[][] board) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void computer(char[][] board) {
        int row;
        int column;
        do {
            row = new Random().nextInt(3)*2;
            column = new Random().nextInt(3)*2;
        } while(board[row][column]!=' ');

        board[row][column] = 'O';

        printBoard(board);
    }

    private static void player(char[][] board) {
        Scanner scn = new Scanner(System.in);
        int userInput;
        int row;
        int column;
        System.out.println("Choose a spot: ");
        do {
            userInput = scn.nextInt();
            row = (userInput-1)/3*2;
            column = (userInput-1)%3*2;
            if(board[row][column]!=' ') {
                System.out.println("Spot taken, choose another spot: ");
            }
         }
        while(board[row][column]!=' ');

        board[row][column] = 'X';

        printBoard(board);
    }

    private static boolean winner(char[][] board) {
        for(int i=0; i<=4; i+=2) {
            if(board[i][0]!=' ' && board[i][0]==board[i][2] &&
                board[i][2]==board[i][4]) {
                    System.out.println("Good Game!");
                    return true;
            }

            if(board[0][i]!=' ' && board[0][i]==board[2][i] &&
                board[2][i]==board[4][i]) {
                    System.out.println("Good Game!");
                    return true;
                }
        }

        if(board[0][0]!=' ' && board[0][0]==board[2][2] &&
                board[2][2]==board[4][4]) {
                    System.out.println("Good Game!");
                    return true;
                }

        if(board[4][0]!=' ' && board[4][0]==board[2][2] &&
            board[2][2]==board[0][4]) {
                System.out.println("Good Game!");
                return true;
            }
        return false;
    }

    private static boolean filled(char[][] board) {
        for(int row=0; row<=4; row+=2) {
            for(int column=0; column<=4; column+=2) {
                if(board[row][column]==' ') {
                    return false;
                }
            }
        }
        System.out.println("Tie!");
        return true;
    }

    private static boolean done(char[][] board) {
        if(winner(board) || filled(board)) {
            return true;
        }
        return false;
    }
}