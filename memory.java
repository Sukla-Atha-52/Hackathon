package JSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class memory 
{

	public static void main(String[] args)  
	{
		try {
			FileReader fr=new FileReader("Memory.txt");
			BufferedReader br=new BufferedReader(fr);
			float avg=0;
			float max=0;
			float total=0;
			int i=0;
			String line;

			JSONObject values=new JSONObject();
			JSONObject value=new JSONObject();
			JSONObject avgmemory=new JSONObject();
			JSONObject maxmemory=new JSONObject();
			JSONObject userName=new JSONObject();
			JSONObject fin=new JSONObject();
			String done="";
			String memory="";
			int count=0;
			while((line=br.readLine())!=null)
			{
				if(count%2!=0)
				{
					memory=line;
					done=memory.trim();
					String splitvalues[]=done.split("\\s+");
					float fval=Float.parseFloat(splitvalues[1]);
					fval=fval/1024;
					if(max<fval)
					{
						max=fval;
					}
					total+=fval;
					String s=i+"s";
					values.put(s, (String.format("%.2f",fval)));
					i++;
				}
				count++;

			}
			avg=total/count;

			fin.put("AverageMemory(MB)", (String.format("%.2f",avg)));
			fin.put("MaxMemory(MB)", (String.format("%.2f",max)));
			fin.put("value", values);
			fin.put("Usecasename", "HomePage");
			System.out.println(fin);
			FileWriter file=new FileWriter("Memory.json");
			file.write(fin.toJSONString());
			file.close();
			fr.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}


	}

}