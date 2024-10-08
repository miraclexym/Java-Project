package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

	// �ı���
    private JTextField displayField;

    // ���캯��
    public Calculator() {
        // ���ô�������
        setTitle("�򵥼�����"); // ���ô��ڱ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���Ĭ�Ϲر�
        setSize(1000, 500); // ���ô��ڳߴ�
        setResizable(false); // ���ô��ڲ����޸ĳߴ�
        setLocationRelativeTo(null); // ���ô�������Ļ����
        setVisible(true); // ���ô��ڿ��ӻ�
        setLayout(new BorderLayout()); // ���ô��ڲ���
        
        // �ı���
        displayField = new JTextField();// ��ʼ���ı���
        displayField.setHorizontalAlignment(JTextField.RIGHT);// ˮƽ���뷽ʽ
        displayField.setEditable(true);// �ɱ༭ģʽ
        add(displayField, BorderLayout.NORTH);// �ı����ڴ��ڲ������ϱ�
        
        // �����ı��������С
        Font font = new Font("Arial", Font.PLAIN, 40);
        displayField.setFont(font);

        // ��ť����Լ���񲼾�
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        
        // ��ť�ı�ǩ��ʾ����
        String[] buttonLabels = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "(", "0", ")", "/",
                "C", "%", ".", "="
        };

        for (String label : buttonLabels) {
        	
        	// ���ð�ť�ϵı�ǩ
            JButton button = new JButton(label);
            
            // ���ð�ť�ϵ������С
            Font buttonFont = new Font("Arial", Font.PLAIN, 40);
            button.setFont(buttonFont);
            
            // ���Ӽ����������Ӱ�ť
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        // ����ť�����ڴ��ڲ���������
        add(buttonPanel, BorderLayout.CENTER);

    }

    // ��ť���������
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton source = (JButton) event.getSource();
            String buttonText = source.getText();

            if (buttonText.equals("=")) {
                try {
                    String expression = displayField.getText();// ��ȡ���ʽ
                    double result = Expression.calculate(expression);// ������ʽ
                    displayField.setText(expression + " = " + Double.toString(result));// չʾ���
                } catch (Exception e) {
                    displayField.setText("Error");
                }
            } else if(buttonText.equals("C")) {
            	displayField.setText("");// ����ַ���ʽ
            }
            else {
                String currentText = displayField.getText();// �����ť
                displayField.setText(currentText + buttonText);// ����ַ���ʽ
            }
        }
    }

    // ���������
    public static void main(String[] args) {
    	new Calculator().setVisible(true);
    }
}
