package android.turingrobot.com.turingrobot;

/**
 * Created by User on 2017/2/6.
 */

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MessageAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private ArrayList<ChatMessage> data;
    private Html.ImageGetter getter;
    private static final int TYPE=2;
    public static final int MESSAGE_LEFT=0;
    public static final int MESSAGE_RIGHT=1;

    public MessageAdapter(LayoutInflater inflater, ArrayList<ChatMessage> data,Html.ImageGetter getter) {
        this.inflater = inflater;
        this.data = data;
        this.getter=getter;
    }

    @Override
    public int getViewTypeCount() {//得到当前缓存布局类型的数量
        return TYPE;//因为有左右两种布局，返回常量2
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();//返回当前布局的类型
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage=data.get(position);
        View view=null;
        ViewHolder viewHolder;
        int type=getItemViewType(position);
        if(convertView==null){
            switch (type){
                case MESSAGE_LEFT:
                    view=inflater.inflate(R.layout.leftmsg,null);
                    break;
                case MESSAGE_RIGHT:
                    view=inflater.inflate(R.layout.rightmsg,null);
                    break;
                default:
                    break;
            }
            viewHolder=new ViewHolder();
            viewHolder.touxiang= (ImageView) view.findViewById(R.id.touxiang);
            viewHolder.title= (TextView) view.findViewById(R.id.title);
            viewHolder.name= (TextView) view.findViewById(R.id.name);
            viewHolder.message= (TextView) view.findViewById(R.id.message);
            viewHolder.time= (TextView) view.findViewById(R.id.time);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.touxiang.setImageResource(chatMessage.getTouxiang());
        viewHolder.title.setText(chatMessage.getTitle());
        viewHolder.name.setText(chatMessage.getName());
        Spanned spanned=Html.fromHtml(chatMessage.getMessage(),getter,null);
        viewHolder.message.setText(spanned);//因为内容可能添加表情，所以传入富文本
        SimpleDateFormat format= new SimpleDateFormat("EEE HH:mm");
        String time=format.format(new Date(chatMessage.getTime()));
        viewHolder.time.setText(time);//设置显示时间的格式
        return view;
    }

    class ViewHolder{
        ImageView touxiang;
        TextView title;
        TextView name;
        TextView message;
        TextView time;
    }
}