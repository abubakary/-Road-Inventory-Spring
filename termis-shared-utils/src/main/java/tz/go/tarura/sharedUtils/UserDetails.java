package tz.go.tarura.sharedUtils;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

	private String id;
	private String email;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private boolean isAgent;  
	private String agentCode;
	private String name; 
	private String checkNumber;  
	private String username;   
	private String regionId;    
	private String councilCode;
	private List<AgentCouncil> agentCouncils;
	
   
}
