package projects.adrian.homebudget.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import projects.adrian.homebudget.aop.annotation.ItemWithIdMustExist;

import java.lang.reflect.Method;
import java.util.UUID;

@Component
@Aspect
@AllArgsConstructor
public class ItemWithIdMustExistAspect {

    private final ApplicationContext applicationContext;

    @Pointcut("@annotation(projects.adrian.homebudget.aop.annotation.ItemWithIdMustExist)")
    public void itemWithIdMustExistAnnotation(){
        
    }
    
    @Before("itemWithIdMustExistAnnotation()")
    @SneakyThrows
    public void validateIfObjectExist(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ItemWithIdMustExist annotation = method.getAnnotation(ItemWithIdMustExist.class);
        Object[] args = joinPoint.getArgs();
        UUID uuid = (UUID) args[0];

        Object serviceBean = applicationContext.getBean(annotation.serviceClass());
        Class<?> serviceClass = serviceBean.getClass();
        Method checkIfExistByIdMethod = serviceClass.getMethod(annotation.checkExistByIdMethodName(), UUID.class);

        boolean isExist = (boolean) checkIfExistByIdMethod.invoke(serviceBean, uuid);

        if (!isExist){
            throw new RuntimeException("Item id does not exist");
        }

    }

//    @After("itemWithIdMustExistAnnotation()")
//    public void afterAdvice() {
//
//    }
//
//    @AfterReturning("itemWithIdMustExistAnnotation()")
//    public void afterReturning() {
//
//    }
//
//    @AfterThrowing("itemWithIdMustExistAnnotation()")
//    public void afterThrowing() {
//
//    }
//
//    @SneakyThrows
//    @Around("itemWithIdMustExistAnnotation()")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) {
//        proceedingJoinPoint.proceed();
//    }

}
