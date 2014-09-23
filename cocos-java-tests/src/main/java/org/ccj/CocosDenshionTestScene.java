package org.ccj;

/**
 * Created by touchsnow 2014/4/19.
 */
public class CocosDenshionTestScene extends TestScene
{

    public Class[] getLayers()
    {
        return new Class[]{MoveTest.class};
    }

    static public class MoveTest extends TestLayer
    {
        @Override
        public void onCreate()
        {
            super.onCreate();
        }
    }
}
