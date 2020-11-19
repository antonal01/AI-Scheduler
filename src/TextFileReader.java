//Κωτσομητόπουλος Σταύρος - ΑΜ: 3150086
//Αντωνίου Αλέξανδρος - ΑΜ: 3140248

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {

	/*
	 * Read lessons from text file
	 */
	static List<Lesson> readLessonsFromFile(String filename) {

		List<Lesson> lessonList = new ArrayList<Lesson>();

		// read file and split
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filename));
			String line = null;
			while ((line = in.readLine()) != null) {
				// For each line create one Lesson object

				String[] lessonInfo = line.split(",");

				if (lessonInfo.length != 4)
					throw new Exception("Wrong txt format");

				Lesson lesson = new Lesson(Integer.valueOf(lessonInfo[0]), lessonInfo[1], lessonInfo[2],
						Integer.valueOf(lessonInfo[3]));
				lessonList.add(lesson);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	/*
	 * Read teachers from text file
	 */
	static List<Teacher> readTeachersFromFile(String teacherFile, String lessonFile) {

		List<Lesson> lessonList = readLessonsFromFile(lessonFile);
		System.out.println(lessonList.size() + " lessons added.");

		List<Teacher> teacherList = new ArrayList<Teacher>();

		// Read file and split
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(teacherFile));
			String line = null;
			while ((line = in.readLine()) != null) {
				// For each line create one Teacher object

				String[] teacherInfo = line.split(",");

				if (teacherInfo.length != 5)
					throw new Exception("Wrong txt format");

				Teacher teacher = new Teacher(Integer.valueOf(teacherInfo[0]), teacherInfo[1],
						Integer.valueOf(teacherInfo[2]), Integer.valueOf(teacherInfo[3]));

				String[] lessonIds = teacherInfo[4].split("-");

				// For every id 1-2-3-4-5 in the .txt file
				for (String lessonId : lessonIds) {

					// Search if we have already a lesson with that lessonId
					for (Lesson lesson : lessonList) {
						if (Integer.valueOf(lessonId) == lesson.getLessonID())
							teacher.addLesson(lesson);
					}
				}
				teacherList.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return teacherList;
	}

	static public List<Teacher> read(String teachersFile, String lessonsFile) {
		return readTeachersFromFile(teachersFile, lessonsFile);
	}

}
