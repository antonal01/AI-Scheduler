public class Main
{
	public static void main(String[] args)
	{


//		// Traverse hashmap and print
//		//for testing purposes only
//		for (Map.Entry<Integer, Gene> entry : hashMapGenes.entrySet()) {
//			Integer keyy = entry.getKey();
//		    Gene value = entry.getValue();
//			System.out.println(keyy + " -> " + value.getTeacher().getTeacherID());
//		}
		System.out.println();
		Genetic g = new Genetic();
		Chromosome x = g.geneticAlgorithm(2, 0.035, 1450, 11100); //best fitness score = 0
//		Chromosome x = g.geneticAlgorithm(50, 0.03, 360, 10000); //best fitness score = 0

		x.print();
//		
	}
}
