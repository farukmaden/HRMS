package javakamp.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javakamp.hrms.business.abstracts.EmployersService;
import javakamp.hrms.core.abstracts.MailVerificationService;
import javakamp.hrms.core.utilities.results.DataResult;
import javakamp.hrms.core.utilities.results.ErrorResult;
import javakamp.hrms.core.utilities.results.Result;
import javakamp.hrms.core.utilities.results.SuccessDataResult;
import javakamp.hrms.core.utilities.results.SuccessResult;
import javakamp.hrms.dataAccess.abstracts.EmployersDao;
import javakamp.hrms.dataAccess.abstracts.UsersDao;
import javakamp.hrms.entities.concretes.Employers;
@Service
public class EmployersManager implements EmployersService{

	private UsersDao usersDao;
	private EmployersDao employersDao;
	private MailVerificationService mailVerificationService;

	@Autowired
	public EmployersManager(EmployersDao employersDao,UsersDao usersDao,MailVerificationService mailVerificationService) {
		super();
		this.employersDao = employersDao;
		this.usersDao = usersDao;
		this.mailVerificationService =  mailVerificationService;
	}

	@Override
	public DataResult<List<Employers>> getAll() {
		return new SuccessDataResult<List<Employers>>(this.employersDao.findAll(),"Başarıyla listelendi.");
	}

	@Override
	public Result record(Employers employers) {
		if(!companyNameControl(employers)) {
			return new ErrorResult("Şirket ismi boş kalamaz.");
		}else if(!webAddressControl(employers)) {
			return new ErrorResult("Web adres kısmı boş kalamaz");
		}else if(!emailControl(employers)) {
			return new ErrorResult("E-mail kısmı boş kalamaz");
		}else if(!passwordControl(employers)) {
			return new ErrorResult("Şifre kısmı boş kalamaz");
		}else if(employers.getPassword().equals(employers.getPasswordRepeat()) == false) {
			return new ErrorResult("Şifreler uyuşmuyor");
		}else if(!phoneNumberControl(employers)) {
			return new ErrorResult("Telefon numarası kısmı boş kalamaz");
			
		}else if(!mailAndWebbaddressControl(employers)){
			return new ErrorResult("E-mailiniz web adresinizdeki domain ile aynı değil.");
			
		}else if(usersDao.findAllByEmail(employers.getEmail()).stream().count() !=0){
			return new ErrorResult("E-mail kullanımda");
		}
		this.mailVerificationService.mailVerification(employers);
		this.employersDao.save(employers);
		return new SuccessResult(true , "eklendi");
	}
	
	public boolean companyNameControl (Employers employers) {
		if(employers.getCompanyName().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	public boolean webAddressControl(Employers employers) {
		if(employers.getWebAddress().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	public boolean emailControl(Employers employers ) {
		if(employers.getEmail().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	public boolean passwordControl(Employers employers ) {
		if(employers.getPassword().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	public boolean phoneNumberControl(Employers employers) {
		if(employers.getPhoneNumber().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	public boolean mailAndWebbaddressControl(Employers employers) {
		String [] splitMail = employers.getEmail().split("@");
		String []splitWebaddress = employers.getWebAddress().split("www.");
		if(!splitMail[1].equals(splitWebaddress[1])) {
			return false;
		}else {
			return true;
		}
	}

}



