import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TextEditor implements ActionListener{
    JFrame frame;
    Rectangle size;
    JMenuBar menu;
    JMenu file, edit, theme;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem copy, cut, paste, selectAll, close;
    JMenuItem light, dark, pink, green, orange;
    JTextArea textArea;

    TextEditor() {
        frame = new JFrame("Text Editor");

        menu = new JMenuBar();

        file = new JMenu("File");
        newFile = new JMenuItem("New Note");
        newFile.addActionListener(this);
        openFile = new JMenuItem("Open File");
        openFile.addActionListener(this);
        saveFile = new JMenuItem("Save");
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        edit = new JMenu("Edit");
        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        close = new JMenuItem("Close");
        close.addActionListener(this);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        theme = new JMenu("Theme");
        light = new JMenuItem("Light Theme");
        light.addActionListener(this);
        dark = new JMenuItem("Dark Theme");
        dark.addActionListener(this);
        pink = new JMenuItem("Pink Theme");
        pink.addActionListener(this);
        green = new JMenuItem("Green Theme");
        green.addActionListener(this);
        orange = new JMenuItem("Orange Theme");
        orange.addActionListener(this);

        theme.add(light);
        theme.add(dark);
        theme.add(pink);
        theme.add(green);
        theme.add(orange);

        menu.add(file);
        menu.add(edit);
        menu.add(theme);

        frame.setJMenuBar(menu);

        textArea = new JTextArea();
        textArea.setFont(textArea.getFont().deriveFont(14f));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 5, 0, 0));
        panel.setLayout(new BorderLayout(0, 0));

        panel.add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        frame.add(panel);

        size = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        frame.setSize(size.width + 12, size.height + 12);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent aEvent) {
        if (aEvent.getSource() == newFile) {
            TextEditor newNote = new TextEditor();
        }
        else if (aEvent.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int option = fileChooser.showOpenDialog(null);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader br = new BufferedReader(fileReader);
                    String intermediate;
                    StringBuilder output = new StringBuilder();

                    while ((intermediate = br.readLine()) != null)
                        output.append(intermediate).append("\n");

                    textArea.setText(output.toString());
                    br.close();
                } catch(IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        else if (aEvent.getSource() == saveFile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int option = fileChooser.showSaveDialog(null);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fileWriter);

                    textArea.write(bw);
                    bw.close();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        else if (aEvent.getSource() == copy) {
            textArea.copy();
        }
        else if (aEvent.getSource() == cut) {
            textArea.cut();
        }
        else if (aEvent.getSource() == paste) {
            textArea.paste();
        }
        else if (aEvent.getSource() == selectAll) {
            textArea.selectAll();
        }
        else if (aEvent.getSource() == close) {
            frame.setVisible(false);
            frame.dispose();
        }
        else if (aEvent.getSource() == light) {
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
            textArea.setCaretColor(Color.BLACK);
        }
        else if (aEvent.getSource() == dark) {
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.WHITE);
            textArea.setCaretColor(Color.WHITE);
        }
        else if (aEvent.getSource() == pink) {
            textArea.setBackground(Color.PINK);
            textArea.setForeground(Color.BLACK);
            textArea.setCaretColor(Color.BLACK);
        }
        else if (aEvent.getSource() == green) {
            textArea.setBackground(new Color(144, 238, 144));
            textArea.setForeground(Color.BLACK);
            textArea.setCaretColor(Color.BLACK);
        }
        else if (aEvent.getSource() == orange) {
            textArea.setBackground(new Color(255, 213, 128));
            textArea.setForeground(Color.BLACK);
            textArea.setCaretColor(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}