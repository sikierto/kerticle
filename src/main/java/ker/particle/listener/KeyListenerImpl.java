package ker.particle.listener;

import ker.particle.container.SystemCanvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by kierpagdato on 6/23/17.
 */
public class KeyListenerImpl implements KeyListener {

    private SystemCanvas canvas;

    public KeyListenerImpl(SystemCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        canvas.translateKeyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        canvas.translateKeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        canvas.translateKeyReleased(e);
    }
}
