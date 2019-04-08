package com.xheghun.justanotherpractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.xheghun.justanotherpractice.network.ApiClient;
import com.xheghun.justanotherpractice.network.ApiInterface;
import com.xheghun.justanotherpractice.network.Note;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EditNoteActivity extends AppCompatActivity {
    TextInputLayout nTitleLayout;
    TextInputLayout nTextLayout;
    TextInputEditText nTitle;
    TextInputEditText nText;
    private View view;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        nTitleLayout = findViewById(R.id.note_title_layout);
        nTextLayout = findViewById(R.id.note_text_layout);

        nTitle = findViewById(R.id.note_title);
        nText = findViewById(R.id.note_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:

                String title = String.valueOf(nTitle.getText()).trim();
                String note = String.valueOf(nText.getText()).trim();
                int color = -2184710;

                if (title.isEmpty()) {
                    nTitleLayout.setErrorEnabled(true);
                    nTitleLayout.setError("please provide a title");
                }else if (note.isEmpty()) {
                    nTextLayout.setErrorEnabled(true);
                    nTextLayout.setError("note cannot be empty");
                }
                else {
                    //clear errors
                    nTextLayout.setErrorEnabled(false);
                    nTextLayout.setError("");
                    nTitleLayout.setErrorEnabled(false);
                    nTitleLayout.setError("");

                    //save note
                    saveNote(title,note,color);
                }


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote(final String title, final String note,
                          final int catColor) {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title,note,catColor);

        view = findViewById(R.id.second_container);
call.enqueue(new Callback<Note>() {
    @Override
    public void onResponse(Call<Note> call, Response<Note> response) {
        if (response.isSuccessful() && response.body() != null) {
            Boolean success = response.body().getSuccess();
            if (success) {
                Snackbar.make(view, "su: " + response.body().getMessage(), Snackbar.LENGTH_SHORT).show();

            }else {
                Snackbar.make(view,"su: "+response.body().getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("view more", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditNoteActivity.this);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.setTitle(response.body().getMessage());
                        alertDialog.setMessage(response.body().getSql_error());
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                             alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }).show();
            }
        }
    }

    @Override
    public void onFailure(Call<Note> call, Throwable t) {
        Snackbar.make(view,"fail: "+t.getLocalizedMessage(),Snackbar.LENGTH_SHORT)
                .show();
        t.printStackTrace();
        Log.v("OKHttp: ", Arrays.toString(t.getStackTrace()));

    }
});
    }
}