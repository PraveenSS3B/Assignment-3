package pro.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigListener implements JobExecutionListener{
	Logger log = LoggerFactory.getLogger(ConfigListener.class);
	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("Job Starting ....");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("Job Completed....");
		
	}

}
