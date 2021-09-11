package net.secudev.crudy.model.shared;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AEntity {

	@Id
	private String id = UUID.randomUUID().toString();
}
