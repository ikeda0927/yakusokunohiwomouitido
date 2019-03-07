package com.kohei.ikegon.yakusokunohiwomouitido;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ikego on 2018/04/02.
 */

public class MyDialog extends Dialog {
    public MyDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogView dialogView = new DialogView(getContext());
        setContentView(dialogView.getLayout());
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        getWindow().setAttributes(lp);

    }

}
