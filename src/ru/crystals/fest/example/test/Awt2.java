package ru.crystals.fest.example.test;

/**
 * Created by Artyom on 15.11.2015.
 */
import java.awt.Component;
import java.lang.reflect.Field;

/**
 * additional utilities for working with AWT/Swing.
 * this is a single method for demo purposes.
 * recommended to be combined into a single class
 * module with other similar methods,
 * e.g. MySwingUtilities
 *
 * @author http://javajon.blogspot.com/2013/07/java-awtswing-getcomponentvariablenamec.html
 */
public class Awt2 {

    /**
     * substitute for component.getName() when used in NetBeans or other IDE
     * that creates class fields to hold the components. uses reflection to
     * search through class fields for a match.
     *
     * @param component the component to look for
     * @return hopefully the variable name used to hold this component
     */
    static public String getComponentVariableName(Object object) {

        if (object instanceof Component) {
            final Component component = (Component) object;
            final StringBuilder sb = new StringBuilder();

            // find the form where the variable name would be likely to exist
            final Component parentForm = getParentForm(component);

            // loop through all of the class fields on that form
            for (Field field : parentForm.getClass().getDeclaredFields()) {

                try {
                    // let us look at private fields, please
                    field.setAccessible(true);

                    // get a potential match
                    final Object potentialMatch = field.get(parentForm);

                    // compare it
                    if (potentialMatch == component) {

                        // return the name of the variable used
                        // to hold this component
                        if (sb.length() > 0) sb.append(",");
                        sb.append(field.getName());
                    }

                } catch (SecurityException | IllegalArgumentException
                        | IllegalAccessException ex) {

                    // ignore exceptions
                }
            }

            if (sb.length() > 0) {
                return sb.toString();
            }
        }

        // if we get here, we're probably trying to find the form
        // itself, in which case it may be more useful to print
        // the class name (MyJFrame) than the AWT-assigned name
        // of the form (frame0)
        final String className = object.getClass().getName();
        final String[] split = className.split("\\.");
        final int lastIndex = split.length - 1;
        return (lastIndex >= 0) ? split[lastIndex] : className;

    }

    /**
     * traverses up the component tree to find the top, which i assume is the
     * dialog or frame upon which this component lives.
     *
     * @param sourceComponent
     * @return top level parent component
     */
    static public Component getParentForm(Component sourceComponent) {
        while (sourceComponent.getParent() != null) {
            sourceComponent = sourceComponent.getParent();
        }
        return sourceComponent;
    }
}