//수정필요...

package daily0723;

import java.sql.Timestamp;
import java.sql.regtime;

public class ArticleDto() {}

public ArticleDto(String username, String subject, String content) {
	super();
	this.username = username;
	this.subject = subject;
	this.content = content;
}

public ArticleDto(int idx, String username, String subject, String content, Timestamp regtime) {
	super();
	this.idx = idx;
	this.username = username;
	this.subject = subject;
	this.content = content;
	this.regtime = regtime;
}
    private int idx;
    private String username;
    private String subject;
    private String content;
    private Timestamp regtime;

    // Getters and Setters
    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getRegtime() {
        return regtime;
    }

    public void setRegtime(Timestamp regtime) {
        this.regtime = regtime;
    }

    @Override
    public String toString() {
        return "ArticleDto [idx=" + idx + ", username=" + username + ", subject=" + subject + ", content=" + content + ", regtime=" + regtime + "]";
    }
}
