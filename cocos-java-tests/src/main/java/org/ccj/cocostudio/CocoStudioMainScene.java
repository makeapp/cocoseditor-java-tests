/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio;

import org.ccj.TestMenuLayer;
import org.ccj.TestMenuScene;
import org.ccj.cocostudio.componentstest.ComponentsTestScene;
import org.ccj.cocostudio.guitest.UITestScene;
import org.ccj.cocostudio.scenetest.SceneEditorTest;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/14 12:29 $
 *          $Id$
 */
public class CocoStudioMainScene
    extends TestMenuScene
{

    public TestMenuLayer.MenuData[] getMenuData()
    {
        return new TestMenuLayer.MenuData[]{
            new TestMenuLayer.MenuData("CocoStudioArmatureTest", ArmatureTestScene.class, true),
            new TestMenuLayer.MenuData("CocoStudioGUITest", UITestScene.class, true),
            new TestMenuLayer.MenuData("CocoStudioComponentsTest", ComponentsTestScene.class, true),
            new TestMenuLayer.MenuData("CocoStudioSceneTest", SceneEditorTest.class, true)
        };
    }
}
