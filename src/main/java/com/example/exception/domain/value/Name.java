package com.example.exception.domain.value;

import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"first", "middle", "last"})
public class Name {

    @NotEmpty
    @Column(name = "first")
    private String first;

    @NotEmpty
    @Column(name = "middle")
    private String middle;

    @NotEmpty
    @Column(name = "last")
    private String last;

    @Builder
    public Name(String first, String middle, String last) {
        this.first = first;
        this.middle = StringUtils.hasLength(middle) ? null : middle;
        this.last = last;
    }

    public String getFullName() {
        if (this.middle == null) {
            return String.format("%s %s", this.first, this.last);
        }
        return String.format("%s %s %s", this.first, this.middle, this.last);
    }

}
