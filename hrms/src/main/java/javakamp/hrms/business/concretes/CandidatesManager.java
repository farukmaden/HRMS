package javakamp.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javakamp.hrms.business.abstracts.CandidatesService;
import javakamp.hrms.core.abstracts.MailVerificationService;
import javakamp.hrms.core.abstracts.MernisVerificationService;
import javakamp.hrms.core.utilities.results.DataResult;
import javakamp.hrms.core.utilities.results.ErrorResult;
import javakamp.hrms.core.utilities.results.Result;
import javakamp.hrms.core.utilities.results.SuccessDataResult;
import javakamp.hrms.core.utilities.results.SuccessResult;
import javakamp.hrms.dataAccess.abstracts.CandidatesDao;
import javakamp.hrms.dataAccess.abstracts.UsersDao;
import javakamp.hrms.entities.concretes.Candidates;

@Service
public class CandidatesManager implements CandidatesService {

	private MailVerificationService mailVerificationService;
	private UsersDao usersDao;
	private CandidatesDao candidatesDao;
	private MernisVerificationService mernisVerificationService;

	@Autowired
	public CandidatesManager(CandidatesDao candidatesDao, UsersDao usersDao,
			MailVerificationService mailVerificationService, MernisVerificationService mernisVerificationService) {
		super();
		this.candidatesDao = candidatesDao;
		this.usersDao = usersDao;
		this.mailVerificationService = mailVerificationService;
		this.mernisVerificationService = mernisVerificationService;
	}

	@Override
	public Result record(Candidates candidates) {
		if (!firstNameControl(candidates)) {
			return new ErrorResult(false, "isim kısmı boş kalamaz");
		} else if (!lastNameControl(candidates)) {
			return new ErrorResult("soyisim kısmı boş kalamaz");
		} else if (!emailControl(candidates)) {
			return new ErrorResult("mail kısmı boş kalamaz");
		} else if (!identityNumberControl(candidates)) {
			return new ErrorResult("tc no kısmı boş kalamaz");
		} else if (!passwordControl(candidates)) {
			return new ErrorResult("şifre kısmı bış kalamaz");
		} else if (!birthDateControl(candidates)) {
			return new ErrorResult("doğum tarihi kısmı boş kalamaz");
		} else if (usersDao.findAllByEmail(candidates.getEmail()).stream().count() != 0) {
			return new ErrorResult("mail adresi kullanımda");
		} else if (emailTypeControl(candidates)) {
			return new ErrorResult("mail adresiniz mail formatında değil !");
		} else if (candidates.getPassword().equals(candidates.getPasswordRepeat()) == false) {
			return new ErrorResult("Şifreler Uyuşmuyor");
		} else if (mernisVerificationService.mernisVerification(candidates) == false) {
			return new ErrorResult("mernis doğrulamsı geçersiz");
		} else if (candidatesDao.findAllByIdentityNumber(candidates.getIdentityNumber()).stream().count() != 0) {
			return new ErrorResult("Tc numarası kullanımda");
		}else {
			this.mailVerificationService.mailVerification(candidates);
		this.candidatesDao.save(candidates);
		return new SuccessResult(true, "kayıt başarılı");
		}
		
	}

	@Override
	public DataResult<List<Candidates>> getAll() {

		return new SuccessDataResult<List<Candidates>>(this.candidatesDao.findAll(), "Başarıyla listelendi.");
	}

	public boolean firstNameControl(Candidates candidates) {
		if (candidates.getFirstName().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean lastNameControl(Candidates candidates) {
		if (candidates.getLastName().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean emailControl(Candidates candidates) {
		if (candidates.getEmail().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean passwordControl(Candidates candidates) {
		if (candidates.getPassword().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean identityNumberControl(Candidates candidates) {
		if (candidates.getIdentityNumber().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean birthDateControl(Candidates candidates) {
		if (candidates.getBirthDate() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean emailTypeControl(Candidates candidates) {
		if (candidates.getEmail().contains("@")) {
			return false;
		} else {
			return true;
		}
	}

}






