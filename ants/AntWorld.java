import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.util.List;

/**
 * The world where ants live.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class AntWorld extends World
{
    public static final int SIZE = 640;

    public void act()
    {
        if (getObjects(Food.class).size() == 0)
        {
            Greenfoot.stop();
        }
    }
    
    /**
     * Create a new world. It will be initialised with a few ant hills
     * and food sources
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, AntHill.class);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        AntHill antHill = new AntHill();
        addObject(antHill,328,262);
        antHill.setLocation(200,274);
        AntHill antHill2 = new AntHill();
        addObject(antHill2,468,447);
        Food food = new Food();
        addObject(food,472,165);
        Food food2 = new Food();
        addObject(food2,64,123);
        Food food3 = new Food();
        addObject(food3,370,544);
        food3.setLocation(242,537);
        food3.setLocation(114,531);
        Food food4 = new Food();
        addObject(food4,584,604);
    }
}
