package com.example.user.mykeyboardexample1;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
        String localeString = ims.getLocale();
        Locale locale = new Locale(localeString);
        String currentLanguage = locale.getDisplayLanguage();
        EditText languageEditText = (EditText) findViewById(R.id.editText1);
        Toast.makeText(getApplicationContext(), currentLanguage, Toast.LENGTH_SHORT).show();
    }

    public void onClick2(View view) {
//        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS); // виртуальные клавиатуры
        Intent intent = new Intent(Settings.ACTION_HARD_KEYBOARD_SETTINGS); // API 24: реальные клавиатуры
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onUserLeaveHint() {
        Toast toast = Toast.makeText(getApplicationContext(), "Нажата кнопка HOME", Toast.LENGTH_SHORT);
        toast.show();
        super.onUserLeaveHint();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                Toast.makeText(this, "Нажата кнопка Меню", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case KeyEvent.KEYCODE_SEARCH:
                Toast.makeText(this, "Нажата кнопка Поиск", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case KeyEvent.KEYCODE_BACK:
                onBackPressed();
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                event.startTracking();
                Toast.makeText(this, "Нажата кнопка увелич. громкости", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this, "Нажата кнопка уменьш. громкости", Toast.LENGTH_SHORT)
                        .show();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }



}