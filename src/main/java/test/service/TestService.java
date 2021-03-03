package test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import test.dto.ProfileDto;
import test.dto.TestDto;
import test.mapper.TestMapper;

@Service
public class TestService {
	
	@Autowired
	TestMapper testMapper;

	public List<TestDto> getAccountlist() {
		return testMapper.getAccountlist();
	}

	public TestDto getAcountById(String id) {
		// TODO Auto-generated method stub
		return testMapper.getAccountById(id);
	}

	public void delete(String userId) {
		// TODO Auto-generated method stub
		testMapper.delete(userId);
	}

	public void createAccount(TestDto dto) {
		// TODO Auto-generated method stub
		testMapper.createAccount(dto);
	}

	public void updateAcountById(TestDto dto) {
		// TODO Auto-generated method stub
		testMapper.updateAccountById(dto);
	}

	public ProfileDto getProfileById(String userId) {
		// TODO Auto-generated method stub
		return testMapper.getProfileById(userId);
	}

	public byte[] getImageById(String userId) {
		// TODO Auto-generated method stub
		System.out.println("userId is "+userId);
		return testMapper.getImageById(userId);
	}

}
