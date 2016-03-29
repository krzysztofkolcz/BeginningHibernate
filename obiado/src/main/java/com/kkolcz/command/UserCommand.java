package com.kkolcz.command;

import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.kkolcz.annotation.PasswordMatches;
import com.kkolcz.annotation.ValidEmail;
import com.kkolcz.model.UserProfile;
import com.kkolcz.model.User;

@PasswordMatches 
public class UserCommand{

    private int id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    private Set<UserProfile> userProfiles; 

    public UserCommand(){
    }

    public UserCommand(User user){
      this.id = user.getId();
      this.firstName = user.getFirstName();
      this.lastName = user.getLastName();
      this.password = user.getPassword();
      this.email = user.getEmail();
      this.userProfiles = user.getUserProfiles(); 
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }
 
    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
