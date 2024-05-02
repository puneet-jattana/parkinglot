package com.tw;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotTests {


    @Test
    void shouldParkIfNotParked(){
        Parkable parkable = new Parkable() {};
        ParkingLot parkingLot = new ParkingLot(2L);
        assertDoesNotThrow(()->parkingLot.park(parkable));
    }
    @Test
    void shouldNotParkIfCapacityFull(){
        Parkable parkable = new Parkable() {};
        ParkingLot parkingLot = new ParkingLot(1L);
        try {
            parkingLot.park(parkable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertThrows(CapacityOverFlowException.class, ()->parkingLot.park(new Parkable() {}));
    }

    @Test
    void shouldNotParkIfParked(){
        Parkable parkable = new Parkable() {};
        ParkingLot parkingLot = new ParkingLot(2L);
        try {
            parkingLot.park(parkable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertThrows(ParkableCannotBeParked.class, ()->parkingLot.park(parkable));
    }

    @Test
    void shouldBeTrueIfPark(){
        Parkable parkable = new Parkable() {};
        ParkingLot parkingLot = new ParkingLot(1L);
        parkingLot.canPark(parkable);
        assertTrue(parkingLot.canPark(parkable));
    }

    @Test
    void shouldBeTrueIfParked(){
        Parkable parkable = new Parkable() {};
        ParkingLot parkingLot = new ParkingLot(1L);
        try {
            parkingLot.park(parkable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertTrue(parkingLot.isParkedParkable(parkable));
    }

    @Test
    void shouldBeFalseIfNotParked(){
        Parkable parkable = new Parkable() {};
        ParkingLot parkingLot = new ParkingLot(1L);

        assertFalse(parkingLot.isParkedParkable(parkable));
    }

    @Test
    void shouldUnParkIfParked(){
        Parkable parkable = new Parkable() {};
        ParkingLot parkingLot = new ParkingLot(1L);
        try {
            parkingLot.park(parkable);
        } catch (CapacityOverFlowException | ParkableCannotBeParked e) {
            System.out.println(e.getMessage());
        }
        try {
            assertTrue(parkingLot.unPark(parkable));
        } catch (ParkableCannotBeParked e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void shouldNotUnParkIfNotParked(){
        Parkable parkable = new Parkable() {};
        ParkingLot parkingLot = new ParkingLot(1L);

        assertThrows(ParkableCannotBeParked.class,()->parkingLot.unPark(parkable));

    }

    @Test
    void shouldNotifyWhenParkingLotFull() throws CapacityOverFlowException, ParkableCannotBeParked {
        Parkable parkable = Mockito.mock(Parkable.class);
        ParkingLot parkingLot = new ParkingLot(1L);
        Notifiable notifiable = Mockito.mock(Notifiable.class);
        parkingLot.park(parkable);
        verify(notifiable,times(1)).notifyOwner();
    }



}
