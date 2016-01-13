package entities.Background;

import com.badlogic.gdx.physics.box2d.*;
import gamestates.playState.GameStage;

import java.util.ArrayList;

/**
 * Created by Hairuo on 2016-01-12.
 * This class will contain possible bodies of the objects that make up the background
 *
 * THIS CLASS IS JUST A TEST
 * I DONT KNOW IF WE WILL USE IT OR NOT
 */
public class Background {
    /**
     * Box2d world of the game
     */
    private World world;

    /**
     * the main gamestage
     */
    private GameStage gameStage;

    /**
     * Arraylist of the box2d bodies of the asteroids of the background
     */
    private ArrayList<Body> asteroids;


    /**
     * Constructor of this class
     *
     * Initiliazes the variables and generates the asteroids
     * @param world
     * @param gameStage
     */
    public Background(World world, GameStage gameStage) {
        this.world = world;
        this.gameStage = gameStage;
        this.asteroids = new ArrayList<Body>();
        createAsteroids();
    }


    /**
     * Update method
     *
     * Not yet implemented
     */
    public void update() {

    }

    /**
     * Creates the Box2d Asteroid bodies
     *
     * Runs through a for loop generating box2d circular bodies at random positions
     * Eventually it will create the corresponding actor for it as well
     */
    public void createAsteroids() {

        for (int i = 0; i < 10; i++) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set((float) Math.random() * 1000 - 500, (float) Math.random() * 1000 - 500);
            Body body = world.createBody(bodyDef);

            CircleShape shape = new CircleShape();
            shape.setRadius((float) Math.random() * 10 + 20);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 1f;

            Fixture fixture = body.createFixture(fixtureDef);

            asteroids.add(body);

        }
    }
}