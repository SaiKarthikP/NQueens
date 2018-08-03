N-queen problem solver using Steepest HillClimbing and Simulated Annealing

Description
----
This program solves any n-queen problem with any initial configurations. The goal state is to have n queens placed on a nxn board such that no two pairs of queens are attacking. 


How to run the program
----
1. Compile the program using the command 'javac Main.java' within the directory. Once it compiles, type 'java Main' and the program should run as expected.

2. Alternatively, the program can be run on any Java IDE.

How to use the program
----
First, the user is prompted for the value of n in the n-queens problem. 

The following menu is displayed after the user enters the number of queens.
Menu: 
	1.Show successful solutions of both methods(Steepest HillClimbing and Simulated Annealing)
	2.Show analysis of both methods

Choose 1 to view all successful solutions of both algorithms for 100 intial configurations. Note, only successful solutions are printed to the console. 

Choose 2 to view the analysis of applying both the algorithms. Analysis show the following: 

	1. Number of queens, number of initial configurations tested(instances), initial temperature (used for simulated 	annealing only), schedule (used for simulated annealing only)
	2. Percentage of solved problems for both algorithms
	3. Average search costs for both algorithms for all attempts and all successful attempts
	4. Average time for both algorithms for all attempts and all successful attempts