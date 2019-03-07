package com.kohei.ikegon.yakusokunohiwomouitido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ikego on 2018/02/09.
 */

public class SqliteDataBase extends SQLiteOpenHelper {
    public SqliteDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version){
        super(context,name,factory,version);

    }
    //したのonCreateメソッドはデータベースが新たに作成されたときに実行される
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE IKEDAnoRPG(ID INT PRIMARY KEY,CHAPTER INT NOT NULL," +
                "ISSTORY INT NOT NULL,ISSTORYCHANGEABLE INT NOT NULL,COUNTER INT NOT NULL," +
                "FOOD INT NOT NULL,FLUTE INT NOT NULL,NOWX REAL NOT NULL,NOWY REAL NOT NULL," +
                "LIGHTX REAL NOT NULL,LIGHTY REAL NOT NULL,LIGHTBGNUM INT NOT NULL," +
                "LIGHTABLE INT NOT NULL,SHOWTHELIGHT INT NOT NULL,MYBGNUM INT NOT NULL," +
                "ISANIMATION INT NOT NULL,HOUSE INT NOT NULL,CHANGEBG INT NOT NULL)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
//    //for debug
//    public void presetSaveData(SQLiteDatabase db){
//        int chap = 0;int iss = 0;int issC = 0;int cou = 0;int foo = 0; int flu = 0;int nowX = 4; int nowY = 6;int ligX = 0; int ligY = 0;
//        int ligB = 0;int ligA = 0;int sho = 0;int myb = 8;int isa = 0; int hou = 0;int cha = 2;
//        for(int id = 0;id < 19;id++){
//            switch (id){
//                case 0:
//                    chap=3;
//                    break;
//                case 1:
//                    chap=5;
//                    break;
//                case 2:
//                    chap=9;
//                    break;
//                case 3:
//                    chap=11;
//                    break;
//                case 4:
//                    chap=15;
//                    break;
//                case 5:
//                    chap=16;
//                    break;
//                case 6:
//                    chap=17;
//                    break;
//                case 7:
//                    chap=20;
//                    break;
//                case 8:
//                    chap=21;
//                    break;
//                case 9:
//                    chap=23;
//                    break;
//                case 10:
//                    chap=26;
//                    break;
//                case 11:
//                    chap=28;
//                    break;
//                case 12:
//                    chap=31;
//                    break;
//                case 13:
//                    chap=35;
//                    foo = 1;
//                    break;
//                case 14:
//                    chap=37;
//                    break;
//                case 15:
//                    chap=38;
//                    break;
//                case 16:
//                    chap=39;
//                    break;
//                case 17:
//                    chap=40;
//                    flu = 1;
//                    break;
//                case 18:
//                    chap=43;
//                    break;
//            }
//            ContentValues values = new ContentValues();
//            values.put("CHAPTER",chap);
//            values.put("ISSTORY",iss);
//            values.put("ISSTORYCHANGEABLE",issC);
//            values.put("COUNTER",cou);
//            values.put("FOOD",foo);
//            values.put("FLUTE",flu);
//            values.put("NOWX",nowX);
//            values.put("NOWY",nowY);
//            values.put("LIGHTX",ligX);
//            values.put("LIGHTY",ligY);
//            values.put("LIGHTBGNUM",ligB);
//            values.put("LIGHTABLE",ligA);
//            values.put("SHOWTHELIGHT",sho);
//            values.put("MYBGNUM",myb);
//            values.put("ISANIMATION",isa);
//            values.put("HOUSE",hou);
//            values.put("CHANGEBG",cha);
//
//            String where = "ID=?";
//            String[] whereArgs = {String.valueOf(id)};
//
//            //データを追加するメソッドでは不便だったため、SQL文で直接書く
//            String sql_insert = "INSERT INTO IKEDAnoRPG VALUES("+String.valueOf(id)+","+
//                    String.valueOf(chap)+
//                    ","+String.valueOf(iss)+
//                    ","+String.valueOf(issC)+
//                    ","+String.valueOf(cou)+
//                    ","+String.valueOf(foo)+
//                    ","+String.valueOf(flu)+
//                    ","+String.valueOf(nowX)+
//                    ","+String.valueOf(nowY)+
//                    ","+String.valueOf(ligX)+
//                    ","+String.valueOf(ligY)+
//                    ","+String.valueOf(ligB)+
//                    ","+String.valueOf(ligA)+
//                    ","+String.valueOf(sho)+
//                    ","+String.valueOf(myb)+
//                    ","+String.valueOf(isa)+
//                    ","+String.valueOf(hou)+
//                    ","+String.valueOf(cha)+")";
//
//            //updateが成功したらrowsには0以外の数値が入り、失敗(そのIDをもつデータがまだ存在しなかった
//            // 場合にはrowsに0が入る)
//            int rows = db.update("IKEDAnoRPG",values,where,whereArgs);
//            //Log.d("save","############ "+rows+" Automatic ############");
//            if(rows != 0){
//                //Log.d("save","############ UPDATE Automatic ############");
//            }else{
//                db.execSQL(sql_insert);
//                //Log.d("save","############ INSERT Automatic ############");
//            }
//        }
//    }
//    //for debug

    public void saveData(SQLiteDatabase db,int id){
        ContentValues values = new ContentValues();
        values.put("CHAPTER",FunctionView.getChapter());
        values.put("ISSTORY",(FunctionView.getIsStory())?1:0);
        values.put("ISSTORYCHANGEABLE",(FunctionView.getIsStoryChangeable())?1:0);
        values.put("COUNTER",FunctionView.getCounter());
        values.put("FOOD",(FunctionView.getFoodEnable())?1:0);
        values.put("FLUTE",(FunctionView.getFluteJudge())?1:0);
        values.put("NOWX",Character.getNowX());
        values.put("NOWY",Character.getNowY());
        values.put("LIGHTX",LightEffect.getLightX());
        values.put("LIGHTY",LightEffect.getLightY());
        values.put("LIGHTBGNUM",LightEffect.getLightBGNum());
        values.put("LIGHTABLE",(LightEffect.getLightable())?1:0);
        values.put("SHOWTHELIGHT",(LightEffect.getShowTheLight())?1:0);
        values.put("MYBGNUM",ObjectList.getMyBGNum());
        values.put("ISANIMATION",(StoryView.getIsAnimation())?1:0);
        values.put("HOUSE",(StoryView.getHouse())?1:0);
        values.put("CHANGEBG",StoryView.getChangeBG());

        String where = "ID=?";
        String[] whereArgs = {String.valueOf(id)};

        //データを追加するメソッドでは不便だったため、SQL文で直接書く
        String sql_insert = "INSERT INTO IKEDAnoRPG VALUES("+String.valueOf(id)+","+
                String.valueOf(FunctionView.getChapter())+
                ","+String.valueOf((FunctionView.getIsStory())?1:0)+
                ","+String.valueOf((FunctionView.getIsStoryChangeable())?1:0)+
                ","+String.valueOf(FunctionView.getCounter())+
                ","+String.valueOf((FunctionView.getFoodEnable())?1:0)+
                ","+String.valueOf((FunctionView.getFluteJudge())?1:0)+
                ","+String.valueOf(Character.getNowX())+
                ","+String.valueOf(Character.getNowY())+
                ","+String.valueOf(LightEffect.getLightX())+
                ","+String.valueOf(LightEffect.getLightY())+
                ","+String.valueOf(LightEffect.getLightBGNum())+
                ","+String.valueOf((LightEffect.getLightable())?1:0)+
                ","+String.valueOf((LightEffect.getShowTheLight())?1:0)+
                ","+String.valueOf(ObjectList.getMyBGNum())+
                ","+String.valueOf((StoryView.getIsAnimation())?1:0)+
                ","+String.valueOf((StoryView.getHouse())?1:0)+
                ","+String.valueOf(StoryView.getChangeBG())+")";

        //updateが成功したらrowsには0以外の数値が入り、失敗(そのIDをもつデータがまだ存在しなかった
        // 場合にはrowsに0が入る)
        int rows = db.update("IKEDAnoRPG",values,where,whereArgs);
        //Log.d("TAG","############ "+rows+" ############");
        if(rows != 0){
            //Log.d("TAG","############ UPDATE ############");
        }else{
            db.execSQL(sql_insert);
            //Log.d("TAG","############ INSERT ############");
        }
    }

    public float[] getListData(SQLiteDatabase db, int id){
        String[] columns = {"CHAPTER","COUNTER","CHANGEBG"};
        String where = "ID=?";
        String params[] = {String.valueOf(id)};
        Cursor cursor = db.query("IKEDAnoRPG",columns,where,params,null,null,null,null);
        float[] listData = new float[3];
        if(cursor.moveToNext()){
            listData[0] = cursor.getInt(cursor.getColumnIndex("CHAPTER"));
            listData[1] = cursor.getInt(cursor.getColumnIndex("COUNTER"));
            listData[2] = cursor.getInt(cursor.getColumnIndex("CHANGEBG"));
        }else{
        }
        return listData;
    }
    public float[] getSaveData(SQLiteDatabase db,int id){
        //データを取り出す対象のカラムを指定
        String[] columns = {"CHAPTER","ISSTORY","ISSTORYCHANGEABLE","COUNTER","FOOD","FLUTE",
                "NOWX","NOWY","LIGHTX","LIGHTY","LIGHTBGNUM","LIGHTABLE","SHOWTHELIGHT",
                "MYBGNUM","ISANIMATION","HOUSE","CHANGEBG"};
        //データを検索するときの条件を示しており、?にはparamsが代入され、IDで検索することができる
        String where = "ID=?";
        //探したいデータのIDを代入
        String params[] = {String.valueOf(id)};
        //(第5引数にはgroupBy句、第6引数にはhavingBy句、第7引数にはorderBy句、第8引数には取得件数の上限を指定する)
        Cursor cursor = db.query("IKEDAnoRPG",columns,where,params,null,null,null,null);
        float[] save_data = new float[17];
        //cursorにデータが含まれていた場合、moveToNext()はtrueを返す
        if(cursor.moveToNext()){
            save_data[0] = cursor.getInt(cursor.getColumnIndex("CHAPTER"));
            save_data[1] = cursor.getInt(cursor.getColumnIndex("ISSTORY"));
            save_data[2] = cursor.getInt(cursor.getColumnIndex("ISSTORYCHANGEABLE"));
            save_data[3] = cursor.getInt(cursor.getColumnIndex("COUNTER"));
            save_data[4] = cursor.getInt(cursor.getColumnIndex("FOOD"));
            save_data[5] = cursor.getInt(cursor.getColumnIndex("FLUTE"));
            save_data[6] = cursor.getFloat(cursor.getColumnIndex("NOWX"));
            save_data[7] = cursor.getFloat(cursor.getColumnIndex("NOWY"));
            save_data[8] = cursor.getFloat(cursor.getColumnIndex("LIGHTX"));
            save_data[9] = cursor.getFloat(cursor.getColumnIndex("LIGHTY"));
            save_data[10] = cursor.getInt(cursor.getColumnIndex("LIGHTBGNUM"));
            save_data[11] = cursor.getInt(cursor.getColumnIndex("LIGHTABLE"));
            save_data[12] = cursor.getInt(cursor.getColumnIndex("SHOWTHELIGHT"));
            save_data[13] = cursor.getInt(cursor.getColumnIndex("MYBGNUM"));
            save_data[14] = cursor.getInt(cursor.getColumnIndex("ISANIMATION"));
            save_data[15] = cursor.getInt(cursor.getColumnIndex("HOUSE"));
            save_data[16] = cursor.getInt(cursor.getColumnIndex("CHANGEBG"));
        }else{
        }
        return save_data;
    }

}
