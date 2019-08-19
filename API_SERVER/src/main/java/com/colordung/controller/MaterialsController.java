package com.colordung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colordung.domain.Materials;
import com.colordung.domain.PagingList;
import com.colordung.domain.SearchMaterials;
import com.colordung.service.MaterialsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Materials for API")
@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/materials")
public class MaterialsController {
	@Autowired
	private MaterialsService materialsService;
	
	@ApiOperation(
			value = "",
			notes = "컨텐츠명 중복확인",
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
	@PostMapping("/getCountByName")
	public int getCountByName(@RequestBody Materials materials) {
		return materialsService.getCountByName(materials.getName());
	}
	
	@ApiOperation(
			value = "",
			notes = "컨텐츠 등록",
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
	@PostMapping("/save")
	public int saveContents(@RequestBody Materials materials) {
		return materialsService.saveMaterials(materials);
	}
	
	@ApiOperation(
			value = "",
			notes = "컨텐츠 삭제",
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
	@DeleteMapping("/{seq}")
	public int deleteContents(@PathVariable("seq") String seq){
		return materialsService.deleteMaterials(seq);
	}
	
	@ApiOperation(
			value = "",
			notes = "컨텐츠목록조회",
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
	@PostMapping("/list")
	public PagingList getMaterialsList(@RequestBody SearchMaterials searchMaterials){
		return materialsService.getMaterialsList(searchMaterials);
	}
	
}
