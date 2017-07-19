package one.kafuuchino.piller.models;

/**
 * Created by Junseok Oh on 2017-07-19.
 */

public class SettingsItem {
    private boolean isRightDataVisible = false;
    private String title, content, rightContent;

    public SettingsItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public SettingsItem(String title, String content, String rightContent) {
        this.title = title;
        this.content = content;
        this.rightContent = rightContent;
        this.isRightDataVisible = true;
    }

    public boolean isRightDataVisible() {
        return isRightDataVisible;
    }

    public void setRightDataVisible(boolean rightDataVisible) {
        isRightDataVisible = rightDataVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRightContent() {
        return rightContent;
    }

    public void setRightContent(String rightContent) {
        this.rightContent = rightContent;
    }
}
