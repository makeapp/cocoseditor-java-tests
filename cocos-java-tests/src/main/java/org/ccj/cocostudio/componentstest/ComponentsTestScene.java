/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.componentstest;

import org.ccj.TestLayer;
import org.ccj.TestScene;
import org.ccj.VisibleRect;
import org.ccj.d2.Menu;
import org.ccj.d2.MenuItem;
import org.ccj.d2.MenuItemFont;
import org.ccj.d2.Node;
import org.ccj.d2.Sprite;
import org.ccj.editor.ccs.ComAttribute;
import org.ccj.editor.ccs.ComAudio;
import org.ccj.base.Color3B;
import org.ccj.math.Rect;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/17 15:29 $
 *          $Id$
 */
public class ComponentsTestScene extends TestScene
{
    public Class[] getLayers()
    {
        return new Class[]{ComponentsTestLayer1.class};
    }

    public static class ComponentsTestLayer1
        extends TestLayer
        implements MenuItem.MenuItemListener
    {
        public void onCreate()
        {
            super.onCreate();

            Node root = this.createGameScene();
            root.getChildByTag(1).addComponent(ComAudio.create());
            root.getChildByTag(1).addComponent(new PlayerController());
            root.addComponent(ComAudio.create());
            root.addComponent(ComAttribute.create());
            root.addComponent(new SceneController());

            this.addChild(root, 0, 1);
        }

        public Node createGameScene()
        {
            Node root = Node.create();
            Sprite player = Sprite.create("components/Player.png", new Rect(0, 0, 27, 40));
            player.setPosition(30, winSize.height / 2);
            root.addChild(player, 1, 1);

            MenuItemFont itemBack = MenuItemFont.create("Back", this);
            itemBack.setColor(new Color3B(0, 0, 0));
            float x = VisibleRect.bottomRight().getX() - 50;
            float y = VisibleRect.bottomRight().getY() + 25;
            itemBack.setPosition(x, y);
            Menu menuBack = Menu.create(itemBack);
            menuBack.setPosition(0, 0);
            this.addChild(menuBack);
            return root;
        }

        public void onClicked(MenuItem item)
        {

        }
    }
}
