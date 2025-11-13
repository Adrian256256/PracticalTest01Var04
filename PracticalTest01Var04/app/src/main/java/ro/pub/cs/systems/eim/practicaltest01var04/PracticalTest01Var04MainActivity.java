package ro.pub.cs.systems.eim.practicaltest01var04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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
//            information.append("Checkbox 1: ").append(checkBox1.isChecked() ? "checked" : "not checked").append("\n");
//            information.append("Checkbox 2: ").append(checkBox2.isChecked() ? "checked" : "not checked").append("\n");
//            information.append("EditText 1: ").append(editText1.getText().toString()).append("\n");
//            information.append("EditText 2: ").append(editText2.getText().toString()).append("\n");
            information_textView.setText(information.toString());
        });
    }
}