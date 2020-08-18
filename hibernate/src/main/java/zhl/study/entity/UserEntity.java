package zhl.study.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

/**
 * zhl.study.entity.UserEntity
 */
@Entity
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity extends BaseEntity {

    @NotNull
    @Size(
            min = 2,
            max = 12,
            message = "min is 2 ,max is 12"
    )
    private String username;

    @NotNull
    @Size(
            min = 2,
            max = 12,
            message = "min is 2 ,max is 12"
    )
    private String password;

}
