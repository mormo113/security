package tools;

/**
 * Created by b010cli on 22/09/2016.
 */
public class Counter {

    private static int counter;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Counter.counter = counter;
    }

    public static void increase(){
        counter++;
    }

    public static void reset(){
        counter=0;
    }
}
