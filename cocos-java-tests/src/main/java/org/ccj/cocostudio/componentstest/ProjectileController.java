/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.componentstest;

import java.util.ArrayList;
import java.util.List;

import org.ccj.Director;
import org.ccj.d2.Node;
import org.ccj.d2.Sprite;
import org.ccj.d2.action.CallFunc;
import org.ccj.d2.action.MoveTo;
import org.ccj.d2.action.Sequence;
import org.ccj.editor.ccs.ComController;
import org.ccj.math.Vec2;
import org.ccj.math.Rect;
import org.ccj.math.Size;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/21 11:12 $
 *          $Id$
 */
public class ProjectileController
    extends ComController
{
    SceneController comController;

    public void onEnter()
    {
        super.onEnter();
        comController = SceneController.cast(getOwner().getParent().getComponent("SceneController"), SceneController.class);

        Size winSize = Director.getInstance().getWinSize().fetch();
        this.getOwner().setPosition(30, winSize.height / 2);
        this.getOwner().setTag(3);

        SceneController com = SceneController.cast(this.getOwner().getParent().getComponent("SceneController"), SceneController.class);
        com.getProjectiles().add(this.getOwner());
    }

    public void move(float x, float y)
    {
        Size winSize = Director.getInstance().getWinSize().fetch();

        float offX = x - this.getOwner().getPositionX();
        float offY = y - this.getOwner().getPositionY();

        if (offX <= 0) return;

        // Determine where we wish to shoot the projectile to
        float realX = winSize.width + (this.getOwner().getWidth() / 2);
        float ratio = offY / offX;
        float realY = (realX * ratio) + this.getOwner().getPositionY();
        Vec2 realDest = new Vec2(realX, realY);

        // Determine the length of how far we're shooting
        float offRealX = realX - this.getOwner().getPositionX();
        float offRealY = realY - this.getOwner().getPositionY();
        float length = (float) Math.sqrt((offRealX * offRealX) + (offRealY * offRealY));
        float velocity = 480 / 1; // 480pixels/1sec
        float realMoveDuration = length / velocity;


        // Move projectile to actual endpoint
        this.getOwner().runAction(Sequence.create(
            MoveTo.create(realMoveDuration, realDest), new CallFunc()
            {
                public void execute()
                {
                    comController.spriteMoveFinished(getOwner());
                }
            }
        ));
    }

    public void onUpdate(float delta)
    {
        super.onUpdate(delta);

        SceneController com = SceneController.cast(this.getOwner().getParent().getComponent("SceneController"), SceneController.class);
        List targets = com.getTargets();

        Node projectile = this.getOwner();

        Rect projectileRect = new Rect(projectile.getPositionX() - (projectile.getWidth() / 2),
            projectile.getPositionY() - (projectile.getHeight() / 2), projectile.getWidth(), projectile.getWidth());

        List targetsToDelete = new ArrayList();
        Sprite target = null;
        Rect targetSize = null;

        if (targets != null) {
            for (int i = 0; i < targets.size(); i++) {
                target = (Sprite) targets.get(i);
                float px = target.getPositionX();
                float py = target.getPositionY();
                Rect targetRect = new Rect(px - (target.getWidth() / 2), py - (target.getHeight() / 2), target.getWidth(), target.getHeight());
                if (targetRect.intersectsRect(projectileRect)) {
                    targetsToDelete.add(target);
                }
            }
        }

        for (int i = 0; i < targetsToDelete.size(); i++) {
            target = (Sprite) targetsToDelete.get(i);
            EnemyController enemyController = SceneController.cast(target.getComponent("EnemyController"), EnemyController.class);
            if (enemyController != null) {
                enemyController.die();
            }else{
                target.removeFromParent();
            }
        }

        if (targetsToDelete.size() > 0) {
            this.die();
        }
    }

    private void die()
    {
        SceneController com = SceneController.cast(this.getOwner().getParent().getComponent("SceneController"), SceneController.class);
        List projectiles = com.getProjectiles();
        projectiles.remove(this.getOwner());
        this.getOwner().removeFromParent();
    }
}
