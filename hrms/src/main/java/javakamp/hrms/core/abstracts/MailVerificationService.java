package javakamp.hrms.core.abstracts;

import javakamp.hrms.entities.concretes.Users;

public interface MailVerificationService {
	void mailVerification (Users users);
}
