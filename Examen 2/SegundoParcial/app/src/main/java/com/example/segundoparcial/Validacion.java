package com.example.segundoparcial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import io.paperdb.Paper;

public class Validacion extends Activity {
    String save_pattern_key = "pattern_code";
    PatternLockView mPatternLockView;
    String final_pattern = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(this);
        final String save_pattern = Paper.book().read(save_pattern_key);
        if(save_pattern != null && !save_pattern.equals("null"))
        {
            setContentView(R.layout.validacion_main);
            mPatternLockView = (PatternLockView)findViewById(R.id.pattern_lock_view);
            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {
                    final_pattern = PatternLockUtils.patternToString(mPatternLockView,pattern);
                    if(final_pattern.equals(save_pattern)){
                        Toast.makeText(Validacion.this, "Patron correcto!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Validacion.this,BaseDatos.class);
                        startActivity(intent);

                    }else{ Toast.makeText(Validacion.this, "Patron incorrecto!", Toast.LENGTH_SHORT).show();}

                }

                @Override
                public void onCleared() {

                }
            });
            Button btnSetup = (Button) findViewById(R.id.ptr);
            btnSetup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Paper.book().delete(save_pattern_key);
                    Intent intent = new Intent(Validacion.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}


