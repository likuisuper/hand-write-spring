package com.cxylk.springframework.test.bean;

/**
 * @author likui
 * @date 2021/11/18 3:50 下午
 **/
public class UserService {
    private String uId;

    private UserDao userDao;

    public void queryUserInfo(){
        System.out.println(userDao.queryByName(uId));
    }

    public String getUid() {
        return uId;
    }

    public void setUid(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
