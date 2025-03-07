import java.util.*;
import java.io.*;
class chess {
     int board[][];

     chess() {
          this.board = new int[][] {{-4, -6, -5, -3, -2, -5, -6, -4},
                                    {-1, -1, -1, -1, -1, -1, -1, -1},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1},
                                    {4, 6, 5, 3, 2, 5, 6, 4}};
     }
     chess(int board[][]) { this.board = board; }

     void display() {
          int count = 0;
          for (int i = 0; i < 8; i++) {
            System.out.print(8-i+" ");
               for (int j = 0; j < 8; j++) {
                    int piece = board[i][j];
                    if (piece == 0) {
                         if (count % 2 == 0) {
                              System.out.print("■ ");
                         } else {
                              System.out.print("□ ");
                         }
                    } else if (piece > 0) {
                         switch (piece) {
                         case 1:
                              System.out.print("♟ ");
                              break;
                         case 4:
                              System.out.print("♜ ");
                              break;
                         case 6:
                              System.out.print("♞ ");
                              break;
                         case 5:
                              System.out.print("♝ ");
                              break;
                         case 3:
                              System.out.print("♛ ");
                              break;
                         case 2:
                              System.out.print("♚ ");
                              break;
                         case 10:
                              System.out.print(". ");
                         default:
                              System.out.print(board[i][j]);
                              break;
                         }
                    } else {
                         switch (piece) {
                         case -1:
                              System.out.print("♙ ");
                              break;
                         case -4:
                              System.out.print("♖ ");
                              break;
                         case -6:
                              System.out.print("♘ ");
                              break;
                         case -5:
                              System.out.print("♗ ");
                              break;
                         case -3:
                              System.out.print("♕ ");
                              break;
                         case -2:
                              System.out.print("♔ ");
                              break;
                         default:
                              System.out.print(board[i][j]);
                              break;
                         }
                    }
                    count++;
               }
               System.out.println();

               count++;
          }
          System.out.println("  a b c d e f g h");
     }
     public boolean isOpp(int i, int j, int x, int y) {
          if (board[x][y] == 0 || board[i][j] == 0)
               return false;
          if (board[i][j] > 0 && board[x][y] > 0) {
               return false;
          }
          if (board[i][j] < 0 && board[x][y] < 0) {
               return false;
          }
          return true;
     }
     public boolean isSame(int i, int j, int x, int y) {

          if (board[i][j] == 0 || board[x][y] == 0)
               return false;
          if (board[i][j] > 0 && board[x][y] > 0) {
               return true;
          }
          if (board[i][j] < 0 && board[x][y] < 0) {
               return true;
          }
          return false;
     }

     public boolean isEmpty(int i, int j) {
          if (board[i][j] == 0) {
               return true;
          }
          return false;
     }
     public boolean isValid(int x, int y) {
          if (x >= 0 && x <= 7 && y >= 0 && y <= 7)
               return true;
          return false;
     }
     public void makeMove(int i, int j, int x, int y) {
          board[x][y] = board[i][j];
          board[i][j] = 0;
     }

     public boolean isNotEmpty(int x, int y) { return !isEmpty(x, y); }
     public int move(int i, int j, int x, int y) {
          System.out.println("i : " + i + " j : " + j + " x : " + x +
                             " y : " + y);
          if (!isValid(i, j)) {
               System.out.println("Invalid Source ");
               return -101;
          }
          if (!isValid(x, y)) {
               System.out.println("Invalid Destination");
               return -102;
          }
          if (board[i][j] == 0) {
               System.out.println("No Piece at Source");
               return -104;
          }
          if (board[x][y] != 0 && isSame(i, j, x, y)) {
               System.out.println("Attacking Same Piece");
               return -103;
          }
          int piece = board[i][j];
          int place = board[x][y];
          if (piece == 1) {
               if (i == 6) {
                    if (x == 5 &&
                        ((y == (j + 1) || y == (j - 1)) && isOpp(i, j, x, y))) {
                         makeMove(i, j, x, y);
                         return 1;
                    } else if (isEmpty(x, y) && (x == 5 || x == 4) && j == y) {
                         makeMove(i, j, x, y);
                         return 1;
                    } else
                         return -2;
               } else if (Math.abs(x - i) == 1) {
                    makeMove(i, j, x, y);
                    return 1;
               } else
                    return -3;
          }
          if (piece == -1) {
               if (i == 1) {
                    if (x == 2 && ((y == (j + 1) || y == (j - 1)) &&
                                   isOpp(i, j, x, y))) { // SIDE KILL
                         makeMove(i, j, x, y);
                         return 1;
                    } else if (isEmpty(x, y) && (x == 2 || x == 3) && j == y) {
                         makeMove(i, j, x, y);
                         return 1;
                    } else
                         return -4;
               } else if (Math.abs(i - x) == 1) {
                    makeMove(i, j, x, y);
                    return 1;
               } else
                    return -5;
          }
          if (piece == 2 || piece == -2) {
               if ((Math.abs(i - x) == 1 && Math.abs(j - y) == 1) ||
                   (Math.abs(i - x) == 0 && Math.abs(j - y) == 1) ||
                   (Math.abs(i - x) == 1 && Math.abs(j - y) == 0)) {
                    int tempp = board[x][y];
                    int tempm = board[i][j];
                    board[x][y] = board[i][j];
                    board[i][j] = 0;
                    if (!(horseAttack(x, y) || xAttack(x, y) ||
                          lineAttack(x, y))) {
                         // System.out.println("i am ehere");
                         return 1;
                    }
                    System.out.println("horseAttack : " + horseAttack(x, y));
                    System.out.println("xAttack : " + xAttack(x, y));
                    System.out.println("lineAttack : " + lineAttack(x, y));
                    board[x][y] = tempp;
                    board[i][j] = tempm;
                    return -6;
               }
          }
          if (Math.abs(piece) == 4) {
               boolean flag = true;
               if (x == i) {
                    int start = Math.min(y, j);
                    int end = Math.max(y, j);
                    for (int k = start + 1; k < end; k++) {
                         if (!isEmpty(i, k)) {
                              flag = false;
                              break;
                         }
                    }

               } else if (j == y) {
                    int start = Math.min(x, i);
                    int end = Math.max(x, i);
                    for (int k = start + 1; k < end; k++) {
                         if (!isEmpty(k, j)) {
                              flag = false;
                              break;
                         }
                    }
               } else {
                    return -9;
               }
               if (flag == true) {
                    makeMove(i, j, x, y);
                    return 1;
               }
               return -10;
          }
          if (Math.abs(piece) == 5) {
               if (Math.abs(x - i) != Math.abs(y - j))
                    return -11;
               int xdir = (x > i) ? 1 : -1;
               int ydir = (y > j) ? 1 : -1;
               int a = i + xdir;
               int b = j + ydir;
               while (isValid(a, b) && (a != x && b != y)) {
                    if (!isEmpty(a, b))
                         return -12;
                    // board[a][b] = 10;

                    a += xdir;
                    b += ydir;
               }
               makeMove(i, j, x, y);
               return 1;
          }
          if (Math.abs(piece) == 6) {
               if (Math.abs(i - x) == 1 && Math.abs(j - y) == 2) {
                    makeMove(i, j, x, y);
                    return 1;
               }
               if (Math.abs(i - x) == 2 && Math.abs(j - y) == 1) {
                    makeMove(i, j, x, y);
                    return 1;
               }
               return -13;
          }
          if (Math.abs(piece) == 3) {
               if (x == i || j == y) {
                    boolean flag = true;
                    if (x == i) {

                         int start = Math.min(y, j);
                         int end = Math.max(y, j);
                         System.out.println("start HERE " + start + " end " +
                                            end);
                         for (int k = start + 1; k < end; k++) {
                              if (!isEmpty(i, k)) {
                                   System.out.println("is not Empty" + i + " " +
                                                      k);
                                   flag = false;
                                   break;
                              }
                         }

                    } else if (j == y) {

                         int start = Math.min(x, i);
                         int end = Math.max(x, i);
                         System.out.println("start " + start + " end " + end);
                         for (int k = start + 1; k < end; k++) {
                              if (!isEmpty(k, j)) {
                                   System.out.println("is not Empty" + k + " " +
                                                      j);
                                   flag = false;
                                   break;
                              }
                         }
                    } else {
                         return -14;
                    }
                    if (flag == true) {

                         makeMove(i, j, x, y);
                         return 1;
                    }
                    return -15;

               } else if (Math.abs(x - i) != Math.abs(y - j)) {
                    System.out.println("(x-i)" + Math.abs(x - i) + " " +
                                       Math.abs(y - j));
                    return -16;
               } else {
                    int xdir = (x > i) ? 1 : -1;
                    int ydir = (y > j) ? 1 : -1;
                    int a = i + xdir;
                    int b = j + ydir;
                    while (isValid(a, b) && (a != x && b != y)) {
                         if (!isEmpty(a, b))
                              return -17;
                         // board[a][b] = 10;

                         a += xdir;
                         b += ydir;
                    }
                    makeMove(i, j, x, y);
                    return 1;
               }
          }

          return -18;
     }
     public boolean horseAttack(int i, int j) {
          int places[][] = new int[][] {{1, 2}, {-1, 2}, {1, -2}, {-1, -2},
                                        {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};
          for (int k = 0; k < places.length; k++) {
               if (isValid(i + places[k][0], j + places[k][1])) {
                    if (isNotEmpty(places[k][0] + i, places[k][1] + j) &&
                        isOpp(i, j, i + places[k][0], j + places[k][1]) &&
                        Math.abs(board[i + places[k][0]][j + places[k][1]]) ==
                            6)
                         return true;
                    // board[i + places[k][0]][j + places[k][1]] = 10;
               }
          }
          return false;
     }
     public boolean xAttack(int i, int j) {
          boolean flag = false;
          int places[][] = new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
          for (int k = 0; k < places.length; k++) {
               int x = i + places[k][0];
               int y = j + places[k][1];
               while (isValid(x, y)) {
                    if (isNotEmpty(x, y) && isSame(i, j, x, y)) {
                         // board[x][y]=10;
                         break;
                    } else if (isNotEmpty(x, y) && isOpp(i, j, x, y) &&
                               ((Math.abs(board[x][y]) == 3) ||
                                (Math.abs(board[x][y]) == 5))) {
                         System.out.println("Opp at : " + x + " " + y);
                         // board[x][y]=10;
                         flag = true;
                         break;
                    } else {
                         // board[x][y] = 10;
                         x += places[k][0];
                         y += places[k][1];
                    }
               }
          }
          return flag;
     }

     public boolean lineAttack(int i, int j) {
          boolean flag = false;
          int places[][] = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
          for (int k = 0; k < places.length; k++) {
               int x = i + places[k][0];
               int y = j + places[k][1];
               while (isValid(x, y)) {
                    if (isNotEmpty(x, y) && isSame(i, j, x, y)) {
                         // board[x][y]=10;
                         break;
                    } else if (isNotEmpty(x, y) && isOpp(i, j, x, y) &&
                               ((Math.abs(board[x][y]) == 3) ||
                                (Math.abs(board[x][y]) == 4))) {
                         // board[x][y]=10;
                         flag = true;
                         break;
                    } else {
                         // board[x][y] = 10;
                         x += places[k][0];
                         y += places[k][1];
                    }
               }
          }
          return flag;
     }
     public static void main(String args[])throws IOException {
          chess game = new chess();
          // game.board[4][4] = 3;
          //   game.board[34 - 1] = 2;
          //    System.out.println(game.horseAttack(34-1));
          //   game.board[1][4] = 2;
          //   game.board[4][7] = -5;
          //   System.out.println(game.xAttack(1, 4));
          //   System.out.println(game.lineAttack(1, 4));
          //   System.out.println(game.horseAttack(1, 4));
          //   game.display();
          //   System.out.println(game.move(2, 5, 0, 6));
          //   System.out.println(game.move(1,5,2,5));
          //   game.display();
          //   System.out.println(game.move(6, 4, 4, 4));
          //   game.play();
          //   game.display();
          //   System.out.println(game.move(6,3,4,3));
          //   game.play();
          //   game.display();
          //   System.out.println(game.move(4, 4, 5, 4));
          //   game.display();
          //   System.out.println(game.move(7,3,3,3));
          //   game.display();
          // int i = 0;
          InputStreamReader ir=new InputStreamReader(System.in);
          BufferedReader br=new BufferedReader(ir);
          Scanner sc = new Scanner(System.in);
          int player = 1;
          game.display();
          System.out.println("ENTER DEPTH, 3 or 5 is reccomended, 5 takes a "
                             + "minute to respond");
          int d = sc.nextInt();
          System.out.println("Enter color +1 white -1 black");
          int color = sc.nextInt();
                    // int i = 0;
          while (true) {
               System.out.println("ENTER UR MOVE");
               String input = br.readLine().trim();
               String[] notation= input.split(" ");
               int j = notation[0].charAt(0) - 'a';
               int i =
                   game.board.length - Integer.parseInt(notation[0].substring(1));
               int y = notation[1].charAt(0) - 'a';
               int x = game.board.length  -
                       Integer.parseInt(notation[1].substring(1));
               play test = new play(1, game.board, color);
               System.out.println("i:"+i+" j:"+j+"x:"+x+"y:"+y);
               while (test.move(i, j, x, y) < 0) {
                    System.out.println("ERROR " + test.move(i, j, x, y));
                    System.out.println("ENTER valid MOVE");
                    input = br.readLine().trim();
                    notation = input.split(" ");
                    j = notation[0].charAt(0) - 'a';
                    i = game.board.length - 2 -
                        Integer.parseInt(notation[0].substring(1));
                    y = notation[1].charAt(0) - 'a';
                    x = game.board.length - 2 -
                        Integer.parseInt(notation[1].substring(1));

                    game.display();
               }
               game.makeMove(i, j, x, y);
               game.display();
               play inst = new play(d, game.board, -1 * color);
               System.out.println("thinking ....");
               inst.makeMove("");
               // System.out.println(a);
               // player = -1 * player;
               i++;
               game.display();
               // i++;
          }
          /*game.display();
         play inst = new play(5, game.board, 1);
          inst.makeMove("");
          game.display();*/
          // inst=new play(2,game.board,1);
          // inst.makeMove("");
          // game.display();
          //  game.play();
          //  game.display();
          //  game.display();
          // System.out.println(inst.move(6 ,7 ,5 ,6));
     }
}
