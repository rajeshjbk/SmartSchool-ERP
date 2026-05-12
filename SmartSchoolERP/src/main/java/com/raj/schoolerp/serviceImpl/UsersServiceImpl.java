package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.UsersDTO;
import com.raj.schoolerp.exception.UsersException;
import com.raj.schoolerp.model.UserRole;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepo;

	@Override
	public Users addUser(UsersDTO usersDTO) throws UsersException {

		Users newUser = new Users();

		BeanUtils.copyProperties(usersDTO, newUser);

		return usersRepo.save(newUser);
	}

	@Override
	public Users updateUser(Long userId, UsersDTO usersDTO) throws UsersException {

		Users existUser = usersRepo.findById(userId).orElseThrow(() -> new UsersException("User Not Found"));

		BeanUtils.copyProperties(usersDTO, existUser);

		return usersRepo.save(existUser);
	}

	@Override
	public Users getUserById(Long userId) throws UsersException {

		return usersRepo.findById(userId).orElseThrow(() -> new UsersException("Wrong User Id"));
	}

	@Override
	public List<Users> getAllUsers() throws UsersException {

		return usersRepo.findAll();
	}

	@Override
	public Users getUserByUserName(String userName) throws UsersException {

		return usersRepo.findUserByUserName(userName).orElseThrow(() -> new UsersException("Wrong Username"));
	}

	@Override
	public List<Users> getUsersByRole(UserRole role) throws UsersException {

		List<Users> users = usersRepo.findUsersByRole(role);

		if (users.isEmpty()) {

			throw new UsersException("No Users Found");
		}

		return users;
	}

	@Override
	public String deactivate(Long userId) throws UsersException {

		Users existingUser = usersRepo.findById(userId).orElseThrow(() -> new UsersException("User Not Found"));
		existingUser.setActive(false);
		usersRepo.save(existingUser);

		return "Account is Deactivated Successfully";
	}

	@Override
	public String deleteUserById(Long userId) throws UsersException {

		usersRepo.findById(userId).orElseThrow(() -> new UsersException("User Not Found"));

		usersRepo.deleteById(userId);

		return "User deleted with ID: " + userId;
	}

	@Override
	public String deleteUsers(List<Long> userIds) throws UsersException {

		List<Users> users = usersRepo.findUsersByIds(userIds);

		if (users.isEmpty()) {

			throw new UsersException("No Users Found");
		}

		usersRepo.deleteAll(users);

		return "Users deleted successfully";
	}

}
