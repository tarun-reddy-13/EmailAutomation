package com.wiley;

//import java.net.Authenticator;
import javax.mail.PasswordAuthentication;

import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App 
{
  
    //function to send mail
	// arguments -- name of the receiver and email of receiver.
    public void sendEmail(String username,String to) {
    	
    	String from="sender mail address to be used here";
    	String password = "password of sender mail";
    	
    	String host="smtp.gmail.com";
    	Properties properties = System.getProperties();
    	System.out.print("Properties: "+properties);
    	
    	//setting important info to properties
    	//host set
    	properties.put("mail.smtp.host", host);
    	properties.put("mail.smtp.port", "465");
    	properties.put("mail.smtp.ssl.enable", "true");
    	properties.put("mail.smtp.auth", "true");

    	//to get session obj...
    	Session session = Session.getInstance(properties, new Authenticator() {
    		@Override
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(from,password);    		}
    	});
    	
		session.setDebug(true);
    	
		//compose message
    	String message = "\tHello...Mr."+username+" Thanks gor using our services!!!\nHere is Your resume...";
		String sub="\nYour Resume is here";
    	
    	MimeMessage m = new MimeMessage(session);
    	
    	try {
    		//from email
    		m.setFrom(from);
    		
    		//adding recipient to message
    		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		
    		//adding subject to message
    		m.setSubject(sub);
    		
    		
    		
    		//attach the file
    		String path = "File path is to be given";
    		
    		MimeMultipart mimeMultipart = new MimeMultipart();
    		
    		MimeBodyPart textMime = new MimeBodyPart();
    		
    		MimeBodyPart fileMime = new MimeBodyPart();
    		
    		try {
    			
    			//adding mail body
    			
    			textMime.setText(message);
    			
    			File file = new File(path);
    			
    			fileMime.attachFile(file);
    			
    		}
    		catch(Exception E) {}
    		
    		//send the message using transport class
    		Transport.send(m);
    		
    		System.out.println("SUCCESSFULL......");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }
    
}
