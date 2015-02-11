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

** Status as of 10/02/2015

The supporting classes are nearly complete, and the input structure of Scheduler
has been added. Tasks should be completed in the following order:

1. resolve any differences in Employee/Employer implementations
2. Scheduler: fill in the details of the switch structure 
3. Outline GeneticOptimizer.


The program should probably also have a constraint propagation phase of the 
calculation that takes place before the genetic algorithm. This can create a 
preliminary assignment that is workable within the hard constraints (if 
possible). From there, the genetic algorithm should run to improve the fitness 
score. There was a paper somewhere demonstrating that genetic algorithms 
converge more rapidly when 3 base pairs are swapped at a time instead of two 
(more population diversity).
       
Swapping 2 base pairs:
  gen 1: x-a-x-b-x-x-x
  gen 2: x-b-x-a-x-x-x
           ^   ^

Swapping 3 base pairs:
  gen 1: x-a-x-b-x-c-x
  gen 2: x-c-x-a-x-b-x
           ^   ^   ^
