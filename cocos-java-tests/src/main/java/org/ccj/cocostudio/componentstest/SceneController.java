/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.componentstest;

import java.util.ArrayList;
import java.util.List;

import org.ccj.Director;
import org.ccj.audio.AudioEngine;
import org.ccj.d2.Node;
import org.ccj.d2.Sprite;
import org.ccj.editor.ccs.ComAttribute;
import org.ccj.editor.ccs.ComAudio;
import org.ccj.editor.ccs.ComController;
import org.ccj.math.Rect;
import com.googlecode.javacpp.Pointer;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/17 15:28 $
 *          $Id$
 */
public class SceneController
    extends ComController
{
    float _addTargetTime;
    float _elapsedTime;
    List _targets;
    List _projectiles;

    public SceneController()
    {
        setName("SceneController");
    }

    public SceneController(Pointer p)
    {
        super(p);
    }

    public void onEnter()
    {
        super.onEnter();
        this._addTargetTime = 1;
        _targets = new ArrayList();
        _projectiles = new ArrayList();

        Node owner = this.getOwner();

        ComAudio audio = ComAudio.cast(owner.getComponent("CCComAudio"));
//        audio.playBackgroundMusic("Sound/background-music-aac.wav", true);
        AudioEngine.getInstance().playBackgroundMusic("Sound/background-music-aac.wav", true);
        ComAttribute attribute = ComAttribute.cast(owner.getComponent("CCComAttribute"));
        attribute.setInt("KillCount", 0);
    }

    public void onExit()
    {
        super.onExit();
        System.out.println("onExit");

    }

    public void onUpdate(float dt)
    {
        super.onUpdate(dt);
        this._elapsedTime += dt;
        if (this._elapsedTime > this._addTargetTime) {
            this.addTarget();
            this._elapsedTime = 0.0f;
        }
    }

    private void addTarget()
    {
        Sprite target = Sprite.create("components/Target.png", new Rect(0, 0, 27, 40));
        target.retain();
        this.getOwner().addChild(target, 1, 2);
        target.addComponent(new EnemyController());
        target.setTag(2);
        this._targets.add(target);
    }

    public void spriteMoveFinished(Node node)
    {
        this.getOwner().removeChild(node, true);
        if (node.getTag() == 2 && this._targets!=null) {
            this._targets.remove(node);
            GameOverScene gameOverScene = new GameOverScene();
//                gameOverScene._layer._label.setString("You Lose!");
            Director.getInstance().replaceScene(gameOverScene);
        }
        else if (node.getTag() == 3) {
            if (_projectiles != null) {
                this._projectiles.remove(node);
            }
        }
    }

    public List getProjectiles()
    {
        return _projectiles;
    }

    public List getTargets()
    {
        return _targets;
    }

    public void increaseKillCount()
    {

    }
}
