Authors: Andrew Hard, Ezra Schricker
Email: hardandrew1@gmail.com
Created: 10/02/2015
Modified: 10/02/2015

This package is designed to find solutions to the problem of matching employees
to shifts. For small problem sizes, the program will attempt to find a globally 
maximal solution. For larger problems, it will use algorithms that can only 
guarantee a locally maximal result. 

The components of the package are:

Employee.java
	Reads an input file for an employee and stores the information as a list
	of TimeSlots.

Employer.java
	Reads an input file for a business and stores the information as a list
	of TimeSlots.

GeneticOptimizer.java
	Implements a genetic algorithm to match employer and employee schedules.

Scheduler.java
	Loads an employer's shift schedule, as well as the schedules of
	employees, and maps one to the other.

TimeSlot.java
	A basic class representing a period of time and useful associated
	values. Includes a start time, stop time, busy flag, and a preference 
	score.
