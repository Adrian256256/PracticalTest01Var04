package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    Button navigate_to_secondary_activity_button;
    CheckBox checkBox1;
    CheckBox checkBox2;
    EditText editText1;
    EditText editText2;
    TextView information_textView;
    Button display_information_button;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private final IntentFilter intentFilter = new IntentFilter();
    private final MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private static class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BC RECEIVER", Objects.requireNonNull(intent.getStringExtra("broadcast_receiver_extra")));
        }
    }

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

            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
            intent.putExtra("name", editText1.getText().toString());
            intent.putExtra("group", editText2.getText().toString());
            getApplicationContext().startService(intent);
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                if (result.getData() == null) {
                    return;
                }
                Toast.makeText(this, "The activity returned with OK  " , Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "The activity returned with CANCEL " , Toast.LENGTH_LONG).show();
            }
        });

        navigate_to_secondary_activity_button.setOnClickListener(v -> {

            Intent intent = new Intent(this, PracticalTest01Var04SecondaryActivity.class);
            String name = editText1.getText().toString();
            String group = editText2.getText().toString();
            intent.putExtra("name", name);
            intent.putExtra("group", group);

            activityResultLauncher.launch(intent);
        });


        intentFilter.addAction("send_message");
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

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(messageBroadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(messageBroadcastReceiver, intentFilter);
        }
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var04Service.class);
        stopService(intent);

        super.onDestroy();
    }
}