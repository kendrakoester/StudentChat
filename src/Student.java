import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Student 
{
	public String firstName;
	public String lastName;
	public int score;
	public String convoPiece;
	
	Responses responses = new Responses(convoPiece, convoPiece, convoPiece, convoPiece, convoPiece);
	
	public Student(String firstName, String lastName, int score, Responses responses) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
		this.responses = responses;
	}
	
	
	public String getFirstName() 
	{
		return firstName;
	}


	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}


	public String getLastName() 
	{
		return lastName;
	}


	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}


	public int getScore() 
	{
		return score;
	}


	public void setScore(int score) 
	{
		this.score = score;
	}
	
	public String[] getResponses()
	{
		String[] responseArray = {
		responses.getHello(),
		responses.getChatOne(),
		responses.getChatTwo(),
		responses.getChatThree(),
		responses.getBye()
		};
		return responseArray;
	}
	
	public void setResponses()
	{
		this.responses = responses;
	}
	
	public static void sortStudents(ArrayList<Student> studentList) 
	 {
		    for (int i = 0; i < studentList.size(); i++)
		    {
		        for (int j = 0; j < studentList.size(); j++) 
		        {
		            Collections.sort(studentList, new Comparator<Student>(){
						public int compare(Student s1, Student s2) {
							Student student1 = (Student) s1;
		                    Student student2 = (Student) s2;
		                    int res =  student1.getLastName().compareToIgnoreCase(student2.getLastName());
		                    if (res != 0)
		                    {
		                        return res;
		                    }
		                    return student1.getFirstName().compareToIgnoreCase(student2.getFirstName());
						}
		            });
		        }

		    }
		}

    @Override
    public String toString() 
    {
        return "firstName: " + firstName + " lastName: " + lastName + " Responses: " + responses + "\n";
    }


//	public Student[] getArray()
//	{
//		Student studentOne = new Student("Nathan", "Borup", 1);
//		Student studentTwo = new Student("Ethan", "Brown", 2);
//		Student studentThree = new Student("Michael", "Cullimore", 3);
//		Student studentFour = new Student("Kendra", "Koester", 4);
//		Student studentFive = new Student("Cody", "May", 5);
//		Student studentSix = new Student("Brie", "Miller", 6);
//		Student studentSeven = new Student("Rizwan", "Mohammed", 7);
//		Student studentEight = new Student("Lauren", "Ribiro", 8);
//		Student studentNine = new Student("Tyler", "Toponce", 9);
//		
//		Student[] studentArray = {studentOne, studentTwo, studentThree, studentFour, studentFive, studentSix, studentSeven, studentEight, studentNine};
//		
//		//System.out.println(Arrays.toString(studentArray));
//		
//		return studentArray;
//	}
	
//	public Set<Student> StudentHashSet(Student[] studentArray)
//	{
//		Set<Student> students = new HashSet<Student>();
//		for(Student student: studentArray)
//		{
//			students.add(student);	
//		}
//		
//		return students;
//	}
	
//	public ArrayList<Student> StudentHashSet(Student[] studentArray)
//	{
//		ArrayList<Student> students = new ArrayList<Student>();
//		for(Student student: studentArray)
//		{
//			students.add(student);	
//		}
//		
//		return students;
//	}
	

}
