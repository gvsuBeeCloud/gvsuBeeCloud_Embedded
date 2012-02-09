import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Parser {
	
	public static ArrayList<Comparable> parseData(String File, int NumInputs) {

		ArrayList<Comparable> Data = new ArrayList<Comparable>(NumInputs);
		boolean error = false;
		String inFromHive = null;
		try
		{
			FileInputStream fstream = new FileInputStream(File);
			DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        inFromHive = br.readLine();
		}
		catch (Exception e)
        {
			error=true;
//            System.err.println("Error: " + e.getMessage());
        }
		try
		{
			FileInputStream fstream = new FileInputStream("..\\CDM.txt");
			DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String[] hiveInfo = inFromHive.split(",");
	        
	        String readIn;
	        while ((readIn = br.readLine()) != null) 
	        {
	        	String[] subs;
	        	subs=readIn.split("\t");
	        	
	        	if(subs[1].compareTo("String")==0) {
	        		String tempS=hiveInfo[Integer.valueOf(subs[2])];
	        		Data.add(tempS);
	        		if(tempS.compareTo("xxxxx")==0)
	        			break;
		        }
	        	else if(subs[1].compareTo("Double")==0) {
	        		Double tempD=new Double(hiveInfo[Integer.valueOf(subs[2])]);
	        		DecimalFormat df = new DecimalFormat("#.###");
	                String tempDformat = df.format(tempD);
	                tempD = new Double(tempDformat);
	                //getting high and low value
                	int Low = Integer.valueOf(subs[3]);
                	int High = Integer.valueOf(subs[4]);
                	if(tempD<=Low || tempD>=High)
                		error=true;
	        		Data.add(tempD);
	        	}
	        	else if(subs[1].compareTo("int")==0) {
	        		int tempI=Integer.valueOf(hiveInfo[Integer.valueOf(subs[2])]);
	        		//getting high and low value
	        		int Low = Integer.valueOf(subs[3]);
                	int High = Integer.valueOf(subs[4]);
                	if(tempI<=Low || tempI>=High)
                		error=true;
	        		Data.add(tempI);
	        	}
	        	else {
	        		throw new UnknownIdentifier();
	        	}
	        	
	        }        
		}
		catch (Exception e)
        {
			error=true;
//            System.err.println("Error: " + e.getMessage());
        }
		if(error)
		{
			Data.clear();
			Data.add("xxxxx");
			return Data;
		}
		else
			return Data;
	}

	public static void main (String args[])
	{
		//testing input string
		String string = "..\\FromHive1.csv";
		ArrayList<Comparable> DataMain = parseData(string,6);
		
		for(int i=0;i<DataMain.size();i++)
			System.out.println(DataMain.get(i));
		
		//second test, bad data
		System.out.println("\nSecond Test");
		string = "..\\FromHive2.csv";
		DataMain = parseData(string,6);
		
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
