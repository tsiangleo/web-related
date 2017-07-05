package com.github.tsiangleo.mavenweb.dao.impl;

import com.github.tsiangleo.mavenweb.dao.UserDAO;
import com.github.tsiangleo.mavenweb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tsiang on 2017/5/27.
 */

@Repository
public class UserDAOImpl implements UserDAO{

    private static Map<String,User> userDB = new HashMap<String, User>();

    public void add(User user) {
        userDB.put(user.getLoginName(),user);
    }

    public List<User> listAll() {

        List<User> returnList = new ArrayList<User>();
        for (String loginName:userDB.keySet()){
            returnList.add(userDB.get(loginName));
        }
        return returnList;
    }
}
