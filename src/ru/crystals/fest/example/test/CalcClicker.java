package ru.crystals.fest.example.test;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.ComponentMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.core.matcher.JLabelMatcher;
import org.junit.Assert;
import sun.reflect.Reflection;

public enum CalcClicker {
    INSTANCE;

    private Robot robot;
    private ComponentFinder finder;

    private ArrayList<JPanel> list = new ArrayList<JPanel>();
    private ArrayList<JLabel> labelList = new ArrayList<JLabel>();

    private int index = 0;

    private CalcClicker() {
        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        finder = robot.finder();
    }

   /* public void click(int digit) throws Exception {
        String numStr = Integer.toString(digit);
        System.out.println("click() : " + digit);
        for (int i = 0; i < numStr.length(); i++) {
            CaptionMatcher matcher = new CaptionMatcher(String.valueOf(numStr.charAt(i)));
            click(matcher);
        }

    }*/



    /*public void clear() throws Exception {
        CaptionMatcher matcher = new CaptionMatcher("C");
        click(matcher);
    }
*/
    public void plus(ArrayList<JPanel> boxes) throws Exception {

        this.list = boxes;
        for(index = 0; index < boxes.size(); index++) {
            CaptionMatcher matcher = new CaptionMatcher(((JTextField)list.get(index).getComponent(2)).getText(), ((JComboBox)list.get(index).getComponent(0)).getSelectedItem().toString());
            click(matcher);
        }
    }
/*
    public void minus() throws Exception {
        CaptionMatcher matcher = new CaptionMatcher("-");
        click(matcher);
    }

    public void multiply() throws Exception {
        CaptionMatcher matcher = new CaptionMatcher("*");
        click(matcher);
    }

    public void result() throws Exception {
        CaptionMatcher matcher = new CaptionMatcher("=");
        click(matcher);
    }*/


    void makeLabel(String text, boolean type) {
        JLabel outputLabel = new JLabel(text);
        outputLabel.setBorder(new EmptyBorder(0, 5, 0, 25));
        outputLabel.setFont(new Font("monospaced", Font.BOLD, 14));
        outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (type) {
            outputLabel.setForeground(new Color(0, 255, 0));
        } else {
            outputLabel.setForeground(new Color(255, 0, 0));
        }
        labelList.add(outputLabel);
    }


    private void click(CaptionMatcher matcher) throws Exception {
        Component tmp = null;
        long start = System.currentTimeMillis();

        while (tmp == null) {
            try {
                tmp = finder.find(matcher);

                System.out.print(tmp.isEnabled() + "-----");
                final Component foundComponent = tmp;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
//                        robot.moveMouse(btn);
//                        robot.doubleClick(btn);
//                        robot.enterText("Do you know it?");
//                        robot.waitForIdle();
                        actionWithComponent(foundComponent, ((JComboBox)list.get(index).getComponent(0)).getSelectedItem().toString());


                        /*switch (((JComboBox)list.get(index).getComponent(1)).getSelectedItem().toString()) {
                            case "SetText":
                                ((JButton)foundComponent).setText(((JTextField)list.get(index).getComponent(3)).getText());
                                break;

                            case "Click":
                                //robot.moveMouse(btn);
                                ((JButton)foundComponent).doClick();
                                break;

                        }*/


                        //((JButton)btn).doClick();
                        //ActionListener[] AL = ((JTextField)btn).getActionListeners();
                        System.out.print("\n");
                        //System.out.print((AL[0]).toString());
                        System.out.print("\n");
                        //((JButton)btn).setText("333");
                        System.out.print("\n");
                    }
                });

            } catch (Exception e) {
                //e.printStackTrace();
            }
            Thread.yield();
            if (System.currentTimeMillis() - start > 1000) {
                throw new Exception("text to be throwwwwwn");
            }

        }

        //createReport(labelList);
        Thread.sleep(1000);
    }

    public void actionWithComponent(Component component, String type) {
        switch (type) {
            case "JButton":
                switch (((JComboBox)list.get(index).getComponent(1)).getSelectedItem().toString()) {
                    case "Click":
                        ((JButton)component).doClick();
                        break;
                    case "Enable":
                        if((((JTextField)list.get(index).getComponent(3)).getText().compareToIgnoreCase("true") == 0 && ((JButton)component).isEnabled() == true) || (((JTextField)list.get(index).getComponent(3)).getText().compareToIgnoreCase("false") == 0 && ((JButton)component).isEnabled() == false)) {
                            // правда
                            System.out.println("Правда");
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), true);
                        } else {
                            // неправда
                            System.out.println("Неравда");
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), false);
                        }
                        break;
                    case "Visible":
                        if((((JTextField)list.get(index).getComponent(3)).getText().compareToIgnoreCase("true") == 0 && ((JButton)component).isVisible() == true) || (((JTextField)list.get(index).getComponent(3)).getText().compareToIgnoreCase("false") == 0 && ((JButton)component).isVisible() == false)) {
                            // правда
                            System.out.println("Правда");
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), true);
                        } else {
                            // неправда
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), false);
                        }
                        break;
                    case "Background":
                        StringBuffer tmp = new StringBuffer(((JTextField)list.get(index).getComponent(3)).getText());
                        if(tmp.charAt(0) == '#')
                            tmp.deleteCharAt(0);
                        int r = Integer.parseInt(tmp.substring(0,2), 16);
                        int g = Integer.parseInt(tmp.substring(2,4), 16);
                        int b = Integer.parseInt(tmp.substring(4,6), 16);
                        if(((JButton)component).getBackground().equals(new Color(r, g, b))) {
                            //правда
                            System.out.println("Правда");
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), true);
                        }   else {
                            //неправда
                            System.out.println("Неправда");
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), false);
                        }
                        break;
                    case "Text":
                        if(((JButton)component).getText().compareToIgnoreCase(((JTextField)list.get(index).getComponent(3)).getText()) == 0) {
                            // правда
                            System.out.println("Правда");
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), true);
                        } else {
                            // неправда
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), false);
                        }
                        break;
                    case "Font":
                        if(((JButton)component).getFont().getName().compareToIgnoreCase(((JTextField)list.get(index).getComponent(3)).getText()) == 0) {
                            // правда
                            System.out.println("Правда");
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), true);
                        } else {
                            // неправда
                            makeLabel(((JTextField)list.get(index).getComponent(3)).getText() + " : " + ((JTextField)list.get(index).getComponent(3)).getText(), false);
                        }

                        break;
                }
                break;
            case "JRadioButton":
                break;
            case "JCheckBox":
                break;
            case "JLabel":
                break;
            case "JTextField":
                break;

            case "JPasswordField":
                break;
            case "JFormnattedTextField":
                break;
            case "JTextArea":
                break;
            case "JTextPane":
                break;
            case "JEditorPane":
                break;
            case "JComboBox":
                break;
            case "JTable":
                break;
            case "JList":
                break;
            case "JTree":
                break;
            case "JTabbedPane":
                break;
            case "JSplitPane":
                break;
            case "JSpinner":
                break;
            case "JSlider":
                break;
            case "JSeparator":
                break;
            case "JProgressBar":
                break;
            case "JToolBar":
                break;
            case "JScrollBar":
                break;


        }

    }

/*
    public void checkIntValue(int value) throws Exception {
        DisplayLabelMatcher displayLblMatcher = new DisplayLabelMatcher();

        JLabel lbl = null;
        long start = System.currentTimeMillis();

        while (true) {
            try {
                lbl = (JLabel) finder.find(displayLblMatcher);
                Assert.assertEquals(String.valueOf(value), lbl.getText());
                break;

            } catch (Throwable e) {
                // e.printStackTrace();
            }
            Thread.yield();
            if (System.currentTimeMillis() - start > 1000) {
                throw new Exception("text to be thrown");
            }

        }



    }*/

/*    private class DisplayLabelMatcher implements ComponentMatcher {

        @Override
        public boolean matches(Component component) {
            if (component != null && component instanceof JLabel) {
                if ("displayLabel".equals(((JLabel) component).getName())) {
                    return true;
                }
            }
            return false;
        }

    }*/

/*

    public ComponentFinder getFinder() {
        return finder;
    }
*/



    private class CaptionMatcher implements ComponentMatcher {
        private String caption;
        private String type;

        public CaptionMatcher(String caption, String type) {
            this.setType(type);
            this.setCaption(caption);
        }

        @Override
        public boolean matches(Component comp) {
            /*if (comp != null && comp instanceof JButton) {
                //System.out.println("________+ " + Awt2.getComponentVariableName(comp));
                if (caption.equals(Awt2.getComponentVariableName(comp))) {
                    return true;
                }
            }

            if (comp != null && comp instanceof JCheckBox) {
                if (caption.equals(((JCheckBox) comp).getText())) {
                    return true;
                }
            }

            if (comp != null && comp instanceof JTextField) {
                if (caption.equals(((JTextField) comp).getText())) {
                    return true;
                }
            }*/

            switch (type) {
                case "JButton":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JRadioButton":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JCheckBox":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JLabel":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JTextField":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;

                case "JPasswordField":
                    if (comp != null) {
                            if (caption.equals(Awt2.getComponentVariableName(comp))) {
                                return true;
                            }
                        }
                    break;
                case "JFormnattedTextField":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JTextArea":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JTextPane":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JEditorPane":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JComboBox":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JTable":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JList":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JTree":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JTabbedPane":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JSplitPane":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JSpinner":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JSlider":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JSeparator":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JProgressBar":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JToolBar":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;
                case "JScrollBar":
                    if (comp != null) {
                        if (caption.equals(Awt2.getComponentVariableName(comp))) {
                            return true;
                        }
                    }
                    break;


            }

                return false;

        }



        public void setCaption(String caption) {
            this.caption = caption;
        }

        public void setType(String type) {

            this.type = type;
        }

    }

}
