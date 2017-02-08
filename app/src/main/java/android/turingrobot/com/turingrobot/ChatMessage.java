package android.turingrobot.com.turingrobot;

/**
 * Created by User on 2017/2/6.
 */

public class ChatMessage {
    private int touxiang;//头像
    private String title;//标签
    private String name;//昵称
    private String message;//消息内容
    private long time;//发送时间
    private int type;//消息类型，即左边或者右边

    public ChatMessage(int touxiang, String title, String name, String message, long time) {
        this.touxiang = touxiang;
        this.title = title;
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(int touxiang) {
        this.touxiang = touxiang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
