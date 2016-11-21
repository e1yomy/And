package com.jamg.example.serviciosweb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jamg.example.serviciosweb.webservice.Arithmetic;

public class WebServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private class myAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvResult.setText(String.valueOf(res));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            switch (requestId) {
                case R.id.btn_sum:
                    res = Arithmetic.sum(a, b);
                    break;

                case R.id.btn_sub:
                    res = Arithmetic.sub(a, b);
                    break;

                case R.id.btn_mul:
                    res = Arithmetic.mul(a, b);
                    break;

                case R.id.btn_div:
                    res = Arithmetic.div(a, b);
                    break;
            }

            return null;
        }
    }

    private TextView tvResult;
    private EditText txtNumber1, txtNumber2;

    private double res, a, b;
    private int requestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        tvResult = (TextView) findViewById(R.id.tv_result);

        txtNumber1 = (EditText) findViewById(R.id.txt_number1);
        txtNumber2 = (EditText) findViewById(R.id.txt_number2);
        txtNumber1.requestFocus();

        Button btnSum, btnSub, btnMul, btnDiv, btnClear;

        btnSum = (Button) findViewById(R.id.btn_sum);
        btnSum.setOnClickListener(this);

        btnSub = (Button) findViewById(R.id.btn_sub);
        btnSub.setOnClickListener(this);

        btnMul = (Button) findViewById(R.id.btn_mul);
        btnMul.setOnClickListener(this);

        btnDiv = (Button) findViewById(R.id.btn_div);
        btnDiv.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText("");
                txtNumber1.setText("");
                txtNumber2.setText("");
                txtNumber1.requestFocus();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!txtNumber1.getText().toString().equals("") && !txtNumber2.getText().toString().equals("")) {
            a = Double.parseDouble(txtNumber1.getText().toString());
            b = Double.parseDouble(txtNumber2.getText().toString());

            myAsyncTask request;

            switch (view.getId()) {
                case R.id.btn_sum:
                    requestId = R.id.btn_sum;
                    request = new myAsyncTask();
                    request.execute();
                    break;

                case R.id.btn_sub:
                    requestId = R.id.btn_sub;
                    request = new myAsyncTask();
                    request.execute();
                    break;

                case R.id.btn_mul:
                    requestId = R.id.btn_mul;
                    request = new myAsyncTask();
                    request.execute();
                    break;

                case R.id.btn_div:
                    if (b != 0) {
                        requestId = R.id.btn_div;
                        request = new myAsyncTask();
                        request.execute();
                    }
                    break;
            }
        }
    }
}
