/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.componentstest;

import org.ccj.Event;
import org.ccj.KeyEvent;
import org.ccj.Touch;
import org.ccj.d2.Sprite;
import org.ccj.editor.ccs.ComController;
import org.ccj.math.Vec2;
import org.ccj.math.Rect;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/17 15:56 $
 *          $Id$
 */
public class PlayerController
    extends ComController
{
    public PlayerController()
    {
        setName("PlayerController");
    }

    public void onEnter()
    {
        setTouchMode(Touch.MODE_ONE_BY_ONE);
        setTouchEnabled(true);
        setKeypadEnabled(true);
    }

    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {
        super.onKeyPressed(keyCode, event);
        System.out.println("onKeyPressed "+keyCode);
    }

    @Override
    public void onKeyReleased(int keyCode, KeyEvent event) {
        super.onKeyReleased(keyCode, event);
        System.out.println("onKeyReleased "+keyCode);
    }

    public void onExit()
    {
        super.onExit();
        setTouchEnabled(false);
    }

    public void onUpdate(float delta)
    {
        super.onUpdate(delta);
    }


    public boolean onTouchBegan(Touch touch, Event event)
    {
        Vec2 location = touch.getLocation().fetch();

        Sprite projectile = Sprite.create("components/Projectile.png", new Rect(0, 0, 20, 20));
        projectile.retain();
        this.getOwner().getParent().addChild(projectile, 1, 4);

        ProjectileController com = new ProjectileController();
        projectile.addComponent(com);
        com.move(location.getX(), location.getY());
//        this.getOwner().getComponent("Audio").playEffect("Sound/pew-pew-lei.wav");

        return false;
    }
}
