package chenfeng.helloworld;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] messages;
    private Random random;
    private ImageButton imageButton;
    private boolean isRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();
        messages = new String[10];
        messages[0] = "hello";
        messages[1] = "message2";
        messages[2] = "These are not the Droids you're looking for.";
        messages[3] = "All your Androids are belong to us!";
        messages[4] = "Thank you! \nBut our Android is in another castle";
        messages[5] = "It's me! \n Android!";
        messages[6] = "hello?";
        messages[7] = "hello from the other side.";
        messages[8] = "Hey! Look! Listen!";
        messages[9] = "last message";

        isRed = false;
        imageButton =(ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                changePic(view);
            }
        });

    }

    public void changeText(View view){
        TextView text = (TextView) findViewById(R.id.textview1);
        text.setText(messages[random.nextInt(10)]);
    }
    public void changePic(View view){
        if(!isRed){
            imageButton.setImageResource(R.drawable.red_stop_button);
            isRed = true;
        }else{
            imageButton.setImageResource(R.drawable.green_go_button);
            isRed = false;
        }

    }
}
