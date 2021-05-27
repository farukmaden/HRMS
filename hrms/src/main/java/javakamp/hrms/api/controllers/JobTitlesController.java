package javakamp.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javakamp.hrms.business.abstracts.JobTitlesService;
import javakamp.hrms.core.utilities.results.DataResult;
import javakamp.hrms.core.utilities.results.Result;
import javakamp.hrms.entities.concretes.JobTitles;

@RestController
@RequestMapping("/api/titles")
public class JobTitlesController {

	private JobTitlesService jobTitleService;
	
	@Autowired
	public JobTitlesController(JobTitlesService jobTitleService) {
		super();
		this.jobTitleService = jobTitleService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobTitles>> getAll(){
		return this.jobTitleService.getAll();
	}
	@PostMapping("/record")
	public Result record(@RequestBody JobTitles jobTitles) {
		return this.jobTitleService.record(jobTitles);
	}
}