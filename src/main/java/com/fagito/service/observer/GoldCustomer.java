package com.fagito.service.observer;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.fagito.model.Customer;
import com.fagito.repository.SignUpRepository;

public class GoldCustomer implements Observer {
	
	SignUpRepository signupRepository;
	Object cust_obj;
	public GoldCustomer(Object cust_obj,SignUpRepository signUpRepository)
	{
		this.signupRepository=signUpRepository;
		this.cust_obj=cust_obj;
	}
	
	public void update(Object obj) throws Exception 
	{
		if(obj instanceof GoldService)
		{
			GoldService gs=(GoldService) obj;
			sendMail(gs.getGoldOrNotGold_list());
		}
	}
	
	public void sendMail(List<GoldorNotGold> gs) throws Exception
	{
		String gold=null;
		Iterator<GoldorNotGold> list=gs.iterator();
		String mail_id;
		while(list.hasNext())
		{
			Customer cust=(Customer)cust_obj;
			GoldorNotGold gold_obj=list.next();
			if(gold_obj.getIs_gold()==1)
				gold="G";
			if(gold==cust.getMembership())
			{
				mail_id=signupRepository.findEmailById(cust.getCustomer_id());
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
			    props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				      protected PasswordAuthentication getPasswordAuthentication() {
				         return new PasswordAuthentication("amalalexatl@gmail.com", "KUDU@2016");
				      }
				   });
				
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("amalalexatl@gmail.com", false));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail_id));
				msg.setSubject("Gold Membership ");
				msg.setContent(gold_obj.getMessage(),"text/html");
				msg.setSentDate(new Date());
				Transport.send(msg);
				System.out.println("after sendimng mail");
			}
		}
	}
	

}
