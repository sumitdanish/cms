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
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Issue generated by hbm2java
 */
@Entity
@Table(name = "issue")
/* @XmlRootElement(name = "issue") */
public class Issue implements java.io.Serializable {

	private Integer id;
	private Category categoryByCtId;
	private Category categoryByCt2Id;
	private Category categoryByCt1Id;
	private Issue issue;
	private ProdVersion prodVersion;
	private User user;
	private String title;
	private String description;
	private Date createdTime;
	private String moderatorId;
	private String columnName1;
	private String columnName2;
	private String columnName3;
	private String columnName4;
	private String columnName5;
	private Byte isOpen;
	private Byte isOpenAgain;
	private Set<UserComment> userComments = new HashSet<UserComment>(0);
	private Set<Fallow> fallows = new HashSet<Fallow>(0);
	private Set<AddFavouraite> addFavouraites = new HashSet<AddFavouraite>(0);
	private Set<ReportFlag> reportFlags = new HashSet<ReportFlag>(0);
	private Set<AttachementFile> attachementFiles = new HashSet<AttachementFile>(
			0);
	private Set<Issue> issues = new HashSet<Issue>(0);
	private Set<PrivateMessages> privateMessageses = new HashSet<PrivateMessages>(0);

	public Issue() {
	}
	
	public Issue(Integer id){
		this.id = id;
	}
	
	public Issue(Integer id,String title){
		this.id = id;
		this.title = title;
	}
	public Issue(Integer id,String title,String description){
		this.id = id;
		this.title = title;
		this.description = description;
	}
	public Issue(Category categoryByCtId, Category categoryByCt2Id,
			Category categoryByCt1Id, Issue issue, ProdVersion prodVersion,
			User user, String title, String description, Date createdTime,
			String moderatorId, String columnName1, String columnName2,
			String columnName3, String columnName4, String columnName5,
			Byte isOpen, Byte isOpenAgain, Set<UserComment> userComments,
			Set<Fallow> fallows, Set<AttachementFile> attachementFiles,
			Set<ReportFlag> reportFlags, Set<AddFavouraite> addFavouraites,
			Set<Issue> issues) {
		this.categoryByCtId = categoryByCtId;
		this.categoryByCt2Id = categoryByCt2Id;
		this.categoryByCt1Id = categoryByCt1Id;
		this.issue = issue;
		this.prodVersion = prodVersion;
		this.user = user;
		this.title = title;
		this.description = description;
		this.createdTime = createdTime;
		this.moderatorId = moderatorId;
		this.columnName1 = columnName1;
		this.columnName2 = columnName2;
		this.columnName3 = columnName3;
		this.columnName4 = columnName4;
		this.columnName5 = columnName5;
		this.isOpen = isOpen;
		this.isOpenAgain = isOpenAgain;
		this.userComments = userComments;
		this.fallows = fallows;
		this.attachementFiles = attachementFiles;
		this.reportFlags = reportFlags;
		this.addFavouraites = addFavouraites;
		this.issues = issues;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ct_id")
	@JsonIgnore
	public Category getCategoryByCtId() {
		return this.categoryByCtId;
	}

	public void setCategoryByCtId(Category categoryByCtId) {
		this.categoryByCtId = categoryByCtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ct2_id")
	@JsonIgnore
	public Category getCategoryByCt2Id() {
		return this.categoryByCt2Id;
	}

	public void setCategoryByCt2Id(Category categoryByCt2Id) {
		this.categoryByCt2Id = categoryByCt2Id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ct1_id")
	@JsonIgnore
	public Category getCategoryByCt1Id() {
		return this.categoryByCt1Id;
	}

	public void setCategoryByCt1Id(Category categoryByCt1Id) {
		this.categoryByCt1Id = categoryByCt1Id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "duplicate_id")
	@JsonIgnore
	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prod_version_id")
	@JsonIgnore
	public ProdVersion getProdVersion() {
		return this.prodVersion;
	}

	public void setProdVersion(ProdVersion prodVersion) {
		this.prodVersion = prodVersion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	@JsonIgnore
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "title", length = 300)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 512)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "moderator_id", length = 256)
	public String getModeratorId() {
		return this.moderatorId;
	}

	public void setModeratorId(String moderatorId) {
		this.moderatorId = moderatorId;
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

	@Column(name = "is_open")
	public Byte getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Byte isOpen) {
		this.isOpen = isOpen;
	}

	@Column(name = "is_open_again")
	public Byte getIsOpenAgain() {
		return this.isOpenAgain;
	}

	public void setIsOpenAgain(Byte isOpenAgain) {
		this.isOpenAgain = isOpenAgain;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
	public Set<UserComment> getUserComments() {
		return this.userComments;
	}

	public void setUserComments(Set<UserComment> userComments) {
		this.userComments = userComments;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
	public Set<Fallow> getFallows() {
		return this.fallows;
	}

	public void setFallows(Set<Fallow> fallows) {
		this.fallows = fallows;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
	public Set<AttachementFile> getAttachementFiles() {
		return this.attachementFiles;
	}

	public void setAttachementFiles(Set<AttachementFile> attachementFiles) {
		this.attachementFiles = attachementFiles;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
	public Set<ReportFlag> getReportFlags() {
		return this.reportFlags;
	}

	public void setReportFlags(Set<ReportFlag> reportFlags) {
		this.reportFlags = reportFlags;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
	public Set<AddFavouraite> getAddFavouraites() {
		return this.addFavouraites;
	}

	public void setAddFavouraites(Set<AddFavouraite> addFavouraites) {
		this.addFavouraites = addFavouraites;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
	@JsonIgnore
	public Set<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
	public Set<PrivateMessages> getPrivateMessageses() {
		return this.privateMessageses;
	}

	public void setPrivateMessageses(Set<PrivateMessages> privateMessageses) {
		this.privateMessageses = privateMessageses;
	}

}
