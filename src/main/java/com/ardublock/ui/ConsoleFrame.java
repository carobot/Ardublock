package com.ardublock.ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ConsoleFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4057010070646255290L;

	public ConsoleFrame()
	{	
		this.setLayout(new FlowLayout());
		this.setBackground(Color.WHITE);
		JButton generateButton = new JButton("generate code");
		//generateButton.addActionListener(new GenerateCodeButtonListener(this));
		
		JButton saveButton = new JButton("save");
		//saveButton.addActionListener(new SaveButtonListener(this));
		
		JButton openButton = new JButton("open");
		//openButton.addActionListener(new OpenButtonListener(this));
		
		JButton deleteButton = new JButton("delete");
		
		this.add(generateButton);
		this.add(saveButton);
		this.add(openButton);
		this.add(deleteButton);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 100);
	}
}
