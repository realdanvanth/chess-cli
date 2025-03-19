import java.util.*;
class play {
     int board[][];
     int player;
     item items[];
     int piecelength;
     int depth;
     int kingx;
     int kingy;
     private static final int kingval = 350;
     private static final int queenval = 900;
     private static final int rookval = 500;
     private static final int bishopval = 300;
     private static final int knightval = 300;
     private static final int pawnval = 100;

     static int pawn[][] = new int[][] {
         {0, 0, 0, 0, 0, 0, 0, 0},         {50, 50, 50, 50, 50, 50, 50, 50},
         {10, 10, 20, 30, 30, 20, 10, 10}, {5, 5, 10, 27, 27, 10, 5, 5},
         {0, 0, 0, 25, 25, 0, 0, 0},       {5, -5, -10, 0, 0, -10, -5, 5},
         {5, 10, 10, -25, -25, 10, 10, 5}, {0, 0, 0, 0, 0, 0, 0, 0}};

     static int knight[][] =
         new int[][] {{-50, -40, -30, -30, -30, -30, -40, -50},
                      {-40, -20, 0, 5, 5, 0, -20, -40},
                      {-30, 5, 10, 15, 15, 10, 5, -30},
                      {-30, 0, 15, 20, 20, 15, 0, -30},
                      {-30, 5, 15, 20, 20, 15, 5, -30},
                      {-30, 0, 10, 15, 15, 10, 0, -30},
                      {-40, -20, 0, 0, 0, 0, -20, -40},
                      {-50, -40, -30, -30, -30, -30, -40, -50}};

     static int bishop[][] =
         new int[][] {{-20, -10, -10, -10, -10, -10, -10, -20},
                      {-10, 5, 0, 0, 0, 0, 5, -10},
                      {-10, 10, 10, 10, 10, 10, 10, -10},
                      {-10, 0, 10, 10, 10, 10, 0, -10},
                      {-10, 5, 5, 10, 10, 5, 5, -10},
                      {-10, 0, 5, 10, 10, 5, 0, -10},
                      {-10, 0, 0, 0, 0, 0, 0, -10},
                      {-20, -10, -10, -10, -10, -10, -10, -20}};

     static int rook[][] = new int[][] {
         {0, 0, 0, 5, 5, 0, 0, 0},       {-5, 0, 0, 0, 0, 0, 0, -5},
         {-5, 0, 0, 0, 0, 0, 0, -5},     {-5, 0, 0, 0, 0, 0, 0, -5},
         {-5, 0, 0, 0, 0, 0, 0, -5},     {-5, 0, 0, 0, 0, 0, 0, -5},
         {5, 10, 10, 10, 10, 10, 10, 5}, {0, 0, 0, 0, 0, 0, 0, 0}};

     static int queen[][] =
         new int[][] {{-20, -10, -10, -5, -5, -10, -10, -20},
                      {-10, 0, 0, 0, 0, 0, 0, -10},
                      {-10, 5, 5, 5, 5, 5, 0, -10},
                      {-5, 0, 5, 5, 5, 5, 0, -5},
                      {-5, 0, 5, 5, 5, 5, 0, -5},
                      {-10, 0, 5, 5, 5, 5, 0, -10},
                      {-10, 0, 0, 0, 0, 0, 0, -10},
                      {-20, -10, -10, -5, -5, -10, -10, -20}};
     static int[][] king =
         new int[][] {{20, 30, 10, 0, 0, 10, 30, 20},
                      {20, 20, 0, 0, 0, 0, 20, 20},
                      {-10, -20, -20, -20, -20, -20, -20, -10},
                      {-20, -30, -30, -40, -40, -30, -30, -20},
                      {-30, -40, -40, -50, -50, -40, -40, -30},
                      {-30, -40, -40, -50, -50, -40, -40, -30},
                      {-30, -40, -40, -50, -50, -40, -40, -30},
                      {-30, -40, -40, -50, -50, -40, -40, -30}};

     play(int depth, int board[][], int player) {
          this.board = board;
          this.player = player;
          this.depth = depth;
          piecelength = 0;
          initboard();
     }
     play() {
          this.board = new int[][] {{-4, -6, -5, -3, -2, -5, -6, -4},
                                    {-1, -1, -1, -1, -1, -1, -1, -1},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1},
                                    {4, 6, 5, 3, 2, 5, 6, 4}};
          player = -1;
          depth = 1;
          piecelength = 0;
          initboard();
     }
     void initboard() {
          items = new item[16];
          int c = 0;
          for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 8; j++) {
                    if (board[i][j] > 0 && player == 1) {
                         items[c++] = new item(c, board[i][j], i, j);
                         if (board[i][j] == 2) {
                              kingx = i;
                              kingy = j;
                         }
                    } else if (board[i][j] < 0 && player == -1) {
                         items[c++] = new item(c, board[i][j], i, j);
                         if (board[i][j] == -2) {
                              kingx = i;
                              kingy = j;
                         }
                    }
               }
          }
          piecelength = c;
     }
     public double makeMove(String space) {
          if (depth == 0) {

               return prediction();
          }
          int moves[][][] = new int[][][] {
              {},
              {{2, 0}, {1, 0}, {1, -1}, {1, 1}}, // pawn simple
              {{1, 0},
               {0, 1},
               {-1, 0},
               {0, -1},
               {1, 1},
               {1, -1},
               {-1, 1},
               {-1, -1}}, // king simple
              {{1, 0},
               {0, 1},
               {-1, 0},
               {0, -1},
               {1, 1},
               {1, -1},
               {-1, 1},
               {-1, -1}},                           // queen
              {{1, 0}, {0, 1}, {-1, 0}, {0, -1}},   // rook
              {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}}, // Bishop
              {{2, 1},
               {-2, 1},
               {2, -1},
               {1, 2},
               {1, -2},
               {-1, 2},
               {-1, -2}} // knight simple
          };
          double m;
          int mid = 0;
          int mi = 0;
          int mj = 0;
          int mx = 0;
          int my = 0;
          if (player == 1) {
               for (int k = 0; k < moves[1].length; k++) {
                    moves[1][k][0] = -1 * moves[1][k][0];
                    moves[1][k][1] = -1 * moves[1][k][1];
               }
          }
          if (player == 1)
               m = -999.9;
          else
               m = 999.9;
          for (int i = 0; i < piecelength; i++) {
               int type = Math.abs(items[i].type);
               if (type == 1 || type == 2 || type == 6) {
                    for (int j = 0; j < moves[type].length; j++) {
                         /*if (player == 1 && type == 1) {
                           System.out.println(" i am heree....");
                              moves[type][j][0] = -1 * moves[type][j][0];
                              moves[type][j][1] = -1 * moves[type][j][1];
                              System.out.println(moves[type][j][0]+"hel
                         "+moves[type][j][1]);
                         }*/
                         int x = items[i].posx + moves[type][j][0];
                         int y = items[i].posy + moves[type][j][1];
                         /*System.out.println("found : type :" + type +
                         " i: " + items[i].posx +
                         " j : " + items[i].posy +
                         " x : " + x + " y : " + y);*/
                         int isValidmove =
                             move(items[i].posx, items[i].posy, x, y);
                         /*if (horseAttack(kingx, kingy) ||
                             lineAttack(kingx, kingy))
                              isValidmove = 0;*/
                         if (isValidmove > 0) {
                              // System.out.println("Valid move");
                              /*System.out.println(
                              "ValidMove : i: " + items[i].posx + " j : " +
                              items[i].posy + " x : " + x + " y : " + y);*/
                              int copy[][] = Arrays.stream(board)
                                                 .map(int[] ::clone)
                                                 .toArray(int[][] ::new);
                              copy[items[i].posx][items[i].posy] = 0;
                              copy[x][y] = items[i].type;
                              play inst =
                                  new play(depth - 1, copy, -1 * player);
                              boolean kingattacked =
                                  inst.horseAttack(kingx, kingy) ||
                                  inst.lineAttack(kingx, kingy) ||
                                  inst.xAttack(kingx, kingy); // check this
                              if (!kingattacked) {
                                   double p = inst.makeMove(space + "      ");
                                   if (p < m && player == -1) {
                                        m = p;
                                        mid = i;
                                        mi = items[i].posx;
                                        mj = items[i].posy;
                                        mx = x;
                                        my = y;
                                   }
                                   if (p > m && player == 1) {
                                        // System.out.println("i am here");
                                        m = p;
                                        mid = i;
                                        mi = items[i].posx;
                                        mj = items[i].posy;
                                        mx = x;
                                        my = y;
                                   }
                                   if (depth == 2 || depth == 1) {

                                        /*System.out.println(
                                            space +"player: "+player +" "+
                                        items[i].display() + " x: " + x + " y: "
                                        + y); System.out.println(space + "
                                        prediction" + p);*/
                                   }
                              }

                              // System.out.println(space + "" + player +
                              //" prediction : " + p);
                         }
                    }
               } else {
                    for (int j = 0; j < moves[type].length; j++) {
                         int xdir = items[i].posx + moves[type][j][0];
                         int ydir = items[i].posy + moves[type][j][1];
                         int isValidmove = 1;
                         while (move(items[i].posx, items[i].posy, xdir, ydir) >
                                0) {
                              // System.out.println("found : type :" + type +
                              //" i: " + items[i].posx +
                              //" j : " + items[i].posy +
                              //" x : " + xdir +
                              //" y : " + ydir);
                              // System.out.println("Valid move");
                              // System.out.println(
                              //"ValidMove : i: " + items[i].posx +
                              //" j : " + items[i].posy + " x : " + xdir +
                              //" y : " + ydir);
                              int copy[][] = Arrays.stream(board)
                                                 .map(int[] ::clone)
                                                 .toArray(int[][] ::new);
                              copy[items[i].posx][items[i].posy] = 0;
                              copy[xdir][ydir] = items[i].type;
                              play inst =
                                  new play(depth - 1, copy, -1 * player);
                              boolean kingattacked =
                                  inst.horseAttack(kingx, kingy) ||
                                  inst.lineAttack(kingx, kingy) ||
                                  inst.xAttack(kingx, kingy);

                              if (!kingattacked) {
                                   double p = inst.makeMove(space + "      ");
                                   if (p < m && player == -1) {
                                        m = p;
                                        mid = i;
                                        mi = items[i].posx;
                                        mj = items[i].posy;
                                        mx = xdir;
                                        my = ydir;
                                   }
                                   if (p > m && player == 1) {
                                        // System.out.println("am here");
                                        m = p;
                                        mid = i;
                                        mi = items[i].posx;
                                        mj = items[i].posy;
                                        mx = xdir;
                                        my = ydir;
                                   }
                                   if (depth == 2 || depth == 1) {
                                        /*System.out.println(
                                            space + "player: "+player +"
                                        "+items[i].display() + " x: " + xdir + "
                                        y: " + ydir);

                                        System.out.println(space + "prediction "
                                        + p);*/
                                   }
                              }
                              // System.out.println(space + "" + player +
                              //" prediction : " + p);

                              xdir += moves[type][j][0];
                              ydir += moves[type][j][1];
                         }
                    }
               }
          }
          // System.out.println(space + "player " + player +
          //" Making move with p: " + m);
          board[mx][my] = board[mi][mj];
          board[mi][mj] = 0;
          items[mid].posx = mi;
          items[mid].posy = mj;
          // if (depth == 1)
          // System.out.println("min p" + m);
          // display();
          return m;
     }
     public void display() { System.out.println("helo"); }
     public double prediction() {
          double sum = 0;
          int val[] = {0, 1, 4, 9, 5, 3, 3};
          for (int i = 0; i < 8; i++) {
               
               for (int j = 0; j < 8; j++) {
                 int piece = board[i][j];
                    if (piece < 0) {
                         switch (piece) {
                         case -1:
                              sum += -1 *(pawnval+ pawn[board.length - 1 - i][j]);
                              break;
                         case -2:
                              sum += -1 * (kingval+king[board.length - 1 - i][j]);
                              break;
                         case -3:
                              sum += -1 *(queenval+ queen[board.length - 1 - i][j]);
                              break;
                         case -4:
                              sum += -1 *(rookval+rook[board.length - 1 - i][j]);
                              break;
                         case -5:
                              sum += -1 *(bishopval+ bishop[board.length-1 - i][j]);
                         case -6:
                              sum += -1 *(kingval+ knight[board.length - 1 - i][j]);
                         default:
                              sum += 0;
                              break;
                         }
                    } else {
                         switch (piece) {
                         case 1:
                              sum += 1 * (pawnval+pawn[i][j]);
                              break;
                         case 2:
                              sum += 1 *(kingval+ king[i][j]);
                              break;
                         case 3:
                              sum += 1 *(queenval+ queen[i][j]);
                              break;
                         case 4:
                              sum += 1 * (rookval+rook[i][j]);
                              break;
                         case 5:
                              sum += 1 * (bishopval+bishop[i][j]);
                         case 6:
                              sum += 1 * (knightval+knight[i][j]);
                         default:
                              sum += 0;
                              break;
                         }
                    }
               }
          }
          return sum;
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

     public boolean isNotEmpty(int x, int y) { return !isEmpty(x, y); }
     public int move(int i, int j, int x, int y) {
          // System.out.println("i : " + i + " j : " + j + " x : " + x +
          //  " y : " + y);
          if (!isValid(i, j)) {
               return -101;
          }
          if (!isValid(x, y)) {
               return -102;
          }
          if (board[i][j] == 0) {
               return -104;
          }
          if (board[x][y] != 0 && isSame(i, j, x, y)) {
               return -103;
          }
          int piece = board[i][j];
          int place = board[x][y];
          if (piece == 1) {
               if (i == 6) {
                    if (y == j) {
                         if ((x == 4 || x == 5) && isEmpty(x, y)) {
                              return 1;
                         }
                         return -3;
                    }
                    if ((y == (j - 1) || y == (j + 1)) && isOpp(i, j, x, y) &&
                        x == (i - 1)) {
                         return 1;
                    }
                    return -4;
               } else if (x == (i - 1)) {
                    if (y == j && isEmpty(x, y)) {
                         return 1;
                    }
                    if ((y == (j - 1) || y == (j + 1)) && isOpp(i, j, x, y) &&
                        x == (i - 1)) {
                         return 1;
                    }
                    return -4;
               }
          }
          if (piece == -1) {
               if (i == 1) {
                    if (y == j) {
                         if ((x == 2 || x == 3) && isEmpty(x, y)) {
                              return 1;
                         }
                         return -3;
                    }
                    if ((y == (j - 1) || y == (j + 1)) && isOpp(i, j, x, y) &&
                        x == (i + 1)) {
                         return 1;
                    }
                    return -4;
               } else if (x == (i + 1)) {
                    if (y == j && isEmpty(x, y)) {
                         return 1;
                    }
                    if ((y == (j - 1) || y == (j + 1)) && isOpp(i, j, x, y) &&
                        x == (i + 1)) {
                         return 1;
                    }
                    return -4;
               }
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
                         board[x][y] = tempp;
                         board[i][j] = tempm;
                         return 1;
                    }
                    // System.out.println("horseAttack : " + horseAttack(x, y));
                    // System.out.println("xAttack : " + xAttack(x, y));
                    // System.out.println("lineAttack : " + lineAttack(x, y));
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
                    for (int k = start ; k < end; k++) {
                         if (!isEmpty(i, k)) {
                              flag = false;
                              break;
                         }
                    }

               } else if (j == y) {
                    int start = Math.min(x, i);
                    int end = Math.max(x, j);
                    for (int k = start ; k < end; k++) {
                         if (!isEmpty(k, j)) {
                              flag = false;
                              break;
                         }
                    }
               } else {
                    return -9;
               }
               if (flag == true) {
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
               return 1;
          }
          if (Math.abs(piece) == 6) {
               if (Math.abs(i - x) == 1 && Math.abs(j - y) == 2) {
                    return 1;
               }
               if (Math.abs(i - x) == 2 && Math.abs(j - y) == 1) {

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
                         // System.out.println("start HERE " + start + " end " +
                         //  end);
                         for (int k = start + 1; k < end; k++) {
                              if (!isEmpty(i, k)) {
                                   // System.out.println("is not Empty" + i + "
                                   // " +
                                   //   k);
                                   flag = false;
                                   break;
                              }
                         }

                    } else if (j == y) {

                         int start = Math.min(x, i);
                         int end = Math.max(x, i);
                         // System.out.println("start " + start + " end " +
                         // end);
                         for (int k = start + 1; k < end; k++) {
                              if (!isEmpty(k, j)) {
                                   // System.out.println("is not Empty" + k + "
                                   // " +
                                   //  j);
                                   flag = false;
                                   break;
                              }
                         }
                    } else {
                         return -14;
                    }
                    if (flag == true) {

                         return 1;
                    }
                    return -15;

               } else if (Math.abs(x - i) != Math.abs(y - j)) {
                    // System.out.println("(x-i)" + Math.abs(x - i) + " " +
                    // Math.abs(y - j));
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
                    if (!isEmpty(x, y) && isSame(i, j, x, y)) {
                         // board[x][y]=10;
                         break;
                    } else if (!isEmpty(x, y) && isOpp(i, j, x, y) &&
                               ((Math.abs(board[x][y]) == 3) ||
                                (Math.abs(board[x][y]) == 5))) {
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
                    if (!isEmpty(x, y) && isSame(i, j, x, y)) {
                         // board[x][y]=10;
                         break;
                    } else if (!isEmpty(x, y) && isOpp(i, j, x, y) &&
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
}
