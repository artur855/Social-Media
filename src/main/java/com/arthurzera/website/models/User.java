package com.arthurzera.website.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30, unique = true)
	private String username;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_email")
	private UserEmail email;

	@Column(length = 200)
	private String fullName;

	@Column(length = 100)
	private String passwordHash;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@JoinColumn(name = "verification_token")
	private VerificationToken verificationToken;

	@OneToMany(mappedBy = "author")
	private List<Post> posts;

	@Column
	private Date birthday;

	@Column
	private String cellphoneNumber;

	@OneToMany(mappedBy = "user", targetEntity=Comment.class)
	private List<Comment> comments;

	@OneToMany(mappedBy = "postEvaluator", targetEntity=PostEvaluation.class)
	private List<PostEvaluation> postEvaluations;

	@OneToMany(mappedBy="commentEvaluator", targetEntity=CommentEvaluation.class)
	private List<CommentEvaluation> commentEvaluations;
	
	@Column(nullable = false)
	private Date createdAt;

	@ManyToMany
	private List<Role> roles;

	@Column
	private Boolean enabled;

	@Column
	private String profilePictureUrl;

	@Column
	private String aboutMe;

	@Column
	private Date lastSeen;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "followed_id"))
	private List<User> following;

	@ManyToMany(mappedBy = "following")
	private List<User> followers;

	public User() {
	}

	public User(String username, String fullName, String email, String passwordHash) {
		this.username = username;
		this.fullName = fullName;
		this.email = new UserEmail(email);
		this.passwordHash = passwordHash;
		this.enabled = false;
		this.profilePictureUrl = "/img/profile_icons/default.png";
		this.roles = new ArrayList<>();
		this.posts = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.createdAt = new Date();
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", fullName=" + fullName
				+ ", passwordHash=" + passwordHash + ", created_at=" + createdAt + '}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserEmail getEmail() {
		return email;
	}

	public void setEmail(UserEmail email) {
		this.email = email;
	}

	public String getCreatedAt() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(createdAt);

	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
		role.getUsers().add(this);
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
		role.getUsers().add(this);
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public VerificationToken getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}

	public String getLastSeen() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return (lastSeen != null) ? format.format(lastSeen) : "First login!!!";
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public void follow(User user) {
		this.following.add(user);
		user.getFollowers().add(this);
	}

	public void unfollow(User user) {
		this.following.remove(user);
		user.getFollowers().remove(this);
	}

	public boolean isFollowing(User user) {
		return this.following.contains(user);
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

	public List<PostEvaluation> getPostEvaluations() {
		return postEvaluations;
	}

	public void setPostEvaluations(List<PostEvaluation> postEvaluations) {
		this.postEvaluations = postEvaluations;
	}
	
	public List<Post> getPostUpvoted() {
		List<Post> upvoted = new ArrayList<>();
		this.postEvaluations.stream().filter(evaluation -> "UP".equalsIgnoreCase(evaluation.getEvalution().name()))
				.forEach(evaluation -> upvoted.add(evaluation.getPost()));
		return upvoted;
	}
	public List<Post> getPostDownVoted() {
		List<Post> upvoted = new ArrayList<>();
		this.postEvaluations.stream().filter(evaluation -> "DOWN".equalsIgnoreCase(evaluation.getEvalution().name()))
		.forEach(evaluation -> upvoted.add(evaluation.getPost()));
		return upvoted;
	}

	public List<CommentEvaluation> getCommentEvaluations() {
		return commentEvaluations;
	}

	public void setCommentEvaluations(List<CommentEvaluation> commentEvaluations) {
		this.commentEvaluations = commentEvaluations;
	}

	
	public List<Comment> getCommentUpvoted() {
		List<Comment> upvoted = new ArrayList<>();
		this.commentEvaluations.stream().filter(evaluation -> "UP".equalsIgnoreCase(evaluation.getEvalution().name()))
		.forEach(evaluation -> upvoted.add(evaluation.getCommentEvaluated()));
		return upvoted;
	}
	public List<Comment> getCommentDownvoted() {
		List<Comment> upvoted = new ArrayList<>();
		this.commentEvaluations.stream().filter(evaluation -> "DOWN".equalsIgnoreCase(evaluation.getEvalution().name()))
		.forEach(evaluation -> upvoted.add(evaluation.getCommentEvaluated()));
		return upvoted;
	}

}
