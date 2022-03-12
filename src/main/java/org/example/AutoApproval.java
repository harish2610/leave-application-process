package org.example;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.spring.boot.starter.ClientProperties;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("autoApproved")
public class AutoApproval implements ExternalTaskHandler{

    protected static Logger LOG = LoggerFactory.getLogger(AutoApproval.class);
    protected String workerId;

    public AutoApproval(ClientProperties properties) {
        workerId = properties.getWorkerId();
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

                int noOfLeaves = externalTask.getVariable("noOfLeaves");
                externalTaskService.complete(externalTask);

                LOG.info("{}: The External Task {} has been completed with no. of leaves {}!", workerId, externalTask.getId(), noOfLeaves);
            }
    }
