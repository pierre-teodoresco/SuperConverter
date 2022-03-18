package fr.pteodoresco.superconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ConverterListener {

    private Converter converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        converter = new ConverterGeoplugin();
        converter.setListener(this);
        converter.init(this);

        Button convertButton = findViewById(R.id.conversion_button);
        convertButton.setOnClickListener(view -> {
            EditText valueToConvertTextView = findViewById(R.id.conversion_value);
            String textInput = valueToConvertTextView.getText().toString();
            float value = Float.parseFloat(textInput);

            value = converter.convert(value);

            Locale current = getResources().getConfiguration().locale;
            TextView convertResult = findViewById(R.id.conversion_result);
            convertResult.setText(String.format(current, "%.2f", value));
        });
    }

    @Override
    public void update(float val) {
        findViewById(R.id.conversion_button).setEnabled(true);
        Locale current = getResources().getConfiguration().locale;
        TextView labelRate = findViewById(R.id.conversion_rate);
        labelRate.setText(String.format(current, "Conversion Rate : %.4f", converter.getRate()));
    }
}