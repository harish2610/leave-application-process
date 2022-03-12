package org.example;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
@ExternalTaskSubscription("leaveApplicationReview") // create a subscription for this topic name
public class LeaveAppReview implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
                    VariableMap variables = Variables.createVariables();
                    variables.put("requestId", "ABC-"+new Random().nextInt(16));
                    variables.put("noOfLeaves", new Random().nextInt(16));

                    // complete the external task
                    externalTaskService.complete(externalTask,variables);
                    System.out.println("The Leave Application " + externalTask.getId() + " has been reviewed!");
    }
}
