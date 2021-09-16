package net.secudev.crudy.model.activite;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.secudev.crudy.model.shared.AEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Activite extends AEntity {
	
	private String login;
	private String action;
	private LocalDateTime moment;
}
