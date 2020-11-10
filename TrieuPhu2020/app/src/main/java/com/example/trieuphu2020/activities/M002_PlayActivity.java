package com.example.trieuphu2020.activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trieuphu2020.R;
import com.example.trieuphu2020.ScoreRule;
import com.example.trieuphu2020.ScoreSaveLoad;
import com.example.trieuphu2020.common.OnActionCallback;
import com.example.trieuphu2020.databases.entities.QuestionEntity;
import com.example.trieuphu2020.databases.entities.QuestionManager;
import com.example.trieuphu2020.dialog.DialogAudience;
import com.example.trieuphu2020.dialog.DialogCall;
import com.example.trieuphu2020.dialog.DialogConfirm;
import com.example.trieuphu2020.dialog.DialogStop;
import com.example.trieuphu2020.dialog.highscore.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;


public class M002_PlayActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_SCORE = "KEY_SCORE";
    private static final String TAG = M002_PlayActivity.class.getName();
    private static final int KEY_UPDATE_TEXT = 2001;
    private static final int KEY_COMPLETE = 2002;
    private static final String KEY_RESUME = "KEY_RESUME";
    private static final String KEY_START = "KEY_START";
    private static final String KEY_PAUSE = "KEY_PAUSE";
    private static final String KEY_UPDATE_TV = "KEY_UPDATE_TV";
    private static final long START_TIME = 30000;
    public TextView tvNumberQuestion, tvContentQuestion, tvCaseA, tvCaseB, tvCaseC, tvCaseD, tvScore, tvTimer;
    private ImageView imvStop, imvHelp5050, imvHelpCall, imvHelpAudience;
    private EditText edtScore;
    private int selected = 0;
    private int trueCase;
    private int level = 1;
    private long mTimeLeftInMillis = START_TIME;
    private CountDownTimer mCountDownTimer;
    private boolean isTimerRunning;
    private List<User> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_activity_play);
        initViews();
        initData();
    }

    private void initViews() {
        tvNumberQuestion = findViewById(R.id.tv_question_number);
        tvContentQuestion = findViewById(R.id.tv_question_content);
        tvCaseA = findViewById(R.id.tv_case1);
        tvCaseB = findViewById(R.id.tv_case2);
        tvCaseC = findViewById(R.id.tv_case3);
        tvCaseD = findViewById(R.id.tv_case4);
        tvScore = findViewById(R.id.tv_score);
        tvTimer = findViewById(R.id.tv_time);
        imvStop = findViewById(R.id.imv_stop);
        imvHelp5050 = findViewById(R.id.imv_help_5050);
        imvHelpCall = findViewById(R.id.imv_help_call);
        imvHelpAudience = findViewById(R.id.imv_help_audience);
    }

    private void initData() {
        selected = 0;
        tvCaseA.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvCaseB.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvCaseC.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvCaseD.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvCaseA.setVisibility(View.VISIBLE);
        tvCaseB.setVisibility(View.VISIBLE);
        tvCaseC.setVisibility(View.VISIBLE);
        tvCaseD.setVisibility(View.VISIBLE);
        setContentQS();
        setEvent();
        countTimer();
    }

    private void setContentQS() {
        QuestionManager.getInstance().getQuestionById(new QuestionManager.OnHSCallBack() {
            @Override
            public void callBack(Object data) {
                M002_PlayActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        QuestionEntity questionEntity = (QuestionEntity) data;
                        tvNumberQuestion.setText("Question " + level + ":");
                        tvContentQuestion.setText(questionEntity.getQuestion());
                        tvCaseA.setText(questionEntity.getCaseA().toString());
                        tvCaseB.setText(questionEntity.getCaseB());
                        tvCaseC.setText(questionEntity.getCaseC());
                        tvCaseD.setText(questionEntity.getCaseD());
                        trueCase = questionEntity.getTrueCase();
                    }
                });
            }
        }, level);
    }

    private void setEvent() {
        tvCaseA.setOnClickListener(this);
        tvCaseB.setOnClickListener(this);
        tvCaseC.setOnClickListener(this);
        tvCaseD.setOnClickListener(this);
        imvStop.setOnClickListener(this);
        imvHelp5050.setOnClickListener(this);
        imvHelpCall.setOnClickListener(this);
        imvHelpAudience.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_case1:
                selectCase(1, tvCaseA);
                checkAnswer(tvCaseA, selected);
                break;
            case R.id.tv_case2:
                selectCase(2, tvCaseB);
                checkAnswer(tvCaseB, selected);
                break;
            case R.id.tv_case3:
                selectCase(3, tvCaseC);
                checkAnswer(tvCaseC, selected);
                break;
            case R.id.tv_case4:
                selectCase(4, tvCaseD);
                checkAnswer(tvCaseD, selected);
                break;
            case R.id.imv_help_5050:
                pauseTimer();
                if (imvHelp5050.getDrawable().getLevel() == 0) {
                    imvHelp5050.setImageLevel(1);
                    getHelp5050();
                } else {
                    return;
                }
                break;
            case R.id.imv_help_call:
                pauseTimer();
                if (imvHelpCall.getDrawable().getLevel() == 0) {
                    imvHelpCall.setImageLevel(1);
                    DialogCall callDialog = new DialogCall(trueCase, this, new OnActionCallback() {
                        @Override
                        public void callBack(int key, Object data) {
                            startTimer();
                        }
                    });
                    callDialog.show();

                } else {
                    return;
                }
                break;
            case R.id.imv_help_audience:
                pauseTimer();
                if (imvHelpAudience.getDrawable().getLevel() == 0) {
                    imvHelpAudience.setImageLevel(1);
                    DialogAudience audienceDialog = new DialogAudience(trueCase, this, (key, data) -> {
                        startTimer();
                    });
                    audienceDialog.show();
                } else {
                    return;
                }
                break;
            case R.id.imv_stop:
                DialogStop dialogStop = new DialogStop(this, new OnActionCallback() {
                    @Override
                    public void callBack(int key, Object data) {
                        if (key == DialogStop.KEY_STOP_GAME) {
                            saveScoreDialog(Gravity.CENTER);
                        }
                    }
                });
                dialogStop.show();
        }
    }

    private void saveScoreDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_save_score);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowattributes = window.getAttributes();
        windowattributes.gravity = gravity;
        window.setAttributes(windowattributes);
        dialog.setCancelable(false);

        edtScore = dialog.findViewById(R.id.edt_savescore);
        Button btSave = dialog.findViewById(R.id.bt_save);
        Button btGetout = dialog.findViewById(R.id.bt_getout);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listUser = ScoreSaveLoad.getInstance().getData(KEY_SCORE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String name = edtScore.getText().toString();
                        if (name == null) {
                            return;
                        } else {
                            listUser.add(new User(name, level));
                            sortDecrease(listUser);
                            for (int i = 0; i < listUser.size(); i++) {
                                ScoreSaveLoad.getInstance().saveData(KEY_SCORE, listUser);
                            }
                        }
                        finish();
                    }
                }, 500);
            }
        });
        btGetout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dialog.show();
    }

    private void sortDecrease(List<User> listUser) {
        Comparator<User> sortDecre = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user2.getUserScore() - user1.getUserScore();
            }
        };
        Collections.sort(listUser, sortDecre);
        if (listUser.size() > 10) {
            listUser.remove(10);
        }
    }

    private void getHelp5050() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<TextView> listCase = new ArrayList<>();
                listCase.add(tvCaseA);
                listCase.add(tvCaseB);
                listCase.add(tvCaseC);
                listCase.add(tvCaseD);
                int count = 0;
                for (int i = 1; i <= listCase.size(); i++) {
                    if (count >= 2) {
                        return;
                    } else if (i != trueCase) {
                        listCase.get(i - 1).setVisibility(View.INVISIBLE);
                        startTimer();
                        count++;
                    }
                }
            }
        }, 1500);

    }

    private void selectCase(int i, TextView tvCase) {
        pauseTimer();
        tvCase.setBackgroundResource(R.drawable.bg_orange_corner_30);
        selected = i;
    }

    private void checkAnswer(final TextView textView, int selected) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (selected == trueCase) {
                    textView.setBackgroundResource(R.drawable.bg_green_corner_30);
                    M002_PlayActivity.this.nextQuestion(selected);
                    //count++;
                } else {
                    textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                    M002_PlayActivity.this.gameOver();
                }
            }
        }, 1500);
    }

    private void gameOver() {
        new Handler().postDelayed(() -> {
            showAnswerCorrect();
            DialogConfirm confirm = new DialogConfirm("Game over !", "Cùng chơi tiếp nhé"
                    , M002_PlayActivity.this, (key, data) -> {
                if (key == DialogConfirm.KEY_CONFIRM) {
                    finish();
                }
            });
            confirm.show();
        }, 1500);
    }

    private void nextQuestion(int selected) {
        if (level == 15 && selected == trueCase) {
            DialogConfirm confirm = new DialogConfirm("Bạn đã chiến thắng !", "Tiếp tục chơi nhé"
                    , M002_PlayActivity.this, (key, data) -> {
                if (key == DialogConfirm.KEY_CONFIRM) {
                    finish();
                }
            });
            confirm.show();
        } else {
            level++;
            tvScore.setText(ScoreRule.getInstance().getScore(level));
            resetTimer();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    M002_PlayActivity.this.initData();
                }
            }, 1000);
        }
    }

    private void showAnswerCorrect() {
        if (trueCase == 1) {
            tvCaseA.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (trueCase == 2) {
            tvCaseB.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (trueCase == 3) {
            tvCaseC.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (trueCase == 4) {
            tvCaseD.setBackgroundResource(R.drawable.bg_green_corner_30);
        }
    }

    private void countTimer() {
        if (isTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }
        updateText();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis = l;
                updateText();
            }
            @Override
            public void onFinish() {
                isTimerRunning = false;
                gameOver();
                isTimerRunning = true;
            }
        }.start();
    }

    private void updateText() {
        String time = mTimeLeftInMillis / 1000 + "";
        tvTimer.setText(time);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        isTimerRunning = false;
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME;
        updateText();
    }

}