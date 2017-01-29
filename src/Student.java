import java.util.HashSet;
import java.util.Set;

public class Student 
{
	
	private String firstName;
	private String lastName;
	private int score;
	private String chatResponses;
	
	public Student(String firstName, String lastName, int score, String chatResponses) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
		this.chatResponses = chatResponses;
	}
	
	
	public Student[] getArray()
	{
		Student studentOne = new Student("Nathan", "Borup", 1, "chatResponses");
		Student studentTwo = new Student("Ethan", "Brown", 2, "chatResponses");
		Student studentThree = new Student("Michael", "Cullimore", 3, "chatResponses");
		Student studentFour = new Student("Kendra", "Koester", 4, "chatResponses");
		Student studentFive = new Student("Cody", "May", 5, "chatResponses");
		Student studentSix = new Student("Brie", "Miller", 6, "chatResponses");
		Student studentSeven = new Student("Rizwan", "Mohammed", 7, "chatResponses");
		Student studentEight = new Student("Lauren", "Ribiro", 8, "chatResponses");
		Student studentNine = new Student("Tyler", "Toponce", 9, "chatResponses");
		
		Student[] studentArray = {studentOne, studentTwo, studentThree, studentFour, studentFive, studentSix, studentSeven, studentEight, studentNine};
		
		return studentArray;
	}
	
	public Set<Student> StudentHashSet(Student[] studentArray)
	{
		Set<Student> students = new HashSet<Student>();
		for(Student student: studentArray)
		{
			students.add(student);	
		}
		
		return students;
	}

}
