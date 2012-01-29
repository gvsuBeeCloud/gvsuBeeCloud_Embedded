import java.util.ArrayList;

public class Parser {
	
	//public static void parse(String inFromHive) {
	public static void main(String args[]) {
		String inFromHive = "abcde075.555075.555200095187.02";
		int NumInputs = 6;
		ArrayList Data = new ArrayList(NumInputs);
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
		
		for(int i=0;i<NumInputs;i++)
			System.out.println(Data.get(i));
		
		//return Data;
	}

}