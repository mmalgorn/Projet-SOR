package bean;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

public class Photo implements Serializable {
	
	byte[] img;

	public Photo(Blob b) {
		try {
			if (b.length() > 0)
				this.img = b.getBytes(1, (int) b.length());
			b.free();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Photo(byte[] img) {
		this.img = img;
	}

	public byte[] getImg() {
		return img;
	}
	
	public Blob getBlob() throws SerialException, SQLException {
		return new SerialBlob(img);
	}
	
	public void setImg(byte[] img) {
		this.img = img;
	}
}
