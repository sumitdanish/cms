package com.micromax.bugtracker;

// Generated Sep 28, 2015 4:35:12 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Category generated by hbm2java
 */

@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "category")
/* @XmlRootElement (name="category") */
public class Category implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 28400170953042438L;
	/**
	 * 
	 */

	private Integer id;
	// @JsonIgnore
	private Category category;
	private String categoryType;
	private String categoryName;
	private String createdBy;
	private Date createdTime;
	private Byte isActivated;
	private String columnName1;
	private String columnName2;
	private String columnName3;
	private String columnName4;
	private String columnName5;
	private String isProduct;
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<ProdVersion> prodVersions = new HashSet<ProdVersion>(0);
	private Set<Issue> issuesForCtId = new HashSet<Issue>(0);
	private Set<Issue> issuesForCt2Id = new HashSet<Issue>(0);
	private Set<Issue> issuesForCt1Id = new HashSet<Issue>(0);

	public Category() {
	}

	public Category(Integer id) {
		this.id = id;
	}

	public Category(Integer id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}

	public Category(Category category, Date createdTime) {
		this.category = category;
		this.createdTime = createdTime;
	}

	public Category(Category category, String categoryType,
			String categoryName, String createdBy, Date createdTime,
			Byte isActivated, String columnName1, String columnName2,
			String columnName3, String columnName4, String columnName5,
			String isProduct, Set<ProdVersion> prodVersions,
			Set<Category> categories, Set<Issue> issuesForCtId,
			Set<Issue> issuesForCt2Id, Set<Issue> issuesForCt1Id) {
		this.category = category;
		this.categoryType = categoryType;
		this.categoryName = categoryName;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.isActivated = isActivated;
		this.columnName1 = columnName1;
		this.columnName2 = columnName2;
		this.columnName3 = columnName3;
		this.columnName4 = columnName4;
		this.columnName5 = columnName5;
		this.categories = categories;
		this.prodVersions = prodVersions;
		this.issuesForCtId = issuesForCtId;
		this.issuesForCt2Id = issuesForCt2Id;
		this.issuesForCt1Id = issuesForCt1Id;
		this.isProduct = isProduct;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ct_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "category_type", length = 36)
	public String getCategoryType() {
		return this.categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	@Column(name = "category_name", length = 36)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "created_by", length = 256)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "is_activated")
	public Byte getIsActivated() {
		return this.isActivated;
	}

	public void setIsActivated(Byte isActivated) {
		this.isActivated = isActivated;
	}

	@Column(name = "columnName1", length = 256)
	public String getColumnName1() {
		return this.columnName1;
	}

	public void setColumnName1(String columnName1) {
		this.columnName1 = columnName1;
	}

	@Column(name = "columnName2", length = 256)
	public String getColumnName2() {
		return this.columnName2;
	}

	public void setColumnName2(String columnName2) {
		this.columnName2 = columnName2;
	}

	@Column(name = "columnName3", length = 256)
	public String getColumnName3() {
		return this.columnName3;
	}

	public void setColumnName3(String columnName3) {
		this.columnName3 = columnName3;
	}

	@Column(name = "columnName4", length = 256)
	public String getColumnName4() {
		return this.columnName4;
	}

	public void setColumnName4(String columnName4) {
		this.columnName4 = columnName4;
	}

	@Column(name = "columnName5", length = 256)
	public String getColumnName5() {
		return this.columnName5;
	}

	public void setColumnName5(String columnName5) {
		this.columnName5 = columnName5;
	}

	@Column(name = "is_product", length = 2)
	public String getIsProduct() {
		return this.isProduct;
	}

	public void setIsProduct(String isProduct) {
		this.isProduct = isProduct;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	@JsonIgnore
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<ProdVersion> getProdVersions() {
		return this.prodVersions;
	}

	public void setProdVersions(Set<ProdVersion> prodVersions) {
		this.prodVersions = prodVersions;
	}

	// @JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryByCtId")
	public Set<Issue> getIssuesForCtId() {
		return this.issuesForCtId;
	}

	public void setIssuesForCtId(Set<Issue> issuesForCtId) {
		this.issuesForCtId = issuesForCtId;
	}

	// @JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryByCt2Id")
	public Set<Issue> getIssuesForCt2Id() {
		return this.issuesForCt2Id;
	}

	public void setIssuesForCt2Id(Set<Issue> issuesForCt2Id) {
		this.issuesForCt2Id = issuesForCt2Id;
	}

	// @JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryByCt1Id")
	public Set<Issue> getIssuesForCt1Id() {
		return this.issuesForCt1Id;
	}

	public void setIssuesForCt1Id(Set<Issue> issuesForCt1Id) {
		this.issuesForCt1Id = issuesForCt1Id;
	}

	

}
