package zhl.study.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import zhl.study.annotion.Super;

@Super
@Data
@ToString(callSuper = true)
public class SuperUser extends User {

    private String address;



}
