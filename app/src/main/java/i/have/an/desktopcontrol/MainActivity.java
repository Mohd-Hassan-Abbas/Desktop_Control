package i.have.an.desktopcontrol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
 
 EditText field;
 Button sendButton;
 ImageView mic;
 
 ImageView alt;
 ImageView ctr;
 ImageView sft;
 ImageView tab;
 ImageView win;
 
 Socket s;
 DataOutputStream dout;
 String str = "";

 LinearLayout leftP;
 
  Button leftc;
  Button rightc;
  Button sup;
  Button sdo;
  
   Button du;
  Button dd;
  Button dl;
  Button dr;
  Button space;
   ImageView enter;
 ImageView back;
 
  private static final int REQUEST_CODE_SPEECH_INPUT = 1;
  private float x1,x2,y1,y2;
  
  boolean alt_hold=false;
  boolean window_hold=false;
  boolean shift_hold=false;
  boolean ctrl_hold=false;
  
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        field = findViewById(R.id.textField);
        sendButton = findViewById(R.id.btn);
        
        mic = findViewById(R.id.mic);
        
        win = findViewById(R.id.win);
        alt = findViewById(R.id.alt);
        sft = findViewById(R.id.sft);
        tab = findViewById(R.id.tab);
        ctr = findViewById(R.id.ctr);
        
        leftc = findViewById(R.id.leftc);
        rightc = findViewById(R.id.rightc);
        sup = findViewById(R.id.sup);
        sdo = findViewById(R.id.sdo);
        
        leftP = findViewById(R.id.leftP);
        
        du = findViewById(R.id.dup);
        dd = findViewById(R.id.ddo);
        dl = findViewById(R.id.dleft);
        dr = findViewById(R.id.dright);
        space = findViewById(R.id.space);
        enter = findViewById(R.id.enter);
        back = findViewById(R.id.back);
        
        du.setOnClickListener(view -> {
            field.setText("press dup");
            sendButton.callOnClick();
            field.setText("");
        });
        dr.setOnClickListener(view -> {
            field.setText("press dr");
            sendButton.callOnClick();
            field.setText("");
        });
        dl.setOnClickListener(view -> {
            field.setText("press dl");
            sendButton.callOnClick();
            field.setText("");
        });
        dd.setOnClickListener(view -> {
            field.setText("press ddo");
            sendButton.callOnClick();
            field.setText("");
        });
        space.setOnClickListener(view -> {
            field.setText("press space");
            sendButton.callOnClick();
            field.setText("");
        });
        back.setOnClickListener(view -> {
            field.setText("press back");
            sendButton.callOnClick();
            field.setText("");
        });
        enter.setOnClickListener(view -> {
            field.setText("press enter");
            sendButton.callOnClick();
            field.setText("");
        });
        leftc.setOnClickListener(view -> {
            field.setText("click left");
            sendButton.callOnClick();
            field.setText("");
        });
        rightc.setOnClickListener(view -> {
            field.setText("click right");
            sendButton.callOnClick();
            field.setText("");
        });
        sup.setOnClickListener(view -> {
            field.setText("scroll up");
            sendButton.callOnClick();
            field.setText("");
        });
        sdo.setOnClickListener(view -> {
            field.setText("scroll down");
            sendButton.callOnClick();
            field.setText("");
        });
        
        win.setOnClickListener(view -> {
            if(!window_hold) {
                win.setColorFilter(Color.parseColor("#ff0000"));
                field.setText("press window");
                sendButton.callOnClick();
                field.setText("");
                window_hold = true;
            }else{
                win.setColorFilter(null);
                field.setText("release window");
                sendButton.callOnClick();
                field.setText("");
                window_hold = false;
            }
        });
        alt.setOnClickListener(view -> {
             if(!alt_hold) {
                alt.setColorFilter(Color.parseColor("#ff0000"));
                field.setText("press alt");
                sendButton.callOnClick();
                field.setText("");
                alt_hold = true;
            }else{
                alt.setColorFilter(null);
                field.setText("release alt");
                sendButton.callOnClick();
                field.setText("");
                alt_hold = false;
            }
        });
        ctr.setOnClickListener(view -> {
             if(!ctrl_hold) {
                ctr.setColorFilter(Color.parseColor("#ff0000"));
                field.setText("press ctrl");
                sendButton.callOnClick();
                field.setText("");
                ctrl_hold = true;
            }else{
                ctr.setColorFilter(null);
                field.setText("release ctrl");
                sendButton.callOnClick();
                field.setText("");
                ctrl_hold = false;
            }
        });
        tab.setOnClickListener(view -> {
            field.setText("press tab");
            sendButton.callOnClick();
            field.setText("");
        });
        sft.setOnClickListener(view -> {
            if(!shift_hold) {
                    sft.setColorFilter(Color.parseColor("#ff0000"));
                    field.setText("press shift");
                    sendButton.callOnClick();
                    field.setText("");
                    shift_hold = true;
                }else{
                    sft.setColorFilter(null);
                    field.setText("release shift");
                    sendButton.callOnClick();
                    field.setText("");
                shift_hold = false;
            }
        });
        
         mic.setOnClickListener(v -> {
             Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
             intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                             RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
             intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                             Locale.getDefault());
             intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");
             try {
                 startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
             }
             catch (Exception e) {
                 Toast
                     .makeText(MainActivity.this, " " + e.getMessage(),
                               Toast.LENGTH_SHORT)
                     .show();
             }
         });
         
        leftP.setOnTouchListener((v, event) -> {
   if(event.getAction()==MotionEvent.ACTION_DOWN) {
       x1 = event.getX();
       y1 = event.getY();
   }else{
          x2 = event.getX();
          y2 = event.getY();
    }
    return false;
        });
   leftP.setOnClickListener(view ->{
          field.setText((("move x")+(x2-x1)+" "+("move y")+(y2-y1)));
          sendButton.callOnClick();
          field.setText("");
   });
        new Thread(() -> {
            try {
                s = new Socket("192.168.1.37", 6666);
                dout = new DataOutputStream(s.getOutputStream());
                } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        
        sendButton.setOnClickListener(view -> {
            if (!str.equals("exit")) {
                str = field.getText().toString();
                new Thread(() -> {
                    try {
                        dout.writeUTF(str);
                        dout.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                new Thread(() -> {
                    try {
                        dout.close();
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
                field.setText(
                    Objects.requireNonNull(result).get(0));
                sendButton.callOnClick();
                field.setText("");
                mic.callOnClick();
            }
        }
    }
}