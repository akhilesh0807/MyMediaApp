import java.io.*;
import javax.media.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 class MyMediaApp extends JFrame implements ActionListener
{
	
	int returnValue;
	Player player; JFileChooser jfc;
	JButton b1;
	
	File selectedFile;
	public  MyMediaApp()
	{
		
		
		jfc=new JFileChooser();
		b1=new JButton("open files");
		 getContentPane().add( b1, BorderLayout.NORTH );
		setSize(400,400);
		setVisible( true );
		b1.addActionListener(this);
		
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			openFile();
			createPlayer();
			System.out.println(selectedFile);
		}	
		
	}
 


	public void openFile()
	{
		jfc.setFileSelectionMode
			( jfc.FILES_ONLY );
			int returnValue = jfc.showOpenDialog(this );
			if (returnValue == JFileChooser.APPROVE_OPTION) 
			{ selectedFile = jfc.getSelectedFile();}
	}

	public void createPlayer()
	{
		

			try
			{
				player=Manager.createPlayer(selectedFile.toURL());
				player.addControllerListener(new EventHandler());
				player.start();
			}
	
			catch( IOException e)
			{}
			catch(NoPlayerException e1)
			{System.out.println("erororor  ");}
			catch( NullPointerException e5)
			{}
	
	}	
	  // inner class to handler events from media player
   private class EventHandler implements ControllerListener {
      public void controllerUpdate( ControllerEvent e ) {
         if ( e instanceof RealizeCompleteEvent ) {
            Container c = getContentPane();
         
            // load Visual and Control components if they exist
            Component visualComponent =
               player.getVisualComponent();

            if ( visualComponent != null )
               c.add( visualComponent, BorderLayout.CENTER );

            Component controlsComponent =
               player.getControlPanelComponent();

            if ( controlsComponent != null )
               c.add( controlsComponent, BorderLayout.SOUTH );

            c.doLayout();
         }
      }
   }
}
	









class Me1 
{
	public static void main(String x[])
	{
		new MyMediaApp(); 
	}
}
