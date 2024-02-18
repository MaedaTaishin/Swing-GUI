import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class Lecture8_1 {
    public static void main(String[] args) {
        showWindow();
    }

    // DON'T CHANGE THE METHOD SIGNATURE OF showWindow()
    public static MainWindow showWindow() {
        return new MainWindow();
    }
}
/*** I DON'T RECOMMEND TO CHANGE THE ABOVE CODE (EVEN THOUGH YOU CAN) ***/

class MainWindow extends JFrame{
    /********************************/
    public MainWindow(){
        super("Problem1");
        this.setSize(320, 240);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane();
        this.setContentPane(this.createContentPane());
    }

    private JPanel createContentPane() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JButton button1  = new JButton("Left");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Show a generated text on the readonly text box when the button is pushed
                if(event.getSource() == button1){
                    System.out.println("Letf!");
                }
                textArea.setText(textArea.getText() + "Left!\n");
            }
        });
        JButton button2 = new JButton("Right");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Show a generated text on the readonly text box when the button is pushed
                if(event.getSource() == button2){
                    System.out.println("Right");
                }
                textArea.setText(textArea.getText() + "Right!\n");
            }
        });

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(textArea);
        contentPane.add(button1, BorderLayout.WEST);
        contentPane.add(button2, BorderLayout.EAST);
        return contentPane;
    }
    /********************************/
}
