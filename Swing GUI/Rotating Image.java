import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Lecture9_1 {
    public static void main(String[] args) {
        showWindow();
    }

    public static MainWindow showWindow() {
        return new MainWindow();
    }
}

class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
    //GridBagConstraints c = new GridBagConstraints();
	
	public MainWindow(){
        super("Problem1");
        this.setBounds(20, 20, 15, 15);
        this.setContentPane(createContentPane());
        //this.setLayout(new GridBagLayout());
        this.setSize(320, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public JPanel createContentPane(){
        JPanel panel = new JPanel(new BorderLayout());

        MyCanvas canvas = new MyCanvas();
        panel.add(canvas,BorderLayout.CENTER);

        JButton buttonUp = new JButton("Up");
        buttonUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.shift(0,1);
            }
        });
        panel.add(buttonUp,BorderLayout.NORTH);

        JButton buttonDown = new JButton("Down");
        buttonDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.shift(0,-1);
            }
        });
        panel.add(buttonDown,BorderLayout.SOUTH);

        JButton buttonLeft = new JButton("Left");
        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.shift(1,0);
            }
        });
        panel.add(buttonLeft,BorderLayout.WEST);

        JButton buttonRight = new JButton("Right");
        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.shift(-1,0);
            }
        });
        panel.add(buttonRight,BorderLayout.EAST);

        return panel;
    }
}

class MyCanvas extends Canvas {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

    public MyCanvas() {
        try {
            image  = ImageIO.read(new URL("https://docs.oracle.com/javase/tutorial/2d/images/examples/strawberry.jpg "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(image, 0, 0, this);
    }

    public void shift(int dx, int dy) {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, this.image.getType());
        
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(x+dx>=0 && x+dx<width && y+dy>=0 && y+dy<height){
                    newImage.setRGB(x,y,this.image.getRGB(x+dx,y+dy));
                }
                else{
                    int rgb = new Color(255,255,255).getRGB();
                    newImage.setRGB(x,y, rgb);
                }
            }
        }
        this.image = newImage;
        this.repaint();
    }
}