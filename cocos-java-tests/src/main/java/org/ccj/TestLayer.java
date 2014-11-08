/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj;

import org.ccj.d2.LayerGradient;
import org.ccj.base.Color3B;
import org.ccj.math.Size;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/14 13:00 $
 *          $Id$
 */
public class TestLayer
<<<<<<< HEAD
        extends LayerGradient {
    protected Size winSize = Director.getInstance().getWinSize().fetch();

    public String getTitle() {
        return "";
    }

    public String getSubTitle() {
=======
    extends LayerGradient
{
    protected Size winSize = Director.getInstance().getWinSize().fetch();

    public String getTitle()
    {
        return "";
    }

    public String getSubTitle()
    {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
        return "";
    }

    private boolean created = false;

<<<<<<< HEAD
    public TestLayer() {
=======
    public TestLayer()
    {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
        setStartColor(new Color3B(0, 0, 0));
        setEndColor(new Color3B(0x46, 0x82, 0xB4));
    }

<<<<<<< HEAD
    public void onCreate() {

    }

    public void onEnter() {
=======
    public void onCreate()
    {

    }

    public void onEnter()
    {
>>>>>>> 5eddfa732464c149bb47d4ee6b39028ea41f6062
        if (!created) {
            onCreate();
            created = true;
        }
        super.onEnter();
    }
}
