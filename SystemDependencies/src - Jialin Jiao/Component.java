import java.util.HashSet;
import java.util.Set;


/**
 * @author Jialin Jiao (jiaojialin@gmail.com)
 * @version 1.0
 *
 */

class Component {

	private String name;
	private boolean installed;
	
	private Set<String> requiredComponents;
	private Set<String> supportedComponents;
	
	public Component(String name) {
		this.setName(name);
		installed = false;
		requiredComponents = new HashSet<String>();
		supportedComponents = new HashSet<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInstalled() {
		return installed;
	}

	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	public Set<String> getRequiredComponents() {
		return requiredComponents;
	}

	public void setRequiredComponents(Set<String> requiredComponents) {
		this.requiredComponents = requiredComponents;
	}

	public Set<String> getSupportedComponents() {
		return supportedComponents;
	}

	public void setSupportedComponents(Set<String> supportedComponents) {
		this.supportedComponents = supportedComponents;
	}
	
	public void addSupportedComponent(Component c){
		this.supportedComponents.add(c.getName());
	}
	
	public void addRequiredComponent(Component c){
		this.requiredComponents.add(c.getName());
	}	

	public void removeSupportedComponent(Component c){
		this.supportedComponents.remove(c.getName());
	}
	
	public void removeRequiredComponent(Component c){
		this.supportedComponents.remove(c.getName());
	}	
	
	
}
