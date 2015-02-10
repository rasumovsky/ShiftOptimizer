///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  Scheduler.java 
// File:             TimeSlot.java
//
// Authors:          Andrew Hard, Ezra Schricker 
// Email:            hardandrew1@gmail.com
// Created:          October 20, 2014
// Modified: 	     November 9, 2014
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;

/**
 * The TimeSlot class represents a period of time.
 * It has a start and end time, as well as a busy
 * flag and preference score (1-10).
 *
 * @author Andrew Hard
 * @author Ezra Schricker
 */
public class TimeSlot {
    
    // The private objects:
    private int startTime;
    private int endTime;
    private boolean busyTime;
    private int prefScore;
    
    /**
     * Constructs a TimeSlot with the provided fields.
     * @param inTime1: the start time of the slot.
     * @param inTime2: the end time of the slot.
     * @param inBusy: the busy argument.
     * @param inPref: the preference score.
     * @throws IllegalArgumentException if time arguments are negative.
     */
    public TimeSlot(int inTime1, int inTime2, boolean inBusy, int inPref) {
	if (inTime1 < 0 || inTime2 < 0) {
	    throw new IllegalArgumentException();
	}
	
	startTime = inTime1;
	endTime = inTime2;
	busyTime = inBusy;
	prefScore = inPref;
    }
    
    
    /**
     * Constructs an empty TimeSlot.
     */
    public TimeSlot() {
	this(0, 0, false, 0);
    }
    
    
    private int minVal(int a, int b) {
	if (a > b) return b;
	else return a;
    }
    
    
    private int maxVal(int a, int b) {
	if (a > b) return a;
	else return b;
    }

    
    /**
     * Returns the start time for the time slot.
     * @returns startTime.
     */
    public int getStart() {
	return startTime;
    }

    
    /**
     * Returns the end time for the time slot.
     * @returns endTime.
     */
    public int getEnd() {
	return endTime;
    }
    
    
    /**
     * Returns the preference score for the time slot.
     * @returns prefScore.
     */
    public int getPreference() {
	return prefScore;
    }
    
    
    /**
     * Sets a new start time for the time slot.
     * @param newStart: the new start time.
     * @throws IllegalArgumentException if start time negative.
     */
    public void setStart(int newStart) {
	if (newStart > 0) {
	    startTime = newStart;
	}
	else {
	    throw new IllegalArgumentException();
	}
    }
    
    
    /**
     * Sets a new end time for the time slot.
     * @param newEnd: the new end time.
     * @throws IllegalArgumentException if end time negative.
     */
    public void setEnd(int newEnd) {
	if (newEnd > 0) {
	    endTime = newEnd;
	}
	else {
	    throw new IllegalArgumentException();
	}
    }
    
    
    /**
     * Set a new preference score.
     * @param newScore: the new preference score.
     */
    public void setPreference(int newScore) {
	prefScore = newScore;
    }
    
    
    /**
     * Merge this TimeSlot with a given one, so that the
     * two are contained within one TimeSlot.
     * @param inSlot: the slot to merge with this slot.
     */
    public void merge(TimeSlot inSlot) {
	
	// set startTime to the earliest time:
	startTime = this.minVal(this.getStart(), inSlot.getStart());
	// set endTime to the latest time:
	endTime = this.maxVal(this.getEnd(), inSlot.getEnd());
	// mark as busy if either original is busy:
	busyTime = (busyTime && inSlot.isBusy());
	// set the preference score to the highest of the originals:
	prefScore = this.maxVal(prefScore, inSlot.getPreference());
    }
        
    
    /**
     * Tells whether the time slot is marked as busy.
     * @returns true if busy.
     */
    public boolean isBusy() {
	return busyTime;
    }
    
    
    /**
     * Compares this TimeSlot to another to see if they overlap.
     * @param compSlot: another TimeSlot to compare for overlaps.
      */
    public boolean isOverlapping(TimeSlot compSlot) {
	
	// This logic should be checked.
	if (this.isOverlapping(compSlot.getStart()) 
	    || this.isOverlapping(compSlot.getEnd())) {
	    return true;
	}
	else if (compSlot.isOverlapping(startTime) 
		 || compSlot.isOverlapping(endTime)) {
	    return true;
	}
	else {
	    return false;
	}
    }
    
    
    /**
     * Checks whether a specific time overlaps with the slot.
     * @param inTime: the time to check
     * @returns true if the time overlaps with the slot.
     */
    public boolean isOverlapping(int inTime) {
	boolean overlapTest = (inTime >= startTime && inTime <= endTime);
	return overlapTest;
    }
    
}
