package com.ardublock.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ardublock.core.Context;
import com.ardublock.ui.OpenblocksFrame;

public class DeleteButtonListener implements ActionListener
{
	private OpenblocksFrame parentFrame;
	
	public DeleteButtonListener(OpenblocksFrame frame)
	{
		Context.getContext();
		
		this.parentFrame = frame;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		parentFrame.doDelete();
	}

}