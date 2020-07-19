import java.util.*;
import java.io.*; 
import java.util.regex.*;  

//UNICODE 7 OR 8
class func
{
	static void StandardiseStatement(String input_filename) throws FileNotFoundException
	{
		//StringBuffer answer;
		StringBuffer answer= new StringBuffer("Date,Transaction Description,Debit,Credit,Currency,CardName,Transaction,Location\n");
	
		try
		{
		
		FileOutputStream fos=new FileOutputStream("op1.csv", true);  // true for append mode  
		  //writes bytes into file  

		//		parsing a CSV file into Scanner class constructor  
		Scanner sc = new Scanner(new File(input_filename));  
		sc.useDelimiter("\n"); 
		StringBuffer date, description,currency,card_name, transaction, location;
		transaction = new StringBuffer("Domestic");
		card_name= new StringBuffer( "Rahul");
		
		long credit, debit;
		while (sc.hasNext())  //returns a boolean value  
		{  
			String row= sc.next();

			String[] parts=row.split(",");
			//System.out.println(parts[2]+"-----------");
			//System.out.println("d:"+parts[0]+" des"+parts[1]+" amt "+parts[2].toString()+"|||||");
			if((!parts[0].isEmpty()) && (!parts[1].isEmpty()) && (!parts[2].isEmpty()))
			{
							//System.out.println("d:"+parts[0]+" des"+parts[1]+" amt "+parts[2]);
				String regex = "^(3[01]|[12][0-9]|0[1-9])\\-(1[0-2]|0[1-9])\\-[0-9]{4}$";
	 			 //Creating a pattern object
	  			Pattern pattern = Pattern.compile(regex);
	  			//Matching the compiled pattern in the String
	  			Matcher matcher = pattern.matcher(parts[0]);
	  			boolean bool = matcher.matches();
	  			if(bool)
				{
					answer = answer.append( parts[0]);
					answer.append(",");
					answer.append(parts[1]);
					answer.append(",");
					 //b=parts[0].getBytes();
					//System.out.println("date:"+ parts[0] + "ans:: "+answer);
					if(!parts[2].contains("cr"))
					{
						//answer =answer.append( parts[2]);
						String amount= parts[2].replaceAll("\\D+","");

						answer =answer.append(amount);
						answer.append( ",0,");
					}
					
					else
					{
						answer =answer.append("0,");
						String amount= parts[2].replaceAll("\\D+","");

						answer =answer.append(amount);
						answer.append(",");
					}

					if(!transaction.equals("International"))
					{
						answer.append("INR,");
						answer.append( card_name);
						answer.append(",");
						answer.append(transaction);
						answer.append(", XLCX\n");
					}
					else{
						answer= answer.append("XXCURRENCYXX,");
						answer.append( card_name);
						answer.append(",");
						answer.append(transaction);
						answer.append(", XXLOCXX\n");
					}

				}


			}

//			System.out.println(answer);
		//System.out.println(sc.next());  //find and returns the next complete token from this scanner  
		}   
					sc.close();  //closes the scanner  

System.out.println("final    \n"+answer);
			byte[] b= answer.toString().getBytes();
			//String str=sc.nextLine()+"\n";      //str stores the string which we have entered  
			//byte[] b= str.getBytes();       //converts string into bytes  
				fos.write(b); 
			fos.close();            //close the file


		
			
		} //try block closed

	

		catch(IOException e){
		System.out.println("error creating file\n"+e);
		}
	}
  
  
	 
	 public static void main(String[] args) throws FileNotFoundException {
		StandardiseStatement("HDFC-Input-Case1.csv");
		
	}
}