/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.scenetest;

import org.ccj.Director;
import org.ccj.Event;
import org.ccj.EventCustom;
import org.ccj.Scheduler;
import org.ccj.TestLayer;
import org.ccj.TestScene;
import org.ccj.Touch;
import org.ccj.d2.Node;
import org.ccj.d2.action.*;
import org.ccj.editor.ccs.*;
import org.ccj.editor.ccs.JSONObject;
import org.ccj.editor.ccs.JSONUtil;
import org.ccj.math.Vec2;
import org.ccj.ui.Widget;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/17 16:05 $
 *          $Id$
 */
public class SceneEditorTest extends TestScene
{
    public Class[] getLayers()
    {
        return new Class[]{TriggerTest.class,ParticleComponentTest.class, LoadSceneEdtiorFileTest.class, SpriteComponentTest.class, ArmatureComponentTest.class, UIComponentTest.class};
    }

    public static class SceneEditorTestLayer extends TestLayer
    {
        public void initSize(Node node)
        {
            float scale = winSize.height / 320;
            node.setScale(scale);
            node.setPosition((winSize.width - 480 * scale) / 2, (winSize.height - 320 * scale) / 2);
        }
    }

    public static class LoadSceneEdtiorFileTest extends SceneEditorTestLayer
    {
        public void onCreate()
        {
            Node node = SceneReader.getInstance().createNodeWithSceneFile("scenetest/LoadSceneEdtiorFileTest/FishJoy2.json");
            this.addChild(node);
            ActionManagerEx.getInstance().playActionByName("startMenu_1.json", "Animation1");
            this.initSize(node);
        }

        public String getTitle()
        {
            return "loadSceneEdtiorFile Test";
        }
    }

    public static class SpriteComponentTest extends SceneEditorTestLayer
    {
        public void onCreate()
        {
            Node node = SceneReader.getInstance().createNodeWithSceneFile("scenetest/SpriteComponentTest/SpriteComponentTest.json");
            this.addChild(node);
            ActionManagerEx.getInstance().playActionByName("startMenu_1.json", "Animation1");
            Blink action1 = Blink.create(2, 10);
            Blink action2 = Blink.create(2, 5);
            Node sister1 = node.getChildByTag(10003).getComponent("CCSprite").getOwner();
            sister1.runAction(action1);

            Node sister2 = node.getChildByTag(10004).getComponent("CCSprite").getOwner();
            sister2.runAction(action2);

            this.initSize(node);

        }

        public String getTitle()
        {
            return "Sprite Component Test";
        }
    }

    public static class ArmatureComponentTest extends SceneEditorTestLayer
    {
        public void onCreate()
        {
            Node node = SceneReader.getInstance().createNodeWithSceneFile("scenetest/ArmatureComponentTest/ArmatureComponentTest.json");
            this.addChild(node);
            ActionManagerEx.getInstance().playActionByName("startMenu_1.json", "Animation1");

            Node blowFish = node.getChildByTag(10007).getComponent("CCArmature").getOwner();
            blowFish.runAction(MoveBy.create(10, new Vec2(-1000, 0)));

            Node butterFlyFish = node.getChildByTag(10008).getComponent("CCArmature").getOwner();
            butterFlyFish.runAction(MoveBy.create(10, new Vec2(-1000, 0)));
            this.initSize(node);

        }

        public String getTitle()
        {
            return "Armature Component Test";
        }
    }


    public static class UIComponentTest extends SceneEditorTestLayer
    {
        Node node;

        public void onCreate()
        {
            this.node = SceneReader.getInstance().createNodeWithSceneFile("scenetest/UIComponentTest/UIComponentTest.json");
            this.addChild(this.node);
            Widget widget = Widget.cast(this.node.getChildByTag(10025).getComponent("GUIComponent"));
//
//            Widget button = widget.getChildByName("Button_156");
//            button.setVisible(true);

            this.initSize(this.node);
        }

        public void onClick(int type)
        {
            switch (type) {
                case 1: // ccs.TOUCH_EVENT_TYPE_BAGAN
                    Node blowFish = this.node.getChildByTag(10010).getComponent("CCArmature").getOwner();
                    blowFish.runAction(MoveBy.create(10, Vec2.create(-1000, 0)));

                    Node butterFlyFish = this.node.getChildByTag(10011).getComponent("CCArmature").getOwner();
                    butterFlyFish.runAction(MoveBy.create(10, Vec2.create(-1000.0f, 0)));
                    break;
                default:
                    break;
            }
        }

        public String getTitle()
        {
            return "UI Component Test";
        }
    }

    public static class ParticleComponentTest extends SceneEditorTestLayer
    {

        public void onCreate()
        {
            Node node = SceneReader.getInstance().createNodeWithSceneFile("scenetest/ParticleComponentTest/ParticleComponentTest.json");
            if (node == null) {
                return;
            }

            ComRender Particle = ComRender.cast(node.getChildByTag(10020).getComponent("CCParticleSystemQuad"));
            ActionInterval jump = JumpBy.create(5, new Vec2(-500, 0), 50, 4);
            FiniteTimeAction action = Sequence.create(jump, jump.reverse());
            Particle.getNode().runAction(action);

            addChild(node);
        }


        public String getTitle()
        {
            return "Particle Component Test";
        }
    }


    public static class TriggerTest extends SceneEditorTestLayer
    {
        public static final int TRIGGEREVENT_ENTERSCENE = 0,
            TRIGGEREVENT_LEAVESCENE = 1,
            TRIGGEREVENT_INITSCENE = 2,
            TRIGGEREVENT_UPDATESCENE = 3,
            TRIGGEREVENT_TOUCHBEGAN = 4,
            TRIGGEREVENT_TOUCHMOVED = 5,
            TRIGGEREVENT_TOUCHENDED = 6,
            TRIGGEREVENT_TOUCHCANCELLED = 7;

        public void onEnter()
        {
            super.onEnter();
            setTouchMode(Touch.MODE_ONE_BY_ONE);
            setTouchEnabled(true);

            ObjectFactory.getInstance().registerActionType("PlayMusic", PlayMusic.class);
            ObjectFactory.getInstance().registerActionType("TScaleTo", TScaleTo.class);
            ObjectFactory.getInstance().registerActionType("TScaleBy", TRotateBy.class);
            ObjectFactory.getInstance().registerActionType("TMoveTo", TMoveTo.class);
            ObjectFactory.getInstance().registerActionType("TMoveBy", TMoveBy.class);
            ObjectFactory.getInstance().registerActionType("TRotateTo", TRotateTo.class);
            ObjectFactory.getInstance().registerActionType("TRotateBy", TRotateBy.class);
            ObjectFactory.getInstance().registerActionType("TSkewBy", TMoveTo.class);
            ObjectFactory.getInstance().registerActionType("TriggerState", TriggerState.class);
            ObjectFactory.getInstance().registerActionType("ArmaturePlayAction", ArmaturePlayAction.class);
            ObjectFactory.getInstance().registerConditionType("TimeElapsed", TimeElapsed.class);
            ObjectFactory.getInstance().registerConditionType("ArmatureActionState", ArmatureActionState.class);
            ObjectFactory.getInstance().registerConditionType("NodeInRect", NodeInRect.class);
            ObjectFactory.getInstance().registerConditionType("NodeVisible", NodeVisible.class);

            Node node = SceneReader.getInstance().createNodeWithSceneFile("scenetest/TriggerTest/TriggerTest.json");
            initSize(node);
            this.addChild(node, 0, 1);
            this.schedule(new Scheduler.SchedulerCallback()
            {
                public void onUpdate(float delta)
                {
                    super.onUpdate(delta);
                    sendEvent(TRIGGEREVENT_UPDATESCENE);
                }
            });
            sendEvent(TRIGGEREVENT_ENTERSCENE);
        }

        private void sendEvent(int event)
        {
            EventCustom eventCustom = new EventCustom("" + event);
            TriggerManager.getInstance().dispatchEvent(eventCustom);
        }

        public boolean onTouchBegan(Touch touch, Event event)
        {
            sendEvent(TRIGGEREVENT_TOUCHBEGAN);
            return true;
        }

        public void onTouchMoved(Touch touch, Event event)
        {
            sendEvent(TRIGGEREVENT_TOUCHMOVED);

            super.onTouchMoved(touch, event);
        }

        public void onTouchEnded(Touch touch, Event event)
        {
            sendEvent(TRIGGEREVENT_TOUCHENDED);
            super.onTouchEnded(touch, event);
        }

        public void onTouchCancelled(Touch touch, Event event)
        {
            sendEvent(TRIGGEREVENT_TOUCHCANCELLED);
            super.onTouchCancelled(touch, event);
        }
    }

    public static class PlayMusic extends TriggerAction
    {
        int _type = 0;
        int _tag = 0;
        String _comName;

        public boolean onInit()
        {
            super.onInit();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            ComAudio audio = (ComAudio) (node.getComponent(_comName));
            if (_type == 0) {
                audio.playBackgroundMusic();
            }
            else if (_type == 1) {
                audio.playEffect();
            }
            return false;
        }

        public void onDone()
        {
            super.onDone();
        }
    }

    public static class TMoveTo extends TriggerAction
    {
        int _tag = 0;
        float _duration = 0f;

        Vec2 _pos = new Vec2(0, 0);

        public void onSerialize(JSONObject val)
        {
            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if ("Tag".equals(key)) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if ("Duration".equals(key)) {
                    _duration = JSONUtil.getFloatValue_json(subDict, "value");
                }
                else if ("x".equals(key)) {
                    _pos.x = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if ("y".equals(key)) {
                    _pos.y = JSONUtil.getIntValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            return true;
        }

        public void onDone()
        {
            super.onDone();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            ActionInterval actionTo = MoveTo.create(_duration, new Vec2(_pos.x, _pos.y));
            node.runAction(actionTo);
        }

        public void onRemoveAll()
        {
            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            node.getActionManager().removeAllActions();
        }
    }

    public static class TMoveBy extends TriggerAction
    {
        int _tag = 0;
        float _duration = 0f;

        Vec2 _pos = new Vec2(0, 0);

        public void onSerialize(JSONObject val)
        {
            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if ("Tag".equals(key)) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if ("Duration".equals(key)) {
                    _duration = JSONUtil.getFloatValue_json(subDict, "value");
                }
                else if ("x".equals(key)) {
                    _pos.x = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if ("y".equals(key)) {
                    _pos.y = JSONUtil.getIntValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            return true;
        }

        public void onDone()
        {
            super.onDone();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            ActionInterval actionTo = MoveBy.create(_duration, new Vec2(_pos.x, _pos.y));
            node.runAction(actionTo);
        }

        public void onRemoveAll()
        {
            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            node.getActionManager().removeAllActions();
        }
    }

    public static class TScaleTo extends TriggerAction
    {
        float _duration = 0;
        int _tag = 0;
        private float scaleX;
        private float scaleY;

        public void onSerialize(JSONObject val)
        {
            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("Tag")) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("Duration")) {
                    _duration = JSONUtil.getFloatValue_json(subDict, "value");
                }
                else if (key.equals("ScaleX")) {
                    scaleX = JSONUtil.getFloatValue_json(subDict, "value");
                }
                else if (key.equals("ScaleY")) {
                    scaleY = JSONUtil.getFloatValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            return super.onInit();
        }

        public void onDone()
        {
            super.onDone();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            ActionInterval actionBy = ScaleTo.create(_duration, scaleX, scaleY);
            node.runAction(actionBy);
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            node.getActionManager().removeAllActions();
        }
    }


    public static class ArmaturePlayAction extends TriggerAction
    {
        private int _tag;
        private String _ComName;
        private String _aniname;

        public void onSerialize(JSONObject val)
        {
            super.onSerialize(val);

            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("Tag")) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("componentName")) {
                    _ComName = JSONUtil.getStringValue_json(subDict, "value");
                }
                else if (key.equals("AnimationName")) {
                    _aniname = JSONUtil.getStringValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            return super.onInit();
        }

        public void onDone()
        {
            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            ComRender pRender = (ComRender) (node.getComponent(_ComName));
            Armature pAr = (Armature) (pRender.getNode());
            pAr.getAnimation().play(_aniname);
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();
        }
    }


    public static class TriggerState extends TriggerAction
    {
        private int _id;
        private int _state;

        public void onSerialize(JSONObject val)
        {
            super.onSerialize(val);

            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("ID")) {
                    _id = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("State")) {
                    _state = JSONUtil.getIntValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            return super.onInit();
        }

        public void onDone()
        {
            TriggerObject obj = TriggerManager.getInstance().getTriggerObj(_id);
            if (obj != null) {
                if (_state == 0) {
                    obj.setEnabled(false);
                }
                else if (_state == 1) {
                    obj.setEnabled(true);
                }
                else if (_state == 2) {
                    TriggerManager.getInstance().removeTriggerObj(_id);
                }
            }
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();
        }
    }

    public static class TRotateTo extends TriggerAction
    {
        float _duration = 0;
        float _deltaAngle = 0;
        int _tag = 0;

        public void onSerialize(JSONObject val)
        {
            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("Tag")) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("Duration")) {
                    _duration = JSONUtil.getFloatValue_json(subDict, "value");
                }
                else if (key.equals("DeltaAngle")) {
                    _deltaAngle = JSONUtil.getFloatValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            return super.onInit();
        }

        public void onDone()
        {
            super.onDone();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            ActionInterval actionTo = RotateTo.create(_duration, _deltaAngle);
            node.runAction(actionTo);
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            node.getActionManager().removeAllActions();
        }
    }

    public static class TRotateBy extends TriggerAction
    {
        float _duration = 0;
        float _deltaAngle = 0;
        int _tag = 0;

        public void onSerialize(JSONObject val)
        {
            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("Tag")) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("Duration")) {
                    _duration = JSONUtil.getFloatValue_json(subDict, "value");
                }
                else if (key.equals("DeltaAngle")) {
                    _deltaAngle = JSONUtil.getFloatValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            return super.onInit();
        }

        public void onDone()
        {
            super.onDone();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            ActionInterval actionBy = RotateBy.create(_duration, _deltaAngle);
            node.runAction(actionBy);
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();

            Node node = SceneReader.getInstance().getNodeByTag(_tag);
            node.getActionManager().removeAllActions();
        }
    }

    public static class TimeElapsed extends TriggerCondition
    {
        private float _totalTime;
        private float _tmpTime;
        private boolean _suc;
        private Scheduler _scheduler;

        public void onSerialize(JSONObject val)
        {
            super.onSerialize(val);

            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("TotalTime")) {
                    _totalTime = JSONUtil.getFloatValue_json(subDict, "value");
                }
            }
        }

        public void update(float dt)
        {
            _tmpTime += dt;
            if (_tmpTime > _totalTime) {
                _tmpTime = 0.0f;
                _suc = true;
            }
        }

        public boolean onInit()
        {
            _scheduler = Director.getInstance().getScheduler();
//            	_scheduler.retain();
            _scheduler.schedule(new Scheduler.SchedulerCallback()
            {
                public void onUpdate(float delta)
                {
                    update(delta);
                }
            }, 0.0f, 0, 0.0f, false);

            return super.onInit();
        }

        public boolean onDetect()
        {
            return _suc;
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();
        }
    }

    public static class ArmatureActionState extends TriggerCondition
    {
        private int _tag;
        private String _comName;
        private String _aniname;
        private int _state;
        private boolean _suc;
        TriggerManager.ArmatureMovementCallBack callBack;

        public void onSerialize(JSONObject val)
        {
            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("Tag")) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("componentName")) {
                    _comName = JSONUtil.getStringValue_json(subDict, "value");
                }
                else if (key.equals("AnimationName")) {
                    _aniname = JSONUtil.getStringValue_json(subDict, "value");
                }
                else if (key.equals("ActionType")) {
                    _state = JSONUtil.getIntValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            Node pNode = SceneReader.getInstance().getNodeByTag(_tag);
            ComRender pRender = ComRender.cast(pNode.getComponent(_comName));
            ;
            Armature pAr = Armature.cast(pRender.getNode());
            TriggerManager.getInstance().addArmatureMovementCallBack(pAr, new TriggerManager.ArmatureMovementCallBack()
            {
                public void onCallback(Armature armature, int movementType, String movementID)
                {
                    super.onCallback(armature, movementType, movementID);

                    animationEvent(armature, movementType, movementID);
                }
            });

            return super.onInit();
        }

        void animationEvent(Armature armature, int movementType, String movementID)
        {
            String id = movementID;
            if (movementType == _state && id.equals(_aniname)) {
                _suc = true;
            }
        }


        public boolean onDetect()
        {
            return _suc;
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();

            Node pNode = SceneReader.getInstance().getNodeByTag(_tag);
            ComRender pRender = (ComRender) (pNode.getComponent(_comName));
            Armature pAr = (Armature) (pRender.getNode());
            TriggerManager.getInstance().removeArmatureMovementCallBack(pAr, callBack);

        }
    }

    public static class NodeInRect extends TriggerCondition
    {
        private int _tag;
        private int _originX;
        private int _originY;
        private int _sizeWidth;
        private int _sizeHeight;

        public void onSerialize(JSONObject val)
        {
            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("Tag")) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("originX")) {
                    _originX = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("originY")) {
                    _originY = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("sizeWidth")) {
                    _sizeWidth = JSONUtil.getIntValue_json(subDict, "value");
                }
                else if (key.equals("sizeHeight")) {
                    _sizeHeight = JSONUtil.getIntValue_json(subDict, "value");
                }
            }
        }

        public boolean onInit()
        {
            return true;
        }

        public boolean onDetect()
        {
            Node pNode = SceneReader.getInstance().getNodeByTag(_tag);
            if (pNode != null && Math.abs(pNode.getPositionX() - _originX) <= _sizeWidth
                && Math.abs(pNode.getPositionY() - _originY) <= _sizeHeight) {
                System.out.println("onDetect true");
                return true;
            }
            return false;
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();
        }
    }

    public static class NodeVisible extends TriggerCondition
    {
        private int _tag;
        private boolean _visible;

        public void onSerialize(JSONObject val)
        {
            int count = JSONUtil.getArrayCount_json(val, "dataitems");
            for (int i = 0; i < count; ++i) {
                JSONObject subDict = JSONUtil.getSubDictionary_json(val, "dataitems", i);
                String key = JSONUtil.getStringValue_json(subDict, "key");
                if (key.equals("Tag")) {
                    _tag = JSONUtil.getIntValue_json(subDict, "value");
                    continue;
                }
                else if (key.equals("Visible")) {
                    _visible = JSONUtil.getIntValue_json(subDict, "value") > 0 ? true : false;
                    continue;
                }
            }
        }

        public boolean onInit()
        {
            return super.onInit();
        }

        public boolean onDetect()
        {
            Node pNode = SceneReader.getInstance().getNodeByTag(_tag);
            if (pNode != null && pNode.isVisible() == _visible) {
                return true;
            }
            return false;
        }

        public void onRemoveAll()
        {
            super.onRemoveAll();
        }
    }
}
