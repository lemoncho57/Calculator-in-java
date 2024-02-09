import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;


public class SettingsPage implements ActionListener {

    JFrame frame;
    JLabel label;
    JButton showHistoryB;
    JTextArea historyField;
    FileReader reader;
    Scanner scanner;

    Font font = new Font("Meiryo UI", Font.BOLD, 20);


    SettingsPage () {
        frame = new JFrame("Settings");
        frame.setSize(700,700);

        try {
            reader = new FileReader("history.txt" /*, Charset.forName("UTF8")*/);
            scanner = new Scanner(reader);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        label = new JLabel("History");
        label.setBounds(170, 150,50,50);

        showHistoryB = new JButton("Show history");
        showHistoryB.setBounds(175,415,100,37);
        showHistoryB.addActionListener(this);

        historyField = new JTextArea();
        historyField.setBounds(170,200,400,200);
        historyField.setEditable(false);
        historyField.setFont(font);


        frame.add(label);
        frame.add(showHistoryB);
        frame.add(historyField);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showHistoryB)
        {
            // TEST
            //chooser = new JFileChooser();
            //chooser.showSaveDialog(null);

            while (scanner.hasNext())
            {
                String line = scanner.nextLine();
                historyField.setText(historyField.getText() + "\n" + line + "\n");
            }
        }

    }


}
