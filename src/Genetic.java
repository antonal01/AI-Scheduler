//Κωτσομητόπουλος Σταύρος - ΑΜ: 3150086
//Αντωνίου Αλέξανδρος - ΑΜ: 3140248

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetic
{

    //ArrayList that contains the current population of chromosomes
	private ArrayList<Chromosome> population;
    /*
     * ArrayList that contains indexes of the chromosomes in the population ArrayList
     * Each chromosome index exists in the ArrayList as many times as its fitness score
     * By creating this ArrayList so, and choosing a random index from it,
     * the lower the fitness score of a chromosome the greater chance it will be chosen.
    */
	private ArrayList<Integer> fitnessBounds;
	
	public Genetic()
	{
		this.population = null;
		this.fitnessBounds = null;
	}

    /* 
     * populationSize: The size of the population in every step
     * mutationPropability: The propability a mutation might occur in a chromosome
     * maximumFitness: The maximum fitness value of the solution we wish to find
     * maximumSteps: The maximum number of steps we will search for a solution
     */
	public Chromosome geneticAlgorithm(int populationSize, double mutationProbability, int maximumFitness, int maximumSteps)
	{try{
        //We initialize the population
		initializePopulation(populationSize);
		Random r = new Random();
		for(int step=0; step < maximumSteps; step++)
		{
            //Initialize the new generated population
			ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
			for(int i=0; i < populationSize; i++)
			{
                //We choose two chromosomes from the population
                //Due to how fitnessBounds ArrayList is generated, the propability of
                //selecting a specific chromosome depends on its fitness score

				int xIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				Chromosome x = this.population.get(xIndex);
				int yIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				while(yIndex == xIndex)
				{
					yIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				}
				Chromosome y = this.population.get(yIndex);
                //We generate the "child" of the two chromosomes
				Chromosome child = this.reproduce(x, y);
                //We might then mutate the child
				if(r.nextDouble() < mutationProbability)
				{
					child.mutate();
				}
                //...and finally add it to the new population
				newPopulation.add(child);
			}
			this.population = new ArrayList<Chromosome>(newPopulation);

            //We sort the population so the one with the greater fitness is first
			Collections.sort(this.population, Collections.reverseOrder());
            //If the chromosome with the best fitness is acceptable we return it
			//the opposite
			if(this.population.get(0).getFitness() <= maximumFitness)
			{
                System.out.println("Finished after " + step + " steps...");
				return this.population.get(0);
			}
            //We update the fitnessBounds arrayList
			this.updateFitnessBounds();
		}

        System.out.println("Finished after " + maximumSteps + " steps...");
	}catch(Exception ex){
	ex.printStackTrace();
	}
		return this.population.get(0);
	}

    //We initialize the population by creating random chromosomes
	public void initializePopulation(int populationSize)
	{
		this.population = new ArrayList<Chromosome>();
		for(int i=0; i<populationSize; i++)
		{
			this.population.add(new Chromosome());
		}
		this.updateFitnessBounds();
	}

    //Updates the arraylist that contains indexes of the chromosomes in the population ArrayList
	public void updateFitnessBounds()
	{
		this.fitnessBounds = new ArrayList<Integer>();
		for (int i=0; i<this.population.size(); i++)
		{
//			System.out.println("fitness " + this.population.get(i).getFitness());
			for(int j=0; j<this.population.get(i).getFitness(); j++)
			{
                //Each chromosome index exists in the ArrayList as many times as its fitness score
                //By creating this ArrayList so, and choosing a random index from it,
                //the greater the fitness score of a chromosome the greater chance it will be chosen.
				fitnessBounds.add(i);
			}
		}
	}

    //"Reproduces" two chromosomes and generated their "child"
	public Chromosome reproduce(Chromosome x, Chromosome y)
	{
//		try{
		Random r = new Random();
        //Randomly choose the intersection point
		int intersectionPoint = r.nextInt(314) + 1;
		Integer [] childGenes = new Integer[315];
        //The child has the left side of the x chromosome up to the intersection point...
		for(int i=0; i<intersectionPoint; i++)
		{
			childGenes[i] = x.getGenes()[i];
		}
        //...and the right side of the y chromosome after the intersection point
		for(int i=intersectionPoint; i<childGenes.length; i++)
		{
			childGenes[i] = y.getGenes()[i];
		}
		return new Chromosome(childGenes);

//	}catch(Exception ex){
//		ex.printStackTrace();
//		return null;
//	}
	}
}
