package javakamp.hrms.core.concrates;

import org.springframework.stereotype.Service;

import javakamp.hrms.core.abstracts.MernisVerificationService;
import javakamp.hrms.entities.concretes.Candidates;
@Service
public class MernisVerificationManager implements MernisVerificationService {

	@Override
	public boolean mernisVerification(Candidates candidates) {
		if(candidates.getIdentityNumber().length()!=11) {
			System.out.println("mernis doğrulaması başarısız");
			return false;
		}else {
			System.out.println("mernis doğrulaması başarılı");
			return true;
		}
	}

}
