package robert.oauth2jwt.db.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Role extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
