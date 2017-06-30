package ker.particle.listener;

import ker.particle.App;
import ker.particle.container.SystemCanvas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kierpagdato on 6/23/17.
 */
public class ButtonListenerImpl implements ActionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(ButtonListenerImpl.class);

    private App app;
    private SystemCanvas canvas;
    public ButtonListenerImpl(App app) {
        this.app = app;
        this.canvas = app.getMyCanvas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object s = e.getSource();
        if (s == app.getStartButton()){
            Boolean running = !canvas.isRunning();
            LOGGER.info("start button running: " + running + " | myCanvas.isRunning: " + canvas.isRunning());
            if (running){
                app.getStartButton().setText("Stop");
                app.startCanvas();
            }else{
                app.getStartButton().setText("Start");
                canvas.setRunning(false);
                LOGGER.info("stopping the system loop...");
            }
        }else if (s == app.getPauseButton()){
            Boolean paused = !canvas.isPause();
            LOGGER.info("pause button paused: " + paused + " | myCanvas.isPause: " + canvas.isPause());
            canvas.setPause(paused);
            if (paused){
                app.getPauseButton().setText("Unpause");
            }else{
                app.getPauseButton().setText("Pause");
            }
        }else if (s == app.getQuitButton()){
            System.exit(0);
        }else{
            System.out.println("test");
        }
    }
}
