package ru.crystals.fest.example.test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ru.crystals.fest.example.Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CalcTests extends JFrame {
    private CalcClicker clicker = CalcClicker.INSTANCE;

    private ArrayList<JPanel> list = new ArrayList<JPanel>();

//    private BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    private JLabel displayLabel = new JLabel("J-Mark");
    private JLabel testLabel = new JLabel("It is label for test!");

    private JButton startButton = new JButton("Start");
    private JButton addButton = new JButton("Add");
    private JButton testButton = new JButton("Test");
    private JButton test2Button = new JButton("Test2");

    private String[] swingTypes = {
            "JButton",
            "JRadioButton",
            "JCheckBox",
            "JLabel",
            "JTextField",
            "JPasswordField",
            "JFormnattedTextField",
            "JTextArea",
            "JTextPane",
            "JEditorPane",
            "JComboBox",
            "JTable",
            "JList",
            "JTree",
            "JTabbedPane",
            "JSplitPane",
            "JSpinner",
            "JSlider",
            "JSeparator",
            "JProgressBar",
            "JToolBar",
            "JScrollBar",
            "JScrollPane"};
    private String[] actions = {
            "Find",
            "Enable",
            "Visible",
            "Background",
            "Text",
            "Font",
            "Action"};

    private JComboBox typeList = new JComboBox(swingTypes);

    private JPanel mainPanel;
    private JPanel reportPanel;

    private int numberOfExercises = 1;

    private static int xMax = 450;
    private static int yMax = 20;
    private static int fontHeight = 14;



    public CalcTests() {
        super("J-Mark");
        mainPanel = new JPanel();
        mainPanel.setLayout((new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)));

        JPanel menuPanel = new JPanel();
        menuPanel.setMaximumSize(new Dimension(xMax, yMax + 30));
        makeLabel(displayLabel, mainPanel);

        makeButton(addButton, menuPanel);
        makeButton(startButton, menuPanel);
        mainPanel.add(menuPanel);

        Dimension minSize = new Dimension(20, 100);
        Dimension prefSize = new Dimension(20, 100);
        Dimension maxSize = new Dimension(20, 100);
        mainPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        JScrollPane panelPane = new JScrollPane(mainPanel);
        panelPane.getVerticalScrollBar().setUnitIncrement(10);
        setContentPane(panelPane);
        reportPanel = new JPanel();
        reportPanel.setLayout((new BoxLayout(reportPanel, BoxLayout.PAGE_AXIS)));
        reportPanel.setMaximumSize(new Dimension(xMax, yMax + 30));
        reportPanel.add(new Box.Filler(minSize, prefSize, maxSize));
        reportPanel.setVisible(false);
    }

    public void createReport(ArrayList<JLabel> labelList) {
        for(JLabel tmp : labelList) {
            reportPanel.add(tmp);
        }
        this.reportPanel.revalidate();
        this.reportPanel.repaint();

    }

    @BeforeClass
    public static void setUpOnce() throws Exception {
        Calculator.main(null);
        CalcTests calcTestsWindow = new CalcTests();
        calcTestsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcTestsWindow.setSize(xMax + 10, 600);
        calcTestsWindow.setFocusable(true);
        calcTestsWindow.setVisible(true);


    }

   /* @Before
    public void init() throws Exception {
        clicker.clear();
    }*/

    public void testing() throws Exception {
        clicker.plus(list);
    }

    @Test
    public void testPlus() throws Exception {

        Thread.sleep(100000);

    }

    void makeLabel(JLabel label, JPanel panel) {
        label.setBorder(new EmptyBorder(0, 5, 0, 25));
        label.setFont(new Font("monospaced", Font.BOLD, fontHeight));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

    }


    void makeButton(JButton button, JPanel panel) {
        button.setFont(new Font("monospaced", Font.BOLD, fontHeight));
        button.setForeground(Color.DARK_GRAY);
        button.setBackground(Color.LIGHT_GRAY);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ListenersMy());
        panel.add(button);
    }

    void makeTextField(JTextField textField, JPanel panel) {
        textField.setFont(new Font("monospaced", Font.BOLD, fontHeight));
        textField.setForeground(Color.DARK_GRAY);
        textField.setBackground(Color.LIGHT_GRAY);
//        textField.setColumns(20);
        panel.add(textField);
    }

    void makeComboBox(JComboBox swingTypes, final JComboBox actions, JPanel panel) {
        swingTypes.setFont(new Font("monospaced", Font.BOLD, fontHeight));
        swingTypes.setForeground(Color.DARK_GRAY);
        swingTypes.setBackground(Color.LIGHT_GRAY);

        actions.setFont(new Font("monospaced", Font.BOLD, fontHeight));
        actions.setForeground(Color.DARK_GRAY);
        actions.setBackground(Color.LIGHT_GRAY);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                actions.removeAllItems();
                for(String tmp: returnCombo(item))
                    actions.addItem(tmp);


            }
        };
        swingTypes.addActionListener(actionListener);
        panel.add(swingTypes);
        panel.add(actions);
    }
    public class ListenersMy extends Frame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Add":
                    System.out.println("Added!");
                    createGroupBox();
                    break;
                case "Start":
                    System.out.println("Starting!");
                    try {
                        testing();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    mainPanel.setVisible(false);
                    reportPanel.setVisible(true);
                    //


                    break;
                default:
                    break;
            }
        }
    }

    private void createGroupBox() {
        Dimension minSize = new Dimension(20, 20);
        Dimension prefSize = new Dimension(20, 20);
        Dimension maxSize = new Dimension(20, 20);



        JPanel box = new JPanel();
        box.setMaximumSize(new Dimension(xMax, 100));
        makeComboBox(new JComboBox(swingTypes), new JComboBox(actions), box);
        makeTextField(new JTextField("Name"), box);
        makeTextField(new JTextField("Action"), box);

        box.setBorder(BorderFactory.createTitledBorder("Task " + numberOfExercises));
        numberOfExercises++;

        GridLayout layout = new GridLayout(2, 2, 20, 20);
        box.setLayout(layout);
        this.mainPanel.add(new Box.Filler(minSize, prefSize, maxSize));
        this.mainPanel.add(box);
        list.add(box);
        //this.mainPanel.add(Box.createVerticalGlue());
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }

    private String[] returnCombo(String type) {
        switch(type) {
            case "JButton": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action"};
            }
            case "JRadioButton": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JCheckBox": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JLabel": {
                return new String[] {"Click", "Enable", "Visible", "Text", "Font", "Action"};
            }
            case "JTextField": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JPasswordField": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JFormnattedTextField": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JTextArea": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JTextPane": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JEditorPane": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JTable": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JList": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JTree": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font", "Action", "Foreground"};
            }
            case "JTabbedPane": {
                return new String[] {"Click", "Enable", "Visible", "Text", "Font", "Action"};
            }
            case "JSplitPane": {
                return new String[] {"Find", "Enable", "Visible"};
            }
            case "JSpinner": {
                return new String[] {"Find", "Enable", "Visible", "Font", "Action"};
            }
            case "JSlider": {
                return new String[] {"Find", "Enable", "Visible", "Font", "Action"};
            }
            case "JSeparator": {
                return new String[] {"Find", "Enable", "Visible"};
            }
            case "JProgressBar": {
                return new String[] {"Find", "Enable", "Visible", "Text", "Font", "Action", "Foreground"};
            }
            case "JToolBar": {
                return new String[] {"Click", "Enable", "Visible", "Background", "Text", "Font"};
            }
            case "JScrollPane": {
                return new String[] {"Find"};
            }
            case "JScrollBar": {
                return new String[] {"Find", "Enable", "Visible", "Background", "Text", "Font", "Action"};
            }


        }
        return new String[0];
    }
}
