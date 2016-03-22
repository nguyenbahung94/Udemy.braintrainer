package com.example.android.udemybraintrainerbuild14;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbutton,btn,btn1,btn2,btn3,playagain;
    TextView sumtextview,result,point,Timertxt;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    RelativeLayout relativeLayoutnew;
    int locationOfCorrectAnswer;
    int score=0;
    int numberOFquestions=0;

    public void PlayAgain(View view){
        score=0;
        numberOFquestions=0;

        Timertxt.setText("30s");
        point.setText("0/0");
        result.setText("");
        playagain.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                Timertxt.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playagain.setVisibility(View.VISIBLE);
                Timertxt.setText("Done");
                result.setText("your score "+Integer.toString(score)+"/"+Integer.toString(numberOFquestions ));
            }
        }.start();
    }
    public void generateQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumtextview.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        answer.clear();
        int correctAnswer=0;
        for(int i=0;i<4;i++){
            if (i==locationOfCorrectAnswer){
                answer.add(a+b);
            }else{
                correctAnswer=rand.nextInt(41);
                while(correctAnswer==a+b){
                    correctAnswer=rand.nextInt(41);
                }
                answer.add(correctAnswer);
            }
        }
        btn.setText(Integer.toString(answer.get(0)));
        btn1.setText(Integer.toString(answer.get(1)));
        btn2.setText(Integer.toString(answer.get(2)));
        btn3.setText(Integer.toString(answer.get(3)));
    }

    public void ChoseAnwser(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            result.setText("Correct!");
        }else{
            result.setText("Wrong!");
        }
        numberOFquestions++;
        point.setText(Integer.toString(score)+"/"+Integer.toString(numberOFquestions ));
        generateQuestion();
    }

    public void Start(View view){
        generateQuestion();
        startbutton.setVisibility(View.INVISIBLE);
        relativeLayoutnew.setVisibility(RelativeLayout.VISIBLE);
        PlayAgain(findViewById(R.id.btn_playagain));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayoutnew=(RelativeLayout)findViewById(R.id.relativelayoutnew);
        startbutton=(Button)findViewById(R.id.btn_go);
        sumtextview=(TextView)findViewById(R.id.txt_sum);
        result=(TextView)findViewById(R.id.txt_correct);
        point=(TextView)findViewById(R.id.txt_point);
        playagain=(Button)findViewById(R.id.btn_playagain);
        Timertxt=(TextView)findViewById(R.id.txt_timer);
        btn=(Button)findViewById(R.id.button);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);



    }
}
