import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Testing GUI");
        FlowLayout flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        File imgFile = new File("D:\\default.png");
        JLabel label;
        if (imgFile.exists()) {
            ImageIcon img = new ImageIcon(imgFile.getAbsolutePath());
            label = new JLabel(img);
            label.setSize(img.getIconWidth(), img.getIconHeight());
        } else
            label = new JLabel();
        frame.add(label);


        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 1);
        panel.setLayout(gridLayout);

        JButton button = new JButton("Photo 1");
        ActionListener actionListenerButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon img = new ImageIcon("D:\\1.jpg");
                label.setIcon(img);
                label.setSize(img.getIconWidth(), img.getIconHeight());
            }
        };
        button.addActionListener(actionListenerButton);

        JButton button2 = new JButton("Photo 2");
        ActionListener actionListenerButton2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon img = new ImageIcon("D:\\2.jpg");
                label.setIcon(img);
                label.setSize(img.getIconWidth(), img.getIconHeight());
            }
        };
        button2.addActionListener(actionListenerButton2);

        JButton button3 = new JButton("Photo 3");
        ActionListener actionListenerButton3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon img = new ImageIcon("D:\\3.jpg");
                label.setIcon(img);
                label.setSize(img.getIconWidth(), img.getIconHeight());
            }
        };
        button3.addActionListener(actionListenerButton3);

        JButton chooseButton = new JButton("Choose image");
        ActionListener actionListenerChooseButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new ImageFilter());
                int returnValue = fc.showOpenDialog(chooseButton);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    ImageIcon img = new ImageIcon(file.getAbsolutePath());
                    label.setIcon(img);
                }
            }
        };
        chooseButton.addActionListener(actionListenerChooseButton);

        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.add(chooseButton);
        frame.add(panel);

        frame.setLocation(0, 0);
        frame.setEnabled(true);
        frame.setMinimumSize(new Dimension(300, 200));

        frame.pack();
        frame.setVisible(true);
    }
}
