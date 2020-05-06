import greenfoot.*;

/**
 * An ant that collects food.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class Ant extends Creature
{
    private boolean carryingFood;
    private GreenfootImage image1 = getImage();
    private GreenfootImage image2 = new GreenfootImage("ant-with-food.gif");
    
    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public Ant(AntHill home)
    {
        setHomeHill(home);
    }
    
    /**
     * Do what an ant's gotta do.
     */
    public void act()
    {
        status();
        checkForFood();
        searchForFood();
    }
    
    private void status()
    {
        if(carryingFood = true && atHome())
        {
            setImage(image1);
            carryingFood = false;
            getHomeHill().countFood();
        }
        else
        {
            searchForFood();
        }
    }
    
    private void searchForFood()
    {
        randomWalk();
        checkForFood();
    }
    
    private boolean atHome()
    {
        if (getHomeHill() != null) 
        {
            return (Math.abs(getX() - getHomeHill().getX()) < 4) && 
                   (Math.abs(getY() - getHomeHill().getY()) < 4);
        }
        else 
        {
            return false;
        }
    }
    
    private void checkForFood()
    {
        Food food = (Food) getOneIntersectingObject(Food.class);
        if (food != null) 
        {
            food.removeCrumb();
        }
    
    }
}