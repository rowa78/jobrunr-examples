package de.ronnywalter.jobrunr.jobrunrexamples;

import de.ronnywalter.jobrunr.jobrunrexamples.jobs.AbstractJob;
import de.ronnywalter.jobrunr.jobrunrexamples.jobs.JobA;
import de.ronnywalter.jobrunr.jobrunrexamples.jobs.JobB;
import de.ronnywalter.jobrunr.jobrunrexamples.jobs.MyJobRequest;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class JobrunrExamplesApplication implements CommandLineRunner, ApplicationContextAware {

	private final JobA jobA;
	private final JobB jobB;

	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(JobrunrExamplesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// direct call of jobs
		//BackgroundJob.enqueue(() -> jobA.run());
		//BackgroundJob.enqueue(() -> jobB.run());

		// a bit more dynamically
		Map<String, AbstractJob> beans = applicationContext.getBeansOfType(AbstractJob.class);

		beans.values().forEach(job -> BackgroundJob.<AbstractJob>enqueue(x -> job.run()));

		// with jobRequests
		beans.values().forEach(job -> {
			MyJobRequest mjr = new MyJobRequest(UUID.randomUUID(), job.getClass().getName());
			BackgroundJobRequest.enqueue(mjr);
		});

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
