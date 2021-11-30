package com.example.exception.domain.value;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"postcode", "homeAddress", "detailAddress"})
public class Address {

    @NotEmpty
    @Column(name = "postcode")
    private String postcode;

    @NotEmpty
    @Column(name = "homeAddress")
    private String homeAddress;

    @NotEmpty
    @Column(name = "detailAddress")
    private String detailAddress;

    @Builder
    public Address(String postcode, String homeAddress, String detailAddress) {
        this.postcode = postcode;
        this.homeAddress = homeAddress;
        this.detailAddress = detailAddress;
    }

    public String getFullAddress() {
        return String.format("%s %s %s", postcode, homeAddress, detailAddress);
    }

}
