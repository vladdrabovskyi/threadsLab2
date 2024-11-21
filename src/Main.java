
public class Main {
    public static void main(String[] args) {
        int dim = 100;
        int threadNum = 4; // кількість потоків
        ArrClass arrClass = new ArrClass(dim, threadNum);
        arrClass.setRandomNegativeValue(); // Додаємо від’ємне значення до масиву

        // Запуск пошуку мінімального значення
        MinResult result = arrClass.threadMin();
        System.out.println("Minimum value: " + result.minValue);
        System.out.println("Index of minimum value: " + result.minIndex);
    }
}
