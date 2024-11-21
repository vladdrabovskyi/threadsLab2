
import java.util.Random;

public class ArrClass {
    private final int dim;
    private final int threadNum;
    public final int[] arr;

    public ArrClass(int dim, int threadNum) {
        this.dim = dim;
        arr = new int[dim];
        this.threadNum = threadNum;

        // заповнення масиву
        for (int i = 0; i < dim; i++) {
            arr[i] = i;
        }
    }

    // Додаємо від’ємне значення
    public void setRandomNegativeValue() {
        Random random = new Random();
        int index = random.nextInt(dim);
        arr[index] = -random.nextInt(100) - 1;
    }

    private MinResult minResult = new MinResult(Integer.MAX_VALUE, -1);
    synchronized public void updateMinResult(int minValue, int minIndex) {
        if (minValue < minResult.minValue) {
            minResult.minValue = minValue;
            minResult.minIndex = minIndex;
        }
    }

    public MinResult threadMin() {
        ThreadMin[] threadMins = new ThreadMin[threadNum];
        int partSize = dim / threadNum;

        // Ініціалізація та запуск потоків
        for (int i = 0; i < threadNum; i++) {
            int startIndex = i * partSize;
            int finishIndex = (i == threadNum - 1) ? dim : startIndex + partSize;
            threadMins[i] = new ThreadMin(startIndex, finishIndex, this);
            threadMins[i].start();
        }

        // Очікування завершення всіх потоків
        for (ThreadMin threadMin : threadMins) {
            try {
                threadMin.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return minResult;
    }
}
