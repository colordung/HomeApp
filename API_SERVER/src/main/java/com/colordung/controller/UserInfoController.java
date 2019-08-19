package com.colordung.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colordung.domain.UserInfo;
import com.colordung.domain.SearchUser;
import com.colordung.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User for API")
@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(
            value = "",
            notes = "사용자 저장",
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
    @PostMapping("/saveUser")
    public UserInfo saveMember(@RequestBody UserInfo userInfo) {
        return userInfoService.saveMember(userInfo);
    }
    @ApiOperation(
            value = "",
            notes = "사용자상세조회",
            httpMethod = "GET",
//			produces = "application/json",
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
    @GetMapping(value={"/{id}"})
    public UserInfo getMember(@PathVariable("id") String id){
        return userInfoService.getMember(id);
    }

    @ApiOperation(
			value = "",
			notes = "사용자ID 중복체크",
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
	@GetMapping("/duplicationUserIdCheck/{id}")
	public int getDuplicationUserIdCheck(@PathVariable("id") String id){
		return userInfoService.getDuplicationUserIdCheck(id);
	}

}