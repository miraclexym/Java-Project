package Calculator;

import java.util.Stack;

public class Expression { // 表达式求值 //利用这个静态函数Expression.calculate(表达式)来获得值
    public static double calculate(String expression) { // 中序表达式求值
        Stack<Double> valueStack = new Stack<>(); // 操作数栈
        Stack<Character> operatorStack = new Stack<>(); // 运算符栈

        for (int i = 0; i < expression.length(); i++) { // 遍历字符串
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') { // 读取到操作数
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && ((Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.'))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--; // 因为在 for 循环中会额外执行一次 i++
                valueStack.push(Double.parseDouble(num.toString())); // 操作数入栈
            } else if (c == '(') { // 读取左括号
                operatorStack.push(c);
            } else if (c == ')') { // 读取右括号
                while (operatorStack.peek() != '(') {
                    calculate(valueStack, operatorStack);
                }
                operatorStack.pop(); // 弹出左括号
            } else if (isOperator(c)) { //读取处理运算符
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), c)) {
                    calculate(valueStack, operatorStack);
                }
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) { // 弹出剩余运算符
            calculate(valueStack, operatorStack);
        }

        return valueStack.pop();
    }

    // 计算一次运算符栈和操作数栈
    private static void calculate(Stack<Double> values, Stack<Character> operators) {
        char operator = operators.pop();
        double operand2 = values.pop();
        double operand1 = values.pop();
        double result = performOperation(operand1, operand2, operator);
        values.push(result);
    }
    
    // 执行计算操作
    private static double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) throw new ArithmeticException("Division by zero!");
                return operand1 / operand2;
            case '%':
                return operand1 % operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    // 是运算符
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    // 运算符优先级
    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return -1;
        }
    }

    // 具有更高的优先级
    private static boolean hasHigherPrecedence(char op1, char op2) {
        int precedence1 = getPrecedence(op1);
        int precedence2 = getPrecedence(op2);
        return precedence1 >= precedence2;
    }

    public static void main(String[] args) {
        String infixExpression = "(18*9-26)/7+43%10";
        double result = Expression.calculate(infixExpression);
        System.out.println("Result: " + result);
    }
}
