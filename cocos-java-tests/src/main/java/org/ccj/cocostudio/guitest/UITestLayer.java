/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.guitest;

import org.ccj.TestLayer;
import org.ccj.d2.Node;
import org.ccj.ui.Text;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/19 11:21 $
 *          $Id$
 */
public class UITestLayer extends TestLayer
{
    Node mainNode;

    Text topDisplayText;

    public void onCreate()
    {
        super.onCreate();

        //add main node
        mainNode = Node.create();
        float scale = winSize.height / 320;
        mainNode.setAnchorPoint(0, 0);
        mainNode.setScale(scale);
        mainNode.setPosition((winSize.width - 480 * scale) / 2, (winSize.height - 320 * scale) / 2);
        this.addChild(mainNode);

        topDisplayText = Text.create();
        topDisplayText.setFontName("20px Arial");
        topDisplayText.setPosition(240, 320 - 50);
        mainNode.addChild(topDisplayText, 100);
    }
}
