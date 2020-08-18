package zhl.study.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Future;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础Entity
 */
@Setter
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_strategy")
    @GenericGenerator(name = "uuid_strategy",strategy = "uuid")
    protected String id;

    protected LocalDateTime createTime;

    protected LocalDateTime updateTime;

}
