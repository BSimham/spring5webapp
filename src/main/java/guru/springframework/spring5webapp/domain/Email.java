package guru.springframework.spring5webapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Email {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String cust_name;
	private String cust_email;
	private String subject;
	private String intropurpose;
	private String body;
	private String closing;
	private Boolean flag=false;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_email() {
		return cust_email;
	}
	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getIntropurpose() {
		return intropurpose;
	}
	public void setIntropurpose(String intropurpose) {
		this.intropurpose = intropurpose;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getClosing() {
		return closing;
	}
	public void setClosing(String closing) {
		this.closing = closing;
	}
	public Email() {
		
	}
	
	@Override
	public String toString() {
		return "Email [id=" + id + ", cust_name=" + cust_name + ", cust_email=" + cust_email + ", subject=" + subject
				+ ", intropurpose=" + intropurpose + ", body=" + body + ", closing=" + closing + ", flag=" + flag + "]";
	}
	public Email(Long id, String cust_name, String cust_email, String subject, String intropurpose, String body,
			String closing, Boolean flag) {
		super();
		this.id = id;
		this.cust_name = cust_name;
		this.cust_email = cust_email;
		this.subject = subject;
		this.intropurpose = intropurpose;
		this.body = body;
		this.closing = closing;
		this.flag = flag;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public void setFlag() {
		this.flag = false;
	}
	
	

}
