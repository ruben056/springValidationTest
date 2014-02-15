import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import be.rd.beans.Contact;


public class ContactValidatorTest {

	private ClassPathXmlApplicationContext appCtx;
	private ClassPathXmlApplicationContext getAppCtx(){
		if(appCtx == null){
			appCtx = new ClassPathXmlApplicationContext("beanvalidationctx.xml");
		}
		return appCtx;
	}
	
	@Test
	public void testContactValidator(){
		
		Contact A = getAppCtx().getBean("contactA", Contact.class);
		Contact B = getAppCtx().getBean("contactB", Contact.class);
		
		Validator validator = getAppCtx().getBean("validator", Validator.class);
		
		BeanPropertyBindingResult resultA = new BeanPropertyBindingResult(A, "testA");
		BeanPropertyBindingResult resultB = new BeanPropertyBindingResult(B, "testB");
		
		ValidationUtils.invokeValidator(validator, A, resultA);
		ValidationUtils.invokeValidator(validator, B, resultB);
		
		List<ObjectError> errorsA = resultA.getAllErrors();
		List<ObjectError> errorsB = resultB.getAllErrors();
		
		printContentOfList(errorsA);
		printContentOfList(errorsB);
		
		Assert.assertTrue(errorsA.size() == 0);
		Assert.assertTrue(errorsB.size() == 1);
	}
	
	private void printContentOfList(List<ObjectError> list){
		for(ObjectError cur : list){
			System.out.println(cur.getCode());
		}
	}
}
