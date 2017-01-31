import java.util.Random;

public class Responses 
{
	public Random random = new Random();
	
	public String hello = "";
	public String chatOne = "";
	public String chatTwo ="";
	public String chatThree = "";
	public String bye = "";
	
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
			case 2: return "Nice to meet you.";
			case 3: return "Tacos are the best!";
			case 4: return "Nope";
			case 5:	return "Where are you from?";
			case 6: return "I love puppies!";	
			case 7:	return "Yes";
			case 8:	return "What are you doing this weekend?";
			case 9:	return "How many siblings do you have?";
			case 10: return "I am confused. Are you?";
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
			case 1: return "Maybe"; 
			case 2: return "7";
			case 3: return "Canada";
			case 4: return "Time for grocery shopping";
			case 5:	return "I like Pizza.";
			case 6: return "No";	
			case 7:	return "Cheese is holy.";
			case 8:	return "Spain";
			case 9:	return "Going on a hike";
			case 10: return "Yup";
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
			case 1: return "How is the weather?"; 
			case 2: return "Winter is too cold";
			case 3: return "Na";
			case 4: return "Spinich is the best vegetable";
			case 5:	return "Wish it was Summer";
			case 6: return "Did you get a good grade on the exam?";	
			case 7:	return "Jibberish";
			case 8:	return "Yeah";
			case 9:	return "Its party time!";
			case 10: return "Time for bed";
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
		
}
