package com.siana.batchexample.controller;

import com.siana.batchexample.model.User;
import com.siana.batchexample.repository.UserRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoadController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/load")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        System.out.println("Job Execution Status" + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            System.out.println("Job is still running");
        }
        return jobExecution.getStatus();
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable("id") Integer id) {
        return userRepository.findUserById(id);
    }

    @GetMapping("/allUsers")
    private List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @GetMapping
    @RequestMapping("/hello")
    public String TestConnection() {
        return "Hello Siana!!!!";
    }
}
