package com.example.sahil.calci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextno1, editTextno2;
    TextView textResult;
    Button buttonMultiply , buttonReset;
    String number1 ="";
    String number2 ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextno1 = (EditText) findViewById(R.id.edittxtno1);
        editTextno2 = (EditText) findViewById(R.id.edittxtno2);
        textResult = (TextView)  findViewById(R.id.TextResult);
        buttonMultiply = (Button) findViewById(R.id.button2);
        buttonReset = (Button) findViewById(R.id.reset);



        buttonMultiply.setOnClickListener(new View.OnClickListener() {

                                              @Override
                                              public void onClick(View v) {

                                                  if(editTextno2.getText().length() == 0 || editTextno1.getText().length()==0){

                                                      Toast.makeText(MainActivity.this, "Please enter numbers", Toast.LENGTH_SHORT).show();
                                                  }
                                                  else{
                                                      number1  = editTextno1.getText().toString();
                                                      number2 = editTextno2.getText().toString();


                                                      getResult(number1,number2);
                                                  
                                              }}
                                          }

        );
         buttonReset.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                number1 = null;
                                                number2 = null;
                                                editTextno1.setText("");
                                                editTextno2.setText("");
                                                textResult.setText("");

                                            }
                                        }
         );

    }

    private void getResult(String number1, String number2) {



        String num1 = number1;
        String tempnum1 = num1;
        String num2 = number2;
        String tempnum2 = num2;

        // Check condition if one string is negative
        if(num1.charAt(0) == '-' && num2.charAt(0)!='-')
        {
            num1 = num1.substring(1);
        }
        else if(num1.charAt(0) != '-' && num2.charAt(0) == '-')
        {
            num2 = num2.substring(1);
        }
        else if(num1.charAt(0) == '-' && num2.charAt(0) == '-')
        {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
        }
        String s1 = new StringBuffer(num1).reverse().toString();
        String s2 = new StringBuffer(num2).reverse().toString();

        int[] m = new int[s1.length()+s2.length()];

        // Go from right to left in num1
        for (int i = 0; i < s1.length(); i++)
        {
            // Go from right to left in num2
            for (int j = 0; j < s2.length(); j++)
            {
                m[i+j] = m[i+j]+(s1.charAt(i)-'0')*(s2.charAt(j)-'0');

            }
        }


        String product = new String();
        // Multiply with current digit of first number
        // and add result to previously stored product
        // at current position.
        for (int i = 0; i < m.length; i++)
        {
            int digit = m[i]%10;
            int carry = m[i]/10;
            if(i+1<m.length)
            {
                m[i+1] = m[i+1] + carry;
            }
            product = digit+product;

        }

        // ignore '0's from the right
        while(product.length()>1 && product.charAt(0) == '0')
        {
            product = product.substring(1);
        }

        // Check condition if one string is negative
        if(tempnum1.charAt(0) == '-' && tempnum2.charAt(0)!='-')
        {
            product = new StringBuffer(product).insert(0,'-').toString();
        }
        else if(tempnum1.charAt(0) != '-' && tempnum2.charAt(0) == '-')
        {
            product = new StringBuffer(product).insert(0,'-').toString();
        }
        else if(tempnum1.charAt(0) == '-' && tempnum2.charAt(0) == '-')
        {
            product = product;
        }
        textResult.setText(product);
    }
}


