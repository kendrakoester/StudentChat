import java.util.Random;

public class Responses 
{
	public Random random = new Random();
	
	public int[] getResponses()
	{
		int  one = random.nextInt(5) + 1;
		int two = random.nextInt(10) + 6;
		int three = random.nextInt(15) + 11;
		int four = random.nextInt(20) + 16;
		int five = random.nextInt(25) + 21;
		
		int[] responses = {one, two, three, four, five};
		return responses;
	}
	
	public String GetResponse(int responseType)
	{
		switch(responseType)
		{
			case 1: return "Hi"; //case 1 - 5 is all "Hello's"
			case 2: return "Good Morning";
			case 3: return "Hello";
			case 4: return "Hola";
			case 5:	return "Hey";
			case 6: return "Whats up??";	//cases 6 - 15 are filler 
			case 7:	return "How are you??";
			case 8:	return "I am SO bored!";
			case 9:	return "Im ready for Friday";
			case 10: return "Is it party time?";
			case 11:	return "What did you have for lunch?";
			case 12:	return "Did you get a good grade on the exam?";
			case 13:	return "Yeas";
			case 14:	return "Nah";
			case 15:	return "Maybe";
			case 16:	return "Later Gator";//cases 16 - 20 are "Bye"
			case 17:	return "See you in awhile Crocadile";
			case 18:	return "Bye";
			case 19:	return "Goodnight";
			case 20:	return "Too Da Loo";
		}
		return "You have an ID ten T error. Please try again.";

	}
}
