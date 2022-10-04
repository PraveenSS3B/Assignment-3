package pro.operations;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import pro.dao.Book;
import pro.listener.ConfigListener;
import pro.processors.ConfigProcessor;
import pro.readers.ConfigReader;
import pro.writers.ConfigWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private ConfigReader configReader;

	@Autowired
	private ConfigWriter configWriter;

	@Autowired
	private ConfigProcessor configProcessor;

	@Autowired
	private ConfigListener configListener;

	
	Logger log = LoggerFactory.getLogger(BatchConfig.class);

	@Bean
	public Step step3() throws MalformedURLException {
		log.info("Performing Step 3");
		return stepBuilderFactory.get("Step 3").<Book, Book>chunk(2).reader(configReader.readerXml())
				.processor(configProcessor.processor3()).writer(configWriter.xml2dbjdbcWriter()).build();
	}

	@Bean
	public Job job() throws MalformedURLException {
		return jobBuilderFactory.get("Job 3").incrementer(new RunIdIncrementer()).start(step3())
				.listener(configListener)
				.build();
	}

}
