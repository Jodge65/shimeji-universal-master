package com.jodge.shimeji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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
		
		if(out.exists())
		{
			out.delete();
		}
		
		try
		{
			out.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		//file reading
		InputStreamReader reader = null;
		try
		{
			reader = new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		
		OutputStreamWriter writer = null;
		try
		{
			writer = new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		String line = "";
		try 
		{
			Translator dico = Translator.getInstance();
			List<String> dicoIn = dico.getLanguage(langueIn);
			List<String> dicoOut = dico.getLanguage(langueOut);
			int nbWords = dicoOut.size();
			
			BufferedReader bufIn = new BufferedReader(reader);
			while ((line = bufIn.readLine()) != null) 
			{
				line = Normalizer.normalize(line, Normalizer.Form.NFKC);
				for(int i = 0; i < nbWords; i++)
				{
					line = line.replace(dicoIn.get(i), dicoOut.get(i));
				}
				writer.write(line + "\n");
			} // end of while
			writer.close();
			reader.close();
		}
		catch(Exception e){
		    e.printStackTrace();
		}
		
	}
}
