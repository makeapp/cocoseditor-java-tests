/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.guitest;

import org.ccj.TestScene;
import org.ccj.editor.ccs.GUIReader;
import org.ccj.base.Ref;
import org.ccj.ui.Helper;
import org.ccj.ui.TextField;
import org.ccj.ui.Widget;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/19 16:07 $
 *          $Id$
 */
public class UITextFieldTest
    extends TestScene
{
    public Class[] getLayers()
    {
        return new Class[]{UITextFieldEditorTest.class};
    }

    public static class UITextFieldEditorTest extends UITestLayer
        implements TextField.TextFieldListener
    {
        public void onCreate()
        {
            super.onCreate();

            Widget root = GUIReader.getInstance().widgetFromJsonFile("cocosui/UIEditorTest/UITextField_Editor/ui_textfield_editor_1.json");
            mainNode.addChild(root);

            TextField textField_normal = TextField.cast(Helper.seekWidgetByName(root, "TextField_1109"));
            textField_normal.addEventListenerTextField(this);

            TextField textField_max_character = TextField.cast(Helper.seekWidgetByName(root, "TextField_1110"));
            textField_max_character.addEventListenerTextField(this);

            TextField textField_password = TextField.cast(Helper.seekWidgetByName(root, "TextField_1107"));
            textField_password.addEventListenerTextField(this);
        }

        public void onChanged(Ref ref, int action)
        {
            switch (action) {
                case TextField.EVENT_ATTACH_WITH_IME:
                    this.topDisplayText.setText("attach with IME");
                    break;
                case TextField.EVENT_DETACH_WITH_IME:
                    this.topDisplayText.setText("detach with IME");
                    break;
                case TextField.EVENT_INSERT_TEXT:
                    this.topDisplayText.setText("insert words");
                    break;
                case TextField.EVENT_DELETE_BACKWARD:
                    this.topDisplayText.setText("delete word");
                    break;
                default:
                    break;
            }
        }
    }

}
