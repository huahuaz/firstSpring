package com.yc.biz;

import com.yc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service
public class StudentBizImpl {
    private StudentDao studentDao;

    public StudentBizImpl() {

    }



    //@Inject //javax中的依赖注入,如果有多个对象,(比如这里有 StudentDaoJpaImpl 和 StudentDaoMybatisImpl 对象)
    //因为有多个对象可以注入,所以这里必须用@Named("studentDaoJpaImpl"),如只有一个对象,则不需要写

    @Autowired //rg.springframework
    @Qualifier("studentDaoJpaImpl") //如果有多个对象,(比如这里有 StudentDaoJpaImpl 和 StudentDaoMybatisImpl 对象)
    //因为有多个对象可以注入,所以这里必须用@Named("studentDaoJpaImpl"),如只有一个对象,则不需要写

    //@Resource(name = "studentDaoJpaImpl")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int add(String name) {
        System.out.println("-----------业务层-----------");
        System.out.println("用户名是否重名?");
        int result = studentDao.add(name);
        System.out.println("-----------业务操作结束-----------");
        return result;
    }

    public void update(String name) {
        System.out.println("-----------业务层-----------");
        System.out.println("用户名是否重名?");
        studentDao.update(name);
        System.out.println("-----------业务操作结束-----------");
    }

}
