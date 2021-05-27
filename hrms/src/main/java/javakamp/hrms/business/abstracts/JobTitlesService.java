package javakamp.hrms.business.abstracts;

import java.util.List;

import javakamp.hrms.core.utilities.results.DataResult;
import javakamp.hrms.core.utilities.results.Result;
import javakamp.hrms.entities.concretes.JobTitles;

public interface JobTitlesService {
	
	
	
	DataResult<List<JobTitles>> getAll();
	
	Result record (JobTitles jobTitles);
	

}
