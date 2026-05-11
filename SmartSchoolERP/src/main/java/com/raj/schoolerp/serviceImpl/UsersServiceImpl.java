package com.raj.schoolerp.serviceImpl;

import java.util.List;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.AdminDTO;
import com.raj.schoolerp.DTO.UsersDTO;
import com.raj.schoolerp.entity.UserRole;
import com.raj.schoolerp.entity.Users;
import com.raj.schoolerp.exception.UsersException;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.UsersService;
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepo;
		
	@Override
	public Users addUser(UsersDTO usersDTO) throws UsersException {
		
		Users newUser = new Users();
		/*newUser.setEmail(usersDTO.getEmail());
		newUser.setUserName(usersDTO.getUserName());
		newUser.setPassword(usersDTO.getPassword());
		newUser.setFullName(usersDTO.getFullName());
		newUser.setPhone(usersDTO.getPhone());
		newUser.setRole(usersDTO.getRole());*/
		
		BeanUtils.copyProperties(usersDTO, newUser);
		
		//verify the given customer already exists or not
		Optional<Users> userObj = usersRepo.findByEmail(usersDTO.getEmail());
			
		if(userObj.isPresent()) {
			
			throw new UsersException("User Email Already Exists");
		}
		return usersRepo.save(newUser);
	}

	@Override
	public Users addUserAdmin(AdminDTO adminDTO) throws UsersException {
		Users newAdmin = new Users();
		/*newAdmin.setEmail(adminDTO.getEmail());
		newAdmin.setUserName(adminDTO.getUserName());
		newAdmin.setPassword(adminDTO.getPassword());
		newAdmin.setFullName(adminDTO.getFullName());
		newAdmin.setPhone(adminDTO.getPhone());*/
		
		BeanUtils.copyProperties(adminDTO, newAdmin);
		
		newAdmin.setRole(UserRole.ROLE_ADMIN);
		
		//verify the given customer already exists or not
		Optional<Users> userObj = usersRepo.findByEmail(adminDTO.getEmail());
			
		if(userObj.isPresent()) {
			
			throw new UsersException("Admin Email Already Exists");
		}
		return usersRepo.save(newAdmin);
	}

	@Override
	public Users getUserByEmailId(String emailId) throws UsersException {
		
		return usersRepo.findByEmail(emailId).orElseThrow(()-> new UsersException("Email Id Not Found"));
	}

	@Override
	public Users getUserDetails(Long userId) throws UsersException {
		
		return usersRepo.findById(userId).orElseThrow(()-> new UsersException("User Not Found"));
	}

	@Override
	public Users changePassword(Long userId, UsersDTO usersDTO) throws UsersException {
		
		Users userObj = usersRepo.findById(userId).orElseThrow(()-> new UsersException("User Not Found"));
		
		if(usersDTO.getPassword().length()>=5) {
			
			return usersRepo.save(userObj);
		}else {
			throw new RuntimeErrorException(null,"Please Provide a Valid Password");
		}		
	}

	@Override
	public List<Users> getAllUserDetails() throws UsersException {
		
		return usersRepo.findAll();
	}

	@Override
	public String deactivate(Long userId) throws UsersException {
		
		Users existingUser = usersRepo.findById(userId).orElseThrow(()-> new UsersException("User Not Found"));
		existingUser.setActive(false);
		usersRepo.save(existingUser);
		
		return "Account is Deactivated Successfully";
	}

}
