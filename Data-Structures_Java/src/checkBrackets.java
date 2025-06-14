import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class checkBrackets {

    static class Bracket {

        char type;
        int position;

        Bracket(char type, int position) {
            this.type = type;
            this.position = position;
        }

        boolean Match(char c) {
            if (this.type == '[' && c == ']')
                return true;
            else if (this.type == '{' && c == '}')
                return true;
            else if (this.type == '(' && c == ')')
                return true;
            return false;
        }
    }

    public static boolean isBalanced(String input) {
        Stack<Bracket> stack = new Stack<Bracket>();
        Bracket top;
        for (int position = 0; position < input.length(); ++position) {
            char next = input.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                stack.push(new Bracket(next, position));
            } else if (next == ')' || next == ']' || next == '}') {
                if (stack.empty()) {
                    System.out.println(position + 1);
                    return false;
                }
                top = stack.pop();
                if (!top.Match(next)) {
                    System.out.println(position + 1);
                    return false;
                }
            }
        }
        if (!stack.empty()){
            System.out.println(stack.peek().position + 1);
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        boolean result = isBalanced(text);
        if (result)
            System.out.println("Success");
    }
}
