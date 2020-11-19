
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher {

	private int teacherID;
	private int maxHoursPerDay;
	private int maxHoursPerWeek;
	private String teacherName;
	private List<Lesson> lessons;

	Teacher(int teacherID, String teacherName, int maxHoursPerDay, int maxHoursPerWeek) {
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.maxHoursPerWeek = maxHoursPerWeek;
		this.maxHoursPerDay = maxHoursPerDay;
		lessons = new ArrayList<Lesson>();
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public int getMaxHoursPerDay() {
		return maxHoursPerDay;
	}

	public void setMaxHoursPerDay(int maxHoursPerDay) {
		this.maxHoursPerDay = maxHoursPerDay;
	}

	public int getMaxHoursPerWeek() {
		return maxHoursPerWeek;
	}

	public void setMaxHoursPerWeek(int maxHoursPerWeek) {
		this.maxHoursPerWeek = maxHoursPerWeek;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public void addLesson(Lesson lesson) {
		this.lessons.add(lesson);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lessons,teacherID,maxHoursPerDay,maxHoursPerWeek,teacherName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (lessons == null) {
			if (other.lessons != null)
				return false;
		} else if (!lessons.equals(other.lessons))
			return false;
		if (maxHoursPerDay != other.maxHoursPerDay)
			return false;
		if (maxHoursPerWeek != other.maxHoursPerWeek)
			return false;
		if (teacherID != other.teacherID)
			return false;
		if (teacherName == null) {
			if (other.teacherName != null)
				return false;
		} else if (!teacherName.equals(other.teacherName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Teacher [teacherID=" + teacherID + ", maxHoursPerDay=" + maxHoursPerDay + ", maxHoursPerWeek="
				+ maxHoursPerWeek + ", teacherName=" + teacherName + ", lessons=" + lessons + "]";
	}

}
