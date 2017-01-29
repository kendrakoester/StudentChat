import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class studentChat 
{

	public static void main(String[] args) 
	{
		Student student = new Student(null, null, 0, null);
		Set<Student> students = new HashSet<Student>();
		students = student.StudentHashSet(student.getArray());
		System.out.println(students);
		
		//create a method to pick two random students to send to the student class
		
		//create method to pick random responses
			//remove users
			//add users to chat
			//student.getResponse(randomNumber);
		
		//Print out conversation
		
		// create an iterator
		   Iterator iterator = students.iterator(); 
		      
		   // check values
		   while (iterator.hasNext())
		   {
		   System.out.println("Value: "+iterator.next() + " ");  
		   }
	}

}
