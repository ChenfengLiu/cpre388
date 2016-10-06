package chenfeng.hw2q2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    Button bt3, bt4,bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView);

        bt3 = (Button) findViewById(R.id.button3); //A
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "A");
            }
        });
        bt4 = (Button) findViewById(R.id.button4); //A
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "B");
            }
        });
        bt5 = (Button) findViewById(R.id.button5); //A
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "C");
            }
        });
        bt6 = (Button) findViewById(R.id.button6); //A
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "D");
            }
        });
        bt7 = (Button) findViewById(R.id.button7); //A
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "E");
            }
        });
        bt8 = (Button) findViewById(R.id.button8); //A
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "F");
            }
        });
        bt9 = (Button) findViewById(R.id.button9); //A
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "G");
            }
        });
        bt10 = (Button) findViewById(R.id.button10); //A
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "H");
            }
        });
        bt11 = (Button) findViewById(R.id.button11); //A
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "I");
            }
        });
        bt12 = (Button) findViewById(R.id.button12); //A
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage(view, "J");
            }
        });

    }

    private void changeImage(View v,String id){
        if(id.equals("A")){
            iv.setImageDrawable(R.drawable.A);
        }
        else if(id.equals("B")){
            iv.setImageDrawable(R.drawable.B);
        }
        else if(id.equals("C")){
            iv.setImageDrawable(R.drawable.C);
        }
        else if(id.equals("D")){
            iv.setImageDrawable(R.drawable.D);
        }
        else if(id.equals("E")){
            iv.setImageDrawable(R.drawable.E);
        }
        else if(id.equals("F")){
            iv.setImageDrawable(R.drawable.F);
        }
        else if(id.equals("G")){
            iv.setImageDrawable(R.drawable.G);
        }
        else if(id.equals("H")){
            iv.setImageDrawable(R.drawable.H);
        }
        else if(id.equals("I")){
            iv.setImageDrawable(R.drawable.I);
        }
        else if(id.equals("J")) {
            iv.setImageDrawable(R.drawable.J);
        }
    }
}
