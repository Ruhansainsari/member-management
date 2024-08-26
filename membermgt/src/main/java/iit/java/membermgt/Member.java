package iit.java.membermgt;

import jakarta.persistence.*;

@Entity
@Table
public class Member {
    @Id
    @SequenceGenerator(name = "member_id", sequenceName = "member_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_sequence")
    private Long memberID;
    private String firstName;
    private String lastName;
    private String contactNum;
    private String email;
    private String membType;
    private String joinDate;
    private String expDate;
    private String membStatus;

    public Member(Long memberID, String firstName, String lastName, String contactNum, String email, String membType, String joinDate, String expDate, String membStatus) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNum = contactNum;
        this.email = email;
        this.membType = membType;
        this.joinDate = joinDate;
        this.expDate = expDate;
        this.membStatus = membStatus;
    }

    public Member() {

    }

    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
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

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembType() {
        return membType;
    }

    public void setMembType(String membType) {
        this.membType = membType;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getMembStatus() {
        return membStatus;
    }

    public void setMembStatus(String membStatus) {
        this.membStatus = membStatus;
    }
}
