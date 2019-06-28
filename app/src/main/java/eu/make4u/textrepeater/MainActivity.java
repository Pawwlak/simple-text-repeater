package eu.make4u.textrepeater;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit_text, repeat, output;
    Button button,copy, clear, share_text;
    String text = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text = findViewById(R.id.edit_text);
        repeat = findViewById(R.id.repeat);
        output = findViewById(R.id.output);

        button = findViewById(R.id.button);
        copy = findViewById(R.id.copy);
        clear = findViewById(R.id.clear);
        share_text = findViewById(R.id.share_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edit_text.getText().toString();
                String number = repeat.getText().toString();

                if(!number.equals("")) {
                    int number_repeat = Integer.parseInt(repeat.getText().toString());
                    text = "";
                    for(int i=0; i<number_repeat; i++){
                        text = text + input + " ";
                    }
                    output.setText(text);
                    Toast.makeText(getApplicationContext(), "Generete Finished", Toast.LENGTH_SHORT).show();

                }
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Share", output.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Copied ", Toast.LENGTH_SHORT).show();


            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.getText().clear();
                repeat.getText().clear();
                output.getText().clear();
            }
        });

        share_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareintent = new Intent();
                shareintent.setAction(Intent.ACTION_SEND);
                shareintent.putExtra(Intent.EXTRA_TEXT, output.getText().toString());
                shareintent.setType("text/plain");
                startActivity(shareintent);

            }
        });
    }
}
