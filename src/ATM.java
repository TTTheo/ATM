import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This is the GUI View of the ATM. Note: an IDE was used to help generate this
@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
public class ATM {
    protected JPanel ATM;

    private JPanel numpad;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b0;
    private JButton dotButton;
    private JButton enterButton;
    private JButton clearButton;
    private JButton cancelButton;

    private JPanel screen;
    private JLabel screenText;
    private JTextField screenInput;
    private int maxScreenInput;

    private JPanel leftChoices;
    private JButton choice1;
    private JButton choice2;

    private JPanel rightChoices;
    private JButton choice3;
    private JButton choice4;

    private JButton[] numberButtons;
    private JButton[] choices;

    public ATM() {
        ATM = new JPanel();
        ATM.setLayout(new GridBagLayout());
        numpad = new JPanel();
        numpad.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 0, 0);
        ATM.add(numpad, gbc);
        b2 = new JButton();
        b2.setText("2");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b2, gbc);
        b4 = new JButton();
        b4.setText("4");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b4, gbc);
        b5 = new JButton();
        b5.setText("5");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b5, gbc);
        b6 = new JButton();
        b6.setText("6");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b6, gbc);
        b1 = new JButton();
        b1.setText("1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b1, gbc);
        b7 = new JButton();
        b7.setText("7");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b7, gbc);
        b8 = new JButton();
        b8.setText("8");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b8, gbc);
        b9 = new JButton();
        b9.setText("9");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b9, gbc);
        b0 = new JButton();
        b0.setText("0");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b0, gbc);
        dotButton = new JButton();
        dotButton.setText(".");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(dotButton, gbc);
        enterButton = new JButton();
        enterButton.setText("Enter");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(enterButton, gbc);
        clearButton = new JButton();
        clearButton.setText("Clear");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(clearButton, gbc);
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(cancelButton, gbc);
        b3 = new JButton();
        b3.setText("3");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        numpad.add(b3, gbc);
        screen = new JPanel();
        screen.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ATM.add(screen, gbc);
        screenText = new JLabel();
        screenText.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        screen.add(screenText, gbc);
        screenInput = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        screen.add(screenInput, gbc);
        rightChoices = new JPanel();
        rightChoices.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        ATM.add(rightChoices, gbc);
        choice3 = new JButton();
        choice3.setText("Choice 3");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightChoices.add(choice3, gbc);
        choice4 = new JButton();
        choice4.setText("Choice 4");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightChoices.add(choice4, gbc);
        leftChoices = new JPanel();
        leftChoices.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        ATM.add(leftChoices, gbc);
        choice1 = new JButton();
        choice1.setText("Choice 1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftChoices.add(choice1, gbc);
        choice2 = new JButton();
        choice2.setText("Choice 2");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftChoices.add(choice2, gbc);

        numberButtons = new JButton[] {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9};
        choices = new JButton[] {choice1, choice2, choice3, choice4};
        maxScreenInput = 20;

        // Init numpad actions
        for (int i = 0; i < 10; i++) {
            int val = i;
            ActionListener a = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String screenStr = screenInput.getText();
                    if (screenStr.length() > maxScreenInput) {
                        return;
                    }
                    screenInput.setText(screenStr + String.valueOf(val));
                }
            };
            numberButtons[i].addActionListener(a);
        }

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String screenStr = screenInput.getText();
                if (screenStr.length() > maxScreenInput) {
                    return;
                } else if (screenStr.indexOf('.') != -1) {
                    return;
                }
                screenInput.setText(screenStr + ".");
            }
        };
        dotButton.addActionListener(a);

        a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenInput.setText("");
            }
        };
        clearButton.addActionListener(a);
    }

    // Format a label in html, use for all labels
    public String formatLabelText(String s) {
        String formattedStr = s.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>");
        String htmlStr = "<html>" + formattedStr + "</html>";
        return htmlStr;
    }

    public void setScreenText(String s) {
        String formatted = formatLabelText(s);
        screenText.setText(formatted);
    }

    public void setAction(int i, ActionListener a) {
        choices[i-1].addActionListener(a);
    }

    public void clearActions(JButton b) {
        for(ActionListener al: b.getActionListeners() ) {
            b.removeActionListener(al);
        }
    }

    public void setEnterButton(ActionListener a) {
        enterButton.addActionListener(a);
    }

    public void setCancelButton(ActionListener a) {
        cancelButton.addActionListener(a);
    }

    public String getUserInput() {
        return screenInput.getText();
    }

    // Clears all important user buttons, called first in every handler
    public void clearUserButtons() {
        for (JButton b: choices) {
            clearActions(b);
        }
        clearActions(enterButton);
        clearActions(cancelButton);
    }
}
