package mgoyun.com.mgoyun;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import mgoyun.com.mgoyun.R;



public class Language extends AppCompatActivity {

    Button England,Turkey;
    MediaPlayer buton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  tamekran();

        setContentView(R.layout.activity_language);

        final Veritabanidil db3=new Veritabanidil(this);


        if(db3.gettext(1)==null){
            Turkey=(Button)findViewById(R.id.turkey);
            England=(Button)findViewById(R.id.abd);

            buton= MediaPlayer.create(Language.this,R.raw.buton);

            Turkey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buton.start();
                    db3.deletedata(1);
                    db3.Ekle("tr",1);
                    Intent intent = new Intent(Language.this, OyunMenu.class);
                    startActivity(intent);
                }
            });
            England.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buton.start();
                    db3.deletedata(1);
                    db3.Ekle("eng",1);
                    Intent intent = new Intent(Language.this, OyunMenu.class);
                    startActivity(intent);
                }
            });
        }else{
            Intent intent = new Intent(Language.this, OyunMenu.class);
            startActivity(intent);
        }


    }

    private void tamekran() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        tamekran();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onResume();
    }

    @Override
    public void onBackPressed() {
     cikis();
    }
    public void cikis(){

        int ui_flags =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

        final Dialog dialog = new Dialog(Language.this);
        dialog.setContentView(R.layout.custom_dialog);

        Button btnKaydet = (Button) dialog.findViewById(R.id.button_kaydet);
        Button btnIptal = (Button) dialog.findViewById(R.id.button_iptal);
        ImageView ivResim = (ImageView) dialog.findViewById(R.id.imageview_resim);


        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                ActivityCompat.finishAffinity(Language.this);
            }
        });
        // iptal butonunun tıklanma olayları
        btnIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                dialog.dismiss();
            }
        });
        dialog.getWindow().
                setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

// Set full-sreen mode (immersive sticky):
        dialog.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
        dialog.show();
        dialog.getWindow().
                clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


    }



}