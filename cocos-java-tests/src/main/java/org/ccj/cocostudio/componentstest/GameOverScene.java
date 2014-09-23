/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.componentstest;

import org.ccj.Director;
import org.ccj.Scene;
import org.ccj.VisibleRect;
import org.ccj.d2.Label;
import org.ccj.d2.LayerColor;
import org.ccj.d2.Menu;
import org.ccj.d2.MenuItem;
import org.ccj.d2.MenuItemFont;
import org.ccj.d2.action.CallFunc;
import org.ccj.d2.action.DelayTime;
import org.ccj.d2.action.Sequence;
import org.ccj.base.Color3B;
import org.ccj.math.Size;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/23 17:08 $
 *          $Id$
 */
public class GameOverScene extends Scene
{
    GameOverLayer _layer;

    public void onEnter()
    {
        super.onEnter();

        this._layer = new GameOverLayer();
        this.addChild(this._layer);
    }


    class GameOverLayer extends LayerColor
    {
        Label _label;

        GameOverLayer()
        {
            setColor(new Color3B(255, 255, 255));
            setOpacity(255);
        }

        public void onEnter()
        {
            super.onEnter();
            Size winSize = Director.getInstance().getWinSize().fetch();
            this._label = Label.createWithSystemFont("You Lose!", "Artial", 32);
            this._label.retain();
            this._label.setColor(new Color3B(0, 0, 0));
            this._label.setPosition(winSize.width / 2, winSize.height / 2);
            this.addChild(this._label);
            this.runAction(Sequence.create(DelayTime.create(3), new CallFunc()
            {
                public void execute()
                {
                    super.execute();
                    Director.getInstance().replaceScene(new ComponentsTestScene());
                }
            }));
            MenuItemFont itemBack = MenuItemFont.create("Back");
            itemBack.setOnClickListener(new MenuItem.MenuItemListener()
            {
                public void onClicked(MenuItem item)
                {
                    Director.getInstance().replaceScene(new ComponentsTestScene());
                }
            });
            itemBack.setColor(new Color3B(0, 0, 0));
            itemBack.setPosition(VisibleRect.bottomRight().x - 50, VisibleRect.bottomRight().y + 20);

            Menu menuBack = Menu.create(itemBack);
            menuBack.setPosition(0, 0);
            this.addChild(menuBack);
        }
    }
}
