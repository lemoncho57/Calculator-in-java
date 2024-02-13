import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class Calculator implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton[] numberB = new JButton[10];
    JButton[] functionsB = new JButton[9];
    JButton addB;
    JButton subB;
    JButton mulB;
    JButton divB;
    JButton decB;
    JButton equB;
    JButton delB;
    JButton clrB;
    JButton plusMinusB;
    JPanel panel;
    JButton infoB;
    JButton darkLightModeB;
    JButton settingB;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu helpMenu;
    JMenuItem exitItem; // fileMenu
    JMenuItem infoItem; // helpMenu
    Font font = new Font("Arial Rounded MT", Font.BOLD, 26);
    Font fontSettingsB = new Font("Arial Rounded MT", Font.PLAIN, 16);
    Color darkGreen = new Color(0, 100, 0);
    ImageIcon icon = new ImageIcon("Assets/Images/icons8-calculator-96.png");
    ImageIcon darkLightIcon = new ImageIcon("Assets/Images/darkLight.png");
    Image imageDarkLighIconEdited = darkLightIcon.getImage();
    Image newImageDarkLightIconEdited = imageDarkLighIconEdited.getScaledInstance(65,65, Image.SCALE_SMOOTH);

    ImageIcon infoIcon = new ImageIcon("Assets/Images/infoImageIcon.png");
    Image imageInfoIconEdited = infoIcon.getImage();
    Image newImageInfoIconEdited = imageInfoIconEdited.getScaledInstance(65,65, Image.SCALE_SMOOTH);

    FileWriter writer;
    File historyFile;

    double num1;
    double num2;
    double result;
    char numOperator;

    boolean isDark = true;

    Calculator(){
        frame = new JFrame("Calculator");
        frame.setSize(450, 620);

        infoIcon = new ImageIcon(newImageInfoIconEdited);
        darkLightIcon = new ImageIcon(newImageDarkLightIconEdited);

        textField = new JTextField();
        textField.setBounds(50,25,330,45);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBackground(Color.darkGray);
        textField.setForeground(Color.white);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        infoItem = new JMenuItem("Info", infoIcon);
        infoItem.addActionListener(this);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        fileMenu.add(exitItem);
        helpMenu.add(infoItem);

        // History
        try {
            historyFile = new File("history.txt");
            writer = new FileWriter("history.txt");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        addB = new JButton("+");
        subB = new JButton("-");
        mulB = new JButton("*");
        divB = new JButton("/");
        decB = new JButton(".");
        equB = new JButton("=");
        delB = new JButton("DEL");
        clrB = new JButton("C");
        plusMinusB = new JButton("±");

        //infoB = new JButton(infoIcon);
        darkLightModeB = new JButton(darkLightIcon);
        settingB = new JButton("⚙\uFE0F");

        functionsB[0] = addB;
        functionsB[1] = subB;
        functionsB[2] = mulB;
        functionsB[3] = divB;
        functionsB[4] = decB;
        functionsB[5] = equB;
        functionsB[6] = delB;
        functionsB[7] = clrB;
        functionsB[8] = plusMinusB;


        for (int i = 0; i < 9; i++)
        {
            functionsB[i].addActionListener(this);
            functionsB[i].setFont(font);
            functionsB[i].setBackground(Color.orange);
            functionsB[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++)
        {
            numberB[i] = new JButton(String.valueOf(i));
            numberB[i].addActionListener(this);
            numberB[i].setFont(font);
            numberB[i].setBackground(darkGreen);
            numberB[i].setForeground(Color.white);
            numberB[i].setFocusable(false);
        }

        delB.setBounds(50,100,105,50);
        clrB.setBounds(163,100, 105,50);
        plusMinusB.setBounds(275,100,105,50);


        darkLightModeB.setBounds(310, 492, 65, 65);
        darkLightModeB.addActionListener(this);

        settingB.setBounds(230, 492, 65, 65);
        settingB.setFont(fontSettingsB);
        settingB.addActionListener(this);

        panel = new JPanel();
        panel.setBounds(50,170,330,315);
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setBackground(Color.gray);

        panel.add(numberB[1]);
        panel.add(numberB[2]);
        panel.add(numberB[3]);
        panel.add(subB);
        panel.add(numberB[4]);
        panel.add(numberB[5]);
        panel.add(numberB[6]);
        panel.add(addB);
        panel.add(numberB[7]);
        panel.add(numberB[8]);
        panel.add(numberB[9]);
        panel.add(mulB);
        panel.add(decB);
        panel.add(numberB[0]);
        panel.add(equB);
        panel.add(divB);

        frame.setIconImage(icon.getImage());
        frame.add(textField);
        frame.add(delB);
        frame.add(clrB);
        frame.add(plusMinusB);
        frame.add(darkLightModeB);
        frame.add(settingB);
        frame.add(panel);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().setBackground(Color.gray);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    //
    //MAIN
    //


    public static void main(String[] args) {
        Calculator calc = new Calculator();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++)
        {
            if (e.getSource() == numberB[i])
            {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decB && !textField.getText().contains("."))
        {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addB)
        {
            try {
                num1 = Double.parseDouble(textField.getText());
                numOperator = '+';
                textField.setText("");
            }
            catch (Exception error)
            {
                textField.setText("Enter a number first!");
            }

        }
        if (e.getSource() == subB)
        {
            try {
                num1 = Double.parseDouble(textField.getText());
                numOperator = '-';
                textField.setText("");
            }
            catch (Exception error)
            {
                textField.setText("Enter a number first!");
            }
        }
        if (e.getSource() == mulB)
        {
            try {
                num1 = Double.parseDouble(textField.getText());
                numOperator = '*';
                textField.setText("");
            }
            catch (Exception error)
            {
                textField.setText("Enter a number first!");
            }
        }
        if (e.getSource() == divB)
        {
            try {
                num1 = Double.parseDouble(textField.getText());
                numOperator = '/';
                textField.setText("");
            }
            catch (Exception error)
            {
                textField.setText("Enter a number first!");
            }
        }
        if (e.getSource() == equB)
        {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (numOperator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    default:
                        textField.setText("Incorrect");
                        break;
                }
                if (e.getSource() == addB || e.getSource() == subB || e.getSource() == mulB || e.getSource() == divB|| e.getSource() == plusMinusB)
                {
                    num1 = result;
                }

                writer.write("\n" + num1 + numOperator + num2 + " = " + result);
                writer.flush();
            }
            catch (Exception error)
            {
                textField.setText("Make a calculation first!");
            }
            textField.setText(String.valueOf(result));
        }
        if (e.getSource() == clrB)
        {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        }
        if (e.getSource() == delB)
        {
            if (Objects.equals(textField.getText(), "Enter a number first!") || Objects.equals(textField.getText(), "Incorrect") || Objects.equals(textField.getText(), "Make a calculation first!"))
            {
                textField.setText("");
                num1 = 0;
                num2 = 0;
                result = 0;
            }
            String s = textField.getText();
            textField.setText("");
            for (int i = 0; i < s.length()-1; i++)
            {
                textField.setText(textField.getText() + s.charAt(i));
            }

        }
        if (e.getSource() == plusMinusB)
        {
            String text = textField.getText();

            try {
                double timesClicked = Double.parseDouble(text);
                timesClicked *= -1;
                textField.setText(String.valueOf(timesClicked));
            }
            catch (Exception error)
            {
                textField.setText("Enter a number first!");
            }
        }

        if (e.getSource() == darkLightModeB)
        {
            if (isDark)
            {
                frame.getContentPane().setBackground(Color.white);
                panel.setBackground(Color.white);

                textField.setBackground(Color.white);
                textField.setForeground(Color.black);

                isDark = false;
            }
            else
            {
                frame.getContentPane().setBackground(Color.gray);
                panel.setBackground(Color.gray);

                textField.setBackground(Color.darkGray);
                textField.setForeground(Color.white);

                isDark = true;
            }
        }
        if (e.getSource() == settingB)
        {
            new SettingsPage();
        }

        if (e.getSource() == exitItem)
        {
            System.exit(0);
        }
        if (e.getSource() == infoItem)
        {
            JOptionPane.showMessageDialog(null, "This is calculator that is made in java :)");
        }
    }
}