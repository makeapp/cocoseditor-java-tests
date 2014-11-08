package org.ccj;

import org.ccj.d2.TMXTiledMap;
import org.ccj.d2.action.MoveTo;
import org.ccj.d2.action.ScaleTo;
import org.ccj.d2.action.Spawn;
import org.ccj.math.Size;
import org.ccj.math.Vec2;

/**
 * Created by yuanyou@makeapp.co on 2014/4/19.
 */
public class TileMapTestScene extends TestScene {

    public Class[] getLayers() {
        return new Class[]{MoveTest.class};
    }

    static public class MoveTest extends TestLayer {
        @Override
        public void onCreate() {
            super.onCreate();

            TMXTiledMap tmx = TMXTiledMap.create("TileMaps/iso-test.tmx");
//            tmx.setAnchorPoint(0,0);
//            tmx.setPosition(0, 0);
            addChild(tmx);
            Size ms = tmx.getMapSize();
            Size ts = tmx.getTileSize();

            MoveTo action = MoveTo.create(1.0f, new Vec2(-ms.getWidth() * ts.getWidth() / 2, -ms.getHeight() * ts.getHeight() / 2));
            ScaleTo scaleTo = ScaleTo.create(1,2.3f,2.3f);

            tmx.runAction(Spawn.create(action,scaleTo));
        }
    }
}
