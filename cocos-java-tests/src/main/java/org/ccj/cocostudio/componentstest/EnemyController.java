/**
 *  Copyright(c) Shanghai YiJun Network Technologies Inc. All right reserved.
 */
package org.ccj.cocostudio.componentstest;

import java.util.List;

import org.ccj.Director;
import org.ccj.d2.action.CallFunc;
import org.ccj.d2.action.MoveTo;
import org.ccj.d2.action.Sequence;
import org.ccj.editor.ccs.ComController;
import org.ccj.math.Vec2;
import org.ccj.math.Size;

/**
 * @author <a href="mailto:yuanyou@makeapp.co">yuanyou</a>
 * @version $Date:2014/4/21 10:52 $
 *          $Id$
 */
public class EnemyController
    extends ComController
{
    SceneController comController;

    public void onEnter()
    {
        super.onEnter();

        comController = SceneController.cast(getOwner().getParent().getComponent("SceneController"), SceneController.class);

        // Determine where to spawn the target along the Y axis
        Size winSize = Director.getInstance().getWinSize().fetch();
        float minY = this.getOwner().getHeight() / 2;
        float rangeY = winSize.height - this.getOwner().getHeight();
        float actualY = (float) (Math.random() * rangeY) + minY;

        // Create the target slightly off-screen along the right edge,
        // and along a random position along the Y axis
        this.getOwner().setPosition(winSize.width + (this.getOwner().getWidth() / 2), actualY);

        // Determine speed of the target
        float minDuration = 2.0f;
        float maxDuration = 4.0f;
        float rangeDuration = maxDuration - minDuration;
        float actualDuration = (float) (Math.random() % rangeDuration) + minDuration;

        // Create the actions
        MoveTo actionMove = MoveTo.create(actualDuration, new Vec2(0 - this.getOwner().getWidth() / 2, actualY));
        CallFunc actionMoveDone = new CallFunc()
        {
            public void execute()
            {
                comController.spriteMoveFinished(getOwner());
            }
        };
        this.getOwner().runAction(Sequence.create(actionMove, actionMoveDone));
    }


    public void die()
    {
        List targets = comController.getTargets();
        targets.remove(this.getOwner());
        this.getOwner().removeFromParent();
        comController.increaseKillCount();
    }
}
