package de.ronnywalter.jobrunr.jobrunrexamples.jobs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MyJobRequest implements JobRequest {

    private UUID uuid;
    private String text;

    @Override
    public Class<? extends JobRequestHandler> getJobRequestHandler() {
        return MyJobRequestHandler.class;
    }
}
