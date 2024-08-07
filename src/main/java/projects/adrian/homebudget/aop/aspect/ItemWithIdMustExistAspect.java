package projects.adrian.homebudget.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ItemWithIdMustExistAspect {

    @Before("@annotation(projects.adrian.homebudget.aop.annotation.ItemWithIdMustExist)")
    public void beforeAdvice(){

    }

}
