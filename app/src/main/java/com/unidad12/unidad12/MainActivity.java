package com.unidad12.unidad12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity {
    private AdView adView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //se obtiene el id del banner, para asignarle la publicidad y publicarlo en pantalla
        adView = (AdView)findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);


        //si el usuario esta conectado que abra el otro layout
        if(AccessToken.getCurrentAccessToken()==null){
            goLoginScreen();
        }
    }

    //metodo que se da si el usuario ya esta conectado para que aparezca el mensaje de bienvenido
    private void goLoginScreen(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    //cerrar sesion si se presiona el boton y regresa a la pantalla para loguearse
    public void logout(View view){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }


    //metodos para conocer acciones sobre el banner
    @Override
    protected void onResume() {
        if (adView!=null){
            adView.resume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if(adView != null){
            adView.pause();
        }
        super.onPause();
    }
}
