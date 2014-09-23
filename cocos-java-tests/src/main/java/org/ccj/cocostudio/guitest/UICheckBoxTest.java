/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.guitest;

import org.ccj.TestScene;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/19 16:05 $
 *          $Id$
 */
public class UICheckBoxTest
    extends TestScene
{

    public Class[] getLayers()
    {
        return new Class[]{UICheckBoxEditorTest.class};
    }

    public static class UICheckBoxEditorTest extends UITestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

}
