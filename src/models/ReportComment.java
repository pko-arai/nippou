package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "reportscomment")

@NamedQueries({
    @NamedQuery(
            name = "comment_select",
            query = "SELECT rc.id FROM ReportComment AS rc WHERE rc.report_id = :report_id"),

    @NamedQuery(
            name = "comment_count",
            query = "SELECT COUNT(rc) FROM ReportComment AS rc WHERE rc.report_id = :report_id"),
})


@Entity
public class ReportComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment" , nullable = false)
    private String comment;

    @Column(name = "created_at" , nullable = false)
    private Timestamp created_at;

    @Column(name = "report_id" , nullable = false)
    private Integer report_id;

    @ManyToOne
    @JoinColumn(name = "employee_id" , nullable = false)
    private Employee employee;

    @Column(name = "updated_at" , nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Integer getReport_id() {
        return report_id;
    }

    public void setReport_id(Integer report_id) {
        this.report_id = report_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee_id(Employee employee) {
        this.employee = employee;
    }

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

}
