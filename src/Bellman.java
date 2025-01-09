import java.util.Arrays;

public class Bellman {
    private final double THRESHOLD = 1e-3; // 阈值

    private int size;
    private int punishment;
    private double discount;
    private int[][] grid ;
    private double[][] returnValue;
    private double[][][] actionValue;
    private int[][] result;

    public Bellman(int[][] grid, int punishment, double discount) {
        this.size = grid.length;
        this.punishment = punishment;
        this.discount = discount;
        this.grid = grid;
        this.returnValue = new double[size][size];
        this.actionValue = new double[size][size][5];
        this.result = new int[size][size];
        for (int i = 0; i < size; i++) Arrays.fill(this.result[i], -1);
    }

    public void valueIteration() throws InterruptedException {
        double delta;
        do {
            delta = 0;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    // 0 -> Up
                    actionValue[i][j][0] = (i==0) ? punishment + returnValue[i][j] * discount : grid[i-1][j] + returnValue[i-1][j] * discount;
                    // 1 -> Down
                    actionValue[i][j][1] = (i==size-1) ? punishment + returnValue[i][j] * discount : grid[i+1][j] + returnValue[i+1][j] * discount;
                    // 2 -> Left
                    actionValue[i][j][2] = (j==0) ? punishment + returnValue[i][j] * discount : grid[i][j-1] + returnValue[i][j-1] * discount;
                    // 3 -> Right
                    actionValue[i][j][3] = (j==size-1) ? punishment + returnValue[i][j] * discount : grid[i][j+1] + returnValue[i][j+1] * discount;
                    // 4 -> Stay
                    actionValue[i][j][4] = grid[i][j] + returnValue[i][j] * discount;
                }

            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    double max = -100;
                    double oldValue = returnValue[i][j];
                    for (int k = 0; k < 5; k++) if (actionValue[i][j][k] > max) {
                        max = actionValue[i][j][k];
                        result[i][j] = k;
                    }
                    returnValue[i][j] = max;
                    delta = Math.max(delta, Math.abs(returnValue[i][j] - oldValue));
                }

            clearConsole();
            print(delta);
            Thread.sleep(100);
        } while(delta > THRESHOLD);
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear the screen
        System.out.flush();
    }

    private void print(double delta) {
        System.out.println("Delta: " + delta);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                System.out.print(result[i][j] + " ");
                if (j == size-1) System.out.println();
            }
        System.out.println("----------------");
    }

}

