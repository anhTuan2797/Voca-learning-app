package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.data.word;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class WordBottomSheet extends BottomSheetDialogFragment {
    private word mWord;
    private TopicViewModel topicViewModel;

    private final float Dialog_Font_size = 24;

    public WordBottomSheet(word mWord,TopicViewModel topicViewModel) {
        this.mWord = mWord;
        this.topicViewModel = topicViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.word_bottom_sheet_layout,container,false);
        TextView header = v.findViewById(R.id.word_name_header_bottom_sheet);
        header.setText(mWord.getWord());
        Button learnedButton = v.findViewById(R.id.remember_button_bottom_sheet);
        Drawable img;
        if(mWord.getPriority() == 0){
//            img = getContext().getResources().getDrawable(R.drawable.ic_star_full);
            learnedButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star_full,0,0,0);
        }
        else {
//            img = getContext().getResources().getDrawable(R.drawable.ic_star);
            learnedButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star,0,0,0);
        }
//        learnedButton.setCompoundDrawables(img,null,null,null);
        Button editButton = v.findViewById(R.id.edit_button_bottom_sheet);
        Button deleteButton = v.findViewById(R.id.delete_word_button_bottom_sheet);

        learnedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable img;
                if(mWord.getPriority() == 0){
                    mWord.setPriority(1);
                    learnedButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star,0,0,0);
                }
                else {
                    mWord.setPriority(0);
                    learnedButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star_full,0,0,0);
                }
                topicViewModel.updateWord(mWord);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
            @Override
            public void onClick(View v) {
                TextView wordTextView = new TextView(getContext());
                TextView hintTextView = new TextView(getContext());
                TextView meanTextView = new TextView(getContext());

                wordTextView.setPadding(30,60,10,10);
                hintTextView.setPadding(30,60,10,10);
                meanTextView.setPadding(30,60,10,10);

                wordTextView.setTextColor(Color.BLACK);
                hintTextView.setTextColor(Color.BLACK);
                meanTextView.setTextColor(Color.BLACK);

                wordTextView.setTextSize(Dialog_Font_size);
                hintTextView.setTextSize(Dialog_Font_size);
                meanTextView.setTextSize(Dialog_Font_size);


                EditText wordEdiText = new EditText(getContext());
                EditText hintEditText = new EditText(getContext());
                EditText meanEditText = new EditText(getContext());

                wordEdiText.setPadding(30,10,20,10);
                hintEditText.setPadding(30,10,20,10);
                meanEditText.setPadding(30,10,20,10);

                wordEdiText.setTextSize(Dialog_Font_size);
                hintEditText.setTextSize(Dialog_Font_size);
                meanEditText.setTextSize(Dialog_Font_size);

                wordEdiText.setText(mWord.getWord());
                hintEditText.setText(mWord.getWord_hint());
                meanEditText.setText(mWord.getWord_mean());

                wordTextView.setText("Word");
                hintTextView.setText("Hint");
                meanTextView.setText("Mean");

                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                layout.addView(wordTextView);
                layout.addView(wordEdiText);
                layout.addView(hintTextView);
                layout.addView(hintEditText);
                layout.addView(meanTextView);
                layout.addView(meanEditText);

                final AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Edit word")
                        .setView(layout)
                        .setPositiveButton(R.string.change, null)
                        .setNegativeButton(getString(R.string.clear), null).create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button clearButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                        clearButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wordEdiText.setText("");
                                hintEditText.setText("");
                                meanEditText.setText("");
                            }
                        });

                        Button changeButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                        changeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String word = wordEdiText.getText().toString();
                                String hint = hintEditText.getText().toString();
                                String mean = meanEditText.getText().toString();
                                if(TextUtils.isEmpty(word) || TextUtils.isEmpty(hint) || TextUtils.isEmpty(mean)){
                                    Toast.makeText(getActivity(), "empty field", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    mWord.setWord(word);
                                    mWord.setWord_hint(hint);
                                    mWord.setWord_mean(mean);
                                    topicViewModel.updateWord(mWord);
                                    Toast.makeText(getActivity(), "word changed", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                });
                dialog.show();
            }
        });



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topicViewModel.deleteWords(mWord);
                dismiss();
                Toast.makeText(getActivity(), "word deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }


}
