
//Κωτσομητόπουλος Σταύρος - ΑΜ: 3150086
//Αντωνίου Αλέξανδρος - ΑΜ: 3140248

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
	// Table that holds the chromosome itself
	// Each position represents the key value of 1 hour per class of
	// HashMapGenes
	private Integer[] genes;
	// Integer that holds the fitness score of the chromosome
	private int fitness;

	static Map<Integer, Gene> hashMapGenes = null;

	// Constructs a randomly created chromosome
	public Chromosome() {
		// Read data from files
		if (hashMapGenes == null) {
			hashMapGenes = new HashMap<Integer, Gene>();
			List<Teacher> teacherList = TextFileReader.read("teachers.txt", "lessons.txt");
			int key = 0;
			for (Teacher teacher : teacherList) {
				for (Lesson lesson : teacher.getLessons()) {
					for (int i = 0; i < lesson.getHours(); i++) {
						Gene gene = new Gene(teacher, lesson.getLessonID(), lesson.getClassName());
						hashMapGenes.put(key, gene);
						key++;
					}
				}
			}
		}

		this.genes = new Integer[hashMapGenes.size()];

		// suffle
		List<Integer> geneList = Arrays.asList(genes);
		Collections.shuffle(geneList);
		genes = geneList.toArray(genes);

		int i = 0;
		for (Map.Entry<Integer, Gene> entry : hashMapGenes.entrySet()) {
			genes[i] = entry.getKey();
			i++;
		}

		this.calculateFitness();
	}

	// Constructs a copy of a chromosome
	public Chromosome(Integer[] genes) {
		this.genes = new Integer[315];
		for (int i = 0; i < 315; i++) {
			this.genes[i] = genes[i];
		}
		this.calculateFitness();
	}

	public Integer[] getGenes() {
		return this.genes;
	}

	public int getFitness() {
		return this.fitness;
	}

	public void setGenes(int[] genes) {
		for (int i = 0; i < this.genes.length; i++) {
			this.genes[i] = genes[i];
		}
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	// Calculates the fitness score of the chromosome as the collisions that
	// occur
	// The minimum score number is 0 and it's the best score
	public void calculateFitness() {
		int negative_score = 0;
		for (int i = 0; i < (this.genes.length) / 9; i++) {
			boolean sameTeachers = false; // check if there are same teachers on
											// the same hour
			boolean sameClassName = false; // check if there is same class name
											// on the same hour
			for (int j = i * 9; j < ((this.genes.length) / 35) * (i + 1); j++) {
				for (int k = j + 1; k < ((this.genes.length) / 35) * (i + 1); k++) {

					// the more the score the worse => the less the best

					// // if IDteacher[j]=IDteacher[k]=> score+
					if (hashMapGenes.get(genes[k]).getTeacher().getTeacherID() == hashMapGenes.get(genes[j])
							.getTeacher().getTeacherID()) {
						sameTeachers = true;
						negative_score += 1;
					}
					// if claasName[j]=className[k] => score+
					if (hashMapGenes.get(genes[k]).getClassName().equals(hashMapGenes.get(genes[j]).getClassName())) {
						sameClassName = true;
						negative_score += 1;
					}

				}
			}
			if (sameTeachers == true) {
				negative_score += 2;
			}
			if (sameClassName == true) {
				negative_score += 2;
			}
		}
		for (int i = 0; i < (this.genes.length) / 63; i++) {
			for (int j = i * 63; j < ((this.genes.length) / 5) * (i + 1); j++) {
				int count = 0;
				for (int k = j + 1; k < ((this.genes.length) / 5) * (i + 1); k++) {
					if (hashMapGenes.get(genes[k]).getlessonId() == hashMapGenes.get(genes[j]).getlessonId()
							&& hashMapGenes.get(genes[k]).getClassName()
									.equals(hashMapGenes.get(genes[j]).getClassName())) {
						negative_score += 2;
					}
					if (hashMapGenes.get(genes[k]).getTeacher().getTeacherID() == hashMapGenes.get(genes[j])
							.getTeacher().getTeacherID()) {
						count++;
						if (count >= 2) {
							negative_score += 3;
						}

					}

				}

			}

		}
		this.fitness = negative_score;
	}

	// Mutate by randomly changing the position between 2 cells, only if these cells make the fitness score better 
	public void mutate() {
		int randomCell_1;
		int randomCell_2;
		do {
			Random r1 = new Random();
			Random r2 = new Random();
			int temp = 0;
			randomCell_1 = r1.nextInt(315);
			randomCell_2 = r2.nextInt(315);
			temp = this.genes[randomCell_1];
			this.genes[randomCell_1] = this.genes[randomCell_2];
			this.genes[randomCell_2] = temp;
		} while (hashMapGenes.get(genes[randomCell_1]).getTeacher().getTeacherID() == hashMapGenes
				.get(genes[randomCell_2]).getTeacher().getTeacherID()
				|| hashMapGenes.get(genes[randomCell_1]).getClassName()
						.equals(hashMapGenes.get(genes[randomCell_2]).getClassName()));

		this.calculateFitness();

		System.out.println("fitness score: " + this.fitness);
	}

	public void print() {
		try {
			// create schedule file
			PrintWriter writer = new PrintWriter("schedule.txt", "UTF-8");
			writer.println("--------------------");
			writer.println("High School Schedule");
			writer.println("--------------------\n");
			for (int i = 0; i < 5; i++) {
				// loop per day
				int hoursCounter = 8;
				if (i == 0)
					writer.println("\t\t Monday:\n");
				else if (i == 1)
					writer.println("\t\t Tuesday:\n");
				else if (i == 2)
					writer.println("\t\t Wednesday:\n");
				else if (i == 3)
					writer.println("\t\t Thursday:\n");
				else if (i == 4)
					writer.println("\t\t Friday:\n");
				for (int j = i * 63; j < ((this.genes.length) / 5) * (i + 1); j++) {
					// loop per hour
					if (j % 9 == 0) {
						writer.println("Time from : " + hoursCounter + ":00 to " + (hoursCounter + 1) + ":00");
						hoursCounter++;
					}
					writer.println(hashMapGenes.get(genes[j]).toString());
				}
			}

			writer.close();
		} catch (IOException e) {
			System.err.println("Problem creating the file.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		for (int i = 0; i < this.genes.length; i++) {
			if (this.genes[i] != ((Chromosome) obj).genes[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(genes);
	}

	@Override
	// The compareTo function has been overriden so sorting can be done
	// according to fitness scores
	public int compareTo(Chromosome x) {
		return this.fitness - x.fitness;
	}
}
