package com.hearts;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class MyJDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  public MyJDialog(JFrame parent, String title, String message) {
    super(parent, title);
    System.out.println("creating the window..");
    // set the position of the window
    Point p = new Point(400, 400);
    setLocation(p.x, p.y);

    // Create a message
    JPanel messagePane = new JPanel();
    messagePane.add(new JLabel(message));
    // get content pane, which is usually the
    // Container of all the dialog's components.
    getContentPane().add(messagePane);

    // Create a button
    JPanel buttonPane = new JPanel();
    JButton button = new JButton("OK");
    buttonPane.add(button);
    // set action listener on the button
    button.addActionListener(new MyActionListener());
    getContentPane().add(buttonPane, BorderLayout.PAGE_END);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
  }

  // override the createRootPane inherited by the JDialog, to create the rootPane.
  // create functionality to close the window when "Escape" button is pressed
  public JRootPane createRootPane() {
    JRootPane rootPane = new JRootPane();
    KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
    Action action = new AbstractAction() {
      
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        System.out.println("escaping..");
        setVisible(false);
        dispose();
      }
    };
    InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(stroke, "ESCAPE");
    rootPane.getActionMap().put("ESCAPE", action);
    return rootPane;
  }

  // an action listener to be used when an action is performed
  // (e.g. button is pressed)
  class MyActionListener implements ActionListener {

    //close and dispose of the window.
    public void actionPerformed(ActionEvent e) {
      System.out.println("disposing the window..");
      setVisible(false);
      dispose();
    }
  }

  public static void main(String[] a) {
    MyJDialog dialog = new MyJDialog(new JFrame(), "Tomaszu", "Sprawdź swoje konto steam");
    // set the size of the window
    dialog.setSize(300, 150);
  }
}
