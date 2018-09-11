package com.richard.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richard.entity.User;
import com.richard.service.UserServcie;
import com.richard.utils.result.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value =  "用户相关的API列表",description="用户API组")
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserServcie userServcie;
	
	@PostMapping("/add")
	@ApiOperation(value = "用户新增", notes = "用户新增")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "信息id",required=false),
						@ApiImplicitParam(paramType = "body", dataType = "registtime", name = "id", value = "注册时间",required=false)})
	public ResponseEntity<JsonResult> addUser(@RequestBody(required=true) User user){
		JsonResult r = new JsonResult();
		try {
			//user.setUsername("ccccc");
			//user.setPassword("1234567");
			user.setRegisttime(new Date());
			this.userServcie.addUser(user);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	@DeleteMapping("/delete/{userId}")
	@ApiOperation(value = "通过用户id删除", notes = "通过用户id删除")
	public ResponseEntity<JsonResult> deleteUser(@RequestParam(name="userId",required=true) @ApiParam(required=true,value="用户id",defaultValue="1") Integer userId){
		JsonResult r = new JsonResult();
		try {
			this.userServcie.deleteUser(userId);
			r = responseMsg("ok","success");
		} catch (Exception e) {
			r = responseMsg("error",e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		} 
		return ResponseEntity.ok(r);
	}
	
	@PutMapping("/update")
	@ApiOperation(value = "用户修改", notes = "用户修改")
	public ResponseEntity<JsonResult> updateUser(@RequestBody(required=true) @ApiParam(required=true,value="待修改用户信息",defaultValue="") User user){
		JsonResult r = new JsonResult();
		try {
			this.userServcie.updateUser(user);
			r = responseMsg("ok","success");
		} catch (Exception e) {
			r = responseMsg("error",e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		} 
		return ResponseEntity.ok(r);
	}

	@GetMapping("/list")
	@ApiOperation(value = "用户列表", notes = "用户列表")
	public ResponseEntity<JsonResult> getUser(@RequestBody(required=false) User user, 
			@RequestParam(name="page",required=false) @ApiParam(required=false,value="页码[默认1]",defaultValue="1") Integer page,
			@RequestParam(name="size",required=false) @ApiParam(required=false,value="每页记录数[默认10]",defaultValue="5")Integer size){
		System.out.println(page);
		System.out.println(size);
		//User user = new User();
		page = (page==null || page<0)?1:page;
		size = (size==null || size<0)?5:size;
		JsonResult r = new JsonResult();
		try {
			List<User> users = this.userServcie.getUser(user,page,size);
			r.setResult(users);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
	@GetMapping("/get/{userId}")
	@ApiOperation(value = "用户id查询用户明细信息", notes = "用户id查询用户明细信息")
	public ResponseEntity<JsonResult> getUserById(
			@PathVariable("userId") @ApiParam(required=true,value="用户id",defaultValue="") Integer userId){
		JsonResult r = new JsonResult();
		try {
			//User user = this.userServcie.getUserById(userId);
			//r.setResult(user);
			//r.setStatus("ok");
			
			/***********************此块模块模拟高并发*****************************/
			Runnable myRunnable = new Runnable() {
				@Override
				public void run() {
					userServcie.getUserById(userId);
				}
			};
			
			//建立线程池,1000次请求，16并发量
	        ExecutorService exec = Executors.newFixedThreadPool(16);
	        int count = 0;
	        for(int i=0 ; i< 1000; i++){
	             exec.submit(myRunnable);
	             count++;
	        }
	        System.out.println(count+" 人次操作完成！");
	        
	        User user = this.userServcie.getUserById(userId);
	        r.setResult(user);
	        //r.setResult("success");
			r.setStatus("ok");
	        /***********************此块模块模拟高并发*****************************/
			
			
			
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	private JsonResult responseMsg(String status,Object result) {
		JsonResult r = new JsonResult();
		r.setStatus(status);
		r.setResult(result);
		return r;
	}
	
}
