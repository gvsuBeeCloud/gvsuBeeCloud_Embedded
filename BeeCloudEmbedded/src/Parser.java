import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Parser {
	
	public static ArrayList<Comparable> parseData(String inFromHive, int NumInputs) {

		ArrayList<Comparable> Data = new ArrayList<Comparable>(NumInputs);
		try
		{
			FileInputStream fstream = new FileInputStream("..\\CDM.txt");
			DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));

	        
	        String readIn;
	        while ((readIn = br.readLine()) != null) 
	        {
	        	String[] subs;
	        	subs=readIn.split("\t");
/////////////DEBUG/////////////
//	        	System.out.println(readIn);
//	        	System.out.println(subs[0]);
//	        	System.out.println(subs[1]);
//	        	System.out.println(subs[2]);
//	        	System.out.println(subs[3]);
/////////////DEBUG/////////////
	        	if(subs[1].compareTo("String")==0) {
	        		String tempS=inFromHive.substring(Integer.valueOf(subs[2]), (Integer.valueOf(subs[2])+Integer.valueOf(subs[3])));
	        		Data.add(tempS);
	        		if(tempS.compareTo("xxxxx")==0)
	        			break;
		        }
	        	else if(subs[1].compareTo("Double")==0) {
	        		Double tempD=new Double(inFromHive.substring(Integer.valueOf(subs[2]), (Integer.valueOf(subs[2])+Integer.valueOf(subs[3]))));
	        		Data.add(tempD);
	        	}
	        	else if(subs[1].compareTo("int")==0) {
	        		int tempI=Integer.valueOf(inFromHive.substring(Integer.valueOf(subs[2]), (Integer.valueOf(subs[2])+Integer.valueOf(subs[3]))));
	        		Data.add(tempI);
	        	}
	        	else {
	        		throw new UnknownIdentifier();
	        	}
	        	
	        }
/////////////DEBUG/////////////	        
//	        String ID = inFromHive.substring(0,5) ;
//			Double Lat = new Double(inFromHive.substring(5,12));
//			Double Long = new Double(inFromHive.substring(12,19));
//			int TempIn = Integer.valueOf(inFromHive.substring(19, 22));
//			int TempExt = Integer.valueOf(inFromHive.substring(22, 25));
//			Double Weight = new Double(inFromHive.substring(25, 31));
//			
//			Data.add(ID);
//			Data.add(Lat);
//			Data.add(Long);
//			Data.add(TempIn);
//			Data.add(TempExt);
//			Data.add(Weight);
/////////////DEBUG/////////////	        
		}
		catch (Exception e)
        {
                System.err.println("Error: " + e.getMessage());
        }
		
		return Data;
	}

	public static void main (String args[])
	{
		//testing input string
		String string = "abcde075.555075.555200095187.02";
		ArrayList<Comparable> DataMain = parseData(string,6);
		
		for(int i=0;i<DataMain.size();i++)
			System.out.println(DataMain.get(i));
	}
	
}

//***********************************************
//Custom exception class that descends from Java's Exception class.
class UnknownIdentifier extends Exception
{
	String mistake;

	//Default constructor - initializes instance variable to unknown
	public UnknownIdentifier()
	{
		super();// call superclass constructor
		mistake = "unknownIdentifier";
		System.err.println("Error: Unknown Identifier");
	}

	//Constructor receives some kind of message that is saved in an instance variable.
	public UnknownIdentifier(String err)
	{
		super(err);     // call super class constructor
		mistake = err;  // save message
		System.err.println("Error: Unknown Identifier");
	}

	//public method, callable by exception catcher. It returns the error message.
	public String getError()
	{
		return mistake;
	}
}
