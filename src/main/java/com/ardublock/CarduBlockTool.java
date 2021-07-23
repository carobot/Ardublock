package com.ardublock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import processing.app.Editor;
import processing.app.EditorTab;
import processing.app.SketchFile;
import processing.app.tools.Tool;

import com.ardublock.core.Context;
import com.ardublock.ui.ArduBlockToolFrame;
import com.ardublock.ui.listener.OpenblocksFrameListener;

public class CarduBlockTool implements Tool, OpenblocksFrameListener
{
	static Editor editor;
	static ArduBlockToolFrame openblocksFrame;
	
	public void init(Editor editor) {
		if (CarduBlockTool.editor == null )
		{
			CarduBlockTool.editor = editor;
			CarduBlockTool.openblocksFrame = new ArduBlockToolFrame();
			CarduBlockTool.openblocksFrame.addListener(this);
			Context context = Context.getContext();
			String arduinoVersion = this.getArduinoVersion();
			context.setInArduino(true);
			context.setArduinoVersionString(arduinoVersion);
			context.setEditor(editor);
			System.out.println("Arduino Version: " + arduinoVersion);
		}
	}

	public void run() {
		try {
			CarduBlockTool.editor.toFront();
			CarduBlockTool.openblocksFrame.setVisible(true);
			CarduBlockTool.openblocksFrame.toFront();
		} catch (Exception e) {
			
		}
	}

	public String getMenuTitle() {
		return Context.APP_NAME;
	}

	public void didSave() {
		
	}
	
	public void didLoad() {
		
	}
	
	public void didSaveAs()
	{
		
	}
	
	public void didNew()
	{
		
	}
	
	//Added for a delete button
	public void didDelete() 
	{
		
	}
	
	public void didGenerate(String source) {
		//CarduBlockTool.editor.setText(source);
		java.lang.reflect.Method method;
		try {
			// pre Arduino 1.6.12
			Class ed = CarduBlockTool.editor.getClass();
			Class[] cArg = new Class[1];
			cArg[0] = String.class;
			method = ed.getMethod("setText", cArg);
			method.invoke(CarduBlockTool.editor, source);
		}
		catch (NoSuchMethodException e) {
			CarduBlockTool.editor.getCurrentTab().setText(source);
		} catch (IllegalAccessException e) {
			CarduBlockTool.editor.getCurrentTab().setText(source);
		} catch (SecurityException e) {
			CarduBlockTool.editor.getCurrentTab().setText(source);
		} catch (InvocationTargetException e) {
			CarduBlockTool.editor.getCurrentTab().setText(source);
		}
		CarduBlockTool.editor.handleExport(false);
	}
	
	private String getArduinoVersion()
	{
		Context context = Context.getContext();
		File versionFile = context.getArduinoFile("lib/version.txt");
		if (versionFile.exists())
		{
			try
			{
				InputStream is = new FileInputStream(versionFile);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String line = reader.readLine();
				reader.close();
				if (line == null)
				{
					return Context.ARDUINO_VERSION_UNKNOWN;
				}
				line = line.trim();
				if (line.length() == 0)
				{
					return Context.ARDUINO_VERSION_UNKNOWN;
				}
				return line;
				
			}
			catch (FileNotFoundException e)
			{
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
			catch (UnsupportedEncodingException e)
			{
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
		}
		else
		{
			return Context.ARDUINO_VERSION_UNKNOWN;
		}
		
	}
}
