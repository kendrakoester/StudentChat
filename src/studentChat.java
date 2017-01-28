import java.util.HashSet;
import java.util.Set;

public class studentChat 
{

	public static void main(String[] args) 
	{
		//create a method to pick two random students to send to the student class
		Student student = new Student(null, null, 0, null);
		
		Set<Student> students = new HashSet<Student>();
		
		students = student.StudentHashSet(student.getArray());

		System.out.println(students);

	}

}
