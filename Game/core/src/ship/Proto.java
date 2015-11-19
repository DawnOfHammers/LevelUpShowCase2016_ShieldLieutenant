package ship;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.geom.Point2D;

/**Prototype of a basic enemy ship type.
 * Created by Feng on 11/14/2015.
 */
public class Proto extends Enemy {
    /**Creates a new Proto at points x,y.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    public Proto(int x, int y){
        super(x,y);
        super.health = 5;
        super.actions = new boolean[2];
        super.sprite = new Sprite(new Texture(("Proto.png")));
    }

    /**Moves to a target location in a straight line.
     *
     */
    @Override
    protected void move() {
        int speed = 2;
        double dx = (goal_coords.getX() - coordX), dy = (goal_coords.getY() - coordY);
        double hyp = Math.hypot(dx, dy);

        {// Ship Movement
            coordX += speed * dx / hyp;
            coordY += speed * dy / hyp;
        }
    }

    /**Action 0: When hp is above 2.
     *
     * Action 1: When hp is under 2.
     *
     */
    @Override
    protected void behavior(){
        actions[0] = health > 2;
        actions[1] = health <=2;
    }

    /**Action 0: Move to a random position.
     *
     * Action 1: Stop moving.
     */
    @Override
    protected void action(){
        if(actions[0]) {
            //TODO change implementation of coordsX and Y in ship.
            Point2D.Double curr_coords = new Point2D.Double(coordX, coordY);
            if (curr_coords.equals(goal_coords)) {
                goal_coords = new Point2D.Double((int)(Math.random()*1000), (int)(Math.random()*800));
            }
        }
        if(actions[1]){
            goal_coords = new Point2D.Double(coordX, coordY);
        }
    }
}
