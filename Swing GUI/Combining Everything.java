import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class Lecture14_1 {
    public static void main(String[] args) {
        showWindow();
    }

    // DON'T CHANGE THE METHOD SIGNATURE OF showWindow()
    public static MainWindow showWindow() {
        return new MainWindow();
    }
}
/*** I DON'T RECOMMEND TO CHANGE THE ABOVE CODE (EVEN THOUGH YOU CAN) ***/

class MainWindow extends JFrame {
    public MainWindow() {
        super("Paint Tool");
        setContentPane(createContentPane());
        // Don't change the size!
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createContentPane() {
        CanvasPanel canvas = new CanvasPanel();
        MouseListener listener = new MouseListener(canvas);
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);

        JTextArea textArea = new JTextArea(); //make a text box
        textArea.setEditable(true); //jtextbox is editable
        contentPane.add(textArea,BorderLayout.NORTH);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {//load button
            public void actionPerformed(ActionEvent e) {
                String link = textArea.getText(); 
                canvas.loadImage(link);
            }
        });
        contentPane.add(loadButton, BorderLayout.WEST);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {//save button
            public void actionPerformed(ActionEvent e) {
                String link = textArea.getText();
                canvas.saveImageAsPng(link);
            }
        });
        contentPane.add(saveButton, BorderLayout.EAST);
        return contentPane;
    }
}

class MouseListener extends MouseAdapter {
    private final CanvasPanel canvasPanel;

    // Store the most recent X and Y of `mousePressed` or `mousePressed`.
    // 最も最近の `mousePressed` もしくは `mousePressed` のXとYを保存する。
    private int lastX;
    private int lastY;

    MouseListener(CanvasPanel canvas) {
        canvasPanel = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        canvasPanel.drawLine(e.getX(),e.getY(),e.getX(),e.getY()); 
        lastX=e.getX(); 
        lastY=e.getY();

        // Draw a line (i.e., dot) between the current mouse position and the current mouse position.
        // 現在のマウス位置から現在のマウス位置間で直線（つまり、ドット）を描く。
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        canvasPanel.drawLine(lastX,lastY,e.getX(),e.getY()); 
        lastX=e.getX(); 
        lastY=e.getY();

        // Draw a line between the post-movement pixel (i.e., current mouse position) and the pre-movement pixel (i.e., (lastX, lastY)).
        // 移動前のピクセル（つまり、現在のマウス）から移動後のピクセル（つまり、(lastX, lastY)）間で直線を描く。
    }
}

class CanvasPanel extends JPanel {
    private final BufferedImage image;
    private final Graphics2D graphics;

    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setColor(Color.BLACK);
    }

    public void drawLine(int startX, int startY, int endX, int endY) {
        graphics.drawLine(startX, startY, endX, endY);
        repaint();
    }
    
    public void loadImage(String fileName) {
        try {
            Image im = ImageIO.read(new File(fileName));
            image.getGraphics().drawImage(im, 0, 0, null);
            repaint();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveImageAsPng(String fileName) {
        try {
            File outputfile = new File(fileName);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
