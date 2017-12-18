
import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Editor1 extends JFrame 
{

//	Komponen Login
	JPanel lgnPnl;
	JButton lgnOkBtn, lgnCancelBtn;
	JLabel lgnUserLb, lgnPassLb, lgnRoleLb, lgnImageLb;
	JTextField lgnUserTf, lgnPassTf;
	JComboBox lgnRoleCb;
	Vector editorVc;
	

//	Komponen editor
	JPanel editorPnl,editorListPnl,editorBtnPnl;
	JTable editorTbl;
	JButton editorNewBtn,editorEditBtn,editorDeleteBtn;
	JLabel editorStatusLbl;
	JTextArea editorTa;
	DefaultTableModel editorDtm;
	JSplitPane editorSp;
	
//	Komponen Menu
	JMenuBar editorMb;
	JMenu editorFileMenu,editorHelpMenu;
	JMenuItem editorSaveMi,editorExitMi,editorHelpMi,editorAboutMi;
	
	public Editor1()
	{
		initComponents();
		setComponents();
		viewComponents(2);
	}
	
	public void initComponents()
	{
//		init login
		lgnPnl = new JPanel();
		lgnOkBtn = new JButton("OK");
		lgnCancelBtn = new JButton("Cancel");
		lgnUserLb = new JLabel("Username"); 
		lgnPassLb = new JLabel("Password");
		lgnRoleLb = new JLabel("Role");
		lgnImageLb = new JLabel(new ImageIcon("Binus.jpg"));
		lgnUserTf = new JTextField();
		lgnPassTf = new JTextField();
		lgnRoleCb = new JComboBox();
		
//		init editor
		editorPnl = new JPanel();
		editorListPnl = new JPanel();
		editorBtnPnl = new JPanel();
		editorDtm = new DefaultTableModel(new String[]{"List"},0);
		editorTbl = new JTable(editorDtm) ;
		editorNewBtn = new JButton("New");
		editorEditBtn = new JButton("Edit");
		editorDeleteBtn = new JButton("Delete");
		editorStatusLbl = new JLabel();
		editorTa = new JTextArea();
		editorVc = new Vector();
		editorSp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
//		init menu
		editorMb = new JMenuBar();
		editorFileMenu = new JMenu("File");
		editorHelpMenu = new JMenu("Help");
		editorSaveMi = new JMenuItem("Save") ;
		editorExitMi = new JMenuItem("Exit") ;
		editorHelpMi = new JMenuItem("Help");
		editorAboutMi = new JMenuItem("About...");
		
		
	}
	
	public void setComponents()
	{
		//Set all
		setTitle("Editor");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setIconImage(new ImageIcon("Binus.jpg").getImage());
		
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
		lgnPassTf.setBounds(120,190,100,30);
		lgnPnl.add(lgnPassTf);
		lgnRoleCb.setBounds(120,230,100,30);
		lgnPnl.add(lgnRoleCb);
		lgnImageLb.setBounds(60,20,100,100);
		lgnPnl.add(lgnImageLb);
		lgnOkBtn.setBounds(10,320,100,30);
		lgnPnl.add(lgnOkBtn);
		lgnCancelBtn.setBounds(120,320,100,30);
		lgnPnl.add(lgnCancelBtn);
		
		lgnRoleCb.addItem("Student");
		lgnRoleCb.addItem("Lecturer");
		lgnRoleCb.addItem("Parent");
		
		
		//set Editor
		editorPnl.setLayout(new BorderLayout());
		editorListPnl.setLayout(new BorderLayout());
		//editorListPnl.add(new JScrollPane(editorTbl),BorderLayout.CENTER);
		editorBtnPnl.setLayout(new FlowLayout());
		editorBtnPnl.add(editorNewBtn);
		editorBtnPnl.add(editorEditBtn);
		editorBtnPnl.add(editorDeleteBtn);
		editorListPnl.add(editorBtnPnl,BorderLayout.SOUTH);
//		editorPnl.add(editorListPnl,BorderLayout.WEST);
		editorPnl.add(editorStatusLbl,BorderLayout.SOUTH);
//		editorPnl.add(editorTa,BorderLayout.CENTER);
		//editorPnl.add(new JScrollPane(editorTa),BorderLayout.CENTER);
		editorTbl.setPreferredScrollableViewportSize(editorTbl.getPreferredSize());
		editorVc.add("Untitled");
		editorDtm.addRow(editorVc);
		editorSp.setLeftComponent(editorListPnl);
		editorSp.setRightComponent(new JScrollPane(editorTa));
		editorPnl.add(editorSp,BorderLayout.CENTER);
		
//		set Menu
		editorFileMenu.add(editorSaveMi);
		editorFileMenu.add(editorExitMi);
		editorHelpMenu.add(editorHelpMi);
		editorHelpMenu.add(editorAboutMi);
		editorMb.add(editorFileMenu);
		editorMb.add(editorHelpMenu);
	}
	
	public void viewComponents(int type)
	{
		if(type==1){
			add(lgnPnl);
			setSize(250,500);
		}else if(type==2){
			add(editorPnl);
			setJMenuBar(editorMb);
			setSize(800,500);
		}else if(type==3){
			
		}

		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Editor1();
	}
}
