import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class JForth {
    public static void main(String[] args) {
        JForth forth = new JForth();
        //testing
        while (!forth.exit) {
            forth.print(forth.step());
        }
    }
    Map<String,Opcode[]> words;
    BufferedReader buf;
    Deque<Integer> stack;
    boolean lineEnd;
    boolean exit;
    enum Opcode {
        PRINT, ADD, EXIT, SHOW, SUB
    }
    String underflow = " stack underflow\n";
    JForth() {
        print("Welcome to jforth\n");
        buf = new BufferedReader(new InputStreamReader(System.in));
        lineEnd = false;
        exit = false;
        words = new HashMap<String, Opcode[]>();
        words.put(".", new Opcode[] {Opcode.PRINT});
        words.put("+", new Opcode[] {Opcode.ADD});
        words.put("-", new  Opcode[] {Opcode.SUB});
        words.put("BYE", new Opcode[] {Opcode.EXIT});
        words.put(".S", new Opcode[] {Opcode.SHOW});
        stack = new ArrayDeque<>();
    }
    public void print(String string) {
        System.out.print(string);
    }
    public boolean isLineEnd() {
        return lineEnd;
    }
    public String step() {
        String line;
        try {
            line = buf.readLine();
        } catch (IOException e) {
            return e.toString();
        }
        for (String word: line.split(" ")) {
            Opcode[] code = words.get(word.toUpperCase());
            if (code == null) {
                int number = 0, i = 0;
                boolean neg;
                if (word.charAt(0) == '-') {
                    i = 1;
                    neg = true;
                } else neg = false;
                for (; i < word.length(); i++) {
                    int dig = word.charAt(i) - '0';
                    if (dig >= 0 && dig <= 9) number = (number * 10) + dig;
                    else {
                        return word + " is not a word\n";
                    }
                }
                if (neg) number = -number;
                stack.push(number);
            } else {
                int top, nxt;
                for (Opcode op: code) {
                    switch (op) {
                        case EXIT:
                            exit = true;
                            break;
                        case SUB:
                            if (stack.size() < 2) return underflow;
                            top = stack.pop();
                            nxt = stack.pop();
                            stack.push(nxt - top);
                            break;
                        case ADD:
                            if (stack.size() < 2) return underflow;
                            top = stack.pop();
                            nxt = stack.pop();
                            stack.push(nxt + top);
                            break;
                        case SHOW:
                            Deque<Integer> rev = new ArrayDeque<>();
                            for (Integer var: stack) rev.push(var);
                            print("<" + stack.size() + "> ");
                            for (Integer var: rev) print(var + " ");
                            break;
                        case PRINT:
                            if (stack.size() < 1) return underflow;
                            top = stack.pop();
                            print(top + " ");
                            break;
                    }
                }
            }
        }
        return "ok\n";
    }
}