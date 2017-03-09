package bean;

import java.io.Serializable;
import java.sql.Timestamp;

import annotation.Table;

@Table(name = "log")
public class Log implements Serializable {

	int		log_id;
	int		log_id_admin;
	Timestamp	log_date;

	public Log(int log_id_admin, Timestamp log_date) {
		this.log_id_admin = log_id_admin;
		this.log_date = log_date;
	}
	
	public Log(int log_id, int log_id_admin, Timestamp log_date) {
		this.log_id = log_id;
		this.log_id_admin = log_id_admin;
		this.log_date = log_date;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Log other = (Log) obj;
		if (this.log_id != other.getLog_id()) return false;
		if (this.log_id_admin != other.getLog_id_admin()) return false;
		if (!this.log_date.equals(other.getLog_date())) return false;
		return true;
	}

	public Timestamp getLog_date() {
		return log_date;
	}

	public int getLog_id() {
		return log_id;
	}

	public int getLog_id_admin() {
		return log_id_admin;
	}

	public void setLog_date(Timestamp log_date) {
		this.log_date = log_date;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public void setLog_id_admin(int log_id_admin) {
		this.log_id_admin = log_id_admin;
	}

}
