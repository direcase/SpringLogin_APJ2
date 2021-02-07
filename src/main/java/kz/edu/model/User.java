package kz.edu.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "UserEntity")
@Table(name = "users")
public class User implements Serializable
{
    private long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Role role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public long getId()
    {
        return this.user_id;
    }
    public void setId(long user_id)
    {
        this.user_id = user_id;
    }

    @Column(name="first_name")
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "last_name")
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Column(name = "email")
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {this.email = email;}

    @Column(name = "password")
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    public Role getRole()
    {
        return role;
    }
    public void setRole(Role role)
    {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}