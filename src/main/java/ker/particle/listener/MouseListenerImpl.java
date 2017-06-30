package ker.particle.listener;

import ker.particle.container.SystemCanvas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by kierpagdato on 6/23/17.
 */
public class MouseListenerImpl implements MouseListener{

    private SystemCanvas canvas;

    public MouseListenerImpl(SystemCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        canvas.translateMousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvas.translateMouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
