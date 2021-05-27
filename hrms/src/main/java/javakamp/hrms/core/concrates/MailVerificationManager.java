package javakamp.hrms.core.concrates;

import org.springframework.stereotype.Service;

import javakamp.hrms.core.abstracts.MailVerificationService;
import javakamp.hrms.entities.concretes.Users;
@Service
public class MailVerificationManager implements MailVerificationService{

	@Override
	public void mailVerification(Users users) {
		System.out.println(users.getEmail() + "mail adresine kod gönderildi");
		System.out.println("mail adresi doğrulandı");
		
	}

	
	

}
