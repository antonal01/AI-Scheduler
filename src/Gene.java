//Κωτσομητόπουλος Σταύρος - ΑΜ: 3150086
//Αντωνίου Αλέξανδρος - ΑΜ: 3140248

import java.util.Objects;

/**
 * Representation of 1 class hour for a specific teacher
 * @author Stavros
 */

public class Gene {
	
	private Teacher teacher;
	
	@Override
	public String toString() {
		return "Teacher = " + teacher.getTeacherName() + ", lessonId= " + lessonId + ", className= " + className;
	}

	public Gene(Teacher teacher, int lessonId, String className) {
		super();
		this.teacher = teacher;
		this.lessonId = lessonId;
		this.className = className;
	}

	private int lessonId;
	private String className;
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public int getlessonId() {
		return lessonId;
	}
	
	public void setlessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public int hashCode() {
		return Objects.hash(teacher,lessonId,className);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gene other = (Gene) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (lessonId != other.lessonId)
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}
	
	

}
