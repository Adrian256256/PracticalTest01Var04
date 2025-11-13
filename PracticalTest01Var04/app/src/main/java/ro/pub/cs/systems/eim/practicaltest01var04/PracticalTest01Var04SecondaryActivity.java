package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    Button ok_button;
    Button cancel_button;
    TextView textView;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);
        ok_button = findViewById(R.id.ok_button);
        cancel_button = findViewById(R.id.cancel_button);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String group = intent.getStringExtra("group");
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView.setText(name);
        textView2.setText(group);

        ok_button.setOnClickListener(v -> {
            Intent result = new Intent();
//            result.putExtra(Constants.SUM, sum);
            setResult(RESULT_OK, result);
            finish();
        });

        cancel_button.setOnClickListener(v -> {
            Intent result = new Intent();
            setResult(RESULT_CANCELED, result);
            finish();
        });


    }
}