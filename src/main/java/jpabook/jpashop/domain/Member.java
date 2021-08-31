package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String names;
    private String city;
    private String street;
    private String zipcode;//우편번호


    //==보통 getter는 기본적으로 만들어줄 필요가 있고, SETTER는 상황에 따라서 만들도록 한다==//
    //==SETTER를 만들면 여기저기 다 쓸 수 있게 되므로 유지보수에 좋지 않다.==//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
