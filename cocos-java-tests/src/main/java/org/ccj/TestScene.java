/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj;

import org.ccj.d2.Label;
import org.ccj.d2.Menu;
import org.ccj.d2.MenuItem;
import org.ccj.d2.MenuItemFont;
import org.ccj.d2.MenuItemImage;
import org.ccj.math.Size;


/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/14 14:07 $
 *          $Id$
 */
public abstract class TestScene
<<<<<<< HEAD
        extends Scene implements TestResource, MenuItem.MenuItemListener {
=======
    extends Scene implements TestResource, MenuItem.MenuItemListener
{
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
    int index = 0;

    int BASE_TEST_MENUITEM_PREV_TAG = 1;
    int BASE_TEST_MENUITEM_RESET_TAG = 2;
    int BASE_TEST_MENUITEM_NEXT_TAG = 3;

    int BASE_TEST_MENU_TAG = 10;
    int BASE_TEST_TITLE_TAG = 11;
    int BASE_TEST_SUBTITLE_TAG = 12;

<<<<<<< HEAD
    public TestScene() {
=======
    public TestScene()
    {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
        onCreate();
    }

    TestLayer current = null;
    Label labelTitle = null;
    Label subTitle = null;

<<<<<<< HEAD
    public void onCreate() {
=======
    public void onCreate()
    {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
        Size winSize = Director.getInstance().getWinSize();
        float winWidth = winSize.getWidth();
        float winHeight = winSize.getHeight();

        int size = getLayersCount();

        if (size > 0) {
            current = getLayer(index);
            if (current == null) {
                return;
            }
            addChild(current);

        }


        if (!this.getClass().isAssignableFrom(TestMainScene.class)) {
            Menu menu = Menu.create();

            MenuItemFont pItem = MenuItemFont.create("MainScene");
<<<<<<< HEAD
            pItem.setOnClickListener(new MenuItem.MenuItemListener() {
                public void onClicked(MenuItem item) {
=======
            pItem.setOnClickListener(new MenuItem.MenuItemListener()
            {
                public void onClicked(MenuItem item)
                {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
                    Director.getInstance().popScene();
                }
            });
            menu.addChild(pItem);
            pItem.setPosition(winSize.getWidth() - 90, 20);

            if (size > 0 && current != null && hasNavigation()) {
                String t = current.getTitle();
                labelTitle = Label.createWithSystemFont(t, "Arial", 28);
                this.addChild(labelTitle, 100, BASE_TEST_TITLE_TAG);
                labelTitle.setPosition(winSize.getWidth() / 2, winSize.getHeight() - 50);

                String st = current.getSubTitle();
                if (st != null) {
                    subTitle = Label.createWithSystemFont(st.toString(), "Thonburi", 16);
                    this.addChild(subTitle, 101, BASE_TEST_SUBTITLE_TAG);
                    subTitle.setPosition(winWidth / 2, winHeight - 80);
                }

                MenuItemImage item1 = MenuItemImage.create(s_pathB1, s_pathB2);
                MenuItemImage item2 = MenuItemImage.create(s_pathR1, s_pathR2);
                MenuItemImage item3 = MenuItemImage.create(s_pathF1, s_pathF2);

                item1.setOnClickListener(this);
                item2.setOnClickListener(this);
                item3.setOnClickListener(this);

                item1.setTag(BASE_TEST_MENUITEM_PREV_TAG);
                item2.setTag(BASE_TEST_MENUITEM_RESET_TAG);
                item3.setTag(BASE_TEST_MENUITEM_NEXT_TAG);


                Size cs = item2.getContentSize();
                item1.setPosition(winWidth / 2 - cs.getWidth() * 2, cs.getHeight() / 2);
                item2.setPosition(winWidth / 2, cs.getHeight() / 2);
                item3.setPosition(winWidth / 2 + cs.getWidth() * 2, cs.getHeight() / 2);
                menu.addChild(item1);
                menu.addChild(item2);
                menu.addChild(item3);
            }
            menu.setPosition(0, 0);
            addChild(menu, 100);
        }
    }

<<<<<<< HEAD
    public void onEnter() {
        super.onEnter();
    }

    public void onExit() {
        super.onExit();
    }

    public boolean hasNavigation() {
        return true;
    }

    public int getLayersCount() {
        return getLayers().length;
    }

    public TestLayer getLayer(int idx) {
        try {
            return (TestLayer) getLayers()[idx].newInstance();
        } catch (Exception e) {
=======
    public void onEnter()
    {
        super.onEnter();
    }

    public void onExit()
    {
        super.onExit();
    }

    public boolean hasNavigation()
    {
        return true;
    }

    public int getLayersCount()
    {
        return getLayers().length;
    }

    public TestLayer getLayer(int idx)
    {
        try {
            return (TestLayer) getLayers()[idx].newInstance();
        }
        catch (Exception e) {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
            e.printStackTrace();
        }
        return null;
    }

<<<<<<< HEAD
    public Class[] getLayers() {
=======
    public Class[] getLayers()
    {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
        return new Class[0];
    }


<<<<<<< HEAD
    public void onClicked(MenuItem item) {
        if (item.getTag() == BASE_TEST_MENUITEM_PREV_TAG) {
            index--;
        } else if (item.getTag() == BASE_TEST_MENUITEM_NEXT_TAG) {
            index++;
        } else if (item.getTag() == BASE_TEST_MENUITEM_RESET_TAG) {
=======
    public void onClicked(MenuItem item)
    {
        if (item.getTag() == BASE_TEST_MENUITEM_PREV_TAG) {
            index--;
        }
        else if (item.getTag() == BASE_TEST_MENUITEM_NEXT_TAG) {
            index++;
        }
        else if (item.getTag() == BASE_TEST_MENUITEM_RESET_TAG) {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062

        }

        if (index <= 0) {
            index = 0;
        }

        if (index < getLayersCount()) {
            TestLayer current = getLayer(index);
            if (current != null) {
                current.removeFromParent();
            }
            addChild(current);

            if (labelTitle != null) {
                labelTitle.setString(current.getTitle());
<<<<<<< HEAD
            } else if (subTitle != null) {
=======
            }
            else if (subTitle != null) {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
                subTitle.setString(current.getSubTitle());
            }
        }
    }
}
