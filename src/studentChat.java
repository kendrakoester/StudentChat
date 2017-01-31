import java.util.HashSet;
import java.util.Set;

public class studentChat 
{

	public static void main(String[] args) 
	{
		
		Responses response	 = new Responses("", "", "", "", "");
		Student student = new Student("", "", 0, response);
		Conversation conversation = new Conversation();
		
		Set<Student> students = new HashSet<Student>();
		
		students = student.StudentHashSet(student.getArray());
		
		Student s1 = student.getRandomStudent(students);
		Student s2 = student.getRandomStudent(students);
		
		if(s1 != s2)
		{
			String sOneCOne = s1.getResponses()[0];
			System.out.println(s1 + sOneCOne);
			
			String sTwoCOne = s2.getResponses()[0];
			System.out.println(s2 + sTwoCOne);
			
			String sOneCTwo = s1.getResponses()[1];
			System.out.println(s1 + sOneCTwo);
			
			String sTwoCTwo = s2.getResponses()[1];
			System.out.println(s2 + sTwoCTwo);
			
			String sOneCThree = s1.getResponses()[2];
			System.out.println(s1 + sOneCThree);
			
			String sTwoCThree = s2.getResponses()[2];
			System.out.println(s2 + sTwoCThree);
			
			String sOneCFour = s1.getResponses()[3];
			System.out.println(s1 + sOneCFour);
			
			String sTwoCFour = s2.getResponses()[3];
			System.out.println(s2 + sTwoCFour);
			
			String sOneCFive = s1.getResponses()[4];
			System.out.println(s1 + sOneCFive);
			
			String sTwoCFive = s2.getResponses()[4];
			System.out.println(s2 + sTwoCFive);
		}
		else
		{
			s2 = student.getRandomStudent(students);
		}

//		String sOneCOne = s1.getResponses()[0];
//		String sOneCTwo = s1.getResponses()[1];
//		String sOneCThree = s1.getResponses()[2];
//		String sOneCFour = s1.getResponses()[3];
//		String sOneCFive = s1.getResponses()[4];
//		
//		String sTwoCOne = s2.getResponses()[0];
//		String sTwoCTwo = s2.getResponses()[1];
//		String sTwoCThree = s2.getResponses()[2];
//		String sTwoCFour = s2.getResponses()[3];
//		String sTwoCFive = s2.getResponses()[4];
		
		
		   
	}
	
	

}
