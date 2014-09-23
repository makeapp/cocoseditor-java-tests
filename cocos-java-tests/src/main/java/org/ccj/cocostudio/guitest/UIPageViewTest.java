/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.guitest;

import org.ccj.TestScene;
import org.ccj.editor.ccs.GUIReader;
import org.ccj.base.Ref;
import org.ccj.ui.Helper;
import org.ccj.ui.PageView;
import org.ccj.ui.Widget;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/19 16:06 $
 *          $Id$
 */
public class UIPageViewTest
    extends TestScene
{
    public Class[] getLayers()
    {
        return new Class[]{UIPageViewEditorTest.class};
    }


    public static class UIPageViewEditorTest extends UITestLayer
        implements Widget.TouchEventListener
    {

        public void onCreate()
        {
            super.onCreate();

            Widget root = GUIReader.getInstance().widgetFromJsonFile("cocosui/UIEditorTest/UIPageView_Editor/ui_pageview_editor_1.json");
            mainNode.addChild(root);

            Widget back_label = Helper.seekWidgetByName(root, "back");
            back_label.addTouchEventListener(this);
            PageView pageView = PageView.cast(Helper.seekWidgetByName(root, "PageView_1269"));
            pageView.addEventListenerPageView(new PageView.PageViewListener()
            {
                public void onTurn(Ref ref, int action)
                {
                    System.out.println("on turn");
                }
            });
        }

        public void onTouch(Ref ref, int action)
        {

        }
    }
}
