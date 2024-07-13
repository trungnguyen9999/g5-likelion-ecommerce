package com.likelion.ecommerce.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.entities.Brand;
import com.likelion.ecommerce.response.ResponseStandard;
import com.likelion.ecommerce.service.BrandService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
@Validated
public class BrandController {
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/public/all")
    public ResponseEntity<ResponseStandard> getBrandList() 
    {
		List<Brand> data = brandService.findAll();
		ResponseStandard rp = new ResponseStandard();
		rp.setMessage(Objects.isNull(data) ? "Không tìm thấy dữ liệu" : "Thành công");
		rp.setData(data);
        return ResponseEntity.ok().body(rp);
    }
	
	@PostMapping("/create")
    public ResponseEntity<ResponseStandard> saveBrand(@RequestBody Brand brand)
    {
		ResponseStandard rp = new ResponseStandard();

		rp.setMessage("Insert successful!");
		rp.setData(brandService.save(brand));
        return ResponseEntity.ok().body(rp);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable Integer id)
	{
		//Kiểm tra brand có sản phẩm không
		
		brandService.delete(id);
		return ResponseEntity.ok().body("Delete successful!");
	}
}
