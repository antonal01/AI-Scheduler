# AI-Scheduler
Implementation of a time table using AI methods.

Generating a high school curriculum with **genetic** algorithms by relying on  
***chromosomes***, ***mutation***, ***crossover***, and ***selection***.

>In a genetic algorithm, a population of candidate solutions to an optimization problem is evolved toward better solutions. 
>Each candidate solution has a set of properties (its chromosomes) that can be mutated.

A chromosome is a representation of a school curriculum situation.

The algorithm calculates the fitness score (based on conditions) of the chromosome as the collisions that occur.  
The minimum score number is 0 and it's the best score.  
The more fit individuals are stochastically selected from the current population, and each individual's chromosome is modified (recombined through crossover and randomly mutated) to form a new chromosome.


## Selection (Fitness score conditions)

- The program shouldn't have any recesses.
- No teacher should teach for more than two continuous hours.
- The daily number of teaching hours for each department should be evenly distributed.
- The teaching hours of each course in a class should be as evenly distributed as possible on all days of the week
- The number of teaching hours per week should be evenly distributed for all teachers.

### Specifically

Assuming that each week has five teaching days, each with a maximum of seven hours.  
The program receives two files with courses and teacher's info. The purpose is to find a schedule that meets the conditions of those two files.  
Eventually generating a schedule file that represents the weekly schedule for the high school.

