
import java.util.concurrent.TimeUnit;

public class Main
{ 
  public static final int arrY = 28;
  public static final int arrX = 28;
  public static int[][] arr = new int[arrX][arrY];
  public static int[][] endArr = new int[arrX][arrY];
  public static final int CELL = 1;
  public static final int DEADCELL = 0; 
  public static boolean stop = true;
  public static int generations = 1;
  public static int waitTime = 100;

  public static int isTouching(int X, int Y) {
    
    int integer = 0;
    if (X != 0 && Y != 0) {
      if (arr[X-1][Y-1] == CELL) {
        integer++;
      }
    }
     if (X != 0) {
      if (arr[X-1][Y] == CELL) {
        integer++;
      }
     }
     if (X != 0) {
      if (arr[X-1][Y+1] == CELL) {
        integer++;
      }
     }
    if (Y != 0) {
      if (arr[X][Y-1] == CELL) {
        integer++;
      }
    }
    if (arr[X][Y+1] == CELL) {
      integer++;
    }
    if (Y != 0) {
      if (arr[X+1][Y-1] == CELL) {
        integer++;
      }
    }
    if (arr[X+1][Y] == CELL) {
      integer++;
    }
    if (arr[X+1][Y+1] == CELL) {
      integer++;
    } 
    return integer;
  }
    
  public static void main(String[] args) throws InterruptedException {


  for (int i = 1; i < arrX; i++) {
    for (int j = 1; j < arrY; j++) {
      arr[i][j] = 0;
      endArr[i][j] = 0;
    }
  } 
  MyFrame frame = new MyFrame();
    while (true) {
      while (stop) {
        System.out.print("");
        if (!stop) {
          break;
        }
      }
      try {
        TimeUnit.MILLISECONDS.sleep(waitTime);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      generations++;
      MyFrame.gen.setText("Generation: " + generations);
      for (int i = 0; i <= arrX - 2; i++) {
        for (int j = 0; j <= arrY - 2; j++) {
          int touch = isTouching(i, j);
          if (arr[i][j] == CELL && touch < 2) {
            frame.labelWhite(i, j);
            endArr[i][j] = DEADCELL;
          }
          if (arr[i][j] == CELL && touch == 3) {
            
            frame.labelBlack(i,j);
            endArr[i][j] = CELL;
          }
          if (arr[i][j] == CELL && touch > 3) {
           
            frame.labelWhite(i,j);
            endArr[i][j] = DEADCELL;
          }
          if (arr[i][j] == DEADCELL && touch == 3) {
           
            frame.labelBlack(i,j);
            endArr[i][j] = CELL;
          }
          if (arr[i][j] == CELL && touch == 2) {
            
            frame.labelBlack(i,j);
            endArr[i][j] = CELL;
          }
        }
      }  
   
      for (int i = 0; i < arrX; i++) {
        for (int j = 0; j < arrY; j++) {
          arr[i][j] = endArr[i][j];
          endArr[i][j] = 0;
        }
      }
    }
  }
  public static void stop() {
    if (stop == false) {
        stop = true;
      } else if (stop == true) {
        stop = false;
    }
  }
}


