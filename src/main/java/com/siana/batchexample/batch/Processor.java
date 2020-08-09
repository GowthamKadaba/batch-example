package com.siana.batchexample.batch;

import com.siana.batchexample.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {

    private static final Map<String, String> dept = new HashMap<>();

    public Processor() {
        dept.put("001", "Science");
        dept.put("002", "Technology");
        dept.put("003", "Sports");
        dept.put("004", "Accounts");
        dept.put("005", "Philosophy");
    }

    @Override
    public User process(User user) throws Exception {
        String deptName = dept.get(user.getDept());
        user.setDept(deptName);
        user.setTime(new Date());
        return user;
    }
}
