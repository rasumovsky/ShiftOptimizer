///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  Scheduler.java 
// File:             Employer.java
//
// Authors:          Andrew Hard, Ezra Schricker 
// Email:            hardandrew1@gmail.com
// Created:          October 20, 2014
// Modified: 		 November 9, 2014
///////////////////////////////////////////////////////////////////////////////

import java.util.*;

/**
 * The Employer class reads an input file for a business
 * and stores the information as a list of TimeSlots.
 *
 * @author Andrew Hard
 * @author Ezra Schricker
 */
public class Employer implements iterable{
    
	// The shiftSchedule object is an unsorted list of the TimeSlots
    // associated with an employer for storage.
    private LinkedList<TimeSlot> shiftSchedule;
    
   	// Name of the employer: Don't think we need this, but...
    private String ourName;

    // Also store a sorted version of the list. Busy, high preference come first
    private LinkedList<TimeSlot> shiftSortedSchedule;
    
    /**
     * Initialize the Employer class. Load files with the Employer preferences,
     * store them as TimeSlots in a LinkedList.
     * @param filePath: the file path 
     * @throws FileNotFoundException if file not found. To be passed up and
     * caught in the Scheduler class.
     */
    public Employer(String name, String filePath) {
	
	ourName = name;
	LinkedList<TimeSlot> shiftSchedule = new LinkedList<TimeSlot>();
	LinkedList<TimeSlot> ourSortedSchedule = new LinkedList<TimeSlot>();
	
	// May throw FileNotFoundException, which will be passed up the 
	// call stack to the Scheduler class.
	File file = new File(filePath);
	
	Scanner employerInput = new Scanner(file);
	while (employerInput.hasNextLine()) {
	    // split the line into multiple fields
	    // time1, time2, busy, pref
	    String currLine = employerInput.nextLine();
	    String delims = " ";
	    String[] splitLine = currLine.split(delims);
	    
	    boolean isBusyBool = (boolean)(Integer.parseInt(splitLine[2]));
	    
	    // Create a new TimeSlot using inputs, add to ourSchedule:
	    TimeSlot newSlot = new TimeSlot(Integer.parseInt(splitLine[0]), 
					    Integer.parseInt(splitLine[1]),
					    isBusyBool,
					    Integer.parseInt(splitLine[3])
					    );
	    // Add to unordered list:
	    ourSchedule.add(newSlot);
	    
	    // Add to list sorted by preference score:
	    if (ourSortedSchedule.size() == 0) {
		ourSortedSchedule.add(newSlot);
	    }
	    else {
		for (int i = 0; i < ourSortedSchedule.size(); i++) {
		    if (testSlot.getPreference() 
			> ourSortedSchedule(i).getPreference()) {
			ourSortedSchedule.add(i,testSlot);
			break;
		    }
		}
	    }
	}
	employerInput.close();
    }
    
    
    /**
     * Get the size of the employer's schedule (number of TimeSlots).
     * @returns size of ourSchedule
     */
    public int scheduleSize() {
	return ourSchedule.size();
    }
    
    
    /**
     * Get the name of the employer.
     * @returns name of the employer.
     */
    public String getName() { 
	return ourName;
    }

    
    /**
     * Set up the iterator to traverse the LinkedList
     * @returns: LinkedListIterator object
     */
    public Iterator<TimeSlot> iterator() {
	return ourSchedule.iterator();
    }
    
    
    /**
     * Add a new time slot to the schedule:
     * @param testSlot - The TimeSlot to add
     */
    public void add(TimeSlot testSlot) {
	
	// Add to unordered TimeSlot list:
	ourSchedule.add(testSlot);
	
	// Add to list sorted by preference score:
	for (int i = 0; i < ourSortedSchedule.size(); i++) {
	    if (testSlot.getPreference() 
		> ourSortedSchedule(i).getPreference()) {
		ourSortedSchedule.add(i,testSlot);
		break;
	    }
	}
    }
    
    
    /**
     * Efficiently remove a new time slot from the schedule:
     * @param testSlot - The TimeSlot to remove
     */
	@Override 
    public void remove(TimeSlot testSlot) {
	
	// Do both removes using a single loop:
	for (int i = 0; i < ourSchedule.size(); i++) {
	    
	    bool removeUnsorted = false;
	    bool removeSorted = false;
	    
	    // Remove from unsorted list:
	    if (testSlot == ourSchedule(i)) {
		ourSchedule.remove(i);
		removedUnsorted = true;
	    }
	    
	    // Remove from sorted list:
	    if (testSlot == ourSortedSchedule(i)) {
		ourSortedSchedule.remove(i);
		removedSorted = true;
	    }
	    
	    if (removedUnsorted && removedSorted) {
		break;
	    }
	}
    }
    
    
    /**
     * A method to find all of the employer's busy TimeSlots.
     * @returns: LinkedList of busy TimeSlots
     */
    public LinkedList<TimeSlot> getBusyTimeSlots() {
	
	List<TimeSlot> busyList = new LinkedList<TimeSlot>();
	
	// loop over all time slots:
	Iterator<TimeSlot> timeIter = this.iterator();
	while(timeIter.hasNext()) {
	    TimeSlot currTimeSlot = timeIter.next();
	    // if employer busy during this TimeSlot,
	    if (currTimeSlot.isBusy()) { 
		busyList.add(currTimeSlot);//add slot to busy list
	    }
	}
	return busyList;
    }
    
    
    /**
     * This function compares any testSlot to entire employer schedule
     * to check for overlap in order to determine conflicts
     * @param testSlot: any timeslot to test against 
     * @returns true if testSlot conflicts with employer's TimeSlots
     */
    public boolean hasConflict(TimeSlot testSlot) {
	
	// Iterate over employer timeslots
	Iterator<TimeSlot> timeIter = this.iterator();
	while (timeIter.hasNext()) {
	    // Return true if current slot overlaps with testSlot
	    if (timeIter.next().isOverlapping(testSlot)) {
		return true;
	    }
	}
	// if found no overlaps, return false
	return false;
    }
    
    
    /**
     * Method looks at employer's schedule to see if there is a preference
     * score assigned to a given time. If no TimeSlot includes the time,
     * return 0. If a TimeSlot is defined for the given time, return the 
     * score associated with that TimeSlot.
     * @param testTime: a time to score for the employer
     * @returns PrefScore for particular testSlot, or else 0.
     */
    public int getPrefScore(int testTime) {
	
	// Loop over the list of TimeSlots:
	Iterator<TimeSlot> timeIter = this.iterator();
	while (timeIter.hasNext()) {

	    TimeSlot currTimeSlot = timeIter.next();
	    // If found TimeSlot that matches the requested time:
	    if (currTimeSlot.isOverlapping(testTime)) {
		// return the associated preference score:
		return currTimeSlot.getPreference();
	    }
	}
	// else return zero:
	return 0;
    }

    
    /**
     * Method looks at employer's schedule to see if there is a preference
     * score associated with an external TimeSlot. If no TimeSlot overlaps
     * the requested slot return 0. Otherwise, return the highest preference
     * score for an overlapping TimeSlot of the employer. It is possible that 
     * there will be multiple overlapping TimeSlots in the employer's schedule,
     * so we take the highest into account. 
     * @param testSlot: a TimeSlot to score for the employer
     * @returns Highest PrefScore for particular testSlot, or else 0.
     */
    public int getPrefScore(TimeSlot testSlot) {
	
	int highestPreference = 0;
	// Loop over the list of TimeSlots:
	Iterator<TimeSlot> timeIter = this.iterator();
	while (timeIter.hasNext()) {
	    
	    TimeSlot currTimeSlot = timeIter.next();
	    // If found TimeSlot that matches the requested time:
	    if (currTimeSlot.isOverlapping(testSlot)) {
		// return the associated preference score:
		if (currTimeSlot.getPreference() > highestPreference) {
		    highestPreference = currTimeSlot.getPreference();
		}
	    }
	}
	return highestPreference;
    }
    
        
    /** 
     * return list of elements in order of preference - busy, large to small
     * @returns ordered list of employer Preferences
     */
    public LinkedList<TimeSlot> getPrefSlots() {
	return ourSortedSchedule;
    }
	
}
