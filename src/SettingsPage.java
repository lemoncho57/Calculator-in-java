import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;


public class SettingsPage implements ActionListener {

    JFrame frame;
    JLabel label;
    JButton showHistoryB;
    JButton deleteHistoryB;
    DefaultListModel<String> listModel;
    JList<String> historyField;
    JScrollPane scrollHistoryField;
    FileReader reader;
    Scanner scanner;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem closeItem;

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

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        closeItem = new JMenuItem("Close");
        closeItem.addActionListener(this);
        closeItem.setMnemonic(KeyEvent.VK_C);


        label = new JLabel("History");
        label.setBounds(170, 100,50,50);

        showHistoryB = new JButton("Show history");
        showHistoryB.setBounds(175,520,130,37);
        showHistoryB.addActionListener(this);

        deleteHistoryB = new JButton("Delete history");
        deleteHistoryB.setBounds(320,520,130,37);
        deleteHistoryB.addActionListener(this);

        listModel = new DefaultListModel<String>();
        listModel.clear();

        historyField = new JList<String>();
        historyField.setBounds(170,150,400,350);
        historyField.setVisible(true);
        historyField.setFont(font);
        historyField.setModel(listModel);

        menuBar.add(fileMenu);
        fileMenu.add(closeItem);

        scrollHistoryField = new JScrollPane(historyField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        frame.add(label);
        frame.add(showHistoryB);
        frame.add(deleteHistoryB);
        frame.add(historyField);
        frame.setLayout(null);
        frame.setJMenuBar(menuBar);
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

                listModel.addElement(line);
            }
        }
        if (e.getSource() == deleteHistoryB){
            listModel.clear();
        }

        if (e.getSource() == closeItem)
        {
            frame.dispose();
        }
    }

}
