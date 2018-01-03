package com.kkolcz.app;

import com.kkolcz.config.*;
import com.kkolcz.dao.TmpDao;
import com.kkolcz.model.Tmp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, AppInitializer.class, HibernateConfig.class, SecurityConfig.class );
        TmpDao tmpDao = context.getBean(TmpDao.class);
        List<Tmp> tmpList = tmpDao.findAll();
        for(Tmp t : tmpList){
            System.out.println(t);
        }
    }
}
