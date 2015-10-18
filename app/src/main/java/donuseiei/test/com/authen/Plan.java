package donuseiei.test.com.authen;

/**
 * Created by Pongpayak on 10/16/2015.
 */
public class Plan {
    private String prov;
    private String ip;
    private String mounthlyrate;
    private String cpu;
    private String mem;
    private String str;
    private String nwk;

    public Plan(){

    }

    public Plan(String prov,String ip,String mounthlyrate,String cpu,String mem,String str,String nwk){
        this.prov = prov;
        this.ip = ip;
        this.mounthlyrate = mounthlyrate;
        this.cpu = cpu;
        this.mem = mem;
        this.str = str;
        this.nwk = nwk;
    }

    public String getProv() {
        return prov;
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
    public String getMounthlyrate() {
        return mounthlyrate;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setProv(String name) {
        this.prov = name;
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
    public void setMounthlyrate(String mon) {
        mounthlyrate = mon;
    }
}
