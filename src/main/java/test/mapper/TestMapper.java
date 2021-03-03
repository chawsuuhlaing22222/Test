package test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import test.dto.ProfileDto;
import test.dto.TestDto;

@Mapper
public interface TestMapper {

	List<TestDto> getAccountlist();

	TestDto getAccountById(String id);

	void delete(String userId);

	void createAccount(TestDto dto);

	void updateAccountById(TestDto dto);

	ProfileDto getProfileById(String userId);

	byte[] getImageById(String userId);

}
