package com.jodge.shimeji.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jodge.shimeji.ShimejiConvert;
import com.jodge.shimeji.Translator.Langue;

public class FileHelper
{
	public static String imgFolder = "img";
	public static String confFolder = "conf";
	
	public static String getActionFile(String imageSet)
	{
		return getActionFile(imageSet, false);
	}
	public static String getActionFile(String imageSet, boolean ignoreTrad)
	{
		Map<String, String> alternativeFile = new HashMap<String, String>();
		alternativeFile.put(Langue.EN.toString(), "actions.xml");
		alternativeFile.put(Langue.JAP.toString(), "動作.xml");
		return getFile("actions.xml", imageSet, alternativeFile, ignoreTrad);
	}

	public static String getBehaviorFile(String imageSet)
	{
		return getBehaviorFile(imageSet, false);
	}
	public static String getBehaviorFile(String imageSet, boolean ignoreTrad)
	{
		Map<String, String> alternativeFile = new HashMap<String, String>();
		alternativeFile.put(Langue.EN.toString(), "behaviors.xml");
		alternativeFile.put(Langue.JAP.toString(), "行動.xml");
		return getFile("behaviors.xml", imageSet, alternativeFile, ignoreTrad);
	}
	

	public static String getFile(String fileName, String imageSet, Map<String, String> alternativeFile)
	{
		return getFile(fileName, imageSet, alternativeFile);
	}
	public static String getFile(String fileName, String imageSet, Map<String, String> alternativeFile, boolean ignoreTrad)
	{
		String resultFile = "./" + confFolder + "/" + fileName;
		if (new File("./" + confFolder + "/" + imageSet + "/" + fileName).exists())
		{
			resultFile = "./" + confFolder + "/" + imageSet + "/" + fileName;
		}
		else if (new File("./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + fileName).exists())
		{
			resultFile = "./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + fileName;
		}
		else
		{
			for(Entry<String, String> entry : alternativeFile.entrySet())
			{
				
				if (new File("./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + entry.getKey() + "_" + fileName).exists()) // jap_action.xml
				{
					if(ignoreTrad)
					{
						resultFile = "./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + entry.getValue(); // may not exist, but don't care, only for display
					}
					else
					{
						resultFile = "./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + entry.getKey() + "_" + fileName;
					}
					break;
				}
				else if (new File("./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + entry.getValue()).exists())
				{
					ShimejiConvert.convert(	"./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + entry.getValue(), 
											"./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + entry.getKey() + "_" + fileName,
											entry.getKey(), 
											Langue.EN.toString());
					if(ignoreTrad)
					{
						resultFile = "./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + entry.getValue();
					}
					else
					{
						resultFile = "./" + imgFolder + "/" + imageSet + "/" + confFolder  + "/" + entry.getKey() + "_" + fileName;
					}
					break;
				}
			}
		}

		return resultFile;
	}
}
