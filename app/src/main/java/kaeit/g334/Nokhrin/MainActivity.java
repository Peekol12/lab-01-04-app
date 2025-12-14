package kaeit.g334.Nokhrin;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_sub, btn_mul, btn_div;
    EditText txt_a, txt_b;
    TextView txt_res;

    CalculatorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy =  new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);

        txt_a = findViewById(R.id.txt_a);
        txt_b = findViewById(R.id.txt_b);
        txt_res = findViewById(R.id.txt_tochka);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:1880/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        service = retrofit.create(CalculatorService.class);
    }

    public void on_button_click(View v) {
        String a = txt_a.getText().toString();
        String b = txt_b.getText().toString();

        Call<String> func = null;

        if (v.getId() == R.id.btn_add) {
            func = service.add(a, b);
        } else if (v.getId() == R.id.btn_sub) {
            func = service.subtract(a, b);
        } else if (v.getId() == R.id.btn_mul) {
            func = service.multiply(a, b);
        } else if (v.getId() == R.id.btn_div) {
            func = service.divide(a, b);
        }

        if (func == null) return;

        try {
            String res = func.execute().body();
            txt_res.setText(res);
        } catch (IOException e) {
            txt_res.setText("Ошибка");
        }
    }
}