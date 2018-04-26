package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import main.Sprite;
import spaceinvaders.Menu;
import spaceinvaders.MenuBackground;
import spaceinvaders.MenuSelection;

/**
 * MenuAnimation Class.
 * @author Eden Yefet
 * @param <T> generic
 */
public class MenuAnimation<T> implements Menu<T> {
    private Sprite background;
    private List<MenuSelection<T>> selections;
    private List<Menu<T>> subMenus;
    private KeyboardSensor ks;
    private boolean stop;
    private T status;
    private AnimationRunner runner;

    /**
     * Instantiates a new menu animation.
     * @param ks the KeyboardSensor
     * @param runner AnimationRunner
     */
    public MenuAnimation(KeyboardSensor ks, AnimationRunner runner) {
        this.runner = runner;
        this.background = new MenuBackground();
        this.subMenus = new ArrayList<>();
        this.selections = new ArrayList<>();
        this.ks = ks;
        this.stop = false;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new MenuSelection<>(key, message, returnVal, false));
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.background.drawOn(d);
        d.setColor(Color.WHITE);
        // d.drawText(100, 200, this.title, 80);
        for (MenuSelection<T> s : this.selections) {
            d.drawText(320, 320 + this.selections.indexOf(s) * 30, s.getKey(), 20);
            d.drawText(370, 320 + this.selections.indexOf(s) * 30, s.getOption(), 20);
            if (this.ks.isPressed(s.getKey())) {
                if (!s.isSubMenu()) {
                    this.status = s.getReturnVal();
                    this.stop = true;
                } else {
                    Menu<T> subMenu = this.subMenus.get(this.selections.indexOf(s));
                    this.runner.run(subMenu);
                    this.status = subMenu.getStatus();
                    this.stop = true;
                    subMenu.reset();
                    break;
                }
            }
        }
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void reset() {
        this.stop = false;
        this.status = null;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
