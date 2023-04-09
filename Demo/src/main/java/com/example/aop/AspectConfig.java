package com.example.aop;

//import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//import com.example.controller.StudentController;


/*
 * com.example.controller, -> this is our pointcut,  which method to intercept
 *  and Before-> this is our advice when to intercept , and this
 * combinely is our Aspect.
 */
@Configuration
@Aspect
public class AspectConfig {

	// Logger logger = LoggerFactory.getLogger(StudentController.class);
	Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * @Before(value = "execution(* com.example.controller.*.*(..) )") public void
	 * beforAdvice(JoinPoint joinPoint) { logger.info("Inside the Before Advice. ");
	 * }
	 */
	
	/*
	 * @Before(value =
	 * "execution(* com.example.controller.*.*(..)) and args(object)") public void
	 * beforAdvice(JoinPoint joinPoint, Object object) { logger.info("Request =  " +
	 * object ); }
	 */
	
	/*
	 * @After(value =
	 * "execution(* com.example.controller.*.*(..)) and args(object)") public void
	 * beforAdvice(JoinPoint joinPoint, Object object) { logger.info("Request =  " +
	 * object ); }
	 */
	
	/*
	 * @AfterReturning(value =
	 * "execution(* com.example.controller.*.*(..)) and args(object)", returning =
	 * "returningObject") public void afterReturning(JoinPoint joinPoint, Object
	 * object, Object returningObject) { logger.info("Response =  " +
	 * returningObject ); }
	 */
	
	
	// AroundAdvice, -> combination of before advice, and after returning advice.
	@Around(value = "execution(* com.example.controller.*.*(..)) and args(object)"
		)
	public void beforAdvice(ProceedingJoinPoint proceedingJoinPoint, Object object) {
		logger.info("Request =  " + object );
		
		Object returningObject = null;
		
		
		try {
			returningObject = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Response = " + returningObject);
		
		
		
		
		
		
	}
	

}
