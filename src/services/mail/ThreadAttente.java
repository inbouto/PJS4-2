package services.mail;
import javax.mail.Folder;

import com.sun.mail.imap.IMAPFolder;

public class ThreadAttente extends Thread{
	private final Folder folder;
    private volatile boolean running = true;

    public ThreadAttente(Folder folder) {
        super();
        this.folder = folder;
    }

    public synchronized void kill() {

        if (!running)
            return;
        this.running = false;
    }
    
    

    @Override
    public void run() {
        while (running) {

            try {
                Appli.ensureOpen(folder);
                System.out.println("enter idle");
                System.out.println(folder.getFullName());
                ((IMAPFolder) folder).idle();
            } catch (Exception e) {
                // something went wrong
                // wait and try again
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    // ignore
                }
            }

        }
    }
}
