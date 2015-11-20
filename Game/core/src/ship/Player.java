package ship;

/**
 * Created by Hairuo on 2015-11-05.
 */

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import shield.Shield;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Ship {
    private double maxSpeed;
    private double trueSpeed;
    private double accelX;
    private double accelY;
    private double veloX;
    private double veloY;
    private double angle;
    private double speed;
    private boolean right;
    private boolean left;
    private boolean forward;
    private ArrayList<Shield> shields = new ArrayList<Shield>();

    public Player(int x,int y){
        super(x, y);
        this.accelX = 0;
        this.accelY = 0;
        this.veloY = 0;
        this.veloX = 0;
        this.maxSpeed = 4;
        this.angle = 0;
        this.trueSpeed = 0;
        this.speed = 0;
        this.health = 100;



        sprite = new Sprite(new Texture(("S2.png"))); //initializing the sprite of the player
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);

        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(),sprite.getHeight()); //initilization stuff for the actor
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){ //handles input
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.RIGHT){
                    right = true;
                }
                if(keycode == Input.Keys.LEFT){
                    left = true;
                }
                if(keycode == Input.Keys.UP){
                    forward = true;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode == Input.Keys.RIGHT){
                    right = false;
                }
                if(keycode == Input.Keys.LEFT){
                    left = false;
                }
                if(keycode == Input.Keys.UP){
                    forward = false;
                }
                return true;
            }
        });

    }

    public void inputExecute(boolean left,boolean right,boolean forward){ //executes all input commands

        if (left){
            angle += 1;
        }
        if (right){
            angle -= 1;

        }
        if (forward) {
            speed += 0.005;
        }else{
            speed = 0;
        }

    }





    @Override
    public void act(float delta) { //performs any actions directed towards the actor
        super.act(delta);
    }

    @Override
    protected void move(){//moves space ship

        accelX = Math.sin(Math.toRadians(angle)) * speed; //acceleration calcs
        accelY = Math.cos(Math.toRadians(angle)) * speed;
       // System.out.println(speed);
        trueSpeed = Math.sqrt(Math.pow(veloX, 2) + Math.pow(veloY, 2)); //finds the actual speed of the player
        if(trueSpeed < maxSpeed) { //changes the velocity if the ship had not reached maximum speed
            veloX += accelX ;
            veloY += accelY ;
        }
        veloX *= 0.97; //deceleration
        veloY *= 0.97;
        this.setX(this.getX()-(float)veloX);
        this.setY(this.getY()+(float)veloY);
        this.setRotation((float)angle);


    }
    public void update(){
        inputExecute(left,right,forward);
        move();
        sprite.setRotation(this.getRotation());
        sprite.setPosition(this.getX(),this.getY());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getTrueSpeed() {
        return trueSpeed;
    }

    public double getAccelX() {
        return accelX;
    }

    public double getAccelY() {
        return accelY;
    }

/**    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }
**/
    public double getVeloX() {
        return veloX;
    }

    public double getVeloY() {
        return veloY;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int change) {
        this.health-=change;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isForward() {
        return forward;
    }
}