import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class remoteServerSend 
{
        public static void main(String args[])
        {       
                        
                //Step 1 - Connecting to the appengine URL:
                try 
                {
                        URL homeURL = new URL("http://beecloudproject.appspot.com");
                        URLConnection mainURLConnection = homeURL.openConnection();
                        mainURLConnection.connect();

                }
                catch (MalformedURLException e) 
                { 
                        // new URL() failed
                        print("New URL() Failed");
                }        
                catch (IOException e) 
                {
                        // openConnection() failed
                        print("openConnection() Failed");
                }
                
                //Step 2 - Build the Query String
                        try
                        {
                                FileInputStream fstream = new FileInputStream("..\\parsedInfo.txt");
                                DataInputStream in = new DataInputStream(fstream);
                                BufferedReader br = new BufferedReader(new InputStreamReader(in));
  
                                String readIn;
                                String createURL;
                                String baseCreateURL = "";
                                while ((readIn = br.readLine()) != null)   
                                {
                                        baseCreateURL = "http://beecloudproject.appspot.com/updateHive?";
                                        createURL = readIn.replaceAll("PARSEDDATAKEY=", "");
                                        createURL = createURL.replaceAll("PARSEDDATAVALUE", "");
                                        createURL = createURL.replaceAll(",","&");
                                        baseCreateURL = baseCreateURL + createURL;
                                        
                                        HttpClient client = new HttpClient();
                                        HttpMethod method = new GetMethod(baseCreateURL);
                                        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
                                }
                                
                                in.close();             
                        }

                        catch (Exception e)
                        {
                                System.err.println("Error: " + e.getMessage());
                        }       
        }
        
        public static void print(String string) {
            System.out.println(string);
        }
}