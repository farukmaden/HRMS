package javakamp.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javakamp.hrms.business.abstracts.JobTitlesService;
import javakamp.hrms.core.utilities.results.DataResult;
import javakamp.hrms.core.utilities.results.ErrorResult;
import javakamp.hrms.core.utilities.results.Result;
import javakamp.hrms.core.utilities.results.SuccessDataResult;
import javakamp.hrms.core.utilities.results.SuccessResult;
import javakamp.hrms.dataAccess.abstracts.JobTitlesDao;
import javakamp.hrms.entities.concretes.JobTitles;

@Service
public class JobTitleManager implements JobTitlesService{

	private JobTitlesDao jobTitlesDao;
	
	@Autowired
	public JobTitleManager(JobTitlesDao jobTitlesDao) {
		super();
		this.jobTitlesDao = jobTitlesDao;
	}

	@Override
	public DataResult<List<JobTitles>> getAll() {
		return new SuccessDataResult<List<JobTitles>>(this.jobTitlesDao.findAll(),"Başarıyla listelendi.");
	}

	@Override
	public Result record(JobTitles jobTitles) {
		
		if(jobTitlesDao.findAllByTitle(jobTitles.getTitle()).stream().count() !=0) {
			return new ErrorResult("İş pozisyonu zaten mevcut.");
		}else {
			this.jobTitlesDao.save(jobTitles);
		return new SuccessResult( true,	"kayıt başarılı");
		}
		
	}
	
	

}
