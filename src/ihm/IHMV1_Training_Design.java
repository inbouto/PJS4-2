package ihm;
	 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class IHMV1_Training_Design extends JPanel implements ActionListener {
    JLabel result;
    String currentClasse;
 
    public IHMV1_Training_Design() {
    	super(new GridBagLayout());
    	
    	//Création de la fenêtre
    	JFrame frame = new JFrame("Training");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        centerWindow(frame);
        
        JLabel labelTitre = new JLabel("ENTRAINEMENT DE L'IA");
        labelTitre.setFont(new Font("Dialog", Font.BOLD, 25));
        
        JLabel labelPhrase = new JLabel("Voici le texte à classifier :");
        
        JTextArea textePhrase = new JTextArea(12, 40);
        textePhrase.setEditable(false);
        textePhrase.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textePhrase);
        
        JLabel labelClasse = new JLabel("Sélectionnez la classe correspondante");
        
        String[] classes = {
                 "Nourriture",
                 "Jeux",
                 "Animaux",
                 "Films",
                 "Sports",
                 "Etudes",
                 "Travail",
                 "Bizarre",
                 "Monuments"
        };
 
        currentClasse = classes[0];
 
        JComboBox listeClasses = new JComboBox(classes);
        listeClasses.setEditable(false);
        listeClasses.addActionListener(this);
        		
		listeClasses.setBackground(new Color(233, 233, 233));
		
        JPanel panelConfirmation = new JPanel();
        panelConfirmation.setLayout(new FlowLayout());
        
        JButton boutonSkip = new JButton("Skip");
        boutonSkip.setBackground(new Color(255, 0, 0));
	    boutonSkip.setForeground(new Color(255, 0, 0));
		panelConfirmation.add(boutonSkip);
		
		panelConfirmation.add(Box.createRigidArea(new Dimension(100,0)));
		        
		JButton boutonOk = new JButton("Ok");
		boutonSkip.setBackground(new Color(255, 0, 0));
	    boutonSkip.setForeground(new Color(255, 0, 0));
		panelConfirmation.add(boutonOk);
		
		panelConfirmation.setBackground(new Color(233, 233, 233));
        
 
        //affichage
        JPanel panelGeneral = new JPanel();
        
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.PAGE_AXIS));
        
        panelGeneral.add(labelTitre);
        labelTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitre.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panelGeneral.add(labelPhrase);
        labelPhrase.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPhrase.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panelGeneral.add(scrollPane, textePhrase);
        textePhrase.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panelGeneral.add(labelClasse);
        labelClasse.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelClasse.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));        
        
        panelGeneral.add(listeClasses);
        listeClasses.setAlignmentX(Component.CENTER_ALIGNMENT);        
        listeClasses.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        
        panelGeneral.add(panelConfirmation);
        panelConfirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelConfirmation.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panelGeneral.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelGeneral.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        panelGeneral.setBackground(new Color(233, 233, 233));
 
        frame.add(panelGeneral, BorderLayout.PAGE_START);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void creerComboBoxClasses(){
    	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); 
        String[] classes = {
                 "Nourriture",
                 "Jeux",
                 "Animaux",
                 "Films",
                 "Sports",
                 "Etudes",
                 "Travail",
                 "Bizarre",
                 "Monument"
        };
 
        currentClasse = classes[0];
 
        //Set up the UI for selecting a pattern.
        JLabel label1 = new JLabel("Sélectionnez la classe correspondante");
        JLabel label2 = new JLabel("ou ajoutez en une à la liste");
 
        JComboBox listeClasses = new JComboBox(classes);
        listeClasses.setEditable(false);
        listeClasses.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
    	// /!\ A REMPLIR /!\
    }
    
    public static void centerWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 3.5);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 3.5);
        frame.setLocation(x, y);
    }
 
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
//    private static void createAndShowGUI() {
// 
//        //Create and set up the content pane.
//        JComponent newContentPane = new IHMV1_Training_Design();
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
// 
//    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new IHMV1_Training_Design();
            }
        });
    }
}