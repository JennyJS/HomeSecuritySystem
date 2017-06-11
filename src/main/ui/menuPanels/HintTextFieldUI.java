package main.menuPanels;

import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Display hint in text field.
 *
 * Created by manhongren on 6/3/17.
 */
public class HintTextFieldUI extends BasicTextFieldUI implements FocusListener {

    private String hint;
    private boolean hideOnFocus;
    private Color color;

    private void repaint() {
        if(getComponent() != null) {
            getComponent().repaint();
        }
    }

    public HintTextFieldUI(String hint, boolean hideOnFocus) {
        this(hint,hideOnFocus, null);
    }

    public HintTextFieldUI(String hint, boolean hideOnFocus, Color color) {
        this.hint = hint;
        this.hideOnFocus = hideOnFocus;
        this.color = color;
    }

    @Override
    protected void paintSafely(Graphics g) {
        super.paintSafely(g);
        JTextComponent comp = getComponent();
        if(hint != null && comp.getText().length() == 0 && !(hideOnFocus && comp.hasFocus())){
            if(color != null) {
                g.setColor(color);
            } else {
                g.setColor(comp.getForeground().brighter().brighter().brighter());
            }
            int padding = (comp.getHeight() - comp.getFont().getSize())/2;
            g.drawString(hint, 2, comp.getHeight()-padding-1);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(hideOnFocus) repaint();

    }

    @Override
    public void focusLost(FocusEvent e) {
        if(hideOnFocus) repaint();
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        getComponent().addFocusListener(this);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        getComponent().removeFocusListener(this);
    }
}
