import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Jialin Jiao (jiaojialin@gmail.com)
 * @version 1.0
 *
 */

public class ComponentManager {

	private Map<String, Component> components;
	private Set<String> explictedInstalled;
	
	public ComponentManager(){
		this.components = new LinkedHashMap<String, Component>();
		explictedInstalled = new HashSet<String>();
	}
	public Map<String, Component> getComponents() {
		return components;
	}
	private void setComponents(Map<String,Component> components) {
		this.components = components;
	}
	public void addComponents(Component component){
		this.components.put(component.getName(), component);
	}
	public Component getComponent(String name){
		return this.components.get(name);
	}
	
	public void list(){
		for(Component c: this.components.values()){
			if(c.isInstalled()){
				System.out.println("	"+ c.getName());
			}
		}
	}
	
	
	
	public void addDependencies(String cname, String[] dependencies){
		Component c = new Component(cname);
		this.addComponents(c);
		
		for(String dname: dependencies){
			Component d = getComponent(dname);
			if(d==null) d = new Component(dname);
			c.addRequiredComponent(d);
			d.addSupportedComponent(c);
			this.addComponents(d);
		}
	}
	
	public void install(String name, boolean explictedInstalled){
		
		Component c = getComponent(name);
		
		if(c==null){
			c = new Component(name);
			this.addComponents(c);
			
		}
		if(c.isInstalled()) {
			System.out.println("  "+ c.getName() + " is already installed.");
			return;
		}
		
		if(explictedInstalled) this.explictedInstalled.add(name);
		
		for(String x: c.getRequiredComponents()){
			if(!getComponent(x).isInstalled()){
				install(x, false);
			}
		}
		c.setInstalled(true);
		System.out.println("  Installing " + name);	
	}

	
	public void remove(String name){
		Component c = getComponent(name);
		if(!c.isInstalled()){
			System.out.println("  "+ name + " is not installed.");
			return;
		}
		if(c.getSupportedComponents().size() > 0){
			System.out.println("  "+  name + " is still needed.");
			return;
		}
		c.setInstalled(false);
		System.out.println("  Removing " + name);
		this.explictedInstalled.remove(name);
		
		for(String n: c.getRequiredComponents()){
			
			Component r = getComponent(n);
			r.removeSupportedComponent(c);
			if(r.isInstalled() && r.getSupportedComponents().size() ==0 && !this.explictedInstalled.contains(r.getName())){
				remove(r.getName());
			}
		}
		
	}
	
	
	public static void main(String[] args) {

		ComponentManager mgr = new ComponentManager();

		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while (!line.equals("END")) {
			System.out.println(line);

			String[] columns = line.split("\\s+");
			switch (columns[0]) {
			case "DEPEND":
				mgr.addDependencies(columns[1], Arrays.copyOfRange(columns, 2, columns.length));
				break;
			case "INSTALL":
				mgr.install(columns[1], true);
				break;
			case "REMOVE":
				mgr.remove(columns[1]);
				break;
			case "LIST":
				mgr.list();
				break;
			default:
				System.out.println("Invalid Commands. Only DEPEND, INSTALL, REMOVE, and LIST are supported.");
				break;
				
			}

			line = in.nextLine();
		}

	}	
	

}
