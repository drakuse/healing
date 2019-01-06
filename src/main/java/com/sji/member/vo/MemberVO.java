package com.sji.member.vo;

public class MemberVO {
	private String id;
	private String password;
	private String age;
	private String gender;
	private String name;
	private String email;

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", age=" + age + ", gender=" + gender + ", name="
				+ name + ", email=" + email + "]";
	}

	public MemberVO() {
		super();
	}

	public MemberVO(String id, String password, String age, String gender, String name, String email) {
		this.id = id;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}