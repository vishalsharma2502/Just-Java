package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view){

        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkBox.isChecked();

        CheckBox chocolateCheckBox  = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox .isChecked();

        EditText editText = (EditText) findViewById(R.id.name_edit_view);
        String name = editText.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, name, hasWhippedCream,hasChocolate);
        displayMessage(priceMessage);

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo: 47.6, -122.3"));
//        if(intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order For Vishal");
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }



    }

    private void displayQuantity(int numberOfCoffees){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+ numberOfCoffees);
    }

    private void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        int basePrice = 5;

        if(hasWhippedCream){
            basePrice +=1 ;
        }

        if(hasChocolate){
            basePrice +=2;
        }
        return quantity * basePrice;
    }

    private String createOrderSummary(int price, String name, boolean addWhippedCream, boolean chocolate){
        String priceMessage = "Name: "+name;
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + chocolate;
        priceMessage += "\nQuantity: "+quantity +"\nTotal: $"+ price + "\nThank you!";
        return priceMessage;
    }

    public void increment(View view){
        if(quantity == 100){
            Toast.makeText(this,"You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if(quantity == 1){
            Toast.makeText(this,"You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

}