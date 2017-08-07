package robert.oauth2.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue
    @Column(name = "ID")
    private Long id;

	@JsonIgnore
	private String uuid = UUID.randomUUID()
			.toString();

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o )
			return true;

		if ( o == null || getClass() != o.getClass() )
			return false;

		BaseEntity that = (BaseEntity) o;
		return this.hashCode() == that.hashCode();
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}
}
