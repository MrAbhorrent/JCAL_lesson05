package ru.geekbrains;

import java.util.Arrays;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;
    static String mainPartMessageString = "Расчет времени обработки массива";

    public static void main(String[] args) {

        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        System.out.println(mainPartMessageString + " в один поток");
        long t1 = System.currentTimeMillis();
        calculateArray(arr);
        long t2 = System.currentTimeMillis();
        float deltaT = (float) (t2 - t1)/1000;
        System.out.println("Время выполнения задачи = " + deltaT + " сек.");

        System.out.println(mainPartMessageString + " в два потока");
        t1 = System.currentTimeMillis();
        float a1[] = new float[h];
        float a2[] = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread thread1 = new Thread(new ThreadCalculate(a1));
        Thread thread2 = new Thread(new ThreadCalculate(a2));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, 0, arr, h, h);
        } catch (InterruptedException e) {
            System.out.println(String.format("Исключение в потоках. %s", e.getMessage()));
        }
        t2 = System.currentTimeMillis();
        deltaT = (float) (t2 - t1)/1000;
        System.out.println("Время выполнения задачи = " + deltaT + " сек.");

    }

    private static void calculateArray(float[] someArray) {
        for (int i = 0; i < someArray.length; i++) {
            someArray[i] = (float) (someArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }


    private static class threadCalculate implements Runnable {

        @Override
        public void run() {

        }
    }
}
