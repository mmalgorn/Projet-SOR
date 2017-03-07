package bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import annotation.Table;

@Table(name = "admin")
public class Admin {

	Integer	admin_id;
	String	admin_user;
	String	admin_password;
	
	public Admin() {}

	public Admin(Integer admin_id, String admin_user, String admin_password) {
		this.admin_id = admin_id;
		this.admin_user = admin_user;
		this.admin_password = admin_password;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Admin other = (Admin) obj;
		if (admin_id == null) {
			if (other.admin_id != null) return false;
		} else if (!admin_id.equals(other.admin_id)) return false;
		if (admin_password == null) {
			if (other.admin_password != null) return false;
		} else if (!admin_password.equals(other.admin_password)) return false;
		if (admin_user == null) {
			if (other.admin_user != null) return false;
		} else if (!admin_user.equals(other.admin_user)) return false;
		return true;
	}

	public Integer getAdmin_id() {
		return admin_id;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public String getAdmin_user() {
		return admin_user;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	
	public void encryptPwd() {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(this.admin_password.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			this.admin_password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public void setAdmin_user(String admin_user) {
		this.admin_user = admin_user;
	}

}
