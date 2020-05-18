import greenfoot.*;

/**
 * An ant that collects food.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class Ant extends Creature
{
    private boolean carryingFood = false;
    private GreenfootImage image1, image2;
    private int phAvailable, followTrailTimeRemaining;
    
    private final int MAX_PH_AVAILABLE = 16;
    private final int TIME_FOLLOWING_TRAIL = 30;
    
    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public Ant(AntHill home)
    {
        setHomeHill(home);
        
        phAvailable = MAX_PH_AVAILABLE;
        followTrailTimeRemaining = 0;
        
        image1 = getImage();
        image2 = new GreenfootImage("ant-with-food.gif");
    }
    
    /**
     * Do what an ant's gotta do.
     */
    public void act()
    {
        status();
    }
     
    private void handlePheromoneDrop()
    {
        if (phAvailable == MAX_PH_AVAILABLE)
        {
            new Pheromone();
            Pheromone ph = new Pheromone();
            getWorld().addObject(ph, getX(), getY());
            phAvailable = 0;
        }
        else
        {
            phAvailable++;
        }
    }
    
    private boolean smellsPheromone()
    {
        if (isTouching(Pheromone.class))
        {
            return(true);
        }
        else
        {
            return(false);
        }
    }
    
    private void walkTowardsPheromoneCenter()
    {
        Pheromone pheromone = (Pheromone) getOneIntersectingObject(Pheromone.class);
        if (pheromone != null)
        {
            headTowards(pheromone);
            if (pheromone.getX() == getX() && pheromone.getY() == getY())
            {
                followTrailTimeRemaining = TIME_FOLLOWING_TRAIL;
            }
        }
    }
    
    private void status()
    {
        if(carryingFood == true)
        {
            handlePheromoneDrop();
            walkTowardsHome();
            if (atHome())
            {   
                setImage(image1);
                carryingFood = false;
                getHomeHill().countFood();
            }
        }
        else
        {
            searchForFood();
        }
    }
    
    private void searchForFood()
    {
        if (followTrailTimeRemaining == 0)
        {
            walkTowardsPheromoneCenter();
            randomWalk();
        }
        else
        {
            followTrailTimeRemaining--;
            walkAwayFromHome();
        }
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
            carryingFood = true;
            setImage(image2);
        }
    }
}