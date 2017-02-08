import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Conversation 
{
	
	public Conversation()
	{
		
	}
	
		Responses response	 = new Responses("", "", "", "", "");
		Student student = new Student("", "", 0, response);
		//Conversation conversation = new Conversation();
		
		Set<Student> students = new HashSet<Student>();
		
	public String haveConversation()
	{
		students = student.StudentHashSet(student.getArray());

		Student s1 = student.getRandomStudent(students);
		Student s2 = student.getRandomStudent(students);
		
		sortStudents(s1, s2);
		
		String returnString = "";
		
		if(s1 != s2)
		{
			String sOneCOne = s1.getResponses()[0];
			//System.out.println(s1 + sOneCOne);
			returnString += (s1 + sOneCOne + "\n");
			
			String sTwoCOne = s2.getResponses()[0];
			//System.out.println(s2 + sTwoCOne);
			returnString += (s2 + sTwoCOne + "\n");
			
			String sOneCTwo = s1.getResponses()[1];
			//System.out.println(s1 + sOneCTwo);
			returnString += (s1 + sOneCTwo + "\n");
			
			String sTwoCTwo = s2.getResponses()[1];
			//System.out.println(s2 + sTwoCTwo);
			returnString += (s2 + sTwoCOne + "\n");
			
			String sOneCThree = s1.getResponses()[2];
			//System.out.println(s1 + sOneCThree);
			returnString += (s1 + sOneCThree + "\n");
			
			String sTwoCThree = s2.getResponses()[2];
			//System.out.println(s2 + sTwoCThree);
			returnString += (s2 + sTwoCThree + "\n");
			
			String sOneCFour = s1.getResponses()[3];
			//System.out.println(s1 + sOneCFour);
			returnString += (s1 + sOneCFour + "\n");
			
			String sTwoCFour = s2.getResponses()[3];
			//System.out.println(s2 + sTwoCFour);
			returnString += (s2 + sTwoCFour + "\n");
			
			String sOneCFive = s1.getResponses()[4];
			//System.out.println(s1 + sOneCFive);
			returnString += (s1 + sOneCFive + "\n");
			
			String sTwoCFive = s2.getResponses()[4];
			//System.out.println(s2 + sTwoCFive);
			returnString += (s2 + sTwoCFive + "\n");
		}
		else
		{
			s2 = student.getRandomStudent(students);
		}
		
		return returnString;
		
	}
	
	public void sortStudents(Student s1, Student s2)
	{
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(s1);
		studentList.add(s2);

		Collections.sort(studentList, new Comparator<Student>(){

		    public int compare(Student s1, Student s2) 
		    {
		        String firstName1 = ((Student) s1).getFirstName();
		        String firstName2 = ((Student) s2).getFirstName();

		        int res = firstName1.compareTo(firstName2);
		        if (res != 0) 
		        {
		           return res;
		        } else 
		        {
		           String lastName1 = ((Student) s1).getLastName();
		           String lastName2 = ((Student) s2).getLastName();
		           return lastName1.compareTo(lastName2);
		        }
		    }
		});
	}
	
	
		
}
