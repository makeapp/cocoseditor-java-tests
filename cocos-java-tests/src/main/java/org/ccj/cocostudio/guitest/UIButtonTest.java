/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.guitest;

import org.ccj.TestScene;
import org.ccj.editor.ccs.GUIReader;
import org.ccj.base.Ref;
import org.ccj.ui.Helper;
import org.ccj.ui.Widget;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/17 16:05 $
 *          $Id$
 */
public class UIButtonTest extends TestScene
{
    public Class[] getLayers()
    {
        return new Class[]{UIButtonEditorTest.class};
    }

    public static class UIButtonEditorTest extends UITestLayer
        implements Widget.TouchEventListener
    {
        public void onTouch(Ref ref, int action)
        {
            switch (action) {
                case Widget.TOUCH_BAGAN:
                    this.topDisplayText.setText("Touch Down");
                    break;

                case Widget.TOUCH_MOVED:
                    this.topDisplayText.setText("Touch Move");
                    break;

                case Widget.TOUCH_ENDED:
                    this.topDisplayText.setText("Touch Up");
                    break;

                case Widget.TOUCH_CANCELED:
                    this.topDisplayText.setText("Touch Cancelled");
                    break;

                default:
                    break;
            }
        }

        public void onCreate()
        {
            super.onCreate();

            Widget root = GUIReader.getInstance().widgetFromJsonFile("cocosui/UIEditorTest/UIButton_Editor/UIButton_Editor_1.json");
            mainNode.addChild(root);

            Widget back_label = Helper.seekWidgetByName(root, "back");
            back_label.addTouchEventListener(this);
            Widget button = Helper.seekWidgetByName(root, "Button_123");
            button.addTouchEventListener(this);
            Widget title_button = Helper.seekWidgetByName(root, "Button_126");
            title_button.addTouchEventListener(this);
            Widget scale9_button = Helper.seekWidgetByName(root, "Button_129");
            scale9_button.addTouchEventListener(this);
        }
    }


}
