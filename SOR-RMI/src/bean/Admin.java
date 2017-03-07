package bean;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import annotation.Table;

@Table(name = "admin")
public class Admin implements Serializable {

	int		admin_id;
	String	admin_user;
	String	admin_password;

	public Admin() {
	}

	public Admin(int admin_id, String admin_user, String admin_password) {
		this.admin_id = admin_id;
		this.admin_user = admin_user;
		this.admin_password = admin_password;
	}

	public void encryptPwd() {
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(admin_password.getBytes());
			final byte[] digest = md.digest();
			final StringBuffer sb = new StringBuffer();
			for (final byte b : digest)
				sb.append(String.format("%02x", b & 0xff));
			admin_password = sb.toString();
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Admin a = (Admin) obj;
		if (this.admin_id != a.getAdmin_id()) return false;
		if (!this.admin_user.equals(a.getAdmin_user())) return false;
		if (!this.admin_password.equals(a.getAdmin_password())) return false;
		return true;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public String getAdmin_user() {
		return admin_user;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public void setAdmin_user(String admin_user) {
		this.admin_user = admin_user;
	}

}
