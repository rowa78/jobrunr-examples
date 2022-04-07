package de.ronnywalter.jobrunr.jobrunrexamples.jobs;

import org.springframework.stereotype.Component;

@Component
public class JobA extends AbstractJob {

    @Override
    public void run() {
        System.out.println("Job A!");
    }
}
