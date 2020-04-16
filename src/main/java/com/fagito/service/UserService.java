package com.fagito.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fagito.dto.CustomerDTO;
import com.fagito.dto.LoginDTO;
import com.fagito.dto.SignUpDTO;
import com.fagito.model.Customer;
import com.fagito.model.SignUp;
import com.fagito.model.Student_mail;
import com.fagito.repository.CustomerRepository;
import com.fagito.repository.SignUpRepository;
import com.fagito.repository.StudentMailRepository;
import com.fagito.service.visitor.CustomerElement;
import com.fagito.service.visitor.ObjectElement;
import com.fagito.service.visitor.ObjectElementList;
import com.fagito.service.visitor.ValidationVisitorImpl;
import com.fagito.view.Login_Output_to_Ui;

@Service
@PropertySource("application.properties")
public class UserService implements UserServiceInterface
{
	@Value("${spring.user.already_exsist}")
	String errorRegister;
	@Value("${spring.user.successLogin}")
	String successLogin;
	@Value("${spring.user.wrongCredentials}")
	String wrong;
	@Value("${spring.user.successRegister}")
	String successRegister;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SignUpRepository signupRepository;
	@Autowired
	private StudentMailRepository studentmailRepository;
	
	
	public UserDetails getuserbyemail(String emailid) {
	SignUp user=signupRepository.getUserByEmailid(emailid);
	User Usedetails=null;
	if(user !=null) {
		ArrayList<GrantedAuthority> Authority= new ArrayList<>();
		Usedetails=new User(user.getEmail(),user.getPassword(),Authority);
		
	}else {
		throw new UsernameNotFoundException("No user found in the system");
	}
	return Usedetails;
	}
	public String addUser(CustomerDTO customerDTO,SignUpDTO signupDTO) throws Exception
	{
		
		Customer customer_model=new Customer();
		SignUp signup_model=new SignUp();
		List<Student_mail> mail_list;
		int reference_id;
		int sign_up_id;
		
		String user_reference=null;
		//validating user details
		/*result=userValidator.validate(signupDTO);
		if(result!=null)
		{
			return result;
		}*/
		
		ObjectElementList objectElementList=new ObjectElementList();
		objectElementList.addElements(new CustomerElement(signupDTO));
		try
		{
			objectElementList.accept(new ValidationVisitorImpl());
		}
		catch(Exception e)
		{
			throw e;
		}
		
		
		//check for existing user
		int exisiting_user=this.getUserByEmail(signupDTO.getEmail());
		if(exisiting_user!=0)
		{
			return errorRegister;
		}
		//checking for student mail or not
		mail_list=studentmailRepository.findAll();
		Pattern p = Pattern.compile("(.*)@(.*)[.](.*)[.](.*)");
		Matcher m = p.matcher(signupDTO.getEmail());
		if (m.matches()) {
			System.out.print(m.group(3));
		    String domain=m.group(3);
	        for (Student_mail mail : mail_list) 
	        {
	        	if(domain.equals(mail.getMail_domain()))
	        	{
	        		user_reference="S";
	        		break;
	        	}
	        }
		}
		if(user_reference==null)
        	user_reference="C";
		
		//setting up values to customer and signup model
		String reference_id_string=customerRepository.findLastRecord(user_reference);
		
		if(reference_id_string!=null)
			reference_id=Integer.parseInt(reference_id_string.substring(1));
		else
			reference_id=99;
		
		
		String sign_up_id_string=signupRepository.findLastRecord();
		if(sign_up_id_string!=null)
			sign_up_id=Integer.parseInt(sign_up_id_string.substring(2));
		else
			sign_up_id=99;

		customerDTO.setCustomer_id(user_reference+String.valueOf(reference_id+1));
		customerDTO.setMembership("N");
		BeanUtils.copyProperties(customerDTO, customer_model);
		customer_model.setUsertype(user_reference);
		customer_model.setValidity(null);
		
		
		signupDTO.setSign_up_id("SU"+String.valueOf(sign_up_id+1));
		signupDTO.setUser_id(customerDTO.getCustomer_id());
		
		BeanUtils.copyProperties(signupDTO, signup_model);
		
		
		customerRepository.save(customer_model);
		signupRepository.save(signup_model);
		return successRegister;
	}
	
	public int getUserByEmail(String email)
	{
		
		int exsist_result=signupRepository.findByEmailbyId(email);
		System.out.println(exsist_result);
		
		return exsist_result;
	}
	public Login_Output_to_Ui verifyUser(LoginDTO loginDTO) throws Exception
	{
		Login_Output_to_Ui login_out=new Login_Output_to_Ui();
		SignUp exsist_user=signupRepository.getByEmail(loginDTO.getEmail(),loginDTO.getPassword());
		if(exsist_user!=null)
		{
			String name=customerRepository.findNameByCustomerId(exsist_user.getUser_id());
			login_out.setCustomer_id(exsist_user.getUser_id());
			login_out.setCustomer_name(name);
			return login_out; 
		}
		throw new Exception(wrong);
	}
		
}	
		
