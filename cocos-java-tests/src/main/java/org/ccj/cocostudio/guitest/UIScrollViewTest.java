/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.guitest;

import org.ccj.TestScene;
import org.ccj.editor.ccs.GUIReader;
import org.ccj.ui.Widget;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/19 16:06 $
 *          $Id$
 */
public class UIScrollViewTest
    extends TestScene
{
    static String[] SCROLLVIEW_RES = {
        "cocosui/UIEditorTest/UIScrollView_Editor/UIScrollView_Vertical_Editor/ui_scrollview_editor_1.json",
        "cocosui/UIEditorTest/UIScrollView_Editor/UIScrollView_Horizontal_Editor/ui_scrollview_horizontal_editor_1.json",
        "cocosui/UIEditorTest/UIScrollView_Editor/UIScrollView_Both_Editor/ui_scrollview_both_editor_1.json"
    };

    public Class[] getLayers()
    {
        return new Class[]{UIScrollViewEditorTest.class};
    }

    public static class UIScrollViewEditorTest extends UITestLayer
    {
        int SCROLLVIEW_INDEX = 0;

        public void onCreate()
        {
            super.onCreate();


            Widget root = GUIReader.getInstance().widgetFromJsonFile(SCROLLVIEW_RES[SCROLLVIEW_INDEX]);
            mainNode.addChild(root);

//                    var back_label = ccui.helper.seekWidgetByName(root, "back");
//                    back_label.addTouchEventListener(this.backEvent, this);
//
//                    var left_button = ccui.Button.create();
//                    left_button.loadTextures("Images/b1.png", "Images/b2.png", "");
//                    left_button.x = 240-50;
//                    left_button.y = 50;
//                    left_button.anchorX = 0.5;
//                    left_button.anchorY = 0.5;
//                    left_button.zOrder = 999;
//                    left_button.addTouchEventListener(this.previousCallback, this);
//                    this._mainNode.addChild(left_button);
//
//                    var right_button = ccui.Button.create();
//                    right_button.loadTextures("Images/f1.png", "Images/f2.png", "");
//                    right_button.x = 240+50;
//                    right_button.y = 50;
//                    right_button.zOrder = 999;
//                    right_button.anchorX = 0.5;
//                    right_button.anchorY = 0.5;
//                    right_button.addTouchEventListener(this.nextCallback, this);
//                    this._mainNode.addChild(right_button);
        }


    }

}
