package gamestates.almanacState;

import gamestates.almanacState.components.Component;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This is the primary AlmanacPage. Each page in the Almanac will be contained in a system similar to this one.
 * Created by Hongyu Wang on 2/15/2016.
 */
public class AlmanacPage {


    //This is the ArrayList storing all available components.
    private Hashtable<String, Component> components;

    public AlmanacPage(){
        init();
    }

    //This is the protected intializer method, intializing
    //All non constructor required values.
    protected void init(){
        components = new Hashtable<String, Component>();
    }

    /**
     * This method adds a component to the given Almanac Page with a specific
     * String name mapped to it.
     *
     * @param name The name that references the given component.
     * @param comp The specific component mapped to the name.
     * @throws ComponentAlreadyPresentException
     */
    public void addComponent(String name, Component comp) throws ComponentAlreadyPresentException{
        if (components.get(name) != null){
            throw new ComponentAlreadyPresentException();
        }
        components.put(name, comp);
    }

    /**
     * This method will remove a component from the AlmanacPage with
     * a String <name></name>.
     * This method will return true if the component with <name></name> is
     * removed, and otherwise, it will return false.
     *
     * @param name String of the component representation.
     * @return A boolean which represents the success of the method
     */
    public boolean removeComponent(String name){
        return components.remove(name) != null;
    }

    /**
     * This the update method. This is called each tick of the menu loop running.
     *
     * @param dt The rate at which the update is called based on the official game loop time.
     */
    public void update(float dt) {
        for (String name : components.keySet()){
            components.get(name).checkPressed();
        }
    }

    /**
     * This is the draw method of the current AlmanacState.
     * This will call the draw method of all components within the state.
     */
    public void draw() {

    }

    /**
     * This class is an exception that is called if a component
     * is already present with the add method is called.
     */
    private class ComponentAlreadyPresentException extends Exception{
        public ComponentAlreadyPresentException(){
            super("A component with the given name has already been created.");
        }
    }
}

