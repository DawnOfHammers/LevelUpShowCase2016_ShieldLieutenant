package entities.managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**Manages stats from a given file.
 *
 * Created by Kevin Zheng on 2016-02-15.
 */
public class StatManager {
    private String name;
    private String flavor_text;
    private String[] weapons;
    private String sprite_path;
    private int health;
    private int speed;
    private int range;

    /**Creates a new statmanager based on the given file.
     *
     * @param filepath
     * @throws FileNotFoundException
     */
    public StatManager(String filepath) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("enemies/" + filepath));
        read(file);
        test();    //TODO remove
    }

    private void read(BufferedReader file) throws IOException{
        name = this.parseInput(file.readLine());
        sprite_path = this.parseInput(file.readLine());
        weapons = this.parseInput(file.readLine()).split(", ");

        health = Integer.parseInt(this.parseInput(file.readLine()));
        speed  = Integer.parseInt(this.parseInput(file.readLine()));
        range  = Integer.parseInt(this.parseInput(file.readLine()));

        flavor_text = this.parseInput(file.readLine());
    }

    private String parseInput(String input){
        return input.substring(input.indexOf(":") + 2);
    }

    private void test(){
        System.out.println(name);
        System.out.println(sprite_path);
        System.out.println(weapons);
        System.out.println(health + " " + speed + " " + range);
        System.out.println();
        System.out.println(flavor_text);
    }

    /**Gets the name of the ship.
     *
     * @return  name
     */
    public String getName() {
        return name;
    }

    /**Gets the description of the ship.
     *
     * @return  flavor_text
     */
    public String getFlavorText() {
        return flavor_text;
    }

    /**Gets the weapons of the ship.
     *
     * @return  weapons
     */
    public String[] getWeapons() {
        return weapons;
    }

    /**Gets the sprite of the ship.
     *
     * @return  sprite_path
     */
    public String getSprite() {
        return sprite_path;
    }

    /**Gets the speed stat of the ship.
     *
     * @return  speed
     */
    public int getSpeed() {
        return speed;
    }

    /**Gets the health stat of the ship.
     *
     * @return  health
     */
    public int getHealth() {
        return health;
    }

    /**Gets the range stat of the ship.
     *
     * @return range
     */
    public int getRange() {
        return range;
    }
}
