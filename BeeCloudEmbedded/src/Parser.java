import java.util.ArrayList;

public class Parser {
	
	//public static void parse(String inFromHive) {
	public static ArrayList<Comparable> parseData(String inFromHive, int NumInputs) {
		ArrayList<Comparable> Data = new ArrayList<Comparable>(NumInputs);
		String ID = inFromHive.substring(0,5) ;
		Double Lat = new Double(inFromHive.substring(5,12));
		Double Long = new Double(inFromHive.substring(12,19));
		int TempIn = Integer.valueOf(inFromHive.substring(19, 22));
		int TempExt = Integer.valueOf(inFromHive.substring(22, 25));
		Double Weight = new Double(inFromHive.substring(25, 31));
		
		Data.add(ID);
		Data.add(Lat);
		Data.add(Long);
		Data.add(TempIn);
		Data.add(TempExt);
		Data.add(Weight);
		
		return Data;
	}

	public static void main (String args[])
	{
		//testing input string
		String string = "abcde075.555075.555200095187.02";
		ArrayList<Comparable> DataMain = parseData(string,6);
		
		for(int i=0;i<6;i++)
			System.out.println(DataMain.get(i));
	}
}
