package start;

import centuryFactory.BoatFactoryXXCentury;
import centuryFactory.boats.Boat;
import engine.Game;

public class Main {

    static public void main(String [] args){
        Game game = new Game();
        game.setCentury(new BoatFactoryXXCentury());
        game.createBoats();

        System.out.println("Human player boats");

        for (Boat boat : game.getHumanPlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() + "  orientation: "+boat.getOrientation() + " size"+boat.getSize());
        }


        System.out.println("Machine player boats");

        for (Boat boat : game.getMachinePlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() + "  orientation: "+boat.getOrientation() + " size"+boat.getSize());
        }

    }
}