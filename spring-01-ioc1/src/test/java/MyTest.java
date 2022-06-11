import com.zjh.dao.UserDaoMysqlImpl;
import com.zjh.dao.UserDaoOracleImpl;
import com.zjh.service.UserService;
import com.zjh.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");
        userServiceImpl.getUserSv();

//        UserService userService = new UserServiceImpl();
//        ((UserServiceImpl) userService).setUserDao(new UserDaoOracleImpl());
//        userService.getUserSv();
    }
}
