import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@SuppressWarnings("serial")
public class SimpleViewer extends JFrame 
{
    protected JButton myInput;
    protected JTextArea      myOutput;
    protected BlobModel  myModel;
    protected String     myTitle;
    protected String     myLabelString;
    protected JTextField myMessage;
    
    protected static JFileChooser ourChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));

    
    public SimpleViewer(String title, String prompt)
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());
        setTitle(title);
        myTitle = title;
        myLabelString = prompt;
        
        if (! prompt.equals("")){
            panel.add(makeInput(),  BorderLayout.NORTH);
        }
        panel.add(makeOutput(), BorderLayout.CENTER);
        panel.add(makeMessage(), BorderLayout.SOUTH);
        makeMenus();
        connectEvents();
        
        pack();
        setSize(400,400);
        setVisible(true);
    }
    
    public void setModel(BlobModel model){
        myModel = model;
        model.addView(this);
    }
    
    protected JPanel makeMessage(){
        JPanel p = new JPanel(new BorderLayout());
        myMessage = new JTextField(30);
        
        p.setBorder(BorderFactory.createTitledBorder("message"));
        p.add(myMessage, BorderLayout.CENTER);
        return p;
    }
    
    protected JPanel makeInput()
    {
        JPanel p = new JPanel(new BorderLayout());
        JLabel label = new JLabel(myLabelString);
        myInput = new JButton("create"); //new JTextField(12);
        p.setBorder(BorderFactory.createTitledBorder("input"));
        p.add(label,   BorderLayout.WEST);
        p.add(myInput, BorderLayout.CENTER);	
        return p;
    }
    
    protected JPanel makeOutput()
    {
        JPanel p = new JPanel(new BorderLayout());
        myOutput = new JTextArea(10,40);
        //myOutput.setVisibleRowCount(10);
        p.setBorder(
                BorderFactory.createTitledBorder("output"));
        p.add(new JScrollPane(myOutput), BorderLayout.CENTER);
        return p;
        
    }
    
    @SuppressWarnings("serial")
	protected JMenu makeFileMenu(){
        JMenu fileMenu = new JMenu("File");
        
        fileMenu.add(new AbstractAction("Open File"){
            public void actionPerformed(ActionEvent ev){
                int retval = ourChooser.showOpenDialog(null);
                if (retval == JFileChooser.APPROVE_OPTION){
                    File file = ourChooser.getSelectedFile();
                   
                    		// nothing to do for blobs, this line from old version
                    		// TODO Fix this
                        //myModel.initialize(new Scanner(file));
                }		    
            }
        });
        
        fileMenu.add(new AbstractAction("Open URL"){
            public void actionPerformed(ActionEvent ev) {
            	String address = JOptionPane.showInputDialog("Please enter the complete URL (including \"http://\") for a grammar file:");
            	try {
					URI uri = new URI(address);
					URL url = uri.toURL();
					//myModel.initialize(new Scanner(url.openStream()));
					Scanner unUsed = new Scanner(url.openStream());
					showMessage("current grammar: "+url);
				} catch (URISyntaxException e1) {
                    showError(e1.getMessage());
				} catch (MalformedURLException e2) {
                    showError(e2.getMessage());
				} catch (IOException e3) {
					showError("error reading from URL: "+address);
				}
            }
        });
 
        fileMenu.add(new AbstractAction("Quit"){
            public void actionPerformed(ActionEvent ev){
                System.exit(0);
            }
        });
        return fileMenu;
    }
    protected void makeMenus()
    {
        JMenuBar bar = new JMenuBar();
        bar.add(makeFileMenu());
        setJMenuBar(bar);
    }
    
    protected void connectEvents()
    {
    	if (myInput == null) return;

    	myInput.addActionListener(
    			new ActionListener()
    			{
    				public void actionPerformed(ActionEvent ev)
    				{
    					myModel.findBlobs(Integer.parseInt(ev.getActionCommand()));
    				}
    			});
    }
    
    public void clear(){
        myOutput.setText("");
    }
    
    public void update(String s){
       myOutput.append(s+"\n");
    }
    
    
    public void showMessage(String s) {
        myMessage.setText(s);
    }
    
    public void showError(String s){
        JOptionPane.showMessageDialog(this,s,"Model Error",
                JOptionPane.ERROR_MESSAGE);
        if (myInput != null){
            myInput.setText("");
        }
    }
}
