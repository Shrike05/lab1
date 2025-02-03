import java.awt.*;

@SuppressWarnings("unused")
public class Scania extends Car {
    private double deckAngleDeg;

    public Scania(){
        deckAngleDeg = 0;
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
        modelName = "Saab95";
        stopEngine();
    }

    public double getDeckAngle(){
        return deckAngleDeg;
    }

    public void heightenDeck(){
        if(currentSpeed > 0){
            throw new IllegalStateException("The deck cannot be heightened whilst the vehicle is in motion");
        }

        deckAngleDeg = Math.min(deckAngleDeg + 0.1, 70);
    }

    public void lowerDeck(){
        deckAngleDeg = Math.max(deckAngleDeg - 0.1, 0);
    }


    public void startEngine(){
        if(deckAngleDeg > 0){
            throw new IllegalStateException("Cannot start the car whilst the deck is heightened");
        }

        super.startEngine();
    }
}
