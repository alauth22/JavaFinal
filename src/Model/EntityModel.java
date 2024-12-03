package Model;

public class EntityModel {
    private int posX;
    private int posY;
    public EntityModel()
    {
        this.posX = 4;
        this.posY = 4;

    }


    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
}
