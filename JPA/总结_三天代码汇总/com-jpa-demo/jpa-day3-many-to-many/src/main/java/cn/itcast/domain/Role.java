package cn.itcast.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User
 * hasee
 * 2018/12/19
 * 11:49
 *
 * @Version 1.0
 **/
@Entity //@Entity说明这个class是实体类，并且使用默认的orm规则，即class名即数据库表中表名，class字段名即表中的字段名
@Table(name="sys_role")     //建立映射关系
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

    @Column(name="role_name")
    private String roleName;



    //配置多对多
//    @ManyToMany(targetEntity=User.class)      //配置多表关系，这里面要写另外一个的字节码文件
//    @JoinTable(name="sys_user_role",          //文件名
//            //joinColumn:当前对象在中间表中的外键
//            joinColumns={@JoinColumn(name="sys_role_id", referencedColumnName="role_id")},
//            //inverseJoinColumns,对方对象在中间表的外键
//            inverseJoinColumns={@JoinColumn(name="sys_user_id", referencedColumnName="user_id")}
//    )
    @ManyToMany(mappedBy = "roles")     //配置多表关系
    private Set<User> users=new HashSet<User>();



    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users=users;
    }
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long releId) {
        this.roleId=releId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName=roleName;
    }


}
