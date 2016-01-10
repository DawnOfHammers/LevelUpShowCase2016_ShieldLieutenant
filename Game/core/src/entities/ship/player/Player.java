package entities.ship.player;

/**
 * Created by Hairuo on 2015-11-05.
 */


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import entities.managers.InputManager;
import entities.projectiles.Bullet;
import entities.projectiles.Laser;
import entities.shield.*;
import entities.ship.Ship;
import gamestates.playState.GameStage;
import gamestates.playState.Play;
import entities.powerups.*;
import java.util.ArrayList;

public class Player extends Ship {
    private final double maxSpeed = 4;
    private double trueSpeed;
    private double accelX;
    private double accelY;
    private double veloX;
    private double veloY;
    private double angle;
    private ParticleEffect effect;
    public double speed;
    private ArrayList<Powerup> powerups = new ArrayList<Powerup>();
    private int activePowerup;

    private ArrayList<Shield> shields = new ArrayList<Shield>();

    public Player(int x,int y, GameStage gs){
        super(x, y, gs);
        init();

        //this.powerups.add(new Afterburner(0, 0));
        //this.powerups.add(new Omni(0,0));
        this.activePowerup = 0;



    }

    private void init(){
        this.accelX = 0;
        this.accelY = 0;
        this.veloY = 0;
        this.veloX = 0;
        this.angle = 0;
        this.trueSpeed = 0;
        this.speed = 0;
        this.health = 100;
        this.shields.add(new StandardShield(new double[]{this.getX(),this.getY()}, 75, "orange"));
        this.shields.add(new StandardShield(new double[]{this.getX(),this.getY()}, 100, "blue"));


        sprite = new Sprite(new Texture(("S2.png"))); //initializing the sprite of the player
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        //setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()); //initilization stuff for the actor


        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("Trail_1.p"), Gdx.files.internal(""));
        effect.setPosition(this.getX() + sprite.getWidth()/2, this.getY());
        effect.start();




    }


    @Override
    protected void moveAngle() {

    }

    @Override
    protected void movePoint() {

    }

    public void inputExecute(){ //executes all input commands

        if (InputManager.get_input_state(InputManager.PLAYER_LEFT)){
            angle += 1;
        }
        if (InputManager.get_input_state(InputManager.PLAYER_RIGHT)){
            angle -= 1;

        }
        if(InputManager.get_input_state(InputManager.PLAYER_SHIELD_1_LEFT)){
            shields.get(0).rotateCounterClockwise();
        }

        if(InputManager.get_input_state(InputManager.PLAYER_SHIELD_1_RIGHT)){
            shields.get(0).rotateClockwise();
        }

        if(InputManager.get_input_state(InputManager.PLAYER_SHIELD_2_LEFT)){
            shields.get(1).rotateCounterClockwise();
        }

        if(InputManager.get_input_state(InputManager.PLAYER_SHIELD_2_RIGHT)){
            shields.get(1).rotateClockwise();
        }

        if (InputManager.get_input_state(InputManager.ACTIVATE_POWERUP_1)) {
            if(powerups.size()>0)
                powerups.get(activePowerup).activate(this);
        }else{
            if(powerups.size()>0)
                powerups.get(activePowerup).deactivate(this);
        }

        if (InputManager.get_input_state(InputManager.PLAYER_FORWARD)) {
            if (speed < 4)
                speed += 0.005;

        }else{
            speed = 0;
            if (trueSpeed < 0.3) {
                veloX = 0;
                veloY = 0;
            }
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (Shield shield : shields){
            shield.draw(batch,parentAlpha);
        }
        effect.draw(batch);
    }

    @Override
    public void act(float delta) { //performs any actions directed towards the actor
        super.act(delta);
        update(delta);
        effect.update(delta);
    }

    public void updateCamera(){ //locks the camera onto the player
        Play.cam.position.x = getX() + getOriginX();
        Play.cam.position.y = getY() + getOriginY();
        Play.cam.update();
    }

    protected void move(){//moves space entities.ship

        accelX = Math.sin(Math.toRadians(angle)) * speed; //acceleration calcs
        accelY = Math.cos(Math.toRadians(angle)) * speed;
        trueSpeed = Math.sqrt(Math.pow(veloX, 2) + Math.pow(veloY, 2)); //finds the actual speed of the player
        //System.out.println(trueSpeed);
        if(trueSpeed < maxSpeed) { //changes the velocity if the entities.ship had not reached maximum speed
            veloX += accelX ;
            veloY += accelY ;
        } else{
            veloX /= trueSpeed/maxSpeed;
            veloY /= trueSpeed/maxSpeed;
        }

        veloX *= 0.97; //deceleration
        veloY *= 0.97;

        this.setX(this.getX() - (float) veloX);
        this.setY(this.getY() + (float) veloY);

        this.setRotation((float) angle);
        updateCamera();

    }
    public void update(float delta){

        ArrayList<Actor> weapons = gamestage.getWeapons();
        inputExecute();
        move();

        double[] t_coords = Laser.transform(this.getX() + this.sprite.getWidth()/2 , this.getY(), 360 - this.angle , this.getX() + this.sprite.getWidth()/2, this.getY() + this.sprite.getHeight()/2);
        effect.setPosition((float)t_coords[0] , (float)t_coords[1]);
        com.badlogic.gdx.utils.Array<ParticleEmitter> emitters = effect.getEmitters();

        for( ParticleEmitter i : emitters){
            ParticleEmitter.ScaledNumericValue angle = i.getAngle();
            angle.setLow((float)this.angle-90);

        }


        for(Shield shield : shields){
            shield.update(this.getX()+sprite.getWidth()/2,this.getY()+sprite.getHeight()/2, delta);
        }
        checkCollisions(weapons);
        sprite.setRotation(this.getRotation());
        sprite.setPosition(this.getX(), this.getY());
    }

    /**
     * This method will check for collisions with the shields or the player and update all entities involved
     * @param weapons: the list of weapons that will be iterated through to check for collision with either the player or the entities.shield
     */
    public void checkCollisions(ArrayList<Actor> weapons){
        for( Actor weapon : weapons){
            for (Shield  shield : shields){
                if(weapon instanceof Bullet) {
                    if(shield.collideProjectile((Bullet)weapon)){
                        ((Bullet) weapon).setBounced(true);
                    }
                }
            }
        }
    }

    public ArrayList<Shield> getShields() {
        return shields;
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
        return Play.key_events.get(Input.Keys.RIGHT);
    }

    public boolean isLeft() {
        return Play.key_events.get(Input.Keys.LEFT);
    }

    public boolean isForward() {
        return Play.key_events.get(Input.Keys.RIGHT);
    }

    public void removeActivePowerup(){
        powerups.remove(activePowerup);
        activePowerup-=1;
        if (activePowerup<0)
            activePowerup=0;
    }

    public void addShield(Shield shield){
        shields.add(shield);
    }

    public void removeShield(int index){
        shields.remove(index);
    }

}
