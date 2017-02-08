package android.turingrobot.com.turingrobot;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private EditText editText;
    private ArrayList<ChatMessage> data;
    private MessageAdapter adapter;
    private Html.ImageGetter getter;
    private ListView listView;
    private LayoutInflater inflater;
    private GridView gridView;
    private ArrayList<Expression> expressions;
    private ExpressionAdapter expressionAdapter;
    private View view;
    private View chooseView;
    private ImageView imageView;
    private ImageView choose;
    private String name;
    private ChatMessage chatMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edittext);
        imageView = (ImageView) findViewById(R.id.biaoqing);
        choose = (ImageView) findViewById(R.id.choose);
        Button send = (Button) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.listview);
        inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.imageview, null);
        chooseView = inflater.inflate(R.layout.poppupwindow, null);
        data = new ArrayList<>();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow(MainActivity.this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(view);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.update();
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 400);
            }
        });
        gridView = (GridView) view.findViewById(R.id.expression);
        expressions = new ArrayList<>();
        initExpression();
        expressionAdapter = new ExpressionAdapter(inflater, expressions);
        getter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = getResources().getDrawable(Integer.parseInt(source));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        };
        gridView.setAdapter(expressionAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spanned spanned = Html.fromHtml("<img src='" + expressions.get(position).getImage() + "'/>", getter, null);
                //将表情插入到光标所在位置
                editText.getText().insert(editText.getSelectionStart(), spanned);
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow(MainActivity.this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(chooseView);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.update();
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 450);
            }
        });
        RadioGroup radioGroup = (RadioGroup) chooseView.findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) chooseView.findViewById(checkedId);
                name = (String) radioButton.getText();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("send")) {
                    String message = filterHtml(Html.toHtml(editText.getText()));
                    chatMessage = new ChatMessage(R.drawable.banben2, "老大", "巴黎铁塔前的黎明", message, System.currentTimeMillis());
                    chatMessage.setType(MessageAdapter.MESSAGE_RIGHT);
                } else if (name.equals("receive")) {
                    String message = filterHtml(Html.toHtml(editText.getText()));
                    chatMessage = new ChatMessage(R.drawable.banben1, "老二", "东京樱花后的黄昏", message, System.currentTimeMillis());
                    chatMessage.setType(MessageAdapter.MESSAGE_LEFT);
                }
                data.add(chatMessage);
                adapter.notifyDataSetChanged();
                listView.setSelection(data.size());//将ListView定位到最后一行
                editText.setText("");//清空输入框的内容
            }
        });
        adapter = new MessageAdapter(inflater, data, getter);
        listView.setAdapter(adapter);
    }


    /** * 将文本转化为HTML格式后，发送的时候去掉除富文本以外的内容 * * @param str 转化为HTML格式的字符串 * @return 富文本 */
    public String filterHtml(String str) {
        str = str.replaceAll("<(?!br|img)[^>]+>", "").trim();
        return str;
    }

    /** *初始化表情 */
    private void initExpression() {
        Expression expression1 = new Expression();
        expression1.setImage(R.mipmap.a);
        expressions.add(expression1);
        Expression expression2 = new Expression();
        expression2.setImage(R.mipmap.b);
        expressions.add(expression2);
        Expression expression3 = new Expression();
        expression3.setImage(R.mipmap.c);
        expressions.add(expression3);
        Expression expression4 = new Expression();
        expression4.setImage(R.mipmap.d);
        expressions.add(expression4);
        Expression expression5 = new Expression();
        expression5.setImage(R.mipmap.e);
        expressions.add(expression5);
        Expression expression6 = new Expression();
        expression6.setImage(R.mipmap.e);
        expressions.add(expression6);
        Expression expression7 = new Expression();
        expression7.setImage(R.mipmap.f);
        expressions.add(expression7);
        Expression expression8 = new Expression();
        expression8.setImage(R.mipmap.g);
        expressions.add(expression8);
        Expression expression9 = new Expression();
        expression9.setImage(R.mipmap.h);
        expressions.add(expression9);
    }
}
