package ker.particle.container;

import ker.particle.Constants;
import ker.particle.manager.SystemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by kierpagdato on 6/15/17.
 */
public class SystemCanvas extends Canvas implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(SystemCanvas.class);

//    without volatile the thread doesnt get the updated value
    private static volatile boolean running = false;
    private static volatile boolean pause = false;

    private int fps = 60;
    public static volatile int frameCount = 0;

    private SystemManager manager;

    public static volatile Integer CANVAS_X = 0;
    public static volatile Integer CANVAS_Y = 0;

    public SystemCanvas() {

        manager = new SystemManager();

        setSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
        setMinimumSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
        setMaximumSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
        setPreferredSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));

        requestFocusInWindow();
    }

    public synchronized void start() {
        LOGGER.info("starting process... running: " + running);
        if(!running){

            CANVAS_X = getLocationOnScreen().x;
            CANVAS_Y = getLocationOnScreen().y;

            running = true;

            new Thread(this).start();
            LOGGER.info("process started...");
        }else{
            LOGGER.info("it is already running...");
        }

    }

    public void run() {

        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 1;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running){

            double now = System.nanoTime();
            int updateCount = 0;

            if (!pause){
                //Do as many game updates as we need to, potentially playing catchup.
                while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER ){
                    updateGame();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES){
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
                render(interpolation);
                lastRenderTime = now;

                //Update the frames we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime){
//                    LOGGER.info("new second time " + thisSecond + " framecount " + frameCount);
                    fps = frameCount;
                    frameCount = 0;
                    lastSecondTime = thisSecond;
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES){
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    try {Thread.sleep(1);} catch(Exception e) { e.printStackTrace();}

                    now = System.nanoTime();
                }
            }//!pause
        }//while running

        LOGGER.info("while loop stopped: " + running);
    }


    private void updateGame(){
        manager.update();
    }


    public void render(float interpolation) {


        BufferStrategy strategy = getBufferStrategy();
        Graphics2D g;

        if (strategy == null) {
            createBufferStrategy(2);
            return;
        }

        do{
            g = (Graphics2D) strategy.getDrawGraphics();
            try{
                g.setColor(g.getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());

                 /* Enable anti-aliasing and pure stroke */
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                manager.setInterpolation(interpolation);
                manager.draw(g);
            }finally {
                g.dispose();
            }

            strategy.show();

            frameCount++;

            Toolkit.getDefaultToolkit().sync();


        }while (strategy.contentsLost());
    }

    public void translateKeyTyped(KeyEvent e){
        manager.translateKeyTyped(e);
    }

    public void translateKeyPressed(KeyEvent e){
        manager.translateKeyPressed(e);
    }

    public void translateKeyReleased(KeyEvent e){
        manager.translateKeyReleased(e);
    }

    public void translateMousePressed(MouseEvent e){
        manager.translateMousePressed(e);
    }

    public void translateMouseReleased(MouseEvent e){
        manager.translateMouseReleased(e);
    }

    public SystemManager getManager() {
        return manager;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        SystemCanvas.running = running;
    }

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean pause) {
        SystemCanvas.pause = pause;
    }
}
