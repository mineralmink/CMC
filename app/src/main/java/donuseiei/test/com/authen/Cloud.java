package donuseiei.test.com.authen;

/**
 * Created by Pai on 10/14/2015.
 */
public class Cloud {
    private String name;
    private String cpu;
    private String mem;
    private String str;
    private String nwk;
    private String cloudPro;

    public Cloud(){

    }

    public Cloud(String name,String cloudPro,String cpu,String mem,String str,String nwk){
        this.cloudPro = cloudPro;
        setName(name);
        setCpu(cpu);
        setMemory(mem);
        setStorage(str);
        setNetwork(nwk);
    }

    public String getCloudPro() {
        return cloudPro;
    }
    public void setCloudPro(String cloudPro) {
        this.cloudPro = cloudPro;
    }
    public String getName() {
        return name;
    }
    public String getCpu() {
        return cpu;
    }
    public String getMemory() {
        return mem;
    }
    public String getNetwork() {
        return nwk;
    }
    public String getStorage() {
        return str;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    public void setMemory(String mem) {
        this.mem = mem;
    }
    public void setNetwork(String nwk) {
        this.nwk = nwk;
    }
    public void setStorage(String str) {
        this.str = str;
    }
}
