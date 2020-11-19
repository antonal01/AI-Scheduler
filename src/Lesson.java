//Κωτσομητόπουλος Σταύρος - ΑΜ: 3150086
//Αντωνίου Αλέξανδρος - ΑΜ: 3140248

import java.util.Objects;

public class Lesson<T> {
	
	private int lessonID;
	private String lessonName;
	private String className;
	private int hours;
	
	Lesson(int lessonID,String lessonName,String className,int hours){
		this.lessonID = lessonID;
		this.lessonName = lessonName;
		this.className = className;
		this.hours = hours;
	}
	
	Lesson(){};

	public int getLessonID() {
		return lessonID;
	}

	public void setLessonID(int lessonID) {
		this.lessonID = lessonID;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "Lessons [lessonID=" + lessonID + ", lessonName=" + lessonName + ", className=" + className + ", hours="
				+ hours + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(className, hours, lessonID,lessonName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (hours != other.hours)
			return false;
		if (lessonID != other.lessonID)
			return false;
		if (lessonName == null) {
			if (other.lessonName != null)
				return false;
		} else if (!lessonName.equals(other.lessonName))
			return false;
		return true;
	}
		

}
