package de.ronnywalter.jobrunr.jobrunrexamples.jobs;

import lombok.Data;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Service;

@Service
public class MyJobRequestHandler implements JobRequestHandler<MyJobRequest> {


    @Override
    public void run(MyJobRequest myJobRequest) throws Exception {
        System.out.println(myJobRequest.getText());
    }
}
