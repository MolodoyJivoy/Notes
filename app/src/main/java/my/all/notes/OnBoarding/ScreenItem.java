package my.all.notes.OnBoarding;

public class ScreenItem {
    private String Title, Description;
    private int ImageImg;

    public ScreenItem(String title, String description, int imageImg) {
        Title = title;
        Description = description;
        ImageImg = imageImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImageImg() {
        return ImageImg;
    }

    public void setImageImg(int imageImg) {
        ImageImg = imageImg;
    }
}
