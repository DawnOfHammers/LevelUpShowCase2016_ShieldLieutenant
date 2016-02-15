package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import entities.projectiles.Laser;
import game.Tools.Shift;
import gamestates.playState.GameStage;

import java.util.ArrayList;

/**
 * Created by Hairuo on 2016-01-25.
 */
public class GameObject extends Actor{
    protected ArrayList<Shift> rgb_shift;
    protected GameStage gamestage;
    protected Sprite sprite;

    /**
     * An Arraylist of the x,y vertices of the gameObject
     */
    protected ArrayList<double[]> vertices;

    public GameObject(int x, int y, GameStage gs, String sprite_name){
        this.setX(x);
        this.setY(y);
        this.gamestage = gs;
        this.sprite = new Sprite(new Texture(sprite_name+".png"));
        rgb_shift = new ArrayList<Shift>();
        rgb_shift.add(new Shift(0, gamestage, sprite_name, this));
        rgb_shift.add(new Shift(1, gamestage, sprite_name, this));
        rgb_shift.add(new Shift(2,gamestage, sprite_name, this));
        this.vertices = new ArrayList<double[]>();
        this.vertices.add(Laser.transform(this.getX() + sprite.getWidth(), this.getY(), this.getTrajectory(), this.getX() + sprite.getWidth() / 2, this.getY() + sprite.getHeight() / 2));
        this.vertices.add(Laser.transform(this.getX()+sprite.getWidth(), this.getY()+ sprite.getHeight(), this.getTrajectory(), this.getX()+ sprite.getWidth()/2, this.getY() + sprite.getHeight()/2));
        this.vertices.add(Laser.transform(this.getX(), this.getY(), this.getTrajectory(), this.getX()+ sprite.getWidth()/2, this.getY() + sprite.getHeight()/2));
        this.vertices.add(Laser.transform(this.getX(), this.getY() + sprite.getHeight(), this.getTrajectory(), this.getX()+ sprite.getWidth()/2, this.getY() + sprite.getHeight()/2));

    }

    /**
     * Call
     * 
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(gamestage.distort){
            for(Shift i: rgb_shift){
                i.draw(batch, parentAlpha);
            }
        }else {
            sprite.draw(batch);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        this.vertices.set(0, Laser.transform(this.getX() + sprite.getWidth(), this.getY(), this.getTrajectory(), this.getX() + sprite.getWidth() / 2, this.getY() + sprite.getHeight() / 2));
        this.vertices.set(1, Laser.transform(this.getX() + sprite.getWidth(), this.getY() + sprite.getHeight(), this.getTrajectory(), this.getX() + sprite.getWidth() / 2, this.getY() + sprite.getHeight() / 2));
        this.vertices.set(2, Laser.transform(this.getX(), this.getY(), this.getTrajectory(), this.getX() + sprite.getWidth() / 2, this.getY() + sprite.getHeight() / 2));
        this.vertices.set(3, Laser.transform(this.getX(), this.getY() + sprite.getHeight(), this.getTrajectory(), this.getX() + sprite.getWidth() / 2, this.getY() + sprite.getHeight() / 2));


        if(gamestage.distort) {
            for (Shift i : rgb_shift){
                i.act(delta);
            }
        }
    }

    public float getTrajectory(){
        return(this.getRotation());
    }
}
