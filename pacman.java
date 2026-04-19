import java.util.Random;
import java.util.Scanner;

class pacman {
    char[][] grid;
    public int pacman_row;
    public int pacman_column;
    public  int ghost_row;
    public int ghost_column;


    enum directions {
        right,
        left,
        up,
        down,

    }

    directions direction;
    int score = 0;

    public void pacman_movement() {

        if (direction == directions.right) {
            if (grid[pacman_row][pacman_column + 1] != '#') {
                pacman_column += 1;
                if (grid[pacman_row][pacman_column]== '.'){
                    score +=1;
                }
                grid[pacman_row][pacman_column] = ' ';
            }

        }
        else if (direction == directions.up) {
            if (grid[pacman_row - 1][pacman_column] != '#') {
                pacman_row -= 1;
            }
            if (grid[pacman_row][pacman_column]== '.'){
                score +=1;
            }
            grid[pacman_row][pacman_column] = ' ';

        }
        else if (direction == directions.left) {
            if (grid[pacman_row][pacman_column - 1] != '#') {
                pacman_column = pacman_column - 1;
            }
            if (grid[pacman_row][pacman_column]== '.'){
                score +=1;
            }
            grid[pacman_row][pacman_column] = ' ';

        }
        else if (direction == directions.down) {
            if (grid[pacman_row + 1][pacman_column] != '#') {
                pacman_row += 1;
                if (grid[pacman_row][pacman_column]== '.'){
                    score +=1;
                }
                grid[pacman_row][pacman_column] = ' ';
            }

        }


    }
    public void show_grid(){
        for(int i=0 ; i< grid.length ; i++){
            for(int j=0 ; j<grid[i].length ; j++){
                if (i == ghost_row  &&  i == pacman_row &&  j == ghost_column && j == pacman_column){
                    System.out.print("G");
                }
                if (i == pacman_row && j == pacman_column){
                    System.out.print("p");
                } else if (i== ghost_row && j == ghost_column) {
                    System.out.print("G");

                } else{
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }
    }
    public void ghost_movement(){

        Random rand = new Random();
        int moves = rand.nextInt(4);
        switch (moves){
            case 0 :
              if(ghost_column> 0 && grid[ghost_row][ghost_column-1] != '#'){
                  ghost_column-=1;

              }
                break;
            case 1 :
                if( ghost_column < grid[0].length && grid[ghost_row][ghost_column+1] != '#'){
                    ghost_column+=1;

            }
                break;
            case 2 :
                if(ghost_row > 0 && grid[ghost_row - 1][ghost_column] != '#'){
                    ghost_row-=1;

            }
                break;
            case 3:
                if  ( ghost_row < grid.length && grid[ghost_row+1][ghost_column]!= '#'){
                       ghost_row+=1;

            }
                break;
        }

    }
    public boolean remaing_dots() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }
}


class Main{
    public static void main( String[] args) {

        pacman p1 = new pacman();
        p1.grid = new char[][] {
                {'#','#','#','#','#','#','#'},
                {'#','.','.','.','.','.','#'},
                {'#','.','.','.','.','.','#'},
                {'#','#','#','#','#','#','#'}
        };
        p1.pacman_row=2;
        p1.pacman_column=2;
        p1.ghost_row = 1;
        p1.ghost_column =2;


        Scanner sc = new Scanner(System.in);
        while (true){

            System.out.println("enter your next move:(w=up , s=down ,a=left , d=right ");
            String input = sc.nextLine();
            if (input.equals("w")){
                p1.direction = pacman.directions.up;

            } else if (input.equals("s")) {
                p1.direction = pacman.directions.down;
            } else if (input.equals("a")) {
                p1.direction = pacman.directions.left;
            } else if (input.equals("d")) {
                p1.direction = pacman.directions.right;
            }
            p1.pacman_movement();
            p1.ghost_movement();
            System.out.println("score is " + p1.score);

            p1.show_grid();
            System.out.println("       ");

            if (p1.ghost_row== p1.pacman_row && p1.ghost_column == p1.pacman_column){


                System.out.println("you loser☺☻☻");
                break;
            }

            if (p1.remaing_dots()){
                p1.show_grid();
                System.out.println("congratulations you have won the game:♥♥♥♥ ♥♥ ○○YOHOOOOOOOOOO○○ ☺☻♥♥☻☺");
                // these line  is not plagarized  ALT +2 = ☻   ALT+1 =☺   AND  SO ON
                break;
            }

            try{
                Thread.sleep(1000);
            }
            catch (Exception e){}

        }




    }

}
