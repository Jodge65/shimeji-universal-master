package com.jodge.shimeji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import com.jodge.shimeji.Translator.Langue;


public class ShimejiConvert
{
	
	public static void convert(String fileIn, String fileOut)
	{
		convert(fileIn, fileOut, Langue.JAP.toString());
	}
	public static void convert(String fileIn, String fileOut, String langueIn)
	{
		convert(fileIn, fileOut, langueIn, Langue.EN.toString());
	}
	public static void convert(String fileIn, String fileOut, String langueIn, String langueOut)
	{
		File in= new File(fileIn);
		File out= new File(fileOut);
		
		if(!out.exists())
		{
			try
			{
				out.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		//file reading
		FileReader reader = null;
		try
		{
			reader = new FileReader(in);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		
		FileWriter writer = null;
		try
		{
			writer = new FileWriter(out);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		String line;
		try 
		{
			Translator dico = Translator.getInstance();
			List<String> dicoIn = dico.getLanguage(langueIn);
			List<String> dicoOut = dico.getLanguage(langueOut);
			int nbWords = dicoOut.size();
			
			BufferedReader bufIn = new BufferedReader(reader);
			while ((line = bufIn.readLine()) != null) 
			{
				for(int i = 0; i < nbWords; i++)
				{
					line = Normalizer.normalize(line, Normalizer.Form.NFKC);
					line = line.replace(dicoIn.get(i), dicoOut.get(i));
				}
				writer.write(line + "\n");
			} // end of while
			writer.close();
		}
		catch(Exception e){
		    e.printStackTrace();
		}
		
	}
}
