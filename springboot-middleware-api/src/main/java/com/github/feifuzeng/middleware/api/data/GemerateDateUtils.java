package com.github.feifuzeng.middleware.api.data;

import com.github.feifuzeng.middleware.base.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 初始化数据
 * @createTime 2019年10月30日 14:36:00
 */
public class GemerateDateUtils {

    /**
     * 初始化用户列表
     * @return
     */
    public static List<User> initUserList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User("萧峰", 30, "打篮球");
        User user2 = new User("段誉", 23, "打篮球");
        User user3 = new User("虚竹", 25, "打篮球");
        User user4 = new User("丁春秋", 56, "打篮球");
        User user5 = new User("鸠摩智", 47, "打篮球");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        return userList;
    }

}
