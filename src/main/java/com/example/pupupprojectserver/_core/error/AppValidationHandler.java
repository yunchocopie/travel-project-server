package com.example.pupupprojectserver._core.error;

import com.example.pupupprojectserver._core.error.exception.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Aspect // AOP 등록
@Component // IoC 등록
public class AppValidationHandler {
    // Advice (부가 로직 hello 메서드)
    // Advice가 수행될 위치 == PointCut
    // @Before("@annotation(org.springframework.web.bind.annotation.RequestBody)")
    @Before("@annotation(shop.project.pathorderserver._core.errors.AppRequest)")
    public void validCheck(JoinPoint jp) {
        System.out.println("AppValidationHandler.validCheck() 호출");
        Object[] args = jp.getArgs(); // 파라메터(매개변수)
        for (Object arg : args) {
            if (arg instanceof Errors errors) {
                if (errors.hasErrors()) {
                    for (FieldError error : errors.getFieldErrors()) {
                        throw new Exception400(error.getDefaultMessage()); // + error.getField()
                    }
                }
            }
        }
    }
}
