package www.jinke.com.charmhome.bean;

/**
 * Created by root on 18-3-12.
 */

public class ExperienceBean {
    private String name;
    private boolean isSelect;
    private int pic_no;
    private int pic_yes;

    public int getPic_no() {
        return pic_no;
    }

    public void setPic_no(int pic_no) {
        this.pic_no = pic_no;
    }

    public int getPic_yes() {
        return pic_yes;
    }

    public void setPic_yes(int pic_yes) {
        this.pic_yes = pic_yes;
    }

    public ExperienceBean(String name, int pic_no, int pic_yes, boolean isSelect) {
        this.name = name;
        this.pic_no = pic_no;
        this.pic_yes = pic_yes;
        this.isSelect = isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

}
