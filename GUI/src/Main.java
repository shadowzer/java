import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

public class Main {
    public static void changeImage(JLabel label, String path) {
        ImageIcon img = new ImageIcon(path);
        label.setIcon(img);
        label.setSize(img.getIconWidth(), img.getIconHeight());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Testing GUI");
        FlowLayout flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        File defaultImgFile = new File("default.jpg");
        JLabel label = new JLabel();
        if (defaultImgFile.exists())
            changeImage(label, defaultImgFile.getAbsolutePath());
         else
            changeImage(label, "404.jpg");
        frame.add(label);


        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 1);
        panel.setLayout(gridLayout);

        JButton button = new JButton("default.jpg");
        ActionListener actionListenerButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (defaultImgFile.exists())
                    changeImage(label, defaultImgFile.getAbsolutePath());
                else
                    changeImage(label, "404.jpg");
            }
        };
        button.addActionListener(actionListenerButton);

        JButton chooseButton = new JButton("Choose image");
        ActionListener actionListenerChooseButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new ImageFilter());
                int returnValue = fc.showOpenDialog(chooseButton);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    if (file.exists())
                        changeImage(label, file.getAbsolutePath());
                    else
                        changeImage(label, "404.jpg");
                }
            }
        };
        chooseButton.addActionListener(actionListenerChooseButton);


        panel.add(button);
        panel.add(chooseButton);
        frame.add(panel);

        frame.setLocation(0, 0);
        frame.setEnabled(true);
        frame.setMinimumSize(new Dimension(300, 200));
        frame.pack();
        frame.setVisible(true);
    }
}
