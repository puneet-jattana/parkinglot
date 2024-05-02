package com.tw;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final Long capacity;
    List<Parkable> parkables;
    Notifiable notifiable = new Notifiable() {};

    public ParkingLot(Long capacity) {
        this.capacity = capacity;
        this.parkables = new ArrayList<>();
    }

    public boolean canPark(Parkable parkable) {
        return parkables.size() < capacity && !isParkedParkable(parkable);
    }

    public void park(Parkable parkable) throws ParkableCannotBeParked, CapacityOverFlowException{
        if(isParkedParkable(parkable))
            throw new ParkableCannotBeParked("Parked Vehicle is already parked!");
        if (canPark(parkable)) {
            parkables.add(parkable);
            notifyOwner(parkable);
        }else{
            throw new CapacityOverFlowException("Vehicle cannot be parked due to full capacity.");
        }

    }

    public boolean isParkedParkable(Parkable parkable) {
        return parkables.contains(parkable);
    }

    public boolean unPark(Parkable parkable) throws ParkableCannotBeParked{
        if(parkables.remove(parkable)){
            return true;
        }else{
            throw new ParkableCannotBeParked("Vehicle not parked!");
        }
    }

    public boolean notifyOwner(Parkable parkable) {
        if(parkables.size()==capacity) {
            notifiable.notifyOwner();
            return true;
        }
        return false;
    }

}
