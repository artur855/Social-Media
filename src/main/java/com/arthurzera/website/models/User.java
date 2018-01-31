package com.arthurzera.website.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
	private Set<Post> posts;

	@OneToMany(mappedBy = "user")
	private Set<Comment> comments = new HashSet<>();

	@Column(nullable = false)
	private Date createdAt;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

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
	private Set<User> following;

	@ManyToMany(mappedBy = "following")
	private Set<User> followers;
	// TODO ADD UPVOTED POSTS
	// TODO ADD UPVOTED COMMENTS

	// TODO ADD DOWNVOTED POSTS
	// TODO ADD DOWNVOTED COMMENTS

	public User() {
	}

	public User(String username, String fullName, String email, String passwordHash) {
		this.username = username;
		this.fullName = fullName;
		this.email = new UserEmail(email);
		this.passwordHash = passwordHash;
		this.enabled = false;
		this.profilePictureUrl = "/img/profile_icons/default.png";
		this.roles = new HashSet<>();
		this.posts = new HashSet<>();
		this.comments = new HashSet<>();
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

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
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

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
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
}
