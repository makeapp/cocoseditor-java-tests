/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio;

import org.ccj.TestLayer;
import org.ccj.TestScene;
import org.ccj.d2.Menu;
import org.ccj.d2.MenuItem;
import org.ccj.d2.MenuItemFont;
import org.ccj.editor.ccs.Armature;
import org.ccj.editor.ccs.ArmatureDataManager;
import org.ccj.base.Color3B;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/17 11:39 $
 *          $Id$
 */
public class ArmatureTestScene
    extends TestScene
{
    public Class[] getLayers()
    {
        return new Class[]{TestCSWithSkeleton.class,
            TestCSWithSkeleton.class,
            TestDragonBones20.class,
            TestPerformance.class
        };
    }

    public class TestAsynchronousLoading extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();

            ArmatureDataManager armatureDataManager = ArmatureDataManager.getInstance();
//            armatureDataManager.addArmatureFileInfoAsync(s_knight_png, s_knight_plist, s_knight_xml, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_weapon_png, s_weapon_plist, s_weapon_xml, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_robot_png, s_robot_plist, s_robot_xml, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_cyborg_png, s_cyborg_plist, s_cyborg_xml, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_Dragon_png, s_Dragon_plist, s_Dragon_xml, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_Cowboy_json, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_hero_json, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_horse_json, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_bear_json, this, this);
//            armatureDataManager.addArmatureFileInfoAsync(s_HeroAnimation_json, this, this);
        }
    }

    static public class TestDirectLoading extends TestLayer
    {
        public String getTitle()
        {
            return "Test Direct Loading";
        }

        public void onCreate()
        {
            super.onCreate();

            ArmatureDataManager armatureDataManager = ArmatureDataManager.getInstance();

            armatureDataManager.removeArmatureFileInfo(s_bear_json);
            // load resource directly
            armatureDataManager.addArmatureFileInfo(s_bear_json);

            Armature armature = Armature.create("bear");
            armature.getAnimation().playWithIndex(0);
            armature.setAnchorPoint(0.5f, 0.5f);
            armature.setPosition(winSize.width / 2, winSize.height / 2);
            this.addChild(armature);
        }
    }

    static public class TestCSWithSkeleton extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();

            ArmatureDataManager armatureDataManager = ArmatureDataManager.getInstance();

            armatureDataManager.addArmatureFileInfo(s_Cowboy_json);

            Armature armature = Armature.create("Cowboy");
            armature.getAnimation().playWithIndex(0);
            armature.setScale(0.2f);
            armature.setAnchorPoint(0.5f, 0.5f);
            armature.setPosition(winSize.width / 2, winSize.height / 2);
            this.addChild(armature);
        }

        public String getTitle()
        {
            return "Test Export From CocoStudio With Skeleton Effect";
        }
    }


    static public class TestDragonBones20 extends TestLayer
    {
        public void onCreate()
        {
            ArmatureDataManager armatureDataManager = ArmatureDataManager.getInstance();
            armatureDataManager.addArmatureFileInfo(s_Dragon_png, s_Dragon_plist, s_Dragon_xml);
            Armature armature = Armature.create("Dragon");
            armature.getAnimation().playWithIndex(0);
            armature.getAnimation().setSpeedScale(0.4f);
            armature.setScale(0.6f);

            armature.setAnchorPoint(0.5f, 0.5f);
            armature.setPosition(winSize.width / 2, winSize.height / 2);
            this.addChild(armature);
        }
    }

    static public class TestPerformance extends TestLayer
    {
        int armatureCount = 0;
        int ArmaturePerformanceTag = 100;

        public void onCreate()
        {
            super.onCreate();
            ArmatureDataManager armatureDataManager = ArmatureDataManager.getInstance();
            armatureDataManager.addArmatureFileInfo(s_knight_png, s_knight_plist, s_knight_xml);
            MenuItemFont.setFontSize(65);
            MenuItemFont decrease = MenuItemFont.create(" + ", new MenuItem.MenuItemListener()
            {
                public void onClicked(MenuItem item)
                {
                    addArmature(20);
                }
            });
            decrease.setColor(new Color3B(0, 200, 20));
            MenuItemFont increase = MenuItemFont.create(" - ", new MenuItem.MenuItemListener()
            {
                public void onClicked(MenuItem item)
                {
                    delArmature();
                }
            });
            increase.setColor(new Color3B(0, 200, 20));

            Menu menu = Menu.create(decrease, increase);
            menu.alignItemsHorizontally();
            menu.setPosition(winSize.width / 2, winSize.height / 2);
            this.addChild(menu, 10000);
        }

        private void delArmature()
        {
            if (armatureCount == 0)
                return;

            for (int i = 0; i < 20; i++) {
                removeArmatureFromParent(ArmaturePerformanceTag + armatureCount);
                armatureCount--;
                refreshTitile();
            }
        }

        private void addArmature(int number)
        {
            for (int i = 0; i < number; i++) {
                this.armatureCount++;
                Armature armature = Armature.create("Knight_f/Knight");
                armature.getAnimation().playWithIndex(0);
                armature.setScale(0.6f);
                armature.setPosition(50 + this.armatureCount * 2, 150);
                this.addArmatureToParent(armature);
            }
            refreshTitile();
        }

        private void addArmatureToParent(Armature armature)
        {
            this.addChild(armature, 0, ArmaturePerformanceTag + this.armatureCount);
        }

        private void removeArmatureFromParent(int tag)
        {
            this.removeChildByTag(tag);
        }

        private void refreshTitile()
        {
        }
    }

    static public class TestPerformanceBatchNode extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestChangeZorder extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestAnimationEvent extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestFrameEvent extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestParticleDisplay extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestUseMutiplePicture extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestColliderDetector extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestCalculatedVertex extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestBoundingBox extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestAnchorPoint extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestArmatureNesting extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class Hero extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestArmatureNesting2 extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestPlaySeveralMovement extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestChangeAnimationInternal extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

    static public class TestEasing extends TestLayer
    {
        public void onCreate()
        {
            super.onCreate();
        }
    }

}
