package Airport.Baggage_Sorting_Unit.Vehicles;

import Airport.Airport.Airport;
import Airport.Airport.Gate;
import Airport.Airport.GateID;
import Airport.Baggage_Sorting_Unit.BaggageSortingUnit;
import Airport.Base.Container;

import java.util.UUID;

public class BaggageVehicle implements IBaggageVehicle {

    private static int idCounter;
    private final String uuid;
    private final String id;
    private final String type;
    private int speedInMPH;
    private boolean isFlashingLightOn;
    private Container container;
    private IContainerLifter containerLifter;
    private Gate gate;
    private BaggageSortingUnit unit;

    public BaggageVehicle(final String type,
                          BaggageSortingUnit unit) {
        this.uuid = UUID.randomUUID().toString();
        this.id = "" + idCounter++;
        this.type = type;
        speedInMPH = 0;
        isFlashingLightOn = false;
        this.unit = unit;
    }

    @Override
    public String toString() {
        String message = "UUID: " + uuid + "\nID: " + id + "\nType: " + type
                + "\nCurrent speed in MpH: " + speedInMPH + "\nCurrent status of lights: ";
        message += ((isFlashingLightOn) ? "on" : "off");
        message += "\nCurrent Gate: " + gate + "\nAssigned container lifter" + containerLifter;

        return message;
    }

    public String getUuid() {
        return uuid;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getSpeedInMPH() {
        return speedInMPH;
    }

    public boolean isFlashingLightOn() {
        return isFlashingLightOn;
    }

    public Gate getGate() {
        return gate;
    }

    /**
     * finds gate for the given id and stores it
     */
    @Override
    public void setGate(final GateID id) {
        gate = Airport.getInstance().getGatefromID(id);
    }

    public Container getContainer() {
        return container;
    }

    @Override
    public IContainerLifter getContainerLifter() {
        return containerLifter;
    }

    public void setContainerLifter(IContainerLifter containerLifter) {
        this.containerLifter = containerLifter;
    }

    /**
     * setting internal container
     */
    @Override
    public void store(final Container container) {
        this.container = container;
    }

    /**
     * sets internal containerLifter
     */
    @Override
    public void connect(final IContainerLifter containerLifter) {
        this.containerLifter = containerLifter;
    }

    /**
     * sets container on lifter an removes it locally
     */
    @Override
    public void transferContainerToLifter() {
        containerLifter.setContainer(container);
        container = null;
    }

    /**
     * removes local dependency for containerLifter
     */
    @Override
    public void disconnect() {
        containerLifter = null;
    }

    /**
     * moves and sets baggageVehicle to this the known instance for baggageSortingUnit
     */
    @Override
    public void returnToBaggageSortingUnit() {
        setFlashingLightOn();
        move(20);
        stop();
        unit.setBaggageVehicle(this);
        setFlashingLightOff();
    }

    /**
     * moves to gate
     *
     * @param gateID id for the gate to move to
     */
    @Override
    public void executeRequest(final GateID gateID) {
        setFlashingLightOn();
        move(20);
        stop();
        setGate(gateID);
    }

    @Override
    public void setFlashingLightOn() {
        isFlashingLightOn = true;
    }

    /**
     * sets speed
     */
    @Override
    public void move(final int speedInMPH) {
        this.speedInMPH = speedInMPH;
    }

    /**
     * sets speed to 0
     */
    @Override
    public void stop() {
        speedInMPH = 0;
    }

    @Override
    public void setFlashingLightOff() {
        isFlashingLightOn = false;
    }
}