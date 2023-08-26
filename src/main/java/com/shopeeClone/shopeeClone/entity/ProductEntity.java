package com.shopeeClone.shopeeClone.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private Double importPrice;
	
	@Column(nullable = false)
	private Double price;
	private Integer discountPercent;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable = false )
	private SupplierEntity suppilier;
	
	@OneToMany(mappedBy = "product")
	private List<ImageEntity> imageEntities = new ArrayList<>();

	public void addImageEntity(ImageEntity imageEntity){
		this.imageEntities.add(imageEntity);
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(Double importPrice) {
		this.importPrice = importPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public SupplierEntity getSuppilier() {
		return suppilier;
	}

	public void setSupplier(SupplierEntity suppilier) {
		this.suppilier = suppilier;
	}

	public List<ImageEntity> getImageEntities() {
		return imageEntities;
	}

	public void setImageEntities(List<ImageEntity> imageEntities) {
		this.imageEntities = imageEntities;
	}
	
}
