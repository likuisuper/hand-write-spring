package bean;

/**
 * @author likui
 * @date 2021/11/16 4:10 下午
 **/
public class UserService {
    String name;

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息："+name);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
