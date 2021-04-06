package Class.Game;

public class GenericsPrinter<T> {
    private T t;

    public GenericsPrinter(T t) {
        this.t = t;
    }
    
    // Print element
    public void printSomething() {
        System.out.println(t);
    }
}
