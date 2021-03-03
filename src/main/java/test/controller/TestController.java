package test.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import jdk.internal.loader.Resource;
import test.dto.ProfileDto;
import test.dto.TestDto;
import test.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	TestService testService; 

	@GetMapping("/greeting")	
	public List<TestDto> greeting() {	
		List<TestDto> accList = testService.getAccountlist();
		return accList;
	
	}
	
	@GetMapping(value="/getList")
	public List<TestDto> getList() {	
		List<TestDto> accList = testService.getAccountlist();
		//List<TestDto> returnAccList = new ArrayList<TestDto>();
		/*for (int i = 0; i <accList.size(); i++) {
			
          TestDto account = accList.get(i);
          String accImageName=account.getImage();
          account.setImage("http://192.168.99.129:9090/images/"+"flower.jpg");
          returnAccList.add(account);
        }*/
		
		for (int i = 0; i <accList.size(); i++) {
            String image = accList.get(i).getImage();
            accList.get(i).setImage("http://192.168.99.129:9090/images/"+image);
        }
		return accList;
	
	}
	
	@GetMapping(value="/getProfileById/{id}")
	public ProfileDto getProfileById(@PathVariable(value = "id") String userId) {	
		ProfileDto profile = testService.getProfileById(userId);
		return profile;
	
	}
	
	@GetMapping(value = "/image/{id}")
	public @ResponseBody byte[] getImageById(@PathVariable(value = "id") String userId) throws IOException {
	    
	    return testService.getImageById(userId);
	}
	
	
	
	@GetMapping("/account/{id}")
	public TestDto getUsersById(@PathVariable(value = "id") String userId){
		return testService.getAcountById(userId);
		
   }
	
	@DeleteMapping("/delete/account/{id}")
	public void delete(@PathVariable(value = "id") String userId){
		 testService.delete(userId);
		
   }
	
   @PostMapping("/createAccount")
   public void post(@RequestBody TestDto dto){
		  testService.createAccount(dto);
		
 }
	
 @PutMapping("/update/account")
	public void update(@RequestBody TestDto dto){
		 testService.updateAcountById(dto);
		
  }
 
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
     registry.addResourceHandler("/images/**")
     .addResourceLocations("classpath:/static/","classpath:/images/")
     .setCachePeriod(0);
 }
 
 @PostMapping(value = "/updatePhoto",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
 public String updateUserPhoto(@RequestParam("emp") MultipartFile img) throws IOException {
	 
	 byte[] imageByteArray=img.getBytes();
	 String fileName = "4.jpg";
	 String fileLocation = new File("D:\\images").getAbsolutePath() + "\\" + fileName;
	 //String fileLocation =new File("resources\\static\\images").getAbsolutePath()+ "\\" + fileName;
	 FileOutputStream fos = new FileOutputStream(fileLocation);
	 fos.write(imageByteArray);
	 fos.close();
    
	// imageIoWrite(img);
        System.out.println("Request  update photo of "+ img.getName()+
        		" image bytes "+img.getBytes()+" original file name is "+img.getOriginalFilename()
        		+"  file ext is ");
        return "OK";
    }
 //@RequestParam("image") MultipartFile image
	
	
 public static void imageIoWrite(MultipartFile img) {
     BufferedImage bImage = null;
     try {
       //  File initialImage = new File(img);
         bImage = ImageIO.read((File) img);

         ImageIO.write(bImage, "jpg", new File("C://Users/image.jpg"));
         //ImageIO.write(bImage, "jpg", new File("C://Users/Rou/Desktop/image.png"));
        // ImageIO.write(bImage, "bmp", new File("C://Users/Rou/Desktop/image.bmp"));

     } catch (IOException e) {
           System.out.println("Exception occured :" + e.getMessage());
     }
     System.out.println("Images were written succesfully.");
}
	
	
}
