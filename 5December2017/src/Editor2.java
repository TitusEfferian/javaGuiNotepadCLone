import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class File
{
	String name = "";
	String text ="";

	public File(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text=text;
	}



}


public class Editor2 extends JFrame implements WindowListener,ActionListener,MouseListener,KeyListener
{
	//Login
	JPanel lgnPnl;
	JButton lgnOkBtn, lgnCancelBtn;
	JLabel lgnUserLb, lgnPassLb, lgnRoleLb, lgnImageLb;
	JTextField lgnUserTf;
	JPasswordField lgnPassPf;
	JComboBox lgnRoleCb;
	
	//Editor
	JPanel editorPnl;
	JLabel editorStatusLb;
	JTextArea editorTa;
	JPanel editorListPnl;
	DefaultTableModel editorListDtm;
	JTable editorListTbl;
	Vector<File> editorListVc;
	JPanel editorListBtnPnl;
	JButton editorListNewBtn, editorListEditBtn, editorListDelBtn;
	JSplitPane splitPane;
	
	//Menu
	JMenuBar menuBar;
	JMenu fileMenu, helpMenu;
	JMenuItem saveMi, logoutMi, exitMi, helpMi, aboutMi;
	
	//About
	JPanel aboutPnl;
	JLabel aboutImageLb, aboutAuthorLb;
	JButton aboutOkBtn;
	JComponent component;

	public void addListenerForEditorButton()
	{
		editorListNewBtn.addActionListener(this);
		editorListEditBtn.addActionListener(this);
		editorListDelBtn.addActionListener(this);
		editorTa.addKeyListener(this);
		editorListTbl.addKeyListener(this);
		editorListTbl.addMouseListener(this);
	}
	public void addToFile(String text)
	{
		editorListVc.add(new File(text));
		editorListDtm.addRow(new Object[]{editorListVc.get(editorListVc.size()-1).getName()});
	}
	
	public Editor2()
	{
		initComponents();
		setComponents();
		viewComponents(1);
	}
	
	public void initComponents()
	{
		//Init login frame
		lgnPnl = new JPanel();
		lgnOkBtn = new JButton("OK");
		lgnCancelBtn = new JButton("Cancel");
		lgnUserLb = new JLabel("Username"); 
		lgnPassLb = new JLabel("Password");
		lgnRoleLb = new JLabel("Role");
		lgnImageLb = new JLabel(new ImageIcon("Binus.jpg"));
		lgnUserTf = new JTextField();
		lgnPassPf = new JPasswordField();
		lgnRoleCb = new JComboBox();
		
		//Init Editor frame
		editorPnl = new JPanel();
		editorStatusLb = new JLabel("Last Saved: Never"); 
		editorTa = new JTextArea();
		editorListPnl = new JPanel();
		//editorListDtm = new DefaultTableModel(0,1); // 0 row, 1 col
		//editorListDtm = new DefaultTableModel(new Object[][]{{"Isi1"},{"Isi2"}},new String[]{"Title"}); // data, colName
		editorListDtm = new DefaultTableModel(new String[]{"List"},0)
		{
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}; // 0 row, 1 col
		editorListTbl = new JTable(editorListDtm);
		editorListVc = new Vector<File>();
		editorListBtnPnl = new JPanel();
		editorListNewBtn = new JButton("New");
		editorListEditBtn = new JButton("Edit");
		editorListDelBtn = new JButton("Delete");
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		//Set Menu
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		saveMi = new JMenuItem("Save");
		logoutMi = new JMenuItem("Logout");
		exitMi = new JMenuItem("Exit");
		helpMi = new JMenuItem("Help");
		aboutMi = new JMenuItem("About...");
		
		//Set About frame
		aboutPnl = new JPanel();
		aboutImageLb = new JLabel(new ImageIcon("Binus.jpg"));
		aboutAuthorLb = new JLabel("(c) 2017 - Editor - Binus University.");
		aboutOkBtn = new JButton("OK");
		
		
	}
	
	public void setComponents()
	{
		//Set all
		setTitle("Editor");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon("Binus.jpg").getImage());
		addWindowListener(this);
		
		//Set login frame
		lgnPnl.setLayout(null);
		lgnUserLb.setBounds(10,150,100,30);
		lgnPnl.add(lgnUserLb);
		lgnPassLb.setBounds(10,190,100,30);
		lgnPnl.add(lgnPassLb);
		lgnRoleLb.setBounds(10,230,100,30);
		lgnPnl.add(lgnRoleLb);
		lgnUserTf.setBounds(120,150,100,30);
		lgnPnl.add(lgnUserTf);
		lgnPassPf.setBounds(120,190,100,30);
		lgnPnl.add(lgnPassPf);
		lgnRoleCb.setBounds(120,230,100,30);
		lgnPnl.add(lgnRoleCb);
		lgnImageLb.setBounds(60,20,100,100);
		lgnPnl.add(lgnImageLb);
		lgnOkBtn.setBounds(10,320,100,30);
		lgnPnl.add(lgnOkBtn);
		lgnCancelBtn.setBounds(120,320,100,30);
		lgnPnl.add(lgnCancelBtn);
		
		lgnOkBtn.addActionListener(this);
		lgnCancelBtn.addActionListener(this);
		
		lgnRoleCb.addItem("Student");
		lgnRoleCb.addItem("Lecturer");
		lgnRoleCb.addItem("Parent");
		
		
		
		//Set Editor frame
		editorPnl.setLayout(new BorderLayout());
		//editorPnl.add(new JScrollPane(editorTa),BorderLayout.CENTER);
		editorPnl.add(editorStatusLb,BorderLayout.SOUTH);
		editorListPnl.setLayout(new BorderLayout());
		editorListPnl.add(new JScrollPane(editorListTbl),BorderLayout.CENTER);
		editorListBtnPnl.setLayout(new FlowLayout());
		editorListBtnPnl.add(editorListNewBtn);
		editorListBtnPnl.add(editorListEditBtn);
		editorListBtnPnl.add(editorListDelBtn);
		editorListPnl.add(editorListBtnPnl,BorderLayout.SOUTH);
		//editorPnl.add(editorListPnl,BorderLayout.WEST);

		addToFile("untitled");


		editorListTbl.setPreferredScrollableViewportSize(editorListTbl.getPreferredSize());
		editorListTbl.changeSelection(0, 0, false, false);
		editorListTbl.requestFocus();

		

		
		splitPane.setLeftComponent(editorListPnl);
		splitPane.setRightComponent(new JScrollPane(editorTa));
		editorPnl.add(splitPane,BorderLayout.CENTER);
		
		//Set Menu
		fileMenu.add(saveMi);
		fileMenu.addSeparator();
		fileMenu.add(logoutMi);
		fileMenu.addSeparator();
		fileMenu.add(exitMi);
		helpMenu.add(helpMi);
		helpMenu.addSeparator();
		helpMenu.add(aboutMi);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		saveMi.addActionListener(this);
		logoutMi.addActionListener(this);
		exitMi.addActionListener(this);
		aboutMi.addActionListener(this);
		helpMi.addActionListener(this);
		lgnUserTf.addKeyListener(this);
		lgnPassPf.addKeyListener(this);

		//actionlistenerbutton
		addListenerForEditorButton();


		//Set About frame
		aboutPnl.setLayout(null);
		aboutImageLb.setBounds(350,100,100,100);
		aboutPnl.add(aboutImageLb);
		aboutAuthorLb.setBounds(300,220,300,30);
		aboutPnl.add(aboutAuthorLb);
		aboutOkBtn.setBounds(350,300,100,30);
		aboutPnl.add(aboutOkBtn);
		aboutOkBtn.addActionListener(this);
		
		component = lgnPnl;
		add(component);
		
		
	}
	
	public void viewComponents(int type)
	{
		switch(type)
		{
			case 1:	
					remove(component);
					component=lgnPnl;
					add(component);
					add(lgnPnl);
					setSize(250,500);
					break;
			case 2:
					remove(component);
					component = editorPnl;
					add(component);
					setJMenuBar(menuBar);
					setSize(800,500);
					break;
			case 3:
					remove(component);
					component = aboutPnl;
					setJMenuBar(null);
					add(component);
					
					setSize(800,500);
					break;
		}
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Editor2();
	}
	
	public void exitWindow()
	{
		int exit = 0;
		exit=JOptionPane.showConfirmDialog(null, "are you pokemon?", "exit", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		if(exit==0)
		{
			dispose();
		}
	}
	public boolean isLoginValid()
	{
		String user,pass;
		user = lgnUserTf.getText();
		pass = new String(lgnPassPf.getPassword());
		if(user==null||pass==null||user.trim().equals("")||pass.trim().equals(""))
		{
			JOptionPane.showConfirmDialog(null, "incorrect username or password","login",JOptionPane.PLAIN_MESSAGE);
			
			
		}
		else if(user.equals("admin")||pass.equals("admin"))
		{
			
			return true;
			
		}
		else
		{
			JOptionPane.showConfirmDialog(null, "wrong username or password","login",JOptionPane.PLAIN_MESSAGE);
		}
		return false;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("activated");
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("closed");
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		exitWindow();
		
		System.out.println("closing");
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("deactive");
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("deiconfied");
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("iconfied");
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("opened");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lgnOkBtn)
		{
			if(isLoginValid())
			{
				remove(lgnPnl);
				viewComponents(2);
			}
			
		}
		else if(arg0.getSource()==lgnCancelBtn)
		{
			exitWindow();
			
		}
		else if(arg0.getSource()==saveMi)
		{
			System.out.println("save");
		}
		else if(arg0.getSource()==helpMi)
		{
			try
			{
				Runtime.getRuntime().exec("notepad");
			}
			catch(Exception ex)
			{
				
			}
		}
		else if(arg0.getSource()==exitMi)
		{
			
		}
		else if(arg0.getSource()==aboutMi)
		{
			viewComponents(3);
		}
		else if(arg0.getSource()==aboutOkBtn)
		{
			viewComponents(2);
		}
		else if(arg0.getSource()==logoutMi)
		{
			viewComponents(1);
		}
		else if(arg0.getSource()==editorListNewBtn)
		{
			String newFile = "";
			do {

				newFile=JOptionPane.showInputDialog(null,"type new file","new file",JOptionPane.PLAIN_MESSAGE);
				if(newFile.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"must be filed","warning",JOptionPane.ERROR_MESSAGE);
				}

				else
				{
					if(!isAlphanumeric(newFile))
					{
						JOptionPane.showMessageDialog(null,"must be alphanumeric","warning",JOptionPane.ERROR_MESSAGE);
					}
					if(isContains(newFile))
					{
						JOptionPane.showMessageDialog(null,"already exists","warning",JOptionPane.ERROR_MESSAGE);
					}

				}
			}while(newFile.isEmpty()||!isAlphanumeric(newFile)||isContains(newFile));
			addToFile(newFile);

		}
		else if(arg0.getSource()==editorListDelBtn)
		{
			int confirm = JOptionPane.showConfirmDialog(null,"are you sure?","delete",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
			if(confirm==0)
			{
				if(editorListDtm.getRowCount()>1)
				{
					editorListVc.remove(editorListTbl.getSelectedRow());
					editorListDtm.removeRow(editorListTbl.getSelectedRow());
					editorTa.setText(null);
				}
				else
				{
					editorListVc.remove(editorListTbl.getSelectedRow());
					editorListDtm.removeRow(editorListTbl.getSelectedRow());
					editorTa.setText(null);
					addToFile("untitled");
				}

			}
		}
		else if(arg0.getSource()==editorListEditBtn)
		{
			String newFile = "";
			do {

				newFile=JOptionPane.showInputDialog(null,"type new file","new file",JOptionPane.PLAIN_MESSAGE);
				if(newFile.trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"must be filed","warning",JOptionPane.ERROR_MESSAGE);
				}

				else
				{
					if(!isAlphanumeric(newFile))
					{
						JOptionPane.showMessageDialog(null,"must be alphanumeric","warning",JOptionPane.ERROR_MESSAGE);
					}
					if(isContains(newFile))
					{
						JOptionPane.showMessageDialog(null,"already exists","warning",JOptionPane.ERROR_MESSAGE);
					}

				}
			}while(newFile.isEmpty()||!isAlphanumeric(newFile)||isContains(newFile));
			editorListVc.get(editorListTbl.getSelectedRow()).setName(newFile);
			editorListDtm.setValueAt(editorListVc.get(editorListTbl.getSelectedRow()).getName(),editorListTbl.getSelectedRow(),0);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode()==10)
		{
			if(isLoginValid())
			{
				remove(lgnPnl);
				viewComponents(2);
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("keyrelease: "+arg0.getKeyCode()+"-"+arg0.getKeyChar());

		if(arg0.getSource()==editorListTbl)
		{
			if(editorListTbl.getSelectedRow()!= -1 && (arg0.getKeyCode()==38 || arg0.getKeyCode()==40))
			{
				editorTa.setText(editorListVc.get(editorListTbl.getSelectedRow()).getText());
			}
		}
		else if(arg0.getSource()==editorTa)
		{
			if(editorListTbl.getSelectedRow()!=-1)
			{
				editorListVc.get(editorListTbl.getSelectedRow()).setText(editorTa.getText());
			}
		}


	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("keytyped: "+arg0.getKeyCode()+"-"+arg0.getKeyChar());
		
	}
	public boolean isAlphanumeric(String str)
	{
		char[] charArray = str.toCharArray();
		for(char c:charArray)
		{
			if (!Character.isLetterOrDigit(c))
				return false;
		}
		return true;
	}
	public boolean isContains(String fileName)
	{
		for(int a=0;a<editorListVc.size();a++)
		{
			if(fileName.equals(editorListVc.get(a).getName()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==editorListTbl)
		{
			editorTa.setText(editorListVc.get(editorListTbl.getSelectedRow()).getText());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}


//Tambahan:
//Form Editor, Menu, About