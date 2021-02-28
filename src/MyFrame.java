import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyFrame extends JFrame {
    private Robot rob;
    private javax.swing.Timer tm;
    private int kol = 0;
    private Frame wnd;

    public MyFrame() {
        try {
            rob = new Robot();
        } catch (Exception e) {

        }
        tm = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveScreen();
            }
        });
        tm.start();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setVisible(false);
    }
    public void SaveScreen() {
        kol++;
        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        int w = dm.width;
        int h = dm.height;
        try{
            BufferedImage img = rob.createScreenCapture(new Rectangle(0,0,w,h));
            ImageIO.write(img, "PNG", new File("C:\\screen" + kol+ ".png"));

        }catch (Exception e){

        }

        if(kol==6){
            tm.stop();
            wnd = new Frame();

            wnd.setResizable(false);
            wnd.setBounds(0,0,w,h);
            wnd.setBackground(Color.PINK);
            wnd.setAlwaysOnTop(true);
            wnd.setUndecorated(true);
            wnd.setOpacity(0.5f);

            wnd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent arg0) {
                    wnd.setExtendedState(Frame.NORMAL);
                }
            });
            wnd.setVisible(true);
            tm = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    wnd.toFront();
                }
            });
        }
    }

}

