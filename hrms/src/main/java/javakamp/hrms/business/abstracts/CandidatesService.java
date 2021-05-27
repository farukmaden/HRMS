package javakamp.hrms.business.abstracts;

import java.util.List;

import javakamp.hrms.core.utilities.results.DataResult;
import javakamp.hrms.core.utilities.results.Result;
import javakamp.hrms.entities.concretes.Candidates;

public interface CandidatesService {
	DataResult<List<Candidates>> getAll();
	Result record(Candidates candidates);
	
}
