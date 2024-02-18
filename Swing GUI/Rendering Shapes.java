import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class Lecture12_1 {
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
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createContentPane() {
        CanvasPanel canvas = new CanvasPanel();
        canvas.addMouseListener(new MouseAdapter() {
            int startX;
            int startY;

            @Override
            public void mousePressed(MouseEvent e) {
                /********************************/
                startX = e.getX();
                startY = e.getY();
                /********************************/
            }
           

            @Override
            public void mouseReleased(MouseEvent e) {
                /********************************/
                int endX = e.getX();
                int endY = e.getY();
                canvas.drawRectangle(startX, startY, endX, endY);
                /********************************/
            }

        });

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        return contentPane;
    }
}

class CanvasPanel extends JPanel {
    private final BufferedImage image;
    private Graphics2D graphics;

    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }

    /**
     * Draws a rectangle filled with black color between (x1, y1) and (x2, y2).
     * Note that you cannot expect x1 <= x2 and/or y1 <= y2 do not always hold.
     * (x1, y1) から (x2, y2) の範囲で黒色で塗りつぶした長方形を描画する。
     * x1 <= x2 や y1 <= y2 が常に成りつ立つわけではないことに注意せよ。
     */
    public void drawRectangle(int x1, int y1, int x2, int y2) {
        /********************************/
        graphics = image.createGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
        /********************************/
        repaint();
    }
    

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
