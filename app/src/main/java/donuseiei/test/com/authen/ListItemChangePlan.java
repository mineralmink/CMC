package donuseiei.test.com.authen;

public class ListItemChangePlan {
    private String title;
    private String detailNew;
    private String detailOld;

    public ListItemChangePlan(String title, String detailNew, String detailOld) {
        this.title = title;
        this.detailNew = detailNew;
        this.detailOld = detailOld;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailNew() {
        return detailNew;
    }

    public void setDetailNew(String detailNew) {
        this.detailNew = detailNew;
    }

    public String getDetailOld() {
        return detailOld;
    }

    public void setDetailOld(String detailOld) {
        this.detailOld = detailOld;
    }

    @Override
    public String
    toString() {
        return "ListItemChangePlan{" +
                "title='" + title + '\'' +
                ", detailNew='" + detailNew + '\'' +
                ", detailOld='" + detailOld + '\'' +
                '}';
    }
}
