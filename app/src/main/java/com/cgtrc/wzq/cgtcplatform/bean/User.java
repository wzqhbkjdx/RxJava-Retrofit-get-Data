package com.cgtrc.wzq.cgtcplatform.bean;

import com.cgtrc.wzq.cgtcplatform.utils.Constants;
import com.cgtrc.wzq.cgtcplatform.utils.UserSaveUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bym on 16/4/12.
 */
public class User implements Serializable, Cloneable {

    /**
     * @Fields: serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    public final static String TAG = "User";

    private User() {
    }

    public static User getInstance() {
        return UserHolder.getUser();
    }


    /**
     * 静态内部类的单例模式
     */
    private static class UserHolder {

        private static User sInstance ;

        private static User getUser() {
            if(sInstance == null) {
                Object object = UserSaveUtils.restoreObject(Constants.CACHEDIR + TAG);
                if (object == null) { // App第一次启动，文件不存在，则新建之
                    object = new User();
                    UserSaveUtils.saveObject(Constants.CACHEDIR + TAG, object);
                }
                sInstance = (User) object;
            }
            return sInstance;
        }
    }

    /**
     * 包访问权限的save方法
     */
    void save() {
        UserSaveUtils.saveObject(Constants.CACHEDIR + TAG, this);
    }

    /**
     * 通过clone的方式返回一个复制品,这个复制品的属性有可能通过User类的Getter和Setter方法被修改后的
     * @return
     */
    public User getLogindeUser() {
        return (User) getInstance().clone();
    }


    @Override
    protected Object clone() {
        User user = null;
        try {
            user = (User) super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return user;
    }

    private boolean loginStatus; //登录状态
    private String userName;
    private String secret;
    private String cookie;
    private long userID;
    private String headPortraitLink;
    private List<CircleItem> userStars; //用户的收藏
    private List<CircleItem> userPubs; //用户发布的技术分享的条目

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getHeadPortraitLink() {
        return headPortraitLink;
    }

    public void setHeadPortraitLink(String headPortraitLink) {
        this.headPortraitLink = headPortraitLink;
    }

    public List<CircleItem> getUserStars() {
        return userStars;
    }

    public void setUserStars(List<CircleItem> userStars) {
        this.userStars = userStars;
    }

    public List<CircleItem> getUserPubs() {
        return userPubs;
    }

    public void setUserPubs(List<CircleItem> userPubs) {
        this.userPubs = userPubs;
    }
}
