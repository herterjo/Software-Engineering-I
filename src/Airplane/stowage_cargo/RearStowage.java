package Airplane.stowage_cargo;

import java.util.ArrayList;

public class RearStowage extends Stowage {

    private ArrayList<RearStowagePosition> positionList;

    // Konstruktor
    public RearStowage() {
        super(StowageType.rear); // ruft Konstruktor von Stowage auf
        this.positionList = new ArrayList<RearStowagePosition>();
    }

    //hinzugefügt von Gruppe 23 UnitTest
    public ArrayList<RearStowagePosition> getPositionList() {
        return positionList;
    }

    //  Hilfsfunktion zum Füllen des Laderaums
    // fügt die Positionsobjekte der ArrayList hinzu und überprüft dabei, ob die Positionen belegt sind
    // falls der Laderaum bereits voll ist oder eine schon belegte Position nochmal belegt werden soll, wird eine RuntimeException ausgelöst
    public void add_to_positionList(RearStowagePosition position) {
        if(this.getIsComplete()) { throw new RuntimeException("RearStowage ist bereits vollständig."); }
        boolean alreadyInList = false;
        for(RearStowagePosition element: this.positionList) {
            if(position.getId() == element.getId()) { alreadyInList = true; }
        }
        if(alreadyInList) { throw new RuntimeException("Position bereits belegt."); }
        this.positionList.add(position);
        if(this.positionList.size() == 7) { this.setIsComplete(true); }
    }

    //  Hilfsfunktion zum Leeren des Laderaums
    // entfernt das letzte Objekt in der ArrayList
    protected RearStowagePosition remove_from_positionList() {
        if(this.positionList.size() == 0) { return null; }
        return this.positionList.remove(this.positionList.size()-1);
    }

}
