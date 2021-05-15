package mgoyun.com.mgoyun;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Random;

public class OyunMenu extends AppCompatActivity  {

    Button Oyun1,Oyun2,Oyun3,Oyun4,Oyun5,Oyun6,Oyun7,Oyun8,Oyun9,Oyun10,Oyun11,Oyun12,Dil,Cikis,Muzik,MuzikClose;
    MediaPlayer buton,fon;
    MediaPlayer [] menuarkases;
    int oyun,lang;
    String [] dileng={"MY FAMILY","NUMBERS","ALPHABET","COLOURS","SHAPE","FRUITS","ANIMALS","BODY","KITCHEN","VEHICLE","VEGETABLES","OCCUPATIONS"/*,"SELECT LANGUAGE","QUİT"*/};
    Veritabanidil db3=new Veritabanidil(this);
    AnimationDrawable animationDrawable,animationDrawable2,animationDrawable3,animationDrawable4,animationDrawable5,animationDrawable6,animationDrawable7,animationDrawable8,animationDrawable9,animationDrawable10,animationDrawable11,animationDrawable12;
    String cik,ciksoru,evet,hayir;
    Typeface tf1;

    @Override
    protected void onStop() {
        if(fon.isPlaying()){
            fon.pause();
                    }
        super.onStop();

    }


    int dur=0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPause() {
        super.onPause();
            if(fon.isPlaying()){
            fon.pause();
        }
        dur=1;
    }



    @Override
    protected void onRestart() {
        dur=0;

        if(db3.gettext(200)==null){
            Muzik.setEnabled(true);
            MuzikClose.setEnabled(false);
            Muzik.setVisibility(View.VISIBLE);
            MuzikClose.setVisibility(View.INVISIBLE);
        }else{
            Muzik.setEnabled(false);
            MuzikClose.setEnabled(true);
            Muzik.setVisibility(View.INVISIBLE);
            MuzikClose.setVisibility(View.VISIBLE);
        }
      //  tamekran();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        dur=0;
        if(db3.gettext(200)==null){
               fon.start();
          fon.setLooping(true);
            Muzik.setEnabled(true);
            MuzikClose.setEnabled(false);
            Muzik.setVisibility(View.VISIBLE);
            MuzikClose.setVisibility(View.INVISIBLE);
        }else{
            if(fon.isPlaying()){
                fon.pause();
            }
            Muzik.setEnabled(false);
            MuzikClose.setEnabled(true);
            Muzik.setVisibility(View.INVISIBLE);
            MuzikClose.setVisibility(View.VISIBLE);
        }
        tamekran();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // View decorView = getWindow(). getDecorView();
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  tamekran();





      //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
     //   getWindow().setNavigationBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_oyun_menu);

        tf1=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/BABYCAKE.ttf");
        menuarkases=new MediaPlayer[12];

        if (db3.gettext(1).equals("tr")) {
            lang = 0;

        } else if (db3.gettext(1).equals("eng")) {
            lang = 1;
        }


        Oyun1=(Button)findViewById(R.id.aile);
        Oyun2=(Button)findViewById(R.id.sayi);
        Oyun3=(Button)findViewById(R.id.alfabe);
        Oyun4=(Button)findViewById(R.id.renk);
        Oyun5=(Button)findViewById(R.id.sekil);
        Oyun6=(Button)findViewById(R.id.meyve);
        Oyun7=(Button)findViewById(R.id.hayvan);
        Oyun8=(Button)findViewById(R.id.vucud);
        Oyun9=(Button)findViewById(R.id.mutfak);
        Oyun10=(Button)findViewById(R.id.arac);
        Oyun11=(Button)findViewById(R.id.sebze);
        Oyun12=(Button)findViewById(R.id.meslek);
        Dil=(Button)findViewById(R.id.dil);
        Cikis=(Button)findViewById(R.id.cikis);
        Muzik=(Button)findViewById(R.id.muzik);
        MuzikClose=(Button)findViewById(R.id.muzik2);

        Oyun1.setTypeface(tf1);
        Oyun2.setTypeface(tf1);
        Oyun3.setTypeface(tf1);
        Oyun4.setTypeface(tf1);
        Oyun5.setTypeface(tf1);
        Oyun6.setTypeface(tf1);
        Oyun7.setTypeface(tf1);
        Oyun8.setTypeface(tf1);
        Oyun9.setTypeface(tf1);
        Oyun10.setTypeface(tf1);
        Oyun11.setTypeface(tf1);
        Oyun12.setTypeface(tf1);


        if(lang==1){
            Oyun1.setText(dileng[0]);
            Oyun2.setText(dileng[1]);
            Oyun3.setText(dileng[2]);
            Oyun4.setText(dileng[3]);
            Oyun5.setText(dileng[4]);
            Oyun6.setText(dileng[5]);
            Oyun7.setText(dileng[6]);
            Oyun8.setText(dileng[7]);
            Oyun9.setText(dileng[8]);
            Oyun10.setText(dileng[9]);
            Oyun11.setText(dileng[10]);
            Oyun12.setText(dileng[11]);
            Dil.setBackgroundResource(R.mipmap.abd);
            cik="QUIT";
            ciksoru="Are you sure you want to quit?";
            evet="Yes";
            hayir="No";

        }else if(lang==0){

            cik="ÇIKIŞ";
            ciksoru="Çıkış yapmak istediğinize emin misiniz?";
            evet="Evet";
            hayir="Hayır";

        }



        buton= MediaPlayer.create(OyunMenu.this,R.raw.buton);

     fon=MediaPlayer.create(OyunMenu.this,R.raw.fon);

        fon.start();
          fon.setLooping(true);

        Oyun1.setBackgroundResource(R.drawable.aileanim);
        Oyun2.setBackgroundResource(R.drawable.sayianim);
        Oyun3.setBackgroundResource(R.drawable.harfanim);
        Oyun4.setBackgroundResource(R.drawable.renkanim);
        Oyun5.setBackgroundResource(R.drawable.sekilanim);
        Oyun6.setBackgroundResource(R.drawable.meyveanim);
        Oyun7.setBackgroundResource(R.drawable.hayvananim);
        Oyun8.setBackgroundResource(R.drawable.vucudanim);
        Oyun9.setBackgroundResource(R.drawable.mutfakanim);
        Oyun10.setBackgroundResource(R.drawable.aracanim);
        Oyun11.setBackgroundResource(R.drawable.sebzeanim);
        Oyun12.setBackgroundResource(R.drawable.meslekanim);

        animationDrawable=(AnimationDrawable) Oyun1.getBackground();
        animationDrawable2=(AnimationDrawable) Oyun2.getBackground();
        animationDrawable3=(AnimationDrawable) Oyun3.getBackground();
        animationDrawable4=(AnimationDrawable) Oyun4.getBackground();
        animationDrawable5=(AnimationDrawable) Oyun5.getBackground();
        animationDrawable6=(AnimationDrawable) Oyun6.getBackground();
        animationDrawable7=(AnimationDrawable) Oyun7.getBackground();
        animationDrawable8=(AnimationDrawable) Oyun8.getBackground();
        animationDrawable9=(AnimationDrawable) Oyun9.getBackground();
        animationDrawable10=(AnimationDrawable) Oyun10.getBackground();
        animationDrawable11=(AnimationDrawable) Oyun11.getBackground();
        animationDrawable12=(AnimationDrawable) Oyun12.getBackground();


        animationDrawable.start();
        animationDrawable2.start();
        animationDrawable3.start();
        animationDrawable4.start();
        animationDrawable5.start();
        animationDrawable6.start();
        animationDrawable7.start();
        animationDrawable8.start();
        animationDrawable9.start();
        animationDrawable10.start();
        animationDrawable11.start();
        animationDrawable12.start();


        Muzik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    buton.start();
                fon.pause();
                Muzik.setEnabled(false);
                MuzikClose.setEnabled(true);
                Muzik.setVisibility(View.INVISIBLE);
                MuzikClose.setVisibility(View.VISIBLE);
                db3.Ekle("var",200); // 200 müzik için oluşturuldu
            }
        });
        MuzikClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buton.start();

                fon.start();
                fon.setLooping(true);
                Muzik.setEnabled(true);
                MuzikClose.setEnabled(false);
                Muzik.setVisibility(View.VISIBLE);
                MuzikClose.setVisibility(View.INVISIBLE);
                db3.deletedata(200);
            }
        });


        Oyun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    buton.start();
                    oyun=2;
                Intent intent = new Intent(OyunMenu.this, BalonPatlatmaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );

            }
        });


        Oyun2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    buton.start();
                oyun=2;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    buton.start();
                oyun=3;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 buton.start();
                oyun=4;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buton.start();
                oyun=5;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                oyun=6;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                oyun=7;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                oyun=8;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                oyun=9;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                oyun=10;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                oyun=11;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Oyun12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                oyun=12;
                Intent intent = new Intent(OyunMenu.this, SayiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("oyun",oyun);
                startActivity(intent);
                overridePendingTransition(R.anim.sag,R.anim.sol );
            }
        });
        Dil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                db3.deletedata(1);
                Intent intent = new Intent(OyunMenu.this, Language.class);
                startActivity(intent);
                overridePendingTransition(R.anim.sag, R.anim.sol);

            }
        });
        Cikis.setOnClickListener(new View.OnClickListener() {

               // moveTaskToBack(true);



                @Override
                public void onClick(View view) {
                    buton.start();
                    cikis();



                    /*
                    AlertDialog.Builder builder = new AlertDialog.Builder(OyunMenu.this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                    builder.setTitle(cik);
                    builder.setMessage(ciksoru);
                    builder.setNegativeButton(hayir, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                buton.start();
                        }
                    });
                    builder.setPositiveButton(evet, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                buton.start();
                            ActivityCompat.finishAffinity(OyunMenu.this);

                        }
                    });
                    builder.show();

                     */
                }


        });

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

    int b;
    @Override
    public void onWindowFocusChanged(final boolean hasFocus){

    }


    @Override
    public void onBackPressed() {
        cikis();
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(OyunMenu.this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle(cik);
        builder.setMessage(ciksoru);

        builder.setNegativeButton(hayir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    buton.start();
                 //   tamekran();
            }
        });
        builder.setPositiveButton(evet, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    buton.start();
                ActivityCompat.finishAffinity(OyunMenu.this);

            }
        });
        builder.show();

         */
        tamekran();
    }

    public void cikis(){
         int ui_flags =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

        final Dialog dialog = new Dialog(OyunMenu.this);
        dialog.setContentView(R.layout.custom_dialog);


        Button btnKaydet = (Button) dialog.findViewById(R.id.button_kaydet);
        Button btnIptal = (Button) dialog.findViewById(R.id.button_iptal);
        ImageView ivResim = (ImageView) dialog.findViewById(R.id.imageview_resim);


        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton.start();
                ActivityCompat.finishAffinity(OyunMenu.this);
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
