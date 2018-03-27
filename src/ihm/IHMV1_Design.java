package ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import core.InterfaceIHM;
 
public class IHMV1_Design extends JPanel implements ActionListener, InterfaceIHM {
	
	private JTextField zoneDeSaisie;
    private JTextArea texteUser;
    private JTextArea texteIA;
    private final static String newline = "\n";
    private String reponse = null;
 
    public IHMV1_Design() {
        super(new GridBagLayout());      
        
        JFrame frame = new JFrame("L'IAPP");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        centerWindow(frame);       

        frame.add(creerTitre(), BorderLayout.PAGE_START);
        frame.add(creerZoneDeTexte(), BorderLayout.PAGE_END); 
        frame.add(creerTexteUser(), BorderLayout.LINE_START);
        frame.add(creerTexteIA(), BorderLayout.LINE_END);
        
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public JPanel creerTitre() {
    	//Code du titre
        JPanel panelTitre = new JPanel();
    	
    	JLabel Titre = new JLabel("INTELLIGENCE ARTIFICIELLE SUPPORT");
        Titre.setFont(new Font("Dialog", Font.BOLD, 25));
        Titre.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        GridBagConstraints TitreGBC = new GridBagConstraints();
        
        panelTitre.add(Titre, TitreGBC);
        
		return panelTitre;    	
    }
    
    public JPanel creerZoneDeTexte(){
    	//Code de la zone de saisie
        JPanel panelZoneDeSaisie = new JPanel();
        
        TitledBorder borderZoneDeSaisie = new TitledBorder("Tapez ici votre texte");
        borderZoneDeSaisie.setTitleJustification(TitledBorder.CENTER);
        borderZoneDeSaisie.setTitlePosition(TitledBorder.TOP);        
        panelZoneDeSaisie.setBorder(borderZoneDeSaisie);
         
        zoneDeSaisie = new JTextField(82);
        zoneDeSaisie.addActionListener(this);
 
        GridBagConstraints zoneDeSaisieGBC = new GridBagConstraints();
        
        panelZoneDeSaisie.add(zoneDeSaisie, zoneDeSaisieGBC);
        
		return panelZoneDeSaisie;  
    }
    
    public JPanel creerTexteUser(){ 
    	//Code permettant d'afficher le texte entré
        JPanel panelTexteUser = new JPanel();
        
        TitledBorder borderTexteUser = new TitledBorder("Votre texte");
        borderTexteUser.setTitleJustification(TitledBorder.CENTER);
        borderTexteUser.setTitlePosition(TitledBorder.TOP);
        
        panelTexteUser.setBorder(borderTexteUser);
        
        texteUser = new JTextArea(15, 40);
        texteUser.setEditable(false);
        texteUser.setLineWrap(true);
        JScrollPane texteUserSP = new JScrollPane(texteUser);
        GridBagConstraints texteUserGBC = new GridBagConstraints();
        
        panelTexteUser.add(texteUserSP, texteUserGBC);
        
		return panelTexteUser;
    }
    
    public JPanel creerTexteIA(){
    	//Code permettant d'afficher le texte de l'IA
        JPanel panelTexteIA = new JPanel();
        
        TitledBorder borderTexteIA = new TitledBorder("Réponse de l'IA");
        borderTexteIA.setTitleJustification(TitledBorder.CENTER);
        borderTexteIA.setTitlePosition(TitledBorder.TOP);
        
        panelTexteIA.setBorder(borderTexteIA);
        
        texteIA = new JTextArea(15, 40);
        texteIA.setEditable(false);
        texteIA.setLineWrap(true);
        JScrollPane texteIASP = new JScrollPane(texteIA);

        GridBagConstraints texteIAGBC = new GridBagConstraints();
        
        panelTexteIA.add(texteIASP, texteIAGBC);
        
		return panelTexteIA;    
    }
 
    public void actionPerformed(ActionEvent evt) {
        texteUser.append(saisie() + newline);
        zoneDeSaisie.selectAll();        
        texteUser.setCaretPosition(texteUser.getDocument().getLength());
       
        texteIA.append(reponse + newline);
        texteIA.setCaretPosition(texteUser.getDocument().getLength());
    }
    
    public static void centerWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 3.5);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 3.5);
        frame.setLocation(x, y);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new IHMV1_Design();
            }
        });
    }

	@Override
	public String saisie() {		
		return zoneDeSaisie.getText();
	}

	@Override
	public void affichage(String param) {
		this.reponse = param;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
