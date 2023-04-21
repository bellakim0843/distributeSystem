package grpc.examples.securityDeviceCheck;



import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class printer implements Printable, ActionListener {

  JFrame frameToPrint;

  public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

    if (page > 0) { /* We have only one page, and 'page' is zero-based */
      return NO_SUCH_PAGE;
    }

    /*
     * User (0,0) is typically outside the imageable area, so we must translate
     * by the X and Y values in the PageFormat to avoid clipping
     */
    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());

    /* Now print the window and its visible contents */
    frameToPrint.printAll(g);

    /* tell the caller that this page is part of the printed document */
    return PAGE_EXISTS;
  }

  public void actionPerformed(ActionEvent e) {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(this);
    boolean ok = job.printDialog();
    if (ok) {
      try {
        job.print();
      } catch (PrinterException ex) {
        /* The job did not successfully complete */
      }
    }
  }

  public printer(JFrame f) {
    frameToPrint = f;
  }
  

public static void print(String string) {
	  
	    UIManager.put("swing.boldMetal", Boolean.FALSE);
	    JFrame f = new JFrame("Print Detail");
	    f.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	    
		GridBagLayout layout = new GridBagLayout();
		f.setLayout(layout);
	    
	    
	    JTextArea text = new JTextArea(500, 1500);
	    text.append(string);
	    JScrollPane pane = new JScrollPane(text);
	    pane.setPreferredSize(new Dimension(400, 400));
	    f.add( pane);
	    
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
	    JButton printButton = new JButton("Print Out");
	    JButton closeButton = new JButton("Close");
	    
		gbc.gridx = 0;
		gbc.gridy = 0;
		f.add(pane, gbc);

		
		gbc.gridx = 0;
		gbc.gridy =1;
		f.add(printButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		f.add(closeButton, gbc);
	    
	    printButton.addActionListener(new printer(f));
	    
	    closeButton.addActionListener(e -> {

			f.dispose();
		
		});
	    
	    
	   // f.add("South", printButton);
	    f.pack();
	    f.setVisible(true);
	  
  }

  public static void main(String args[]) {
	  print("AAAA");
  }
}
