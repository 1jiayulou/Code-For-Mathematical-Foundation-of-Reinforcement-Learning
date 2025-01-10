import java.util.Arrays;

public class Bellman {
    private final double THRESHOLD = 1e-6; // 阈值

    private int size;
    private int punishment;
    private double discount;
    private int[][] grid;
    private double[][] stateValue;
    private double[][][] actionValue;
    private int[][] policy;

    public Bellman(int[][] grid, int punishment, double discount) {
        this.size = grid.length;
        this.punishment = punishment;
        this.discount = discount;
        this.grid = grid;
        this.stateValue = new double[size][size];
        this.actionValue = new double[size][size][5];
        this.policy = new int[size][size];
        for (int i = 0; i < size; i++) Arrays.fill(this.policy[i], 4);
    }

    private class BestActionPair {
        double actionValue;
        int policy;

        public BestActionPair(double actionValue, int policy) {
            this.actionValue = actionValue;
            this.policy = policy;
        }

        public double getActionValue() {
            return this.actionValue;
        }

        public int getPolicy() {
            return this.policy;
        }
    }

    // 值迭代
    public void valueIteration() throws InterruptedException {
        double delta;
        do {
            delta = 0;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    double oldValue = stateValue[i][j];
                    BestActionPair bestActionPair = getBestAction(i,j);
                    stateValue[i][j] = bestActionPair.getActionValue();
                    policy[i][j] = bestActionPair.getPolicy();
                    delta = Math.max(delta, Math.abs(stateValue[i][j] - oldValue));
                }
            clearConsole();
            print(delta);
            Thread.sleep(100);
        } while (delta > THRESHOLD);
    }

    // 策略迭代
    public void policyIteration() throws InterruptedException {
        boolean policyStable;
        // 评估策略
        do {
            policyEvaluation();
            // 改进策略
            policyStable = true;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    int oldAction = policy[i][j];
                    policy[i][j] = getBestAction(i,j).getPolicy();
                    if (oldAction != policy[i][j]) policyStable = false;
                }
        } while (!policyStable);

    }

    private void policyEvaluation() throws InterruptedException {
        double delta ;
        do {
            delta = 0;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    double oldValue = stateValue[i][j];
                    stateValue[i][j] = getExpectedValue(i,j,policy[i][j]);
                    delta = Math.max(delta, Math.abs(stateValue[i][j] - oldValue));
                }
        } while (delta > THRESHOLD);
        clearConsole();
        print(delta);
        Thread.sleep(100);
    }

    private double getExpectedValue(int i, int j, int action) {
        return switch (action) {
            // UP
            case 0 -> (i == 0 ? punishment : grid[i - 1][j] + discount * stateValue[i - 1][j]);
            // DOWN
            case 1 -> (i == size - 1 ? punishment : grid[i + 1][j] + discount * stateValue[i + 1][j]);
            // LEFT
            case 2 -> (j == 0 ? punishment : grid[i][j - 1] + discount * stateValue[i][j - 1]);
            // UP
            case 3 -> (j == size - 1 ? punishment : grid[i][j + 1] + discount * stateValue[i][j + 1]);
            // STAY
            default -> grid[i][j] + discount * stateValue[i][j];
        };
    }

    private BestActionPair getBestAction(int i, int j) {
        double[] actionValues = new double[5];
        // 0 -> Up
        actionValues[0] = (i == 0 ? punishment : grid[i - 1][j] + discount * stateValue[i - 1][j]);
        // 1 -> Down
        actionValues[1] = (i == size - 1 ? punishment : grid[i + 1][j] + discount * stateValue[i + 1][j]);
        // 2 -> Left
        actionValues[2] = (j == 0 ? punishment : grid[i][j - 1] + discount * stateValue[i][j - 1]);
        // 3 -> Right
        actionValues[3] = (j == size - 1 ? punishment : grid[i][j + 1] + discount * stateValue[i][j + 1]);
        // 4 -> Stay
        actionValues[4] = grid[i][j] + discount * stateValue[i][j];

        // 选择最大值对应的动作
        int bestAction = 4; // 默认动作为“Stay”
        double maxValue = actionValues[4];
        for (int k = 0; k < 5; k++) {
            if (actionValues[k] > maxValue) {
                maxValue = actionValues[k];
                bestAction = k;
            }
        }
        return  new BestActionPair(maxValue, bestAction);
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear the screen
        System.out.flush();
    }

    private void print(double delta) {
        System.out.println("Delta: " + delta);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                // System.out.print( String.format("%.2f", stateValue[i][j]) + " ");
                System.out.print( policy[i][j] + " ");
                if (j == size - 1) System.out.println();
            }
        System.out.println("----------------");
    }

}

