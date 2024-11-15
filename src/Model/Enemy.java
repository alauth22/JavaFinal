package Model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Enemy {
    // Used for all enemy actions, previousRooms is going to mark down all the rooms passed
    // when the enemy moves to the player's location in ActiveEnemyMovement and NoiseMakerEnemyMovement
    private String enemyLocation = "5";
    private boolean enemyAlerted = false;
    private Set<String> previousRooms = new HashSet<String>();
    private final Random randRoom = new Random();

    // All the accessors and mutators
    public String getEnemyLocation()
    {
        return this.enemyLocation;
    }
    public void setEnemyLocation(String enemyLocation)
    {
        this.enemyLocation = enemyLocation;
    }

    public boolean getEnemyAlerted()
    {
        return this.enemyAlerted;
    }
    public void setEnemyAlerted(boolean enemyAlerted)
    {
        this.enemyAlerted = enemyAlerted;
    }

    public Set<String> getPreviousRooms()
    {
        return this.previousRooms;
    }
    public void AddPreviousRooms(String previousRoom)
    {
        this.previousRooms.add(previousRoom);
    }
    // After the enemy has checked every room they passed, the rooms are removed for the next alert/noisemaker
    public void ResetPreviousRooms()
    {
        this.previousRooms.clear();
    }

    // We don't need the mutator as we don't need to set the Random to anything
    public Random getRandRoom()
    {
        return this.randRoom;
    }

    public void checkForPlayer(boolean playerHidden, String playerLocation, boolean lightOn)
    {
        if (!playerHidden && getEnemyLocation().equals(playerLocation))
        {
            //EnterRoomGameEnd();
        }
        else if (getEnemyLocation().equals(playerLocation) && lightOn)
        {
            //HiddenGameEnd();
        }
        else if (getEnemyLocation().equals(playerLocation))
        {
            //Run message to state enemy enters room and leaves
            this.PassiveEnemyMovement();
        }
        else
        {
            //Print a message to tell the player the enemy has moved
        }
    }

    public void checkForPlayer_Alerted(boolean playerHidden, String playerLocation, boolean lightOn)
    {
        if (!playerHidden && getPreviousRooms().contains(playerLocation))
        {
            //EnemyRunsIntoGameEnd();
        }
        else if (getPreviousRooms().contains(playerLocation) && lightOn)
        {
            //EnemyRunsIntoHiddenGameEnd();
        }

        if (getEnemyLocation().equals(playerLocation))
        {
            //Run message to state enemy enters room and leaves
            this.PassiveEnemyMovement();
            setEnemyAlerted(false);
            ResetPreviousRooms();
        }
        else
        {
            //Print a message to tell the player the enemy has moved
        }
    }

    // Checks what room the enemy is currently in, and moves the enemy based on that information
    public void PassiveEnemyMovement()
    {
        if(getEnemyLocation().charAt(0) == 1)
        {
            setEnemyLocation("2a");
        }
        else if (getEnemyLocation().charAt(0) == 2)
        {
            if (getEnemyLocation().charAt(1) == 'a')
            {
                int tempRand = getRandRoom().nextInt(2);
                if (tempRand == 0)
                {
                    setEnemyLocation("1");
                }
                else
                {
                    setEnemyLocation("3a");
                }
            }
            else if (getEnemyLocation().charAt(1) == 'e')
            {
                setEnemyLocation("3b");
            }
            else
            {
                setEnemyLocation("3a");
            }
        }
        else if (getEnemyLocation().charAt(0) == 3)
        {
            if (getEnemyLocation().charAt(1) == 'a')
            {
                int tempRand = getRandRoom().nextInt(4);
                if (tempRand == 0)
                {
                    setEnemyLocation("2a");
                }
                else if (tempRand == 1)
                {
                    setEnemyLocation("2b");
                }
                else if (tempRand == 2)
                {
                    setEnemyLocation("2c");
                }
                else
                {
                    setEnemyLocation("2d");
                }
            }
            else if (getEnemyLocation().charAt(1) == 'b')
            {
                int tempRand = getRandRoom().nextInt(2);
                if (tempRand == 0)
                {
                    setEnemyLocation("2e");
                }
                else
                {
                    setEnemyLocation("4");
                }
            }
            else
            {
                setEnemyLocation("4");
            }
        }
        else if (getEnemyLocation().charAt(0) == 4)
        {
            int tempRand = getRandRoom().nextInt(6);
            if (tempRand == 0)
            {
                setEnemyLocation("3a");
            }
            else if (tempRand == 1)
            {
                setEnemyLocation("3b");
            }
            else if (tempRand == 2)
            {
                setEnemyLocation("3c");
            }
            else if (tempRand == 3)
            {
                setEnemyLocation("3d");
            }
            else if (tempRand == 4)
            {
                setEnemyLocation("3e");
            }
            else
            {
                setEnemyLocation("5");
            }
        }
        // The enemy has to be in the 5th layer of rooms
        else
        {
            setEnemyLocation("4");
        }
    }

    public void ActiveEnemyMovement(String playerLocation)
    {
        if (playerLocation.charAt(0) == 1)
        {
            for (int i = 0; i < 2; i++)
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    i = 2;
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) == 'a')
                    {
                        AddPreviousRooms("1");
                        setEnemyLocation("1");
                    }
                    else if (getEnemyLocation().charAt(1) == 'e')
                    {
                        AddPreviousRooms("3b");
                        setEnemyLocation("3b");
                    }
                    else
                    {
                        AddPreviousRooms("3a");
                        setEnemyLocation("3a");
                    }
                }
                else if (getEnemyLocation().charAt(0) == 3)
                {
                    if (getEnemyLocation().charAt(1) == 'a')
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3a");
                    setEnemyLocation("3a");
                }
                else
                {
                    AddPreviousRooms("4");
                    setEnemyLocation("4");
                }
            }
        }
        else if (playerLocation.charAt(0) == 2)
        {
            if (playerLocation.charAt(1) == 'a')
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'a')
                        {
                            i = 2;
                        }
                        else if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'a')
                        {
                            AddPreviousRooms("2a");
                            setEnemyLocation("2a");
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3a");
                        setEnemyLocation("3a");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
            else if (playerLocation.charAt(1) == 'b')
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'b')
                        {
                            i = 2;
                        }
                        else if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'a')
                        {
                            AddPreviousRooms("2b");
                            setEnemyLocation("2b");
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3a");
                        setEnemyLocation("3a");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
            else if (playerLocation.charAt(1) == 'c')
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'c')
                        {
                            i = 2;
                        }
                        else if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'a')
                        {
                            AddPreviousRooms("2c");
                            setEnemyLocation("2c");
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3a");
                        setEnemyLocation("3a");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
            else if (playerLocation.charAt(1) == 'd')
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'd')
                        {
                            i = 2;
                        }
                        else if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'a')
                        {
                            AddPreviousRooms("2d");
                            setEnemyLocation("2d");
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3a");
                        setEnemyLocation("3a");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
            else
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'e')
                        {
                            i = 2;
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'b')
                        {
                            AddPreviousRooms("2e");
                            setEnemyLocation("2e");
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3b");
                        setEnemyLocation("3b");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
        }
        else if (playerLocation.charAt(0) == 3)
        {
            if (playerLocation.charAt(1) == 'a')
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'a')
                        {
                            i = 2;
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3a");
                        setEnemyLocation("3a");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
            else if (playerLocation.charAt(1) == 'b')
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'b')
                        {
                            i = 2;
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3b");
                        setEnemyLocation("3b");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
            else if (playerLocation.charAt(1) == 'c')
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'c')
                        {
                            i = 2;
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3c");
                        setEnemyLocation("3c");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
            else if (playerLocation.charAt(1) == 'd')
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'd')
                        {
                            i = 2;
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3d");
                        setEnemyLocation("3d");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
            else
            {
                for (int i = 0; i < 2; i++)
                {
                    if (getEnemyLocation().charAt(0) == 1)
                    {
                        AddPreviousRooms("2a");
                        setEnemyLocation("2a");
                    }
                    else if (getEnemyLocation().charAt(0) == 2)
                    {
                        if (getEnemyLocation().charAt(1) == 'e')
                        {
                            AddPreviousRooms("3b");
                            setEnemyLocation("3b");
                        }
                        else
                        {
                            AddPreviousRooms("3a");
                            setEnemyLocation("3a");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 3)
                    {
                        if (getEnemyLocation().charAt(1) == 'e')
                        {
                            i = 2;
                        }
                        else
                        {
                            AddPreviousRooms("4");
                            setEnemyLocation("4");
                        }
                    }
                    else if (getEnemyLocation().charAt(0) == 4)
                    {
                        AddPreviousRooms("3e");
                        setEnemyLocation("3e");
                    }
                    else
                    {
                        AddPreviousRooms("4");
                        setEnemyLocation("4");
                    }
                }
            }
        }
        else if (playerLocation.charAt(0) == 4)
        {
            for (int i = 0; i < 2; i++)
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    setEnemyLocation("2a");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) == 'e')
                    {
                        AddPreviousRooms("3b");
                        setEnemyLocation("3b");
                    }
                    else
                    {
                        AddPreviousRooms("3a");
                        setEnemyLocation("3a");
                    }
                }
                else if (getEnemyLocation().charAt(0) == 3)
                {
                    AddPreviousRooms("4");
                    setEnemyLocation("4");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    i = 2;
                }
                else
                {
                    AddPreviousRooms("4");
                    setEnemyLocation("4");
                }
            }
        }
        else
        {
            for (int i = 0; i < 2; i++)
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    setEnemyLocation("2a");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) == 'e')
                    {
                        AddPreviousRooms("3b");
                        setEnemyLocation("3b");
                    }
                    else
                    {
                        AddPreviousRooms("3a");
                        setEnemyLocation("3a");
                    }
                }
                else if (getEnemyLocation().charAt(0) == 3)
                {
                    AddPreviousRooms("4");
                    setEnemyLocation("4");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("5");
                    setEnemyLocation("5");
                }
                else
                {
                    i = 2;
                }
            }
        }
    }

    public void NoiseMakerEnemyMovement(String playerLocation)
    {
        if (playerLocation.charAt(0) == 1)
        {
            if (getEnemyLocation().charAt(0) == 2)
            {
                if (getEnemyLocation().charAt(1) == 'e')
                {
                    AddPreviousRooms("3b");
                    AddPreviousRooms("4");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2a");
                    AddPreviousRooms("1");
                }
                else if (getEnemyLocation().charAt(1) == 'a')
                {
                    AddPreviousRooms("1");
                }
                else
                {
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2a");
                    AddPreviousRooms("1");
                }
            }
            else if (getEnemyLocation().charAt(0) == 3)
            {
                if (getEnemyLocation().charAt(1) != 'a') {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3a");
                }
                AddPreviousRooms("2a");
                AddPreviousRooms("1");
            }
            else if (getEnemyLocation().charAt(0) == 4)
            {
                AddPreviousRooms("3a");
                AddPreviousRooms("2a");
                AddPreviousRooms("1");
            }
            // The enemy has to be in the 5th layer of rooms
            else
            {
                AddPreviousRooms("4");
                AddPreviousRooms("3a");
                AddPreviousRooms("2a");
                AddPreviousRooms("1");
            }
        }
        else if (playerLocation.charAt(0) == 2)
        {
            // Double check
            if (playerLocation.charAt(1) == 'a')
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) == 'e')
                    {
                        AddPreviousRooms("3b");
                        AddPreviousRooms("4");
                    }
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2a");
                }
                else if (getEnemyLocation().charAt(0) == 3)
                {
                    if (getEnemyLocation().charAt(1) != 'a')
                    {
                        AddPreviousRooms("4");
                        AddPreviousRooms("3a");
                    }
                    AddPreviousRooms("2a");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2a");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2a");
                }
            }
            else if (playerLocation.charAt(1) == 'b')
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2b");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) == 'e')
                    {
                        AddPreviousRooms("3b");
                        AddPreviousRooms("4");
                    }
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2b");
                }
                else if (getEnemyLocation().charAt(0) == 3)
                {
                    if (getEnemyLocation().charAt(1) != 'a')
                    {
                        AddPreviousRooms("4");
                        AddPreviousRooms("3a");
                    }
                    AddPreviousRooms("2b");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2b");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2b");
                }
            }
            else if (playerLocation.charAt(1) == 'c')
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2c");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) == 'e')
                    {
                        AddPreviousRooms("3b");
                        AddPreviousRooms("4");
                    }
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2c");
                }
                else if (getEnemyLocation().charAt(0) == 3)
                {
                    if (getEnemyLocation().charAt(1) != 'a')
                    {
                        AddPreviousRooms("4");
                        AddPreviousRooms("3a");
                    }
                    AddPreviousRooms("2c");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2c");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2c");
                }
            }
            else if (playerLocation.charAt(1) == 'd')
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2d");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) == 'e')
                    {
                        AddPreviousRooms("3b");
                        AddPreviousRooms("4");
                    }
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2d");
                }
                else if (getEnemyLocation().charAt(0) == 3)
                {
                    if (getEnemyLocation().charAt(1) != 'a')
                    {
                        AddPreviousRooms("4");
                        AddPreviousRooms("3a");
                    }
                    AddPreviousRooms("2d");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2d");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("2d");
                }
            }
            else
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("4");
                    AddPreviousRooms("3b");
                    AddPreviousRooms("2e");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    AddPreviousRooms("3a");
                    AddPreviousRooms("4");
                    AddPreviousRooms("3b");
                    AddPreviousRooms("2e");
                }
                else if (getEnemyLocation().charAt(0) == 3)
                {
                    if (getEnemyLocation().charAt(1) != 'b')
                    {
                        AddPreviousRooms("4");
                        AddPreviousRooms("3b");
                    }
                    AddPreviousRooms("2e");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3b");
                    AddPreviousRooms("2e");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3b");
                    AddPreviousRooms("2e");
                }
            }
        }
        else if (playerLocation.charAt(0) == 3)
        {
            if (playerLocation.charAt(1) == 'a')
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) == 'e')
                    {
                        AddPreviousRooms("3b");
                        AddPreviousRooms("4");
                    }
                    AddPreviousRooms("3a");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3a");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3a");
                }
            }
            else if (playerLocation.charAt(1) == 'b')
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("4");
                    AddPreviousRooms("3b");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) != 'e')
                    {
                        AddPreviousRooms("3a");
                        AddPreviousRooms("4");
                    }
                    AddPreviousRooms("3b");
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3b");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3b");
                }
            }
            else if (playerLocation.charAt(1) == 'c')
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("4");
                    AddPreviousRooms("3c");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) != 'e')
                    {
                        AddPreviousRooms("3a");
                        AddPreviousRooms("4");
                        AddPreviousRooms("3c");
                    }
                    else
                    {
                        AddPreviousRooms("3b");
                        AddPreviousRooms("4");
                        AddPreviousRooms("3c");
                    }
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3c");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3c");
                }
            }
            else if (playerLocation.charAt(1) == 'd')
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("4");
                    AddPreviousRooms("3d");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) != 'e')
                    {
                        AddPreviousRooms("3a");
                        AddPreviousRooms("4");
                        AddPreviousRooms("3d");
                    }
                    else
                    {
                        AddPreviousRooms("3b");
                        AddPreviousRooms("4");
                        AddPreviousRooms("3d");
                    }
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3d");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3d");
                }
            }
            else
            {
                if (getEnemyLocation().charAt(0) == 1)
                {
                    AddPreviousRooms("2a");
                    AddPreviousRooms("3a");
                    AddPreviousRooms("4");
                    AddPreviousRooms("3e");
                }
                else if (getEnemyLocation().charAt(0) == 2)
                {
                    if (getEnemyLocation().charAt(1) != 'e')
                    {
                        AddPreviousRooms("3a");
                        AddPreviousRooms("4");
                        AddPreviousRooms("3e");
                    }
                    else
                    {
                        AddPreviousRooms("3b");
                        AddPreviousRooms("4");
                        AddPreviousRooms("3e");
                    }
                }
                else if (getEnemyLocation().charAt(0) == 4)
                {
                    AddPreviousRooms("3e");
                }
                else
                {
                    AddPreviousRooms("4");
                    AddPreviousRooms("3e");
                }
            }
        }
        else if (playerLocation.charAt(0) == 4)
        {
            if (getEnemyLocation().charAt(0) == 1)
            {
                AddPreviousRooms("2a");
                AddPreviousRooms("3a");
                AddPreviousRooms("4");
            }
            else if (getEnemyLocation().charAt(0) == 2)
            {
                if (getEnemyLocation().charAt(1) == 'e')
                {
                    AddPreviousRooms("3b");
                    AddPreviousRooms("4");
                }
                else
                {
                    AddPreviousRooms("3a");
                    AddPreviousRooms("4");
                }
            }
            else
            {
                AddPreviousRooms("4");
            }
        }
        else
        {
            if (getEnemyLocation().charAt(0) == 1)
            {
                AddPreviousRooms("2a");
                AddPreviousRooms("3a");
                AddPreviousRooms("4");
                AddPreviousRooms("5");
            }
            else if (getEnemyLocation().charAt(0) == 2)
            {
                if (getEnemyLocation().charAt(1) == 'e')
                {
                    AddPreviousRooms("3b");
                    AddPreviousRooms("4");
                    AddPreviousRooms("5");
                }
                else
                {
                    AddPreviousRooms("3a");
                    AddPreviousRooms("4");
                    AddPreviousRooms("5");
                }
            }
            else if (getEnemyLocation().charAt(0) == 3)
            {
                AddPreviousRooms("4");
                AddPreviousRooms("5");
            }
            else
            {
                AddPreviousRooms("5");
            }
        }

        setEnemyLocation(playerLocation);
    }
}
