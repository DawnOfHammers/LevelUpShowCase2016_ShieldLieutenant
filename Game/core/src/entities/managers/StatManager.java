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
}
