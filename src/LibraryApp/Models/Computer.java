package LibraryApp.Models;

public class Computer {
    private int _id;
    private boolean isInUse;
    public static final int TotalNumber = 20;
    public static int numberBeingUsed = 0;
    public String location;

    public Computer(int _id) {
        this._id = (int) (Math.random()*1000*(Math.random()*6000));
        this.isInUse = false;
        this.location = null;
    }

    public int get_id() {
        return _id;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public static int getTotalNumber() {
        return TotalNumber;
    }

    public static int getNumberBeingUsed() {
        return numberBeingUsed;
    }

    public static void setNumberBeingUsed(int numberBeingUsed) {
        Computer.numberBeingUsed = numberBeingUsed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean useComputer() {
        if(this.isInUse == false && Computer.numberBeingUsed < Computer.getTotalNumber()) {
            Computer.numberBeingUsed += 1;
            this.isInUse = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean stopUsingComputer() {
        if(this.isInUse == true) {
            Computer.numberBeingUsed -= 1;
            this.isInUse = false;
            return true;
        } else {
            return false;
        }
    }

}
