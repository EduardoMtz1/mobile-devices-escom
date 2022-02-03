package com.cuaspro.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.*;
import android.widget.*;
public class MainActivity extends Activity implements OnClickListener{
    Button jbn1, jbn2, jbn3, jbn4;
    WebSettings ws;
    WebView wv;
    EditText jet;
    String s="https://catkin-complex-couch.glitch.me/";
    public void onCreate(Bundle b){
        super.onCreate(b); setContentView(R.layout.activity_main);
        jbn4 = (Button) findViewById(R.id.xbn4);
        jbn4.setOnClickListener(this);
        jet = (EditText) findViewById(R.id.xet);
        wv = (WebView) findViewById(R.id.xwv);
        wv.setWebViewClient(new Cliente());
        ws = wv.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);
        ws.setUseWideViewPort(true);
        wv.loadUrl(s);
    }
    class Cliente extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            return false;
        }
        public void onPageFinished(WebView view, String url){
            jet.setText(url);
        }
    }
    public void onClick(View v){ int id = v.getId(); switch(id){
            case R.id.xbn4: wv.loadUrl(jet.getText() + ""); break;
        }
    }
}
