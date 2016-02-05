package ru.crystals.fest.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//from http://codereview.stackexchange.com/questions/56718/basic-calculator-using-java-swing-and-awt
/**
 * The OrderCalculatorGUI class creates the GUI for the Brandi's Bagel House application.
 */

public class Calculator extends JFrame implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private Integer[] placeholder = new Integer[15];
    private long placeholderBuilder;
    private double numberOne = 0;
    private double numberTwo = 0;
    private double total = 0;
    private String operand;
    private boolean positiveNumberSwitch = true;

    //    private int testingConsoleDisplayCounter = 1;

    private long beforeDecimal;
    private long afterDecimal;
    private boolean isDecimal;

    private GridBagLayout layout = new GridBagLayout();

    private JLabel displayLabel = new JLabel("0");

    private JButton nineButton = new JButton("9");
    private JButton eightButton = new JButton("8");
    private JButton sevenButton = new JButton("7");
    private JButton sixButton = new JButton("6");
    private JButton fiveButton = new JButton("5");
    private JButton fourButton = new JButton("4");
    private JButton threeButton = new JButton("3");
    private JButton twoButton = new JButton("2");
    private JButton oneButton = new JButton("1");
    private JButton zeroButton = new JButton("0");

    private JButton clearButton = new JButton("C");
    private JButton posnegButton = new JButton("-/+");
    private JButton deleteButton = new JButton("<-");
    private JButton divideButton = new JButton("/");
    private JButton timesButton = new JButton("*");
    private JButton subtractButton = new JButton("-");
    private JButton addButton = new JButton("+");
    private JButton decimalButton = new JButton(".");
    private JButton equalsButton = new JButton("=");

    private JButton jb = new JButton("123");

    public Calculator(String title) {
        super(title);
        setLayout(layout);
        // makePanel (display text, Layout type, cell span, height, width, row number, column number)

        makeLabel(displayLabel, layout, GridBagConstraints.REMAINDER, 1, 1, 0, 1);

        displayLabel.setName("displayLabel");

        // makeButton (display text, Layout type, cell span, height, width, row number, column number)

        makeButton(jb, layout, 1, 1, 1, 1, 1);

        makeButton(clearButton, layout, 1, 1, 1, 1, 1);
        makeButton(posnegButton, layout, 1, 1, 1, 1, 2);
        makeButton(deleteButton, layout, 1, 1, 1, 1, 3);
        makeButton(divideButton, layout, 1, 1, 1, 1, 4);

        makeButton(sevenButton, layout, 1, 1, 1, 2, 1);
        makeButton(eightButton, layout, 1, 1, 1, 2, 2);
        makeButton(nineButton, layout, 1, 1, 1, 2, 3);
        makeButton(timesButton, layout, 1, 1, 1, 2, 4);

        makeButton(fourButton, layout, 1, 1, 1, 3, 1);
        makeButton(fiveButton, layout, 1, 1, 1, 3, 2);
        makeButton(sixButton, layout, 1, 1, 1, 3, 3);
        makeButton(subtractButton, layout, 1, 1, 1, 3, 4);

        makeButton(oneButton, layout, 1, 1, 1, 4, 1);
        makeButton(twoButton, layout, 1, 1, 1, 4, 2);
        makeButton(threeButton, layout, 1, 1, 1, 4, 3);
        makeButton(addButton, layout, 1, 1, 1, 4, 4);

        makeButton(zeroButton, layout, 1, 1, 1, 5, 1);
        makeButton(decimalButton, layout, 1, 1, 1, 5, 2);
        makeButton(equalsButton, layout, GridBagConstraints.REMAINDER, 1, 1, 5, 3);

        // Adds Keyboard key listener
        this.addKeyListener(this);

    }

    // Method used to create and format the top JLabel that displays the entered numbers and end total
    void makeLabel(JLabel label, GridBagLayout gd, int gridwidth, int weightx, int weighty, int gridy, int gridx) {
        GridBagConstraints c = new GridBagConstraints();

        label.setBorder(new EmptyBorder(0, 5, 0, 25));
        label.setFont(new Font("monospaced", Font.BOLD, 26));
        label.setHorizontalAlignment(SwingConstants.RIGHT);

        c.gridwidth = gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = GridBagConstraints.BOTH;
        gd.setConstraints(label, c);
        add(label);

    }


    void makeJTextField(JTextField textField, GridBagLayout gd, int gridwidth, int weightx, int weighty, int gridy, int gridx) {
        GridBagConstraints c = new GridBagConstraints();

        textField.setFont(new Font("monospaced", Font.BOLD, 21));
        textField.setForeground(Color.DARK_GRAY);
        textField.setBackground(Color.LIGHT_GRAY);

        c.gridwidth = gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = GridBagConstraints.BOTH;
        gd.setConstraints(textField, c);
        //checkBox.addActionListener(new ListenersMy());
        add(textField);
    }

    void makeButton(JCheckBox checkBox, GridBagLayout gd, int gridwidth, int weightx, int weighty, int gridy, int gridx) {
        GridBagConstraints c = new GridBagConstraints();

        checkBox.setFont(new Font("monospaced", Font.BOLD, 21));
        checkBox.setForeground(Color.DARK_GRAY);
        checkBox.setBackground(Color.LIGHT_GRAY);

        c.gridwidth = gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = GridBagConstraints.BOTH;
        gd.setConstraints(checkBox, c);
        //checkBox.addActionListener(new ListenersMy());
        add(checkBox);
    }

    // Method used to create and format the GUI JButtons
    void makeButton(JButton button, GridBagLayout gd, int gridwidth, int weightx, int weighty, int gridy, int gridx) {
        GridBagConstraints c = new GridBagConstraints();

        button.setFont(new Font("monospaced", Font.BOLD, 21));
        button.setForeground(Color.DARK_GRAY);
        button.setBackground(Color.LIGHT_GRAY);

        c.gridwidth = gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = GridBagConstraints.BOTH;
        gd.setConstraints(button, c);
        button.addActionListener(this);
        add(button);
    }

    // Event Listener for the button clicks
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "9":
                numberAction(9);
                break;

            case "8":
                numberAction(8);
                break;

            case "7":
                numberAction(7);
                break;

            case "6":
                numberAction(6);
                break;

            case "5":
                numberAction(5);
                break;

            case "4":
                numberAction(4);
                break;

            case "3":
                numberAction(3);
                break;

            case "2":
                numberAction(2);
                break;

            case "1":
                numberAction(1);
                break;

            case "0":
                numberAction(0);
                break;

            case ("123"):
                operand = "123";
                //functionForMultipuleEntryPastTwoNumbers();
                System.out.println("Working!");
                clear();
                break;

            case ("-"):
                operand = "-";
                functionForMultipuleEntryPastTwoNumbers();
                clear();
                break;

            case ("*"):
                operand = "*";
                functionForMultipuleEntryPastTwoNumbers();
                clear();
                break;

            case ("/"):
                operand = "/";
                functionForMultipuleEntryPastTwoNumbers();
                clear();
                break;

            case ("="):
                makeNumberTwo();
                total();
                clear();
                placeholder[0] = 0;
                isDecimal = false;
                break;

            case ("."):
                isDecimal = true;
                makeNumberBeforeDecimal();
                break;

            case ("C"):
                for (int i = 0; i < placeholder.length; i++) {
                    placeholder[i] = null;
                }
                displayLabel.setText("0");
                numberOne = 0;
                numberTwo = 0;
                total = 0;
                placeholder[0] = 0;
                isDecimal = false;
                break;

            case ("<-"):
                backspace();
                numberAction();
                break;

            case ("-/+"):
                if (positiveNumberSwitch == true) {
                    positiveNumberSwitch = false;
                } else {
                    positiveNumberSwitch = true;
                    ;
                        }
                if (placeholder[0] != null) {
                    numberAction();
                }
                break;
            default:
                break;
        }
    }

    // Event Listener to allow the user to enter numbers and operands via their keyboard.
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {
            case '9':
                numberAction(9);
                break;

            case '8':
                numberAction(8);
                break;

            case '7':
                numberAction(7);
                break;

            case '6':
                numberAction(6);
                break;

            case '5':
                numberAction(5);
                        break;

            case '4':
                numberAction(4);
                break;

            case '3':
                numberAction(3);
                break;

            case '2':
                numberAction(2);
                break;

            case '1':
                numberAction(1);
                break;

            case '0':
                numberAction(0);
                break;

            case ('+'):
                operand = "+";
                functionForMultipuleEntryPastTwoNumbers();
                clear();
                break;

            case ('-'):
                operand = "-";
                functionForMultipuleEntryPastTwoNumbers();
                clear();
                break;

            case ('*'):
                operand = "*";
                functionForMultipuleEntryPastTwoNumbers();
                clear();
                break;

            case ('/'):
                operand = "/";
                functionForMultipuleEntryPastTwoNumbers();
                clear();
                        break;

            case ('='):
                makeNumberTwo();
                total();
                clear();
                isDecimal = false;
                break;

            case ('.'):
                isDecimal = true;
                makeNumberBeforeDecimal();
                break;

            case ('C'):
                clear();
                displayLabel.setText("0");
                total = 0;
                placeholder[0] = 0;
                isDecimal = false;
                        break;
        }

        switch ((int) e.getKeyChar()) {
        // Backspace Button
            case (8):
                backspace();
                numberAction();
                break;

            // Enter Button
            case (10):
                makeNumberTwo();
                total();
                clear();
                isDecimal = false;
                placeholder[0] = 0;
                break;

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // Method used to display button value on the JLabel and add value to array.
    private void numberAction() {
        makePlaceholderNumber();
        displayLabel.setText(Long.toString(placeholderBuilder));
    }

    // Method used to take a value of a button or key number entered by the user and add it to the placeholder array.
    private void numberAction(int buttonNumber) {
        for (int i = 0; i < placeholder.length; i++) {
            if (placeholder[i] == null) {
                placeholder[i] = buttonNumber;
                break;
            }
        }
        makePlaceholderNumber();
        if (isDecimal == true) {
            displayLabel.setText(beforeDecimal + "." + Long.toString(placeholderBuilder));
        } else {
            displayLabel.setText(Long.toString(placeholderBuilder));
        }
    }

    // Method converts to numbers in the placeholder [] array into a placeholderBuilder string to be displayed in the display JLabel later.
    private void makePlaceholderNumber() {
        StringBuilder number = new StringBuilder();
        for (Integer num : placeholder) {
            if (num != null) {
                number.append(num);
            }
        }

        if (positiveNumberSwitch == false) {
            long displayNumber = Long.parseLong(number.toString());
            displayNumber = -displayNumber;
            placeholderBuilder = displayNumber;
        } else {
            placeholderBuilder = Long.parseLong(number.toString());
        }

        testingStringOutputToConsole();
    }

    private void makeNumberOne() {
        StringBuilder number = new StringBuilder();
        for (Integer num : placeholder) {
            if (num != null) {
                number.append(num);
            }
        }
        if (isDecimal == true) {
            makeNumberAfterDecimal();
            String x = (Long) beforeDecimal + "." + (Long) afterDecimal;

            numberOne = Double.parseDouble(x);
        } else {
            numberOne = Double.parseDouble(number.toString());
        }

        if (positiveNumberSwitch == false) {
            numberOne = -numberOne;
        }

        testingStringOutputToConsole();
    }

    private void makeNumberTwo() {
        StringBuilder number = new StringBuilder();
        for (Integer num : placeholder) {
            if (num != null) {
                number.append(num);
            }
        }

        if (isDecimal == true) {
            makeNumberAfterDecimal();
            String tempString = (Long) beforeDecimal + "." + (Long) afterDecimal;
            numberTwo = Double.parseDouble(tempString);
        } else {
            numberTwo = Double.parseDouble(number.toString());
        }

        if (positiveNumberSwitch == false) {
            numberTwo = -numberTwo;
        }

        testingStringOutputToConsole();

    }

    // Method used to clear the enter the placeholder array and number total as well as reset positive/negative boolean indicator.
    private void clear() {
        for (int i = 0; i < placeholder.length; i++) {
            placeholder[i] = null;
        }
        positiveNumberSwitch = true;
        total = 0;

        testingStringOutputToConsole();

    }

    // Method used to delete the last number entered by the user.
    private void backspace() {
        placeholder[placeholder.length - 2] = null;

        for (int i = 0; i < placeholder.length; i++) {
            if (placeholder[i] == null) {
                placeholder[i - 1] = null;
                break;
            }
        }

        testingStringOutputToConsole();

    }

    //Method generates the total of the two number and displays it of the displayLabel.
    private void total() {
        switch (operand) {
            case ("+"):
                total = numberOne + numberTwo;
                displayLabel.setText((total % 1 == 0) ? Long.toString((long) total) : Double.toString(total));
                break;

            case ("-"):
                total = numberOne - numberTwo;
                displayLabel.setText((total % 1 == 0) ? Long.toString((long) total) : Double.toString(total));
                break;

            case ("*"):
                total = numberOne * numberTwo;
                displayLabel.setText((total % 1 == 0) ? Long.toString((long) total) : Double.toString(total));
                break;

            case ("/"):
                total = numberOne / numberTwo;
                displayLabel.setText((total % 1 == 0) ? Long.toString((long) total) : Double.toString(total));
                break;
        }
    }

    //Method used to allow for the entering of more than 2 numbers before a equals " = ", for example ( 12 + 3256 + 9752 + ..... )
    private void functionForMultipuleEntryPastTwoNumbers() {
        if (numberOne == 0) {
            makeNumberOne();
        } else {
            makeNumberTwo();
            total();
            numberOne = total;
            numberTwo = 0;
        }
        isDecimal = false;
    }

    //Method used to generate the numbers BEFORE a user entered decimal " . " point
    private void makeNumberBeforeDecimal() {
        StringBuilder number = new StringBuilder();
        for (Integer num : placeholder) {
            if (num != null) {
                number.append(num);
            }
        }
        beforeDecimal = Integer.parseInt(number.toString());

        if (positiveNumberSwitch == false) {
            beforeDecimal = -beforeDecimal;
        }

        clear();
    }

    //Method used to generate the numbers AFTER a user entered decimal " . " point
    private void makeNumberAfterDecimal() {
        StringBuilder number = new StringBuilder();
        for (Integer num : placeholder) {
            if (num != null) {
                number.append(num);
            }
        }
        afterDecimal = Integer.parseInt(number.toString());
        clear();
    }

    //Method used to print out text on the console that can assist in seeing what is actually happening
    private void testingStringOutputToConsole() {
        //        System.out.println("-------------------------");
        //        System.out.println(testingConsoleDisplayCounter++);
        //        for (int j = 0; j < placeholder.length; j++) {
        //            System.out.println("placeholder[" + j + "] =" + placeholder[j] + ";");
        //        }
        //        System.out.println("numberOne: " + numberOne);
        //        System.out.println("numberTwo: " + numberTwo);
        //        System.out.println("Total: " + total);
    }

    public static void main(String[] args) {
        Calculator window = new Calculator("Basic Calculator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 800);
        window.setFocusable(true);
        window.setVisible(true);
    }

}