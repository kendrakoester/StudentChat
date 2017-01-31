import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class studentChat 
{

	public static void main(String[] args) 
	{
//		Student student = new Student(null, null, 0, null);
//		ArrayList<Student> students = new ArrayList<Student>();
//		//Set<Student> students = new HashSet<Student>();
//		students = student.StudentHashSet(student.getArray());
//		System.out.println(students);
//		
//		//List<Student> stringsList = new ArrayList(students);
//		System.out.println(students.get(3));
		
		Set<Student> students = new HashSet<Student>();
		ArrayList<Responses> res = new ArrayList<Responses>();
		students.add(new Student ("Nathan", "Borup", 1, res));
		students.add(new Student("Ethan", "Brown", 2, res));
		students.add(new Student("Michael", "Cullimore", 3, res));
		students.add(new Student("Kendra", "Koester", 4, res));
		students.add(new Student("Cody", "May", 5, res));
		students.add(new Student("Brie", "Miller", 6, res));
		students.add(new Student("Rizwan", "Mohammed", 7, res));
		students.add(new Student("Lauren", "Ribiro", 8, res));
		students.add(new Student("Tyler", "Toponce", 9, res));
		
		System.out.println(students);
		
		 
		
		//create a method to pick two random students to send to the student class
		
		//create method to pick random responses
			//remove users
			//add users to chat
			//student.getResponse(randomNumber);
		
		//Print out conversation
		
		   
	}
	
	

}
