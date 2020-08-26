package JSON;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class battery {

	public static void main(String[] args) {
		try {
			FileReader fr=new FileReader("Battery.txt");
			BufferedReader br=new BufferedReader(fr);
			String line="";
			JSONObject fg=new JSONObject();
			String Foregroundactivities="";
			String uid="";
			String fc="";
			String per="";
			while((line=br.readLine())!=null)
			{
				//System.out.println(line);
				
				if(line.contains("Foreground activities:"))
				{
					Foregroundactivities=line;
					fc=Foregroundactivities.trim();
					String a[]=fc.split("\\s+");
					fc=a[2]+" "+a[3]+" "+a[4]+" "+a[5]+" "+a[6]+" "+a[7];
					fg.put("Foreground_time", fc);
				}
				else if(line.contains("Uid u0a202"))
				{
					uid=line;
					per=uid.trim();
					String array[]=per.split("\\s+");
					per=array[2];
					fg.put("Battery_drain", per);
					float bp=Float.parseFloat(per);
					float battery=bp/1000;
					fg.put("Battery_percentage", (String.format("%.3f",battery)));
				}
			}
			System.out.println(fg);
			FileWriter file=new FileWriter("battery.json");
			file.write(fg.toJSONString());
			file.close();
			fr.close();
	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}