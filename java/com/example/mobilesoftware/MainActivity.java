package com.example.mobilesoftware;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button[] btn = new Button[8];
    MyView drawView;
    int pre_color = Color.BLACK;
    float pre_width = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn[0] = (Button)findViewById(R.id.btn_line);
        btn[1] = (Button)findViewById(R.id.btn_rect);
        btn[2] = (Button)findViewById(R.id.btn_circ);
        btn[3] = (Button)findViewById(R.id.btn_curve);
        btn[4] = (Button)findViewById(R.id.btn_erase);
        btn[5] = (Button)findViewById(R.id.btn_clear);
        btn[6] = (Button)findViewById(R.id.btn_emboss);
        btn[7] = (Button)findViewById(R.id.btn_blur);

        drawView = (MyView) findViewById(R.id.canvas_draw);

    }

    public void sel_width() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog1 = builder.create();

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_line, null);
        dialog1.setView(layout);
        dialog1.show();

        Button line1 = dialog1.findViewById(R.id.line1);
        assert line1 != null;
        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setWidth(10f);
                pre_width = 10f;
                dialog1.dismiss();
            }
        });

        Button line2 = dialog1.findViewById(R.id.line2);
        line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setWidth(15f);
                pre_width = 15f;
                dialog1.dismiss();
            }
        });

        Button line3 = dialog1.findViewById(R.id.line3);
        line3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setWidth(20f);
                pre_width = 20f;
                dialog1.dismiss();
            }
        });

        Button line4 = dialog1.findViewById(R.id.line4);
        line4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setWidth(25f);
                pre_width = 25f;
                dialog1.dismiss();
            }
        });

    }

    public void clscreen() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Clear Screen");
        builder.setMessage("그림을 모두 지우겠습니까?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                drawView.allClear();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {}
        });

        builder.create().show();
    }

    public void sel_color() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog1 = builder.create();

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_color, null);
        dialog1.setView(layout);
        dialog1.show();

        Button color_black = dialog1.findViewById(R.id.color_black);
        color_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.BLACK);
                pre_color = Color.BLACK;
                dialog1.dismiss();
            }
        });

        Button color_blue = dialog1.findViewById(R.id.color_blue);
        color_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.BLUE);
                pre_color = Color.BLUE;
                dialog1.dismiss();
            }
        });

        Button color_lightblue = dialog1.findViewById(R.id.color_lightblue);
        color_lightblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.CYAN);
                pre_color = Color.CYAN;
                dialog1.dismiss();
            }
        });

        Button color_green = dialog1.findViewById(R.id.color_green);
        color_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.GREEN);
                pre_color = Color.GREEN;
                dialog1.dismiss();
            }
        });

        Button color_red = dialog1.findViewById(R.id.color_red);
        color_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.RED);
                pre_color = Color.RED;
                dialog1.dismiss();
            }
        });

        Button color_yellow = dialog1.findViewById(R.id.color_yellow);
        color_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.YELLOW);
                pre_color = Color.YELLOW;
                dialog1.dismiss();
            }
        });

        Button color_pink = dialog1.findViewById(R.id.color_pink);
        color_pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.MAGENTA);
                pre_color = Color.MAGENTA;
                dialog1.dismiss();
            }
        });

        Button color_ltgray = dialog1.findViewById(R.id.color_ltgray);
        color_ltgray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.LTGRAY);
                pre_color = Color.LTGRAY;
                dialog1.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);

        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        Button emboss = (Button) findViewById(R.id.btn_emboss);
        Button blur = (Button) findViewById(R.id.btn_blur);
        menu.findItem(R.id.emboss).setChecked(emboss.isSelected());
        menu.findItem(R.id.blur).setChecked(blur.isSelected());

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.line_menu:
                sel_width();
                break;

            case R.id.color_menu:
                sel_color();
                break;

            case R.id.emboss:
                item.setChecked(!item.isChecked());
                Button btn_emboss = (Button) findViewById(R.id.btn_emboss);
                btn_emboss.setSelected(item.isChecked());
                drawView.isemboss = item.isChecked();
                break;

            case R.id.blur:
                item.setChecked(!item.isChecked());
                Button btn_blur = (Button) findViewById(R.id.btn_blur);
                btn_blur.setSelected(item.isChecked());
                drawView.isblur = item.isChecked();
                break;

            case R.id.fill_color:
                item.setChecked(!item.isChecked());
                if (item.isChecked()) {
                    drawView.fill_tf = true;
                } else {
                    drawView.fill_tf = false;
                }
        }

        return super.onOptionsItemSelected(item);
    }

    public void btn_onclick(View view) {
        Button newButton = (Button) view;

        for (int i =0; i<6; i++) {
            if (newButton == btn[i]) {
                newButton.setSelected(true);
                MyView.num_btn = i;
                if (i == 4) {
                    int color = Color.parseColor("#ffecce");
                    drawView.setColor(color);
                    drawView.setWidth(25f);
                }
                else {
                    drawView.setColor(pre_color);
                    drawView.setWidth(pre_width);
                }
                if (i == 5) {
                    clscreen();
                }
            }
            else {
                btn[i].setSelected(false);
            }
        }

        view.invalidate();
    }

    public void btn_checked(View view) {
        Button newButton = (Button) view;
        Button btn_emboss = (Button) findViewById(R.id.btn_emboss);
        Button btn_blur = (Button) findViewById(R.id.btn_blur);

        if (newButton == btn_emboss) {
            if (btn_emboss.isSelected()) {
                view.setSelected(false);
                drawView.isemboss = false;
            }
            else {
                view.setSelected(true);
                drawView.isemboss = true;
                btn_blur.setSelected(false);
                drawView.isblur = false;
            }
        }
        else if (newButton == btn_blur) {
            if (btn_blur.isSelected()) {
                view.setSelected(false);
                drawView.isblur = false;
            }
            else {
                view.setSelected(true);
                drawView.isblur = true;
                btn_emboss.setSelected(false);
                drawView.isemboss = false;
            }
        }
        view.invalidate();
    }

}
