
public class ThreadMin extends Thread {
    private final int startIndex;
    private final int finishIndex;
    private final ArrClass arrClass;

    public ThreadMin(int startIndex, int finishIndex, ArrClass arrClass) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
    }

    @Override
    public void run() {
        int localMin = Integer.MAX_VALUE;
        int localMinIndex = -1;

        for (int i = startIndex; i < finishIndex; i++) {
            if (arrClass.arr[i] < localMin) {
                localMin = arrClass.arr[i];
                localMinIndex = i;
            }
        }

        arrClass.updateMinResult(localMin, localMinIndex);
    }
}
