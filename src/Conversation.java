import java.util.HashSet;
import java.util.Set;

public class Conversation 
{
	//group of two to print out the conversation
	
	public void Conversation()
	{
		Responses response	 = new Responses("", "", "", "", "");
		Student student = new Student("", "", 0, response);
		
		Set<Student> students = new HashSet<Student>();
		
		students = student.StudentHashSet(student.getArray());
		
		Student s1 = student.getRandomStudent(students);
		String sOneCOne = s1.getResponses()[0];
		
	}
		
}
