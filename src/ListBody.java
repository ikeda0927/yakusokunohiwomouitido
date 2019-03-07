package com.kohei.ikegon.yakusokunohiwomouitido;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getH_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getW_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setIsLoad;

/**
 * Created by ikego on 2018/02/08.
 */

public class ListBody extends View implements ListView.OnItemClickListener{
    int sli = android.R.layout.simple_list_item_1;
    Context context;

    //セーブかロードかの判定
    private static boolean isSave;

    //ロードだったときのデータ{ChangeBG,x,y}
    private static float[] save_data = new float[17];

    //ロード時にMainActivityへ戻るためのIntent
//    Intent intent;

    private static LinearLayout layout;

    private static String space = "                                                                   " +
            "                                                                         ";
    //セーブ時に使うデータベースのID
    public static int list_id = 0;
    //SQLiteデータベース
    SQLiteDatabase db;
    SqliteDataBase IKEDAnoRPG;
    SQLiteOpenHelper helper;

    private static ImageView imageView;
    private static Bitmap menuBitmap;
    private static TextView textView;

    private static int ChangeBG;
    private static float x;
    private static float y;
    private String[] listValue = {"","","","","","","","","","","","","","","","","","",""};

    ListView listView;
    ArrayAdapter<String> data;
//
//    public ListBody(Context context,Boolean isSave){
//        super(context);
//        layout = new LinearLayout(context);
//
//        this.context = context;
//
//        this.isSave = isSave;
//
//        imageView = new ImageView(context);
//        if(isSave){
//            menuBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.save2);
//        }else{
//            menuBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.load2);
//        }
//
//        intent = new Intent(context,MainActivity.class);
//
//        //データベース用
//        //データベース名を変更する際は計4つ変更する必要があるので注意
//        helper = new SqliteDataBase(context,"IKEDAnoRPG.db",null,1);
//        db = helper.getWritableDatabase();
//        IKEDAnoRPG = new SqliteDataBase(context,"IKEDAnoRPG.db",null,1);
//
//        data = new ArrayAdapter<String>(context,sli);
//
//        data.add("1番目"+space);
//        data.add("2番目"+space);
//        data.add("3番目"+space);
//        data.add("4番目"+space);
//        data.add("5番目"+space);
//        data.add("6番目"+space);
//        data.add("7番目"+space);
//        data.add("8番目"+space);
//        data.add("9番目"+space);
//        data.add("10番目"+space);
//        data.add("11番目"+space);
//        data.add("12番目"+space);
//        data.add("13番目"+space);
//        data.add("14番目"+space);
//        data.add("15番目"+space);
//        data.add("16番目"+space);
//        data.add("17番目"+space);
//        data.add("18番目"+space);
//        data.add("19番目"+space);
//
//        imageView.setImageBitmap(menuBitmap);
//        //imageView.setRight(menuBitmap.getWidth());
//        //imageView.setAdjustViewBounds(true);
//        imageView.setBackgroundColor(Color.BLACK);
//        layout.addView(imageView);
//
//        listView = new ListView(context);
//        listView.setBackgroundColor(Color.WHITE);
//
//        listView.setAdapter(data);
//        listView.setOnItemClickListener(this);
//        layout.addView(listView);
//    }
//
//    public ListBody(Context context,int ChangeBG,float x,float y,Boolean isSave){
//        super(context);
//        layout = new LinearLayout(context);
//
//        this.context = context;
//        this.ChangeBG =ChangeBG;
//        this.x = x;
//        this.y = y;
//        this.isSave = isSave;
//
//        imageView = new ImageView(context);
//        if(isSave){
//            menuBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.save2);
//        }else{
//            menuBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.load2);
//        }
//
//        //データベース用
//        helper = new SqliteDataBase(context,"IKEDAnoRPG.db",null,1);
//        db = helper.getWritableDatabase();
//        IKEDAnoRPG = new SqliteDataBase(context,"IKEDAnoRPG.db",null,1);
//
//        data = new ArrayAdapter<String>(context,sli);
//
//        data.add("1番目"+space);
//        data.add("2番目"+space);
//        data.add("3番目"+space);
//        data.add("4番目"+space);
//        data.add("5番目"+space);
//        data.add("6番目"+space);
//        data.add("7番目"+space);
//        data.add("8番目"+space);
//        data.add("9番目"+space);
//        data.add("10番目"+space);
//        data.add("11番目"+space);
//        data.add("12番目"+space);
//        data.add("13番目"+space);
//        data.add("14番目"+space);
//        data.add("15番目"+space);
//        data.add("16番目"+space);
//        data.add("17番目"+space);
//        data.add("18番目"+space);
//        data.add("19番目"+space);
//
//        imageView.setImageBitmap(menuBitmap);
//        imageView.setBackgroundColor(Color.BLACK);
//        //imageView.setAdjustViewBounds(true);
//        //imageView.setRight(menuBitmap.getWidth());
//        layout.addView(imageView);
//
//        listView = new ListView(context);
//        listView.setBackgroundColor(Color.WHITE);
//
//        listView.setAdapter(data);
//        listView.setOnItemClickListener(this);
//        layout.addView(listView);
//    }

    public ListBody(Context context){
        super(context);
        layout = new LinearLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        this.context = context;
        textView = new TextView(context);

        textView.setTextColor(Color.WHITE);
        textView.setText("LOAD");
        textView.setTextSize(40f);
        textView.setMinimumWidth((int)getW_unit()*2);
        textView.setMinimumHeight((int)getH_unit()*2);

        imageView = new ImageView(context);

        //データベース用
        //データベース名を変更する際は計4つ変更する必要があるので注意
        helper = new SqliteDataBase(context,"IKEDAnoRPG.db",null,1);
        db = helper.getWritableDatabase();
        IKEDAnoRPG = new SqliteDataBase(context,"IKEDAnoRPG.db",null,1);

//        //for debug
//        float[] ld = IKEDAnoRPG.getListData(db,1);
//        if(ld[2]==0){
//            //セーブデータをセットします。
//            IKEDAnoRPG.presetSaveData(db);
//        }
//        //for debug

        for(int i = 0;i<19;i++){
            float[] listData = IKEDAnoRPG.getListData(db,i+1);
            listValue[i] = getDate((int)listData[0],(int)listData[2]);
        }

        listView = new ListView(context);
        listView.setBackgroundColor(Color.WHITE);
        listView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        data = new ArrayAdapter<String>(context,sli);
        dataAdder();

        layout.addView(textView);

        listView.setOnItemClickListener(this);
        layout.addView(listView);
    }

    void dataAdder(){
        data.add("1番目   "+listValue[0]);
        data.add("2番目   "+listValue[1]);
        data.add("3番目   "+listValue[2]);
        data.add("4番目   "+listValue[3]);
        data.add("5番目   "+listValue[4]);
        data.add("6番目   "+listValue[5]);
        data.add("7番目   "+listValue[6]);
        data.add("8番目   "+listValue[7]);
        data.add("9番目   "+listValue[8]);
        data.add("10番目  "+listValue[9]);
        data.add("11番目  "+listValue[10]);
        data.add("12番目  "+listValue[11]);
        data.add("13番目  "+listValue[12]);
        data.add("14番目  "+listValue[13]);
        data.add("15番目  "+listValue[14]);
        data.add("16番目  "+listValue[15]);
        data.add("17番目  "+listValue[16]);
        data.add("18番目  "+listValue[17]);
        data.add("19番目  "+listValue[18]);

        listView.setAdapter(data);
    }

    void setListName(int list_id,int chapter,int changeBg){
        listValue[list_id-1] = getDate(chapter,changeBg);
        data.clear();
        dataAdder();
    }

    String getDate(int chapter,int changeBg){
        String s = String.valueOf(context.getText(R.string.day_7));
        if(chapter == 1 || chapter == 21 || chapter == 27 || chapter == 28 || chapter ==29 ||chapter == 43){//test
            s = String.valueOf(context.getText(R.string.day_7));
        }else if(chapter == 3 || chapter == 4 ){
            s = String.valueOf(context.getText(R.string.day_8));
        }else if(chapter == 5 || chapter == 13 ){
            s = String.valueOf(context.getText(R.string.day_5));
        }else if(chapter == 6 || chapter == 42){
            s = String.valueOf(context.getText(R.string.day_));
        }else if(chapter == 7 || chapter == 8 ){
            s = String.valueOf(context.getText(R.string.day_5_));
        }else if(chapter == 9 || chapter == 10 || chapter == 11 || chapter == 12 || chapter == 14 || chapter == 15){
            s = String.valueOf(context.getText(R.string.day_9));
        }else if(chapter == 16 || chapter == 17 || chapter == 18 || chapter == 19 || chapter == 20 || chapter == 22 || chapter == 30 || chapter == 31 || chapter == 32 || chapter == 33 || chapter == 34 || chapter == 35 || chapter == 36 || chapter == 40 || chapter == 41 ){
            s = String.valueOf(context.getText(R.string.day_10));
        }else if(chapter == 24 || chapter == 25 || chapter == 26){
            s = String.valueOf(context.getText(R.string.day_11));
        }else if(chapter == 37 || chapter == 38 || chapter == 39){
            s = String.valueOf(context.getText(R.string.day_m_));
        }
        switch (changeBg){
            case 0:
                s = "未登録";
                break;
            case 1:
                s += "     カフェ付近";
                break;
            case 2:
                s += "     自宅前";
                break;
            case 3:
                s += "     森";
                break;
            case 4:
                s += "     池";
                break;
            case 5:
                s += "     しーちゃんの家付近";
                break;
            case 6:
                s += "     森の入口";
                break;
            case 7:
                s += "     公民館前";
                break;
            case 8:
                s += "     自宅";
                break;
            case 9:
                s += "     しーちゃんの家";
                break;
            case 11:
                s += "     カフェ";
                break;
            case 12:
                s += "     池の奧";
                break;
            case 13:
                s += "     森の奧";
                break;
            case 14:
                s += "     池への道";
                break;
            case 15:
                s += "     小屋";
                break;
            case 16:
                s += "     回想";
                break;
        }
        return s;
    }

    public static void setIsSaveToList(boolean bool){
        isSave = bool;
        if(isSave){
            textView.setText("SAVE");
        }else{
            textView.setText("LOAD");
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view,int pos,long id){
        if(isSave){
            AlertDialog.Builder ad = new AlertDialog.Builder(context);
            list_id = (int)id +1;
            //Log.d("ListID","id : "+String.valueOf(id));
            ad.setMessage(list_id+"番目に登録しますか？");
            //Log.d("TAG","########## "+pos+" ############ "+id+" #############");
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context,"OK!",Toast.LENGTH_SHORT).show();
                    //Log.d("TAG","########## "+ list_id +" #############");
                    IKEDAnoRPG.saveData(db,list_id);
                    setListName(list_id,FunctionView.getChapter(),StoryView.getChangeBG());
                    FunctionView.setListView(false);
                }
            });
            ad.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FunctionView.setListView(false);
                }
            });
            ad.show();
        }else {
            AlertDialog.Builder ad = new AlertDialog.Builder(context);
            list_id = (int)id + 1;
            ad.setMessage(list_id+"番目からロードしますか?");
            //Log.d("TAG","#### ロード ###### "+pos+" ############ "+id+" #############");
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Log.d("TAG","####ロード###### "+ list_id +" #############");
                    //SQLiteからロードしたデータ{ChangeBG,x,y}
                    save_data = IKEDAnoRPG.getSaveData(db,list_id);
//                    intent.putExtra("load_judge",true);
                    //Log.d("TAG","####ロード###### "+ save_data[0] +" "+save_data[1]+" "+save_data[2] +" #############");
                    if(save_data[0] == 0 && save_data[1] == 0 && save_data[2] == 0){
                        Toast.makeText(context,"データがありません",Toast.LENGTH_SHORT).show();
                        save_data[0] = 0;
                        save_data[1] = 1;
                        save_data[2] = 0;
                        save_data[3] = 0;
                        save_data[4] = 0;
                        save_data[5] = 0;
                        save_data[6] = 6f;
                        save_data[7] = 2.5f;
                        save_data[8] = 0;
                        save_data[9] = 0;
                        save_data[10] = 0;
                        save_data[11] = 0;
                        save_data[12] = 0;
                        save_data[13] = 0;
                        save_data[14] = 0;
                        save_data[15] = 0;
                        save_data[16] = 8;

                        FunctionView.setListView(false);
                    }else{
                        Toast.makeText(context,"OK!",Toast.LENGTH_SHORT).show();
                        setIsLoad(true);
                        StoryView.setSaveData(save_data);
                    }
                }
            });
            ad.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context,"OK...",Toast.LENGTH_SHORT).show();
                }
            });
            ad.show();
        }
    }

    LinearLayout layoutView(){
        layout.setBackgroundColor(Color.BLACK);
        layout.setOrientation(LinearLayout.VERTICAL);
        return  layout;
    }
}
