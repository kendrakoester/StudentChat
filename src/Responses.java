import java.util.Random;

public class Responses 
{
	public Random random = new Random();
	
	public String hello;
	public String chatOne;
	public String chatTwo;
	public String chatThree;
	public String bye;
	
	public Responses(String hello, String chatOne, String chatTwo, String chatThree, String bye)
	{
		super();
		this.hello = hello;
		this.chatOne = chatOne;
		this.chatTwo = chatTwo;
		this.chatThree = chatThree;
		this.bye = bye;
	}

	public String getHello()
	{
		int  num = random.nextInt(10) + 1;
		switch(num)
		{
			case 1: return "Hi"; 
			case 2: return "Good Morning";
			case 3: return "Hello";
			case 4: return "Hola";
			case 5:	return "Hey";
			case 6: return "Whats up??";	
			case 7:	return "Sup?";
			case 8:	return "Howdy";
			case 9:	return "Yo!";
			case 10: return "Greetings";
		}
		return hello;
	}

	public void setHello(String hello) 
	{
		this.hello = hello;
	}

	public String getChatOne() 
	{
		int  num = random.nextInt(10) + 1;
		switch(num)
		{
			case 1: return "How is your homework coming?"; 
			case 2: return "";
			case 3: return "Tacos are the best!";
			case 4: return "";
			case 5:	return "";
			case 6: return "I love puppies!";	
			case 7:	return "";
			case 8:	return "";
			case 9:	return "";
			case 10: return "";
		}
		return chatOne;
	}

	public void setChatOne(String chatOne) 
	{
		this.chatOne = chatOne;
	}

	public String getChatTwo() 
	{
		int  num = random.nextInt(10) + 1;
		switch(num)
		{
			case 1: return "How is your homework coming?"; 
			case 2: return "";
			case 3: return "Tacos are the best!";
			case 4: return "";
			case 5:	return "";
			case 6: return "I love puppies!";	
			case 7:	return "";
			case 8:	return "";
			case 9:	return "";
			case 10: return "";
		}
		return chatTwo;
	}

	public void setChatTwo(String chatTwo) 
	{
		this.chatTwo = chatTwo;
	}

	public String getChatThree() 
	{
		
		int  num = random.nextInt(10) + 1;
		switch(num)
		{
			case 1: return "How is your homework coming?"; 
			case 2: return "";
			case 3: return "Tacos are the best!";
			case 4: return "";
			case 5:	return "";
			case 6: return "I love puppies!";	
			case 7:	return "";
			case 8:	return "";
			case 9:	return "";
			case 10: return "";
		}return chatThree;
	}

	public void setChatThree(String chatThree) 
	{
		this.chatThree = chatThree;
	}

	public String getBye() 
	{
		int  num = random.nextInt(10) + 1;
		switch(num)
		{
			case 1: return "See you in a while crocadile"; 
			case 2: return "Good night";
			case 3: return "Bye bye";
			case 4: return "See ya";
			case 5:	return "Ta ta for now";
			case 6: return "Bye";	
			case 7:	return "Too Da Loo";
			case 8:	return "Later Gator";
			case 9:	return "Dont let the door hit you on the way out";
			case 10: return "Smell ya later";
		}
		return bye;
	}

	public void setBye(String bye) 
	{
		this.bye = bye;
	}
	
//	public int[] getResponses()
//	{
//		int  one = random.nextInt(5) + 1;
//		int two = random.nextInt(10) + 6;
//		int three = random.nextInt(15) + 11;
//		int four = random.nextInt(20) + 16;
//		int five = random.nextInt(25) + 21;
//		
//		int[] responses = {one, two, three, four, five};
//		return responses;
//	}
//	
//	public String GetResponse(int responseType)
//	{
//		switch(responseType)
//		{
//			case 1: return "Hi"; //case 1 - 5 is all "Hello's"
//			case 2: return "Good Morning";
//			case 3: return "Hello";
//			case 4: return "Hola";
//			case 5:	return "Hey";
//			case 6: return "Whats up??";	//cases 6 - 15 are filler 
//			case 7:	return "How are you??";
//			case 8:	return "I am SO bored!";
//			case 9:	return "Im ready for Friday";
//			case 10: return "Is it party time?";
//			case 11:	return "What did you have for lunch?";
//			case 12:	return "Did you get a good grade on the exam?";
//			case 13:	return "Yeas";
//			case 14:	return "Nah";
//			case 15:	return "Maybe";
//			case 16:	return "Later Gator";//cases 16 - 20 are "Bye"
//			case 17:	return "See you in awhile Crocadile";
//			case 18:	return "Bye";
//			case 19:	return "Goodnight";
//			case 20:	return "Too Da Loo";
//		}
//		return "You have an ID ten T error. Please try again.";
//
//	}
}
