import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class JForth {
    public static void main(String[] args) {
        JForth forth = new JForth();
        //testing
        while (!forth.exit) {
            forth.step();
        }
    }
    Map<String,Opcode[]> words;
    BufferedReader buf;
    Deque<Integer> stack;
    boolean lineEnd;
    boolean exit;
    enum Opcode {
        PRINT, PLUS, EXIT, SHOW
    }
    JForth() {
        print("Welcome to jforth\n");
        buf = new BufferedReader(new InputStreamReader(System.in));
        lineEnd = false;
        exit = false;
        words = new HashMap<String, Opcode[]>();
        words.put(".", new Opcode[] {Opcode.PRINT});
        words.put("+", new Opcode[] {Opcode.PLUS});
        words.put("BYE", new Opcode[] {Opcode.EXIT});
        words.put(".S", new Opcode[] {Opcode.SHOW});
    }
    public void print(String string) {
        System.out.print(string);
    }
    public boolean isLineEnd() {
        return lineEnd;
    }
    public void step() {
        String line;
        try {
            line = buf.readLine();
        } catch (IOException e) {
            line = "";
        }
        for (String word: line.split(" ")) {
            print(word + "..");
        }
        print("\n");
    }
}