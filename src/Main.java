import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Forth forth = new Forth();
        forth.print(forth.next());
    }
}

class Forth {
    Scanner scanner;
    Forth() {
        print("Welcome to Myforth\n");
        scanner = new Scanner(System.in);
    }
    public void print(String string) {
        System.out.print(string);
    }
    public String next() {
       return scanner.next();
    }
}