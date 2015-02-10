///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  OptimizeShift.java 
// File:             Employee.java
//
// Authors:          Andrew Hard, Ezra Schricker 
// Email:            hardandrew1@gmail.com
// Created:          October 20, 2014
// Modified:         November 7, 2014
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Employee class reads an input file for an employee
 * and stores the information as a list of TimeSlots (called mySchedule?).
 *
 * @author Andrew Hard
 * @author Ezra Schricker
 */

public class Employee implements iterable {
    
    // Name of the employee:
    private String myName;
    
    // The mySchedule object is just an unsorted list of the TimeSlots
    // associated with an employee for storage.
    private LinkedList<TimeSlot> mySchedule;
    
    // Also store a sorted verison of the list. Busy, high preference come first
    private LinkedList<TimeSlot> mySortedSchedule;
    
    /**
     * Initialize the Employee class. Load files with the Employee preferences,
     * store them as TimeSlots in a LinkedList.
     * @param filePath: the file path 
     * @throws FileNotFoundException if file not found. To be passed up and
     * caught in the Scheduler class.
     */
    public Employee(String name, String filePath) {
	
	myName = name;
	LinkedList<TimeSlot> mySchedule = new LinkedList<TimeSlot>();
	LinkedList<TimeSlot> mySortedSchedule = new LinkedList<TimeSlot>();
	
	// May throw FileNotFoundException, which will be passed up the 
	// call stack to the Scheduler class.
	File file = new File(filePath);
	
	Scanner employeeInput = new Scanner(file);
	while (employeeInput.hasNextLine()) {
	    // split the line into multiple fields
	    // time1, time2, busy, pref
	    String currLine = employeeInput.nextLine();
	    String delims = " ";
	    String[] splitLine = currLine.split(delims);
	    
	    boolean isBusyBool = (boolean)(Integer.parseInt(splitLine[2]));
	    
	    // Create a new TimeSlot using inputs, add to mySchedule:
	    TimeSlot newSlot = new TimeSlot(Integer.parseInt(splitLine[0]), 
					    Integer.parseInt(splitLine[1]),
					    isBusyBool,
					    Integer.parseInt(splitLine[3])
					    );
	    // Add to unordered list:
	    mySchedule.add(newSlot);
	    
	    // Add to list sorted by preference score:
	    if (mySortedSchedule.size() == 0) {
		mySortedSchedule.add(newSlot);
	    }
	    else {
		for (int i = 0; i < mySortedSchedule.size(); i++) {
		    if (testSlot.getPreference() 
			> mySortedSchedule(i).getPreference()) {
			mySortedSchedule.add(i,testSlot);
			break;
		    }
		}
	    }
	}
	employeeInput.close();
    }
    
    
    /**
     * Get the size of the employee's schedule (number of TimeSlots).
     * @returns size of mySchedule
     */
    public int scheduleSize() {
	return mySchedule.size();
    }
    
    
    /**
     * Get the name of the employee.
     * @returns name of the employee.
     */
    public String getName() { 
	return myName;
    }

    
    /**
     * Set up the iterator to traverse the LinkedList
     * @returns: LinkedListIterator object
     */
    public Iterator<TimeSlot> iterator() {
	return mySchedule.iterator();
    }
    
    
    /**
     * Add a new time slot to the schedule:
     * @param testSlot - The TimeSlot to add
     */
    public void add(TimeSlot testSlot) {
	
	// Add to unordered TimeSlot list:
	mySchedule.add(testSlot);
	
	// Add to list sorted by preference score:
	for (int i = 0; i < mySortedSchedule.size(); i++) {
	    if (testSlot.getPreference() 
		> mySortedSchedule(i).getPreference()) {
		mySortedSchedule.add(i,testSlot);
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
	for (int i = 0; i < mySchedule.size(); i++) {
	    
	    bool removeUnsorted = false;
	    bool removeSorted = false;
	    
	    // Remove from unsorted list:
	    if (testSlot == mySchedule(i)) {
		mySchedule.remove(i);
		removedUnsorted = true;
	    }
	    
	    // Remove from sorted list:
	    if (testSlot == mySortedSchedule(i)) {
		mySortedSchedule.remove(i);
		removedSorted = true;
	    }
	    
	    if (removedUnsorted && removedSorted) {
		break;
	    }
	}
    }
    
    
    /**
     * A method to find all of the employee's busy TimeSlots.
     * @returns: LinkedList of busy TimeSlots
     */
    public LinkedList<TimeSlot> getBusyTimeSlots() {
	
	List<TimeSlot> busyList = new LinkedList<TimeSlot>();
	
	// loop over all time slots:
	Iterator<TimeSlot> timeIter = this.iterator();
	while(timeIter.hasNext()) {
	    TimeSlot currTimeSlot = timeIter.next();
	    // if employee busy during this TimeSlot,
	    if (currTimeSlot.isBusy()) { 
		busyList.add(currTimeSlot);//add slot to busy list
	    }
	}
	return busyList;
    }
    
    
    /**
     * This function compares any testSlot to entire employee schedule
     * to check for overlap in order to determine conflicts
     * @param testSlot: any timeslot to test against 
     * @returns true if testSlot conflicts with employee's TimeSlots
     */
    public boolean hasConflict(TimeSlot testSlot) {
	
	// Iterate over employee timeslots
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
     * Method looks at employee's schedule to see if there is a preference
     * score assigned to a given time. If no TimeSlot includes the time,
     * return 0. If a TimeSlot is defined for the given time, return the 
     * score associated with that TimeSlot.
     * @param testTime: a time to score for the employee
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
     * Method looks at employee's schedule to see if there is a preference
     * score associated with an external TimeSlot. If no TimeSlot overlaps
     * the requested slot return 0. Otherwise, return the highest preference
     * score for an overlapping TimeSlot of the employee. It is possible that 
     * there will be mutliple overlapping TimeSlots in the employee's schedule,
     * so we take the highest into account. 
     * @param testSlot: a TimeSlot to score for the employee
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
     * return list of elements in order of preference
     * sorting - need to find the fastest method
     * might want to transform into array and use mergesort?
     * @returns ordered list of Employee Preferences
     */
    public LinkedList<TimeSlot> getPrefSlots() {
	return mySortedSchedule;
    }

	
	
	
	
}
