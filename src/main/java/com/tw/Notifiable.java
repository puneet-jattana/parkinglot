package com.tw;

public interface Notifiable {
    default void notifyOwner(){
        System.out.println("Parking lot is at full capacity");
    };
}
