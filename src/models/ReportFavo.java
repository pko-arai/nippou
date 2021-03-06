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

@Table(name = "reportsfavo")

@NamedQueries({
    @NamedQuery(
            name = "favo_id_count",
            query = "SELECT rf.report_id , COUNT(rf) FROM ReportFavo AS rf GROUP BY rf.report_id"
            ),

    @NamedQuery(
            name = "checkRegisteredReport_id",
            query = "SELECT COUNT(rf) FROM ReportFavo AS rf WHERE rf.report_id = :report_id AND rf.employee = :employee"
            ),

    @NamedQuery(
            name = "id_select",
            query = "SELECT rf.id FROM ReportFavo AS rf WHERE rf.report_id = :report_id AND rf.employee = :employee"
            )


})

@Entity
public class ReportFavo  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "report_id",nullable = false)
    private int report_id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "favo_id_count", nullable = false)
    private int favo_id_count;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getFavo_id_count() {
        return favo_id_count;
    }

    public void setFavo_id_count(int favo_id_count) {
        this.favo_id_count = favo_id_count;
    }


}