/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.guitest;

import org.ccj.TestScene;
import org.ccj.editor.ccs.GUIReader;
import org.ccj.base.Ref;
import org.ccj.ui.Button;
import org.ccj.ui.Helper;
import org.ccj.ui.ListView;
import org.ccj.ui.Widget;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/19 16:05 $
 *          $Id$
 */
public class UIListViewTest
    extends TestScene
{
    static String LISTVIEW_RES[] = {
        "cocosui/UIEditorTest/UIListView_Editor/UIListView_Vertical_Editor/ui_listview_editor_1.json",
        "cocosui/UIEditorTest/UIListView_Editor/UIListView_Horizontal_Editor/ui_listview_horizontal_editor_1.json"
    };

    public Class[] getLayers()
    {
        return new Class[]{UIListViewEditorTest.class};
    }

    public static class UIListViewEditorTest extends UITestLayer
    {
        int LISTVIEW_INDEX = 0;

        public void onCreate()
        {
            super.onCreate();

            Widget root = GUIReader.getInstance().widgetFromJsonFile(LISTVIEW_RES[LISTVIEW_INDEX]);
            this.mainNode.addChild(root);

            Widget back_label = Helper.seekWidgetByName(root, "back");
//                    back_label.addTouchEventListener();

            ListView listView = ListView.cast(Helper.seekWidgetByName(root, "ListView_1214"));
            listView.addEventListenerListView(new ListView.ListViewListener()
            {
                public void onItemSelected(Ref ref, int action)
                {
                    System.out.println("onItemSelected" + action);
                }
            });

            Button left_button = Button.create();
            left_button.loadTextures("Images/b1.png", "Images/b2.png", "");
            left_button.setPosition(240 - 50, 50);
            left_button.setAnchorPoint(0.5f, 0.5f);
            left_button.setZOrder(999);
            left_button.addTouchEventListener(new Widget.TouchEventListener()
            {
                public void onTouch(Ref ref, int action)
                {

                }
            });
            this.mainNode.addChild(left_button);

            Button right_button = Button.create();
            right_button.loadTextures("Images/f1.png", "Images/f2.png", "");
            right_button.setPosition(240 + 50, 50);
            right_button.setAnchorPoint(0.5f, 0.5f);
            right_button.setZOrder(999);
            right_button.addTouchEventListener(new Widget.TouchEventListener()
            {
                public void onTouch(Ref ref, int action)
                {

                }
            });
            this.mainNode.addChild(right_button);
        }
    }

}
