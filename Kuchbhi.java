import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
class Kuchbhi extends JFrame
{
	PuzzleButton reset=new PuzzleButton("Reset",0,0),blast,bt[][]=new PuzzleButton[4][4];
	JPanel pa=new JPanel();
	String n[]=new String[16];
	JLabel cl=new JLabel("Number of Clicks: 0");
	int clicks;
	Kuchbhi()
	{
		super("Puzzle wala game");
		setSize(500,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		Font f=new Font("Arial",Font.BOLD,20);
		cl.setBounds(175,50,280,30);
		cl.setFont(f);
		cl.setForeground(Color.green);
		reset.setBounds(205,510,140,30);
		reset.setFont(f);
		reset.setForeground(Color.red);
		pa.setBounds(100,100,300,400);
		pa.setBorder(BorderFactory.createLineBorder(Color.red,2));
		pa.setLayout(new GridLayout(4,4));
		rad();
		addButton();
		add(pa);
		add(cl);
		add(reset);
		setVisible(true);
	}
	public void rad()
	{
		int v,i;
		boolean l[]=new boolean[16];
		Random r=new Random();
		for(i=0;i<15;)
		{
			v=r.nextInt(15)+1;
			if(!l[v])
			{
				n[i++]=""+v;
				l[v]=true;
			}
		}n[15]="";
	}
	public void addButton()
	{
		int c=-1;
		Font fo =new Font("Magneto",Font.BOLD,30);
		PosListener plis=new PosListener();
		reset.addActionListener(plis);
		Insets in=new Insets(0,0,0,0);
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				bt[i][j]=new PuzzleButton(n[++c],i,j);
				bt[i][j].setFont(fo);
				bt[i][j].setForeground(Color.blue);
				bt[i][j].setBorder(BorderFactory.createLineBorder(Color.blue,1));
				bt[i][j].setMargin(in);
				bt[i][j].addActionListener(plis);
				pa.add(bt[i][j]);
			}
		}
		blast=bt[3][3];
	}
	class PosListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			PuzzleButton bget = (PuzzleButton)evt.getSource();
			if(((blast.r==bget.r && Math.abs(blast.c-bget.c)==1)||(blast.c==bget.c && Math.abs(blast.r-bget.r)==1)) && !check())
			{
				cl.setText("Number of Clicks: "+(++clicks));
				blast.setText(bget.getText());
				bget.setText("");
				blast=bget;
			}
			if(bget==reset)
			{
				removeAll();
				setVisible(false);
				new Kuchbhi();
			}
		}
	}
	public boolean check()
	{
		int c=-1;
		String st[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",""};
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4 && c<16;j++)
			{
				if(!(bt[i][j].getText().equals(st[++c])))
				return false;
			}
		}
		return true;
	}
	public static void main(String []args)
	{
		setDefaultLookAndFeelDecorated(true);
		new Kuchbhi();
	}
}