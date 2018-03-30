package services.mail;
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import javax.mail.internet.*;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import core.ICore;
import core.Service;
public class AttenteMail implements Service{

	private  String USER_NAME = "techbotdemo";  // GMail user name (just the part before "@gmail.com")
    private  String PASSWORD = "fgVFunR3Z94ueFnE"; // GMail password
    private ICore core;
	private ThreadAttente idleThread;
    
    public AttenteMail(ICore core){
    	this.core = core;
    }

    private  void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    public  void incomingMail(){
    	Properties properties = new Properties();
        // properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        properties.put("mail.imaps.timeout", "10000");
        Session session = Session.getInstance(properties);
        
        IMAPStore store = null;
        Folder inbox = null;
        
        try {
            store = (IMAPStore) session.getStore("imaps");
            store.connect(USER_NAME, PASSWORD);

            if (!store.hasCapability("IDLE")) {
                throw new RuntimeException("IDLE not supported");
            }

            inbox = (IMAPFolder) store.getFolder("INBOX");
            inbox.addMessageCountListener(new MessageCountAdapter(){
                @Override
                public void messagesAdded(MessageCountEvent event) {
                    Message[] messages = event.getMessages();

                    for (Message message : messages) {
                        try {
                        	System.out.println("Mail Subject:- " + message.getSubject());
							creerReponse(message);
						} catch (MessagingException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        //System.out.println("Mail Subject:- " + message.getSubject());
                    }
                }
            });
            

        
        } catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        idleThread = new ThreadAttente(inbox, this);
        idleThread.setDaemon(false);
        //TODO : j'aimerais comprendre c'est quoi ce setDeamon ?
        idleThread.start();
        try {
				idleThread.join();
		} catch (InterruptedException e) {
			idleThread.kill();
			
			
			System.err.println("Stopping the mailing thread");
		}
        //idleThread.kill(); //to terminate from another thread
        finally {
            close(inbox);
            close(store);
        }  
    }
    
    public  void close(final Folder folder) {
        try {
            if (folder != null && folder.isOpen()) {
                folder.close(false);
            }
        } catch (final Exception e) {
            // ignore
        }

    }
    
    
    
    public  void close(final Store store) {
        try {
            if (store != null && store.isConnected()) {
                store.close();
            }
        } catch (final Exception e) {
            // ignore
        }

    }

    public  void creerReponse(Message message) throws MessagingException, IOException{
    	System.out.println("get in rcreerreponse");
    	System.out.println(message.getFrom());
    	Address[] expediteurs = message.getFrom();
    	String[] adresses = new String[expediteurs.length];
    	for (int i=0; i<expediteurs.length; i++){
    		adresses[i]=expediteurs[i].toString();
    	}
    	String subject = setSubject(message.getSubject());
    	String body = createBody(getTextFromMessage(message));
    	sendFromGMail(USER_NAME, PASSWORD, adresses, subject, body);
    }
    
    private String createBody(String content) {
    	System.out.println(content);
		return this.core.askAI(content);
	}

	public  String setSubject(String s){
    	return "Reponse  :  " + s;
    }
	
	 public void ensureOpen(final Folder folder) throws MessagingException {

		 
		 
	        if (folder != null) {
	            Store store = folder.getStore();
	            if (store != null && !store.isConnected()) {
	                store.connect(USER_NAME, PASSWORD);
	            }
	        } else {
	            throw new MessagingException("Unable to open a null folder");
	        }

	        if (folder.exists() && !folder.isOpen() && (folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
	            System.out.println("open folder " + folder.getFullName());
	            folder.open(Folder.READ_ONLY);
	            if (!folder.isOpen())
	                throw new MessagingException("Unable to open folder " + folder.getFullName());
	        }

	    }

	@Override
	public void run() {
		 
	        
	        //sendFromGMail(from, pass, to, subject, body);
	        incomingMail();
		
	}
	
	
	private String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
	
	public void kill(){
		idleThread.kill();
		idleThread.interrupt();
	}

	@Override
	public Boolean isRunning() { 
		//TODO : A COMPLETER
		if(idleThread == null)
			return false;
		return idleThread.isAlive();
	}

	@Override
	public String getName() {
		return "email";
	}

	
	
}
