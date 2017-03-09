package notepad;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Notepad extends Frame {
	
	
	private java.awt.Menu mEdit;
    private java.awt.MenuItem mExit;
    private java.awt.Menu mFile;
    private java.awt.MenuItem mNew;
    private java.awt.MenuItem mOPen;
    private java.awt.MenuItem mSave;
    private java.awt.MenuBar mbar;
    private java.awt.MenuItem mCut;
    private java.awt.MenuItem mCopy;
    private java.awt.MenuItem mPaste;
    private javax.swing.JTextArea txtContent;
    private String filename;
    private File file;
    private boolean filenameProvided,modified;

    public Notepad() {
        initComponents();
        filenameProvided=modified=false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
    
     */
                            
    private void initComponents() {
        txtContent = new javax.swing.JTextArea();
        mbar = new java.awt.MenuBar();
        mFile = new java.awt.Menu();
        mNew = new java.awt.MenuItem();
        mOPen = new java.awt.MenuItem();
        mSave = new java.awt.MenuItem();
        mExit = new java.awt.MenuItem();
        mEdit = new java.awt.Menu();
        mCut=new java.awt.MenuItem();
        mCopy=new java.awt.MenuItem();
        mPaste=new java.awt.MenuItem();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        txtContent.setName("txtcontent"); // NOI18N
        add(txtContent, java.awt.BorderLayout.CENTER);

        mbar.setName("mbar");

        mFile.setLabel("File");
        mFile.setName("mFile");
        mFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                action1Performed(evt);
            }
        });

        mNew.setLabel("New");
        mNew.setName("New");
        mFile.add(mNew);

        mOPen.setLabel("Open");
        mOPen.setName("mOpen");
        mFile.add(mOPen);

        mSave.setLabel("Save");
        mSave.setName("Save");
       mSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                action1Performed(evt);
        		  

            }
        });
        mFile.add(mSave);

        mExit.setLabel("Exit");
        mExit.setName("Exit");
        mExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                action1Performed(evt);
            }
        });
        mFile.add(mExit);

        mbar.add(mFile);

        mEdit.setLabel("Edit");
        mEdit.setName("Edit");
        mCut.setLabel("Cut");
        mEdit.add(mCut);

        mCopy.setLabel("Copy");
        mEdit.add(mCopy);

        mPaste.setLabel("Paste");
        mEdit.add(mPaste);
        mCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                action1Performed(evt);
        		  

            }
        });
        mCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                action1Performed(evt);
        		  

            }
        });
        mPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                action1Performed(evt);
        		  

            }
        });

        mbar.add(mEdit);

        setMenuBar(mbar);
        mbar.getAccessibleContext().setAccessibleName("mbar");

        pack();
    }// </editor-fold>                        

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {                          
        System.exit(0);
    }                         

    private void mFileActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void mSaveActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    	save();
    	
    }                                     

    private void mExitActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    	if(modified)
		{
			if(saveConfirm())
			{
				save();
			}
		}
		System.exit(0);
    	
    } 
    
    
    public boolean saveConfirm(){
    	
    	int option;
    	option=JOptionPane.showConfirmDialog(this,"Contents not saved,save?","save",JOptionPane.YES_NO_OPTION);
    	
    	if(option==JOptionPane.YES_OPTION)
    		return true;
    	else
    		return false;
    }
    
    
    public boolean getFileName(int so){
    	
    	JFileChooser jfc=new JFileChooser();
    	int option;
    	if(so==1)
    		option=jfc.showSaveDialog(jfc);
    	
    	else
    		option=jfc.showOpenDialog(jfc);
    	if(option==JFileChooser.APPROVE_OPTION)
    	{
    		file=jfc.getSelectedFile();
    		filename=file.getPath();
    		filenameProvided=true;
    		return true;
    	}
    	else
    		return false;
    	
    }
    
    public void saveContents(){
    	String str=txtContent.getText();
    	try{
    		FileOutputStream fout=new FileOutputStream(file);
    		fout.write(str.getBytes());
    		fout.close();
    	}catch(Exception e)
    	{
    		System.out.println("Error"+e);
    	}
    }
    
    public void save(){
    	if(!filenameProvided)
    	{
    		if(!getFileName(1))
    			return ;
    	}
    	saveContents();
    }
    
    
    public void action1Performed(ActionEvent ae){
    	String cmd=ae.getActionCommand();
    	if(cmd.equals("New"))
    	{
    		if(modified)
    		{
    			if(saveConfirm())
    			{
    				save();
    			}
    		}
    	
    	txtContent.setText(" ");
    	filenameProvided=false;
    	modified=true;
    }
    	else if(cmd.equals("Open"))
    	{
    		if(modified)
    		{
    			if(saveConfirm())
    			{
    				save();
    			}
    		}
    		if(getFileName(2))
    		{
    			try
    			{
    				FileInputStream fin;
    				fin=new FileInputStream(file);
    				byte[] b=new byte[(int)file.length()];
    				fin.read(b);
    				String str=new String(b);
    				txtContent.setText(str);
    				fin.close();
    			}catch(Exception e)
    			{
    			System.out.println("Error"+e);	
    			}
    			modified=false;
    			filenameProvided=true;
    		}
    	}
    	else if(cmd.equals("Save"))
    	{
    		save();
    	}
    	else if(cmd.equals("Cut"))
    	{
    		txtContent.cut();
    	}
    	else if(cmd.equals("Copy"))
    	{
    		txtContent.copy();
    	}
    	else if(cmd.equals("Paste"))
    	{
    		txtContent.paste();
    	}
    	
    	else if(cmd.equals("Exit"))
    	{
    		if(modified)
    		{
    			if(saveConfirm())
    			{
    				save();
    			}
    		}
    		System.exit(0);
    	}
    	
    	
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notepad().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify                     
  
    // End of variables declaration                   
}









