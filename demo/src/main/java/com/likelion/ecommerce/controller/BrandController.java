package com.likelion.ecommerce.controller;

import java.util.List;
import java.util.Objects;

import com.likelion.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	private final BrandService brandService;

	private final ProductService productService;

	@GetMapping("/public/all")
    public ResponseEntity<ResponseStandard> getBrandList() 
    {
		List<Brand> data = brandService.findAll();
		ResponseStandard rp = new ResponseStandard();
		rp.setMessage(Objects.isNull(data) ? "Không tìm thấy dữ liệu" : "Thành công");
		rp.setData(data);
        return ResponseEntity.ok().body(rp);
    }

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
    public ResponseEntity<ResponseStandard> saveBrand(@RequestBody Brand brand)
    {
		ResponseStandard rp = new ResponseStandard();
		rp.setMessage("Insert successful!");
		rp.setData(brandService.save(brand));
        return ResponseEntity.ok().body(rp);
    }

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable Integer id)
	{
		if (productService.countByBrandId(id) > 0) {
			return ResponseEntity.ok().body("Brand has some product!");
		}
		brandService.delete(id);
		return ResponseEntity.ok().body("Delete successful!");
	}
}
