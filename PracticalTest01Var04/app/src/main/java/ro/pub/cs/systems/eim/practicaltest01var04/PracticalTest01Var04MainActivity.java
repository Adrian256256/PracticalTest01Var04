package ro.pub.cs.systems.eim.practicaltest01var04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    Button navigate_to_secondary_activity_button;
    CheckBox checkBox1;
    CheckBox checkBox2;
    EditText editText1;
    EditText editText2;
    TextView information_textView;
    Button display_information_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        navigate_to_secondary_activity_button = findViewById(R.id.navigate_to_secondary_activity_button);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        information_textView = findViewById(R.id.information_textView);
        display_information_button = findViewById(R.id.display_information_button);

        display_information_button.setOnClickListener(v -> {
            StringBuilder information = new StringBuilder();
            if (checkBox1.isChecked()) {
                // add information from editText1
                information.append(editText1.getText().toString()).append(" ");
            }
            if (checkBox2.isChecked()) {
                // add information from editText2
                information.append(editText2.getText().toString());
            }

            information_textView.setText(information.toString());
        });


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text1", editText1.getText().toString());
        outState.putString("text2", editText2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("text1")) {
            editText1.setText(savedInstanceState.getString("text1"));
        } else {
            editText1.setText("");
        }

        if (savedInstanceState.containsKey("text2")) {
            editText2.setText(savedInstanceState.getString("text2"));
        } else {
            editText2.setText("0");
        }
    }
}