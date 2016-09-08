package com.example.lab2;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author jamiekujawa
 *
 */
public class MainActivity extends Activity {

    /**
     * A string builder to represent the first number entered in the series of entries
     */
    private StringBuilder expression1;

    /**
     * A string builder to represent the second number entered in the series of entries
     */
    private StringBuilder expression2;

    /**
     * A string to represent the last operator performed
     */
    private String oldOperator;

    private String operator;

    private boolean isEx1, isValidEx;

    private TextView tvMain;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare all buttons used within the layout
        Button zero = (Button) findViewById(R.id.button0);
        Button one = (Button) findViewById(R.id.button1);
        Button two = (Button) findViewById(R.id.button2);
        Button three = (Button) findViewById(R.id.button3);
        Button four = (Button) findViewById(R.id.button4);
        Button five = (Button) findViewById(R.id.button5);
        Button six = (Button) findViewById(R.id.button6);
        Button seven = (Button) findViewById(R.id.button7);
        Button eight = (Button) findViewById(R.id.button8);
        Button nine = (Button) findViewById(R.id.button9);
        Button times = (Button) findViewById(R.id.buttontimes);
        Button clear = (Button) findViewById(R.id.buttonClear);
        Button equal = (Button) findViewById(R.id.buttonEqual);
        Button decimal = (Button) findViewById(R.id.buttonDecimal);
        Button divide = (Button) findViewById(R.id.buttondivide);
        Button add = (Button) findViewById(R.id.buttonplus);
        Button subtract = (Button) findViewById(R.id.buttonminus);

        // declare main text view
        tvMain = (TextView) findViewById(R.id.CalculatorText);

        // Main Strings to represent the expressions
        expression1 = new StringBuilder();
        expression2 = new StringBuilder();
        operator = "";
        tvMain.setText(expression1.append("0.0"));
        expression1.delete(0,expression1.length());
        tvMain.setText(expression1);

        isEx1 = true;
        isValidEx = false;
		/*
		 * Set up all key listener events
		 */
        zero.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("0");
            }

        });

        one.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("1");
            }

        });

        two.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("2");
            }

        });

        three.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("3");
            }

        });

        four.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("4");
            }

        });

        five.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("5");
            }

        });

        six.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("6");
            }

        });

        seven.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("7");
            }

        });

        eight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("8");
            }

        });

        nine.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper("9");
            }

        });

        times.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String times = "*";
                opHelper(times);
            }

        });

        clear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                expression1.delete(0,expression1.length());
                expression2.delete(0,expression2.length());
                operator = "";
                oldOperator = "";
                isValidEx = false;
                isEx1 = true;
                tvMain.setText("0.0");
            }

        });

        equal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                evaluate("=");
            }

        });

        decimal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                exHelper(".");
            }

        });

        divide.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String div = "/";
                opHelper(div);
            }

        });

        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String add = "+";
                opHelper(add);
            }

        });

        subtract.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String sub = "-";
                opHelper(sub);
            }

        });
    }

    /**
     * This method will evaluate an operation using expression1 and expression 2
     *
     * @param buttonPressed or operation to be performed
     * @return the result of the operation
     */
    private void evaluate(String buttonPressed) {
        if(buttonPressed.equals("=")){
            if(isValidEx) {
                double ex1 = Double.parseDouble(expression1.toString());
                double ex2 = Double.parseDouble(expression2.toString());
                String answer = evHelper(ex1, ex2, operator);
                if (answer.equals("error:div0") || answer.equals("no way")) {
                    tvMain.setText(answer);
                } else {
                    expression1.delete(0, expression1.length());
                    expression1.append(answer);
                    expression2.delete(0, expression2.length());
                    operator = "";
                    oldOperator = "";
                    isEx1 = false;
                    isValidEx = false;
                    tvMain.setText(expression1);
                }
            }else{
                if(isEx1){
                    expression1.delete(0,expression1.length());
                }
            }
        }else if(buttonPressed.equals("+") ||buttonPressed.equals("-")||buttonPressed.equals("*") || buttonPressed.equals("/")){
            if(isValidEx){
                double ex1 = Double.parseDouble(expression1.toString());
                double ex2 = Double.parseDouble(expression2.toString());
                String answer = evHelper(ex1,ex2,buttonPressed);
                if(answer.equals("error:div0") || answer.equals("no way")){
                    tvMain.setText(answer);
                }else{
                    expression1.delete(0,expression1.length());
                    expression1.append(answer);
                    expression2.delete(0,expression2.length());
                    isEx1 = false;
                    isValidEx = false;
                    tvMain.setText(expression1);
                }
            }
        }
    }

    private String evHelper(double ex1, double ex2, String op){
        if(op.equals("+")){
            return (ex1 + ex2) + "";
        }else if(op.equals("-")){
            return (ex1 - ex2) + "";
        }else if(op.equals("*")){
            return (ex1 * ex2) + "";
        }else if(op.equals("/")){
            if(ex2==0){
                return "error:div0";
            }else{
                return (ex1 / ex2) + "";
            }
        }else{
            return ex1 + "";
        }
    }

    private void exHelper (String num){
        if(isEx1 && isValidEx){
            expression1.append(num);
            expression2.delete(0,expression2.length());
            isValidEx = false;
            operator = "";
            tvMain.setText(expression1);
        }else if(!isEx1 && isValidEx){
            expression2.append(num);
            tvMain.setText(expression2);
        }else if(isEx1 && !isValidEx){
            expression1.append(num);
            tvMain.setText(expression1);
        }else if(!isEx1 && !isValidEx){
            if(operator.equals("")){
                isEx1 = false;
                expression1.delete(0,expression1.length());
                expression1.append(num);
                tvMain.setText(expression1);
            }else{
                expression2.append(num);
                isValidEx = true;
                tvMain.setText(expression2);
            }

        }
    }

    private void opHelper(String op){
        if(isEx1 && !isValidEx){
            operator = op;
            isEx1 = false;
        }else if(!isEx1 && isValidEx){
            oldOperator = operator;
            operator = op;
            evaluate(oldOperator);
        }else if(!isEx1 && !isValidEx){
            if(operator.equals("")){
                operator = op;
            }else {
                tvMain.setText("ex2 is: " + expression2);
            }
        }
    }

}