/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.guitest;

import org.ccj.TestMenuLayer;
import org.ccj.TestMenuScene;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/14 12:29 $
 *          $Id$
 */
public class UITestScene
    extends TestMenuScene
{
    public TestMenuLayer.MenuData[] getMenuData()
    {
        return new TestMenuLayer.MenuData[]{
            new TestMenuLayer.MenuData("Button", UIButtonTest.class, true),
            new TestMenuLayer.MenuData("CheckBox", UICheckBoxTest.class, true),
            new TestMenuLayer.MenuData("ImageView", UIImageViewTest.class, true),
            new TestMenuLayer.MenuData("Text", UITextTest.class, true),
            new TestMenuLayer.MenuData("TextAtlas", UITextAtlasTest.class, true),
            new TestMenuLayer.MenuData("TextBMFont", UITextBMFontTest.class, true),
            new TestMenuLayer.MenuData("LoadingBar", UILoadingBarTest.class, true),
            new TestMenuLayer.MenuData("Slider", UISliderTest.class, true),
            new TestMenuLayer.MenuData("TextField", UITextFieldTest.class, true),
            new TestMenuLayer.MenuData("Layout", UILayoutTest.class, true),
            new TestMenuLayer.MenuData("ListView", UIListViewTest.class, true),
            new TestMenuLayer.MenuData("PageView", UIPageViewTest.class, true),
            new TestMenuLayer.MenuData("ScrollView", UIScrollViewTest.class, true)
        };
    }
}
