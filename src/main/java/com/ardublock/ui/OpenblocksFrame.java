package com.ardublock.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.mit.blocks.renderable.RenderableBlock;

import com.ardublock.core.Context;
import com.ardublock.ui.listener.ArdublockWorkspaceListener;
import com.ardublock.ui.listener.DeleteButtonListener;
import com.ardublock.ui.listener.GenerateCodeButtonListener;
import com.ardublock.ui.listener.NewButtonListener;
import com.ardublock.ui.listener.OpenButtonListener;
import com.ardublock.ui.listener.OpenblocksFrameListener;
import com.ardublock.ui.listener.SaveAsButtonListener;
import com.ardublock.ui.listener.SaveButtonListener;

import edu.mit.blocks.controller.WorkspaceController;
import edu.mit.blocks.workspace.Workspace; 


public class OpenblocksFrame extends JFrame
{
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 2841155965906223806L;

	private Context context;
	private JFileChooser fileChooser;
	private FileFilter ffilter;
	
	private ResourceBundle uiMessageBundle;
	
	public void addListener(OpenblocksFrameListener ofl)
	{
		context.registerOpenblocksFrameListener(ofl);
	}
	
	public String makeFrameTitle()
	{
		String title = Context.APP_NAME + " " + context.getSaveFileName();
		if (context.isWorkspaceChanged())
		{
			title = title + " *";
		}
		return title;
		
	}
	
	public OpenblocksFrame()
	{	
		
		//Makes sure the size fits to any screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		context = Context.getContext();
		this.setTitle(makeFrameTitle());
		this.setSize(new Dimension(screenSize.width - 400, screenSize.height - 40));
		this.setLayout(new BorderLayout());
		//put the frame to the center of screen
		this.setLocationRelativeTo(null);
	
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
		
		fileChooser = new JFileChooser();
		ffilter = new FileNameExtensionFilter(uiMessageBundle.getString("ardublock.file.suffix"), "abp");
		fileChooser.setFileFilter(ffilter);
		fileChooser.addChoosableFileFilter(ffilter);
		
		initOpenBlocks();
	}
	
	private void initOpenBlocks()
	{
		final Context context = Context.getContext();
		
		/*
		WorkspaceController workspaceController = context.getWorkspaceController();
		JComponent workspaceComponent = workspaceController.getWorkspacePanel();
		*/
		
		final Workspace workspace = context.getWorkspace();
		
		// WTF I can't add worksapcelistener by workspace contrller
		workspace.addWorkspaceListener(new ArdublockWorkspaceListener(this));
		
		//Code for the top panel 
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.decode("#ff6666"));
		
		buttons.setLayout(new FlowLayout());
		final JButton newButton = new JButton(uiMessageBundle.getString("ardublock.ui.new"));
		newButton.addActionListener(new NewButtonListener(this));
		newButton.setBackground(Color.decode("#FFFFFF"));
		newButton.setBorder(new RoundedBoarder(5));
		newButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	newButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        newButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		
		final JButton saveButton = new JButton(uiMessageBundle.getString("ardublock.ui.save"));
		saveButton.addActionListener(new SaveButtonListener(this));
		saveButton.setBackground(Color.decode("#FFFFFF"));
		saveButton.setBorder(new RoundedBoarder(5));
		saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	saveButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        saveButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		
		final JButton saveAsButton = new JButton(uiMessageBundle.getString("ardublock.ui.saveAs"));
		saveAsButton.addActionListener(new SaveAsButtonListener(this)); 
		saveAsButton.setBackground(Color.decode("#FFFFFF"));
		saveAsButton.setBorder(new RoundedBoarder(5));
		saveAsButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	saveAsButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        saveAsButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		
		final JButton openButton = new JButton(uiMessageBundle.getString("ardublock.ui.load"));
		openButton.addActionListener(new OpenButtonListener(this));
		openButton.setBackground(Color.decode("#FFFFFF"));
		openButton.setBorder(new RoundedBoarder(5));
		openButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	openButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        openButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		
		final JButton generateButton = new JButton(uiMessageBundle.getString("ardublock.ui.upload"));
		generateButton.addActionListener(new GenerateCodeButtonListener(this, context));
		generateButton.setBackground(Color.decode("#FFFFFF"));
		generateButton.setBorder(new RoundedBoarder(5));
		generateButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	generateButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        generateButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		
		final JButton deleteButton = new JButton("Reset");
		deleteButton.addActionListener(new DeleteButtonListener(this));
		deleteButton.setBackground(Color.decode("#FFFFFF"));
		deleteButton.setBorder(new RoundedBoarder(5));
		deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	deleteButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        deleteButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		
		final JButton serialMonitorButton = new JButton(uiMessageBundle.getString("ardublock.ui.serialMonitor"));
		serialMonitorButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				context.getEditor().handleSerial();
			}
		});
		serialMonitorButton.setBackground(Color.decode("#FFFFFF"));
		serialMonitorButton.setBorder(new RoundedBoarder(5));
		serialMonitorButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	serialMonitorButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        serialMonitorButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		
		final JButton saveImageButton = new JButton(uiMessageBundle.getString("ardublock.ui.saveImage"));
		saveImageButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				Dimension size = workspace.getCanvasSize();
				System.out.println("size: " + size);
				BufferedImage bi = new BufferedImage(2560, 2560, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = (Graphics2D)bi.createGraphics();
				double theScaleFactor = (300d/72d);  
				g.scale(theScaleFactor,theScaleFactor);
				
				workspace.getBlockCanvas().getPageAt(0).getJComponent().paint(g);
				try{
					final JFileChooser fc = new JFileChooser();
					fc.setSelectedFile(new File("ardublock.png"));
					int returnVal = fc.showSaveDialog(workspace.getBlockCanvas().getJComponent());
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
						ImageIO.write(bi,"png",file);
			        }
				} catch (Exception e1) {
					
				} finally {
					g.dispose();
				}
			}
		});
		saveImageButton.setBackground(Color.decode("#FFFFFF"));
		saveImageButton.setBorder(new RoundedBoarder(5));
		saveImageButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	saveImageButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        saveImageButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		

		buttons.add(newButton);
		buttons.add(saveButton);
		buttons.add(saveAsButton);
		buttons.add(openButton);
		buttons.add(generateButton);
		//added delete button
		buttons.add(deleteButton);
		buttons.add(serialMonitorButton);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.decode("#ff6666"));
		
		final JButton websiteButton = new JButton(uiMessageBundle.getString("ardublock.ui.website"));
		websiteButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
			    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    URL url;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
						url = new URL("https://www.carobot.org/products/carobot-swisscheese/");
			            desktop.browse(url.toURI());
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        }
			    }
			}
		});
		websiteButton.setBackground(Color.decode("#FFFFFF"));
		websiteButton.setBorder(new RoundedBoarder(5));
		websiteButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	websiteButton.setBackground(Color.decode("#90EE90"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        websiteButton.setBackground(Color.decode("#FFFFFF"));
		    }
		});
		
		//Update version everytime it is changed 
		JLabel versionLabel = new JLabel("Carobot Version: 1.0");
		
		bottomPanel.add(saveImageButton);
		bottomPanel.add(websiteButton);
		bottomPanel.add(versionLabel);
		
		
		
		this.add(buttons, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(workspace, BorderLayout.CENTER);
	}
	
	public void doOpenArduBlockFile()
	{
		if (context.isWorkspaceChanged())
		{
			int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.content.open_unsaved"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
			if (optionValue == JOptionPane.YES_OPTION)
			{
				doSaveArduBlockFile();
				this.loadFile();
			}
			else
			{
				if (optionValue == JOptionPane.NO_OPTION)
				{
					this.loadFile();
				}
			}
		}
		else
		{
			this.loadFile();
		}
		this.setTitle(makeFrameTitle());
	}
	
	private void loadFile()
	{
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File savedFile = fileChooser.getSelectedFile();
			if (!savedFile.exists())
			{
				JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.file_not_found"), uiMessageBundle.getString("message.title.error"), JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, null, JOptionPane.OK_OPTION);
				return ;
			}
			
			try
			{
				this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				context.loadArduBlockFile(savedFile);
				context.setWorkspaceChanged(false);
			}
			catch (IOException e)
			{
				JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.file_not_found"), uiMessageBundle.getString("message.title.error"), JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, null, JOptionPane.OK_OPTION);
				e.printStackTrace();
			}
			finally
			{
				this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
	public void doSaveArduBlockFile()
	{
		if (!context.isWorkspaceChanged())
		{
			return ;
		}
		
		String saveString = getArduBlockString();
		
		if (context.getSaveFilePath() == null)
		{
			chooseFileAndSave(saveString);
		}
		else
		{
			File saveFile = new File(context.getSaveFilePath());
			writeFileAndUpdateFrame(saveString, saveFile);
		}
	}

	
	public void doSaveAsArduBlockFile()
	{
		if (context.isWorkspaceEmpty())
		{
			return ;
		}
		
		String saveString = getArduBlockString();
		
		chooseFileAndSave(saveString);
		
	}
	
	public void doDelete() 
	{
		if (context.isWorkspaceEmpty()) {
			return;
		}
		
		else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			context.resetWorksapce();
			context.setWorkspaceChanged(false);
			this.setTitle(this.makeFrameTitle());
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	private void chooseFileAndSave(String ardublockString)
	{
		File saveFile = letUserChooseSaveFile();
		saveFile = checkFileSuffix(saveFile);
		if (saveFile == null)
		{
			return ;
		}
		
		if (saveFile.exists() && !askUserOverwriteExistedFile())
		{
			return ;
		}
		
		writeFileAndUpdateFrame(ardublockString, saveFile);
	}
	
	private String getArduBlockString()
	{
		WorkspaceController workspaceController = context.getWorkspaceController();
		return workspaceController.getSaveString();
	}
	
	private void writeFileAndUpdateFrame(String ardublockString, File saveFile) 
	{
		try
		{
			saveArduBlockToFile(ardublockString, saveFile);
			context.setWorkspaceChanged(false);
			this.setTitle(this.makeFrameTitle());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private File letUserChooseSaveFile()
	{
		int chooseResult;
		chooseResult = fileChooser.showSaveDialog(this);
		if (chooseResult == JFileChooser.APPROVE_OPTION)
		{
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	private boolean askUserOverwriteExistedFile()
	{
		int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.content.overwrite"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
		return (optionValue == JOptionPane.YES_OPTION);
	}
	
	private void saveArduBlockToFile(String ardublockString, File saveFile) throws IOException
	{
		context.saveArduBlockFile(saveFile, ardublockString);
		context.setSaveFileName(saveFile.getName());
		context.setSaveFilePath(saveFile.getAbsolutePath());
	}
	
	public void doNewArduBlockFile()
	{
		if (context.isWorkspaceChanged())
		{
			int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.question.newfile_on_workspace_changed"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
			if (optionValue != JOptionPane.YES_OPTION)
			{
				return ;
			}
		}
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		context.resetWorksapce();
		context.setWorkspaceChanged(false);
		this.setTitle(this.makeFrameTitle());
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	
	
	private File checkFileSuffix(File saveFile)
	{
		String filePath = saveFile.getAbsolutePath();
		if (filePath.endsWith(".abp"))
		{
			return saveFile;
		}
		else
		{
			return new File(filePath + ".abp");
		}
	}
}
