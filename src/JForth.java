import java.util.Scanner;
class JForth {
    public static void main(String[] args) {
        JForth forth = new JForth();
        //testing
        forth.print(forth.get_next());
    }
    Scanner scanner;
    JForth() {
        print("Welcome to jforth\n");
        scanner = new Scanner(System.in);
    }
    public void print(String string) {
        System.out.print(string);
    }
    public String get_next() {
        // scanner already does the work of breaking up words with space
        return scanner.next();
    }
}