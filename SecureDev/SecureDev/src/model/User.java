package model;

/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class that defines the user object.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class User {
	 
	private String username;
	private String password;
	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String Bday;
	private String gender;
	private String telephone;
	private boolean isAdmin;
	private boolean isProfileImage;

	public User(String username, String password, String id, String email, String firstName, String lastName,
			String bday, String gender,String telephone,boolean isProfileImage) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Bday = bday;
		this.gender = gender;
		this.telephone = telephone;
		this.isProfileImage = isProfileImage;
	}
	
	public boolean getisProfileImage() {
		return isProfileImage;
	}

	public void setProfileImage(boolean isProfileImage) {
		this.isProfileImage = isProfileImage;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBday() {
		return Bday;
	}

	public void setBday(String bday) {
		Bday = bday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
