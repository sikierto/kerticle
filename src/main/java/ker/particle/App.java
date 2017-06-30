package ker.particle;

import ker.particle.container.SystemCanvas;
import ker.particle.listener.ButtonListenerImpl;
import ker.particle.listener.KeyListenerImpl;
import ker.particle.listener.MouseListenerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 *
 */
public class App extends JFrame{

    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    private final JButton startButton = new JButton("Start");
    private final JButton quitButton = new JButton("Quit");
    private final JButton pauseButton = new JButton("Pause");

    private SystemCanvas myCanvas;

    private ButtonListenerImpl buttonListener;

    public App(String name){
        super(name);

        LOGGER.info("start particle frame...");
        LOGGER.info("Instantiate myCanvas...");
        myCanvas = new SystemCanvas();

        buttonListener = new ButtonListenerImpl(this);

        Container cp = getContentPane();


//        refrain jPanel to fix to jFrame
//        cp.setLayout(null);
//        arrange components in regions. North, East, South, West, Center
        cp.setLayout(new BorderLayout());

        LOGGER.info("setting up controls...");
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,2));
        p.add(startButton);
        p.add(pauseButton);
        p.add(quitButton);

        LOGGER.info("add contents to main content pane...");
        cp.add(p, BorderLayout.SOUTH);
        cp.add(myCanvas, BorderLayout.CENTER);

//        setUndecorated(false);
        setSize(Constants.HEIGHT, Constants.WIDTH);

//        fit jFrame to jPanel
        pack();

        setResizable(false);

//        display the window in the middle
//        myFrame.setLocationRelativeTo(null);

        LOGGER.info("add Key listener to application...");
        myCanvas.addKeyListener(new KeyListenerImpl(myCanvas));
        LOGGER.info("add mouse listener to application...");
        myCanvas.addMouseListener(new MouseListenerImpl(myCanvas));

        setFocusable(true);

        LOGGER.info("add Action listener to buttons...");
        startButton.addActionListener(buttonListener);
        quitButton.addActionListener(buttonListener);
        pauseButton.addActionListener(buttonListener);

//        keep keylistener active
        startButton.setFocusable(false);
        quitButton.setFocusable(false);
        pauseButton.setFocusable(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        LOGGER.info("myFrame setup finished...");
    }

    public void startCanvas(){

        LOGGER.info("start myCanvas...");
        myCanvas.start();

    }

    public static void main( String[] args ){

        App myApp = new App(Constants.NAME);
        myApp.setVisible(true);
        LOGGER.info("display myFrame...");

    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public SystemCanvas getMyCanvas() {
        return myCanvas;
    }
}
