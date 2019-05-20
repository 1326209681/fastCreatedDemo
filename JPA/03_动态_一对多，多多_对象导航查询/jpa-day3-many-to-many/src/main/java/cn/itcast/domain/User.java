package cn.itcast.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User
 * hasee
 * 2018/12/19
 * 11:57
 *
 * @Version 1.0
 **/
@Entity
@Table(name="sys_user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name")
    private String username;

    @Column(name="user_age")
    private Integer age;

    /*
     * 配置多对多的映射关系
     *       1. 声明表关系的配置
     *           @ManyToMany(targetEntity=Role.class)         //多对多
     *               targetEntity:   代表对方的实体类字节码
     *       2. 配置中间表（包含两个外键）
     *           @JoinTable
     *               name:中间表的名称
     *               joinColumns:配置当前对象在中间表的外键
     *                   @JoinColumn的数组
     *                       name:外键名
     *                       referencedColumnName:  参照的主表的主键名
     *               inverseJoinColumns: 配置对方对象在中间表的外键
     *       3. 这里的@JoinTable相当于创建了一个中间表，它里面包含了两个类的外键，通过外键将两个实体类或者两个表关联起来，
     *          形成了多对多的关系；
     * */
    @ManyToMany(targetEntity=Role.class,cascade = CascadeType.ALL)
    @JoinTable(name="sys_user_role",
            //joinColumns,当前对象在中间表中的外键
            joinColumns={@JoinColumn(name="sys_user_id", referencedColumnName="user_id")},
            //inverseJoinColumns,对方对象在中间表中的外键
            inverseJoinColumns={@JoinColumn(name="sys_role_id", referencedColumnName="role_id")}
    )
    private Set<Role> roles=new HashSet<Role>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles=roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId=userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age=age;
    }
}
