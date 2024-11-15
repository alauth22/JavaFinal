package Model;

public class Door {
    private final int doorID;
    private final String doorDescription;
    private boolean lockStatus;
    private boolean openStatus;
    private boolean enemyNoticed;
    private final String keyID;

    public Door(int doorID, String doorDescription,
                boolean lockStatus, boolean openStatus,
                boolean enemyNoticed, String keyID) {
        this.doorID = doorID;
        this.doorDescription = doorDescription;
        this.lockStatus = lockStatus;
        this.openStatus = openStatus;
        this.enemyNoticed = enemyNoticed;
        this.keyID = keyID;
    }

    //Gets set in constructor and is final
    public int getDoorID() {
        return this.doorID;
    }

    //Gets set in constructor and is final
    public String getDoorDescription() {
        return this.doorDescription;
    }

    public boolean getLockStatus() {
        return this.lockStatus;
    }

    public void setLockStatus(boolean lockStatus) {
        this.lockStatus = lockStatus;
    }

    public boolean getOpenStatus() {
        return this.openStatus;
    }

    public void setOpenStatus(boolean openStatus) {
        this.openStatus = openStatus;
    }

    public boolean getEnemyNoticed() {
        return this.enemyNoticed;
    }

    public void setEnemyNoticed(boolean enemyNoticed) {
        this.enemyNoticed = enemyNoticed;
    }

    public String getKeyID() {
        return this.keyID;
    }
}