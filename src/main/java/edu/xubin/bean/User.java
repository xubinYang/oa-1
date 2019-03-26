package edu.xubin.bean;


public class User {

    private String userid;
    private String name;
    private Integer mobile;
    private String email;
    private Integer departmentid;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }
}

//package edu.xubin.bean;
//
//
//        import org.hibernate.annotations.Cascade;
//
//        import javax.persistence.*;
//
//@Entity
//@Table(name = "user")
//public class UserEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "usercode")
//    private String usercode;
//
//    @Column(name = "username", nullable = false)
//    private String username;
//
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @ManyToOne
//    @JoinColumn(name = "departmentid", referencedColumnName = "departmentid", unique = true)
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private DepartmentEntity departmentEntity;
//
//
//    public String getUsercode() {
//        return usercode;
//    }
//
//    public void setUsercode(String usercode) {
//        this.usercode = usercode;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public DepartmentEntity getDepartmentEntity() {
//        return departmentEntity;
//    }
//
//    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
//        this.departmentEntity = departmentEntity;
//    }
//}
//
//

