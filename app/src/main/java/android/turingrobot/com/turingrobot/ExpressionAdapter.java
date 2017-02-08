package android.turingrobot.com.turingrobot;

/**
 * Created by User on 2017/2/6.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

/** * Created by Administrator on 2015/8/31. */
public class ExpressionAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Expression> expressions;

    public ExpressionAdapter(LayoutInflater inflater, ArrayList<Expression> expressions) {
        this.inflater = inflater;
        this.expressions = expressions;
    }

    @Override
    public int getCount() {
        return expressions.size();
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
        Expression expression=expressions.get(position);
        View view =inflater.inflate(R.layout.imageview,null);
        ImageView image= (ImageView) view.findViewById(R.id.image_expression);
        image.setImageResource(expression.getImage());
        return view;
    }
}
