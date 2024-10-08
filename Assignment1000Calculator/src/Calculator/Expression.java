package Calculator;

import java.util.Stack;

public class Expression { // ���ʽ��ֵ //���������̬����Expression.calculate(���ʽ)�����ֵ
    public static double calculate(String expression) { // ������ʽ��ֵ
        Stack<Double> valueStack = new Stack<>(); // ������ջ
        Stack<Character> operatorStack = new Stack<>(); // �����ջ

        for (int i = 0; i < expression.length(); i++) { // �����ַ���
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') { // ��ȡ��������
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && ((Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.'))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--; // ��Ϊ�� for ѭ���л����ִ��һ�� i++
                valueStack.push(Double.parseDouble(num.toString())); // ��������ջ
            } else if (c == '(') { // ��ȡ������
                operatorStack.push(c);
            } else if (c == ')') { // ��ȡ������
                while (operatorStack.peek() != '(') {
                    calculate(valueStack, operatorStack);
                }
                operatorStack.pop(); // ����������
            } else if (isOperator(c)) { //��ȡ���������
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), c)) {
                    calculate(valueStack, operatorStack);
                }
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) { // ����ʣ�������
            calculate(valueStack, operatorStack);
        }

        return valueStack.pop();
    }

    // ����һ�������ջ�Ͳ�����ջ
    private static void calculate(Stack<Double> values, Stack<Character> operators) {
        char operator = operators.pop();
        double operand2 = values.pop();
        double operand1 = values.pop();
        double result = performOperation(operand1, operand2, operator);
        values.push(result);
    }
    
    // ִ�м������
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

    // �������
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    // ��������ȼ�
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

    // ���и��ߵ����ȼ�
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
