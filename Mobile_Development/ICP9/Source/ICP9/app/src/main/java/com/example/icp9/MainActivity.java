package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";


    EditText userNameView;
    EditText userEmailView;
    CheckBox cheese;
    CheckBox mushrooms;
    CheckBox chicken;
    CheckBox pineapple;
    Button OrderBtn;
    final int PIZZA_PRICE = 10;
    final int CHEESE_PRICE = 3;
    final int MUSHROOMS_PRICE = 2;
    final int CHICKEN_PRICE = 5;
    final int PINEAPPLE_PRICE = 1;
    String size;
    String crust;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameView = findViewById(R.id.user);
        userEmailView =  findViewById(R.id.email);

        pineapple = findViewById(R.id.pineapple);
        cheese = findViewById(R.id.cheese);
        mushrooms = findViewById(R.id.mushrooms);
        chicken =  findViewById(R.id.chicken);
        OrderBtn = findViewById(R.id.btn_order);

        OrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

    }

    public void onSizeSelected(View view){
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.size_small:
                if (checked)
                    size = "Small";
                break;
            case R.id.size_medium:
                if (checked)
                    size = "Medium";
                break;
            case R.id.size_large:
                if (checked)
                    size = "Large";
                break;
        }
    }

    public void onCrustSelected(View view){
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.originalPan:
                if (checked)
                    crust = "Original Pan Pizza";
                break;
            case R.id.handmadePan:
                if (checked)
                    crust = "Handmade Pan";
                break;
            case R.id.thinNCrispy:
                if (checked)
                    crust = "Thin'N Crispy";
                break;
        }
    }


    public void sendEmail() {

        // read user input
        String userName = userNameView.getText().toString();
        // read user email
        String userEmail = userEmailView.getText().toString();
        String[] userEmails = userEmail.split(",");

        // check if pineapple is selected
        boolean hasPineapple = pineapple.isChecked();
        // check if cheese is selected
        boolean hasCheese = cheese.isChecked();
        // check if mushroom is selected
        boolean hasMushrooms = mushrooms.isChecked();
        // check if chicken is selected
        boolean hasChicken = chicken.isChecked();


        // calculate and store the total price
        float totalPrice = calculatePrice(hasCheese, hasMushrooms, hasChicken, hasPineapple);

        // create and store the order summary details
        String orderSummaryMessage = createOrderSummary(userName, hasCheese, hasMushrooms, hasChicken, hasPineapple, totalPrice);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        Log.i("Send email", "");
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, userEmails);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Pizza Delivery Details");
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummaryMessage);
        try {
            startActivity(Intent.createChooser(emailIntent, "Choose any email client"));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        String userName = userNameView.getText().toString();

        boolean hasPineapple = pineapple.isChecked();

        boolean hasCheese = cheese.isChecked();

        boolean hasMushrooms = mushrooms.isChecked();

        boolean hasChicken = chicken.isChecked();

        float totalPrice = calculatePrice(hasCheese, hasMushrooms, hasChicken, hasPineapple);

        String orderSummaryMessage = createOrderSummary(userName, hasCheese, hasMushrooms,hasChicken, hasPineapple, totalPrice);

        Intent redirect = new Intent(MainActivity.this, OrderActivity.class);
        redirect.putExtra("MESSAGE", orderSummaryMessage);
        MainActivity.this.startActivity(redirect);
    }


    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }
    // Order Summary Details
    private String createOrderSummary(String userName, boolean hasCheese, boolean hasMushrooms, boolean hasChicken, boolean hasPineapple, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userName) + ",\n" +
                getString(R.string.order_details) + "\n" +
                getString(R.string.order_summary_pineapple, boolToString(hasPineapple)) + "\n" +
                getString(R.string.order_summary_cheese, boolToString(hasCheese)) + "\n" +
                getString(R.string.order_summary_mushrooms, boolToString(hasMushrooms)) + "\n" +
                getString(R.string.order_summary_chicken, boolToString(hasChicken)) + "\n" +
                "Crust?" + crust + "\n"+
                "Size?" + size + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasCheese, boolean hasMushrooms, boolean hasChicken, boolean hasPineapple) {
        int basePrice = PIZZA_PRICE;
        if (hasCheese) {
            basePrice += CHEESE_PRICE;
        }
        if (hasMushrooms) {
            basePrice += MUSHROOMS_PRICE;
        }
        if (hasChicken) {
            basePrice += CHICKEN_PRICE;
        }
        if (hasPineapple) {
            basePrice += PINEAPPLE_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.tv_quantity);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred pizza");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }


    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}