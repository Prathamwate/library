package com.example.User.Service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.User.Model.User;
import com.example.User.Repository.IUserRepo;

@Service
public class UserService {
	@Autowired
	IUserRepo iuserRepo;

	public int addUser(User user) {
		
	      User user1=iuserRepo.save(user);
	      return user1.getUserId();
	
	}

	public List<User> getUser(Integer userId) {
		List<User> userlist;
		if(userId!=null) {
			userlist=new ArrayList<>();
			userlist.add(iuserRepo.findById(userId).get());
		}
		else {
			userlist=iuserRepo.findAll();
		}
		return userlist;
	}

	public ResponseEntity<String> getUserbyId(Integer id) {
		try {
			User user=iuserRepo.findById(id).get();
			JSONObject json=new JSONObject(user);
			return new ResponseEntity<String>(json.toString(),HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("User Not Found",HttpStatus.ACCEPTED);
		}
		
	}
	
}
