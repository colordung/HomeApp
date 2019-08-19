package com.colordung.controller;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colordung.domain.Menu;
import com.colordung.service.SystemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "System for API")
@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/system")
public class SystemController {
	@Autowired
	private SystemService systemService;
	
	@ApiOperation(
			value = "",
			notes = "메뉴 목록조회",
			httpMethod = "GET",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			responseHeaders = {
					// Headers ...
			})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "No param")
			// Other Http Status Code ...
	})	
	@ApiImplicitParams({
		@ApiImplicitParam(
				name="jwt-header",
				value = "authorization header",
				required = true,
				dataType = "string", 
				paramType = "header", 
				defaultValue = ""
		)
	})
	@GetMapping("/menu/menuList")
	public List<Menu> getMenuList() {
		return systemService.getMenuList();
	}
	
	@ApiOperation(
			value = "",
			notes = "메뉴전체 목록조회",
			httpMethod = "GET",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			responseHeaders = {
					// Headers ...
			})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "No param")
			// Other Http Status Code ...
	})	
	@ApiImplicitParams({
		@ApiImplicitParam(
				name="jwt-header",
				value = "authorization header",
				required = true,
				dataType = "string", 
				paramType = "header", 
				defaultValue = ""
		)
	})
	@GetMapping("/menu/menuAllList")
	public List<Menu> getMenuAllList() {
		return systemService.getMenuAllList();
	}	
	
	
	@ApiOperation(
			value = "",
			notes = "메뉴 상세조회",
			httpMethod = "GET",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			responseHeaders = {
					// Headers ...
			})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "No param")
			// Other Http Status Code ...
	})	
	@ApiImplicitParams({
		@ApiImplicitParam(
				name="jwt-header",
				value = "authorization header",
				required = true,
				dataType = "string", 
				paramType = "header", 
				defaultValue = ""
		)
	})
	@GetMapping("/menu/{menuSeq}")
	public Menu getMenu(@PathVariable("menuSeq") String menuSeq) {
		return systemService.getMenu(menuSeq);
	}
	
	@ApiOperation(
			value = "",
			notes = "메뉴 저장",
			httpMethod = "POST",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			responseHeaders = {
					// Headers ...
			})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "No param")
			// Other Http Status Code ...
	})	
	@ApiImplicitParams({
		@ApiImplicitParam(
				name="jwt-header",
				value = "authorization header",
				required = true,
				dataType = "string", 
				paramType = "header", 
				defaultValue = ""
		)
	})
	@PostMapping("/menu/saveMenu")
	public int getMenu(@RequestBody Menu menu) {
		return systemService.saveMenu(menu);
	}	

	@ApiOperation(
			value = "",
			notes = "메뉴 삭제",
			httpMethod = "DELETE",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			responseHeaders = {
					// Headers ...
			})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "No param")
			// Other Http Status Code ...
	})	
	@ApiImplicitParams({
		@ApiImplicitParam(
				name="jwt-header",
				value = "authorization header",
				required = true,
				dataType = "string", 
				paramType = "header", 
				defaultValue = ""
		)
	})
	@DeleteMapping("/menu/{menuSeq}")
	public int deleteMenu(@PathVariable("menuSeq") String menuSeq){
		return systemService.deleteMenu(menuSeq);
	}
	
	@ApiOperation(
			value = "",
			notes = "메뉴명 중복조회",
			httpMethod = "POST",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			responseHeaders = {
					// Headers ...
			})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "No param")
			// Other Http Status Code ...
	})	
	@ApiImplicitParams({
		@ApiImplicitParam(
				name="jwt-header",
				value = "authorization header",
				required = true,
				dataType = "string", 
				paramType = "header", 
				defaultValue = ""
		)
	})
	@PostMapping("/menu/duplicationMenu")
	public int getDuplicationMenuCheck(@RequestBody Menu menu){
		return systemService.getDuplicationMenuCheck(menu);
	}
	
}

