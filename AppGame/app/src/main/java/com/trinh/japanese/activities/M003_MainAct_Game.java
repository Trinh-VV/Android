package com.trinh.japanese.activities;

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

import com.trinh.japanese.R;
import com.trinh.japanese.ScoreSaveLoad;
import com.trinh.japanese.common.ScoreRule;
import com.trinh.japanese.entities.OnActionCallback;
import com.trinh.japanese.dialog.DialogAudience;
import com.trinh.japanese.dialog.DialogCall;
import com.trinh.japanese.dialog.DialogConfirm;
import com.trinh.japanese.dialog.DialogStop;
import com.trinh.japanese.entities.User;
import com.trinh.japanese.entities.QuestionEntity;
import com.trinh.japanese.database.QuestionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class M003_MainAct_Game extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_SCORE = "KEY_SCORE";
    private static final long START_TIME = 60000;
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
    private boolean isStart;

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
        isTimerRunning = true;
        isStart = true;
        selected = 0;
        setDefaultCase(tvCaseA);
        setDefaultCase(tvCaseB);
        setDefaultCase(tvCaseC);
        setDefaultCase(tvCaseD);
        setEvent();
        countTimer();
        QuestionManager.getInstance().getQuestionById(new QuestionManager.OnHSCallBack() {
            @Override
            public void callBack(Object data) {
                M003_MainAct_Game.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        QuestionEntity questionEntity = (QuestionEntity) data;
                        tvNumberQuestion.setText("Question " + level + ":");
                        tvContentQuestion.setText(questionEntity.getQuestion());
                        tvCaseA.setText(questionEntity.getCaseA().trim());
                        tvCaseB.setText(questionEntity.getCaseB().trim());
                        tvCaseC.setText(questionEntity.getCaseC().trim());
                        tvCaseD.setText(questionEntity.getCaseD().trim());
                        trueCase = questionEntity.getTrueCase();
                    }
                });
            }
        }, level);
    }

    private void setDefaultCase(TextView tv) {
        tv.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv.setVisibility(View.VISIBLE);
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
                break;
            case R.id.tv_case2:
                selectCase(2, tvCaseB);
                break;
            case R.id.tv_case3:
                selectCase(3, tvCaseC);
                break;
            case R.id.tv_case4:
                selectCase(4, tvCaseD);
                break;
            case R.id.imv_help_5050:
                getHelp5050();
                break;
            case R.id.imv_help_call:
                showDialogHelpCall();
                break;
            case R.id.imv_help_audience:
                showDialogHelpAudience();
                break;
            case R.id.imv_stop:
                showDialogStop();
                break;
        }
    }

    private void getHelp5050() {
        pauseTimer();
        if (imvHelp5050.getDrawable().getLevel() == 0) {
            imvHelp5050.setImageLevel(1);
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
                            count++;
                        }
                    }
                }
            }, 1500);
            startTimer();
        } else {
            return;
        }
    }

    private void showDialogHelpCall() {
        pauseTimer();
        if (imvHelpCall.getDrawable().getLevel() == 0) {
            imvHelpCall.setImageLevel(1);
            DialogCall callDialog = new DialogCall(trueCase, this, (key, data) -> startTimer());
            callDialog.show();
        } else {
            return;
        }
    }

    private void showDialogHelpAudience() {
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
    }

    private void showDialogStop() {
        pauseTimer();
        DialogStop dialogStop = new DialogStop(this, new OnActionCallback() {
            @Override
            public void callBack(int key, Object data) {
                if (key == DialogStop.KEY_STOP_GAME) {
                    saveScoreDialog(Gravity.CENTER);
                } else if (key == DialogStop.KEY_NOT_STOP_GAME) {
                    startTimer();
                }
            }
        });
        dialogStop.show();
    }

    private void saveScoreDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_save_score);

        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams wdAttributes = window.getAttributes();
        wdAttributes.gravity = gravity;
        window.setAttributes(wdAttributes);
        dialog.setCancelable(false);

        edtScore = dialog.findViewById(R.id.edt_savescore);
        Button btSave = dialog.findViewById(R.id.bt_save);
        Button btGetout = dialog.findViewById(R.id.bt_getout);
        btSave.setOnClickListener(view -> {
            listUser = ScoreSaveLoad.getInstance().getData(KEY_SCORE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String name = edtScore.getText().toString();
                    if (name == null) {
                        return;
                    } else {
                        listUser.add(new User(name, level));
                        sortUser(listUser);
                        for (int i = 0; i < listUser.size(); i++) {
                            ScoreSaveLoad.getInstance().saveData(KEY_SCORE, listUser);
                        }
                    }
                    finish();
                }
            }, 500);
        });
        btGetout.setOnClickListener(view -> finish());
        dialog.show();
    }

    private void sortUser(List<User> listUser) {
        Comparator<User> sort = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user2.getUserScore() - user1.getUserScore();
            }
        };
        Collections.sort(listUser, sort);
        if (listUser.size() > 10) {
            listUser.remove(10);
        }
    }

    private void selectCase(int i, TextView tvCase) {
        pauseTimer();
        tvCase.setBackgroundResource(R.drawable.bg_orange_corner_30);
        selected = i;
        checkAnswer(tvCase, i);
    }

    private void checkAnswer(final TextView textView, int selected) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (selected == trueCase) {
                    textView.setBackgroundResource(R.drawable.bg_green_corner_30);
                    M003_MainAct_Game.this.nextQuestion(selected);
                } else {
                    textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                    M003_MainAct_Game.this.gameOver();
                }
            }
        }, 1500);
    }

    private void nextQuestion(int selected) {
        if (level == 15 && selected == trueCase) {
            DialogConfirm confirmDL = new DialogConfirm("Bạn đã chiến thắng !", "Tiếp tục chơi nhé"
                    , M003_MainAct_Game.this, (key, data) -> {
                if (key == DialogConfirm.KEY_CONFIRM) {
                    saveScoreDialog(Gravity.CENTER);
                }
            });
            confirmDL.show();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    level++;
                    tvScore.setText(ScoreRule.getInstance().getScore(level));
                    M003_MainAct_Game.this.initData();
                }
            }, 1000);
        }
    }

    private void gameOver() {
        new Handler().postDelayed(() -> {
            showAnswerCorrect();
            DialogConfirm confirm = new DialogConfirm("Game over !", "Cùng chơi tiếp nhé"
                    , M003_MainAct_Game.this, (key, data) -> {
                if (key == DialogConfirm.KEY_CONFIRM) {
                    finish();
                }
            });
            confirm.show();
        }, 1500);
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
        if (!isTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }
        updateText();
    }

    private void startTimer() {
        if (isStart)mTimeLeftInMillis=START_TIME;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis=l;
                updateText();
            }
            @Override
            public void onFinish() {
                isTimerRunning = true;
                gameOver();
            }
        }.start();
    }

    private void pauseTimer() {
        if (mCountDownTimer==null)return;
        isStart =false;
        mCountDownTimer.cancel();
        isTimerRunning = false;
    }

    private void updateText() {
        String time = mTimeLeftInMillis / 1000 + "";
        tvTimer.setText(time);
    }
}