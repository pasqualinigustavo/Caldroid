package com.caldroidsample;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.caldroidsample.application.AppContext;
import com.caldroidsample.utils.CalendarUtils;
import com.caldroidsample.utils.DateFunctions;
import com.caldroidsample.utils.Util;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class CalendarHost extends AppCompatActivity {

    public static String CLOSE_CALENDAR = "CLOSE_CALENDAR";

    private boolean typefaceComplete = false;

    private TextView textViewDataIdaVoltaSelecione;
    private TextView textViewDataIdaVoltaDatas;
    private TextView textViewDataIdaVolta;
    private FrameLayout mFrameLayout;
    private RelativeLayout activity_calendar__relativelayout_main_container;
    private LinearLayout linearData1;
    private LinearLayout linearData2;
    private LinearLayout linearData3;
    private LinearLayout linearData4;
    private TextView textViewData4;
    private TextView textViewData1;
    private TextView textViewData2;
    private TextView textViewData3;
    private ImageView buttonBack;
    private TextView buttonOk;
    private TextView buttonCancel;
    private TextView textViewMes1;
    private TextView textViewMes2;
    private TextView textViewMes3;
    private TextView textViewMes4;
    private TextView textViewTitle;

    public final static String LANGUAGE = "language";
    public final static String KEY_TYPE = "type_open";
    public final static String IDA = "ida";
    public final static String ONLY_IDA = "only_ida";
    public final static String LAST_DATE_SELECT = "last_date_selec";
    public final static String SINGLE_DATE = "single_date";
    public final static String KEY_OPEN_BY_ALERTS = "open_by_alert";

    public static final int KEY_IDA_OK = 102;
    public static final int KEY_VOLTA_OK = 101;

    protected boolean isIda;
    protected boolean isOnlyIda;
    protected boolean isSingleDate;
    protected boolean isOpenByAlert;

    private CaldroidFragment caldroidFragment = null;
    protected Bundle savedInstaceState;
    public Date lastDateSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getApplicationContext().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
//        Locale locale = new Locale("pt-BR", "BR");
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        res.updateConfiguration(config, dm);

        setContentView(R.layout.activity_calendar);

        initComponents();
        initListeners();
        initData();

        caldroidFragment = new CaldroidFragment();
        caldroidFragment.setMinDate(DateFunctions.addDays(new Date(), 1));

        if (getIntent() != null && getIntent().hasExtra(IDA)) {
            isIda = getIntent().getBooleanExtra(IDA, true);
            isSingleDate = getIntent().getBooleanExtra(SINGLE_DATE, false);
            isOpenByAlert = getIntent().getBooleanExtra(KEY_OPEN_BY_ALERTS, false);
        }

        if (getIntent() != null && getIntent().hasExtra(LAST_DATE_SELECT)) {
            String dt = getIntent().getStringExtra(LAST_DATE_SELECT);
            if (dt != null) {
                //   lastDateSelected = Util.getDateByString(dt);
            }

        }
        if (getIntent() != null && getIntent().hasExtra(ONLY_IDA)) {
            isOnlyIda = getIntent().getBooleanExtra(ONLY_IDA, false);
        }
    }

    private void initComponents() {
        mFrameLayout = findViewById(R.id.act_frame_layout);
        activity_calendar__relativelayout_main_container = findViewById(R.id.activity_calendar__relativelayout_main_container);
        linearData1 = findViewById(R.id.linear_selected1);
        linearData2 = findViewById(R.id.linear_selected2);
        linearData3 = findViewById(R.id.linear_selected3);
        linearData4 = findViewById(R.id.linear_selected4);

        textViewData1 = findViewById(R.id.dt_selected1);
        textViewData2 = findViewById(R.id.dt_selected2);
        textViewData3 = findViewById(R.id.dt_selected3);
        textViewData4 = findViewById(R.id.dt_selected4);

        textViewMes1 = findViewById(R.id.dt_selected1_mes);
        textViewMes2 = findViewById(R.id.dt_selected2_mes);
        textViewMes3 = findViewById(R.id.dt_selected3_mes);
        textViewMes4 = findViewById(R.id.dt_selected4_mes);

        textViewTitle = findViewById(R.id.row_offer__textview_subtitle);
        textViewDataIdaVolta = findViewById(R.id.textview_data_ida_volta);
        textViewDataIdaVoltaSelecione = findViewById(R.id.textview_data_ida_volta_selecione);
        textViewDataIdaVoltaDatas = findViewById(R.id.textview_data_ida_volta_datas);

        buttonBack = findViewById(R.id.activity_create_account__imageview_back);
        buttonOk = findViewById(R.id.activity_tickets_filter__textview_confirm);
        buttonCancel = findViewById(R.id.textView_back_cancel);
    }

    private void initListeners() {

    }

    private void initData() {

    }


    public void init() {
        //  mover o calendario para a data selecionada na ida
        if (lastDateSelected != null) {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            cal.setTime(lastDateSelected);
            caldroidFragment = new CaldroidFragment();
            caldroidFragment.setMinDate(lastDateSelected);
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putInt(CaldroidFragment.THEME_RESOURCE, R.style.CaldroidDefault);
            args.putString(LANGUAGE, "pt-BR");
            caldroidFragment.setArguments(args);
        }

        if (isIda) {
            //buttonBack.setImageResource(R.drawable.empty);
            if (isOpenByAlert) {
                textViewTitle.setText(R.string.datas_ida_alerta);
                textViewDataIdaVolta.setVisibility(View.GONE);
                textViewDataIdaVoltaDatas.setVisibility(View.GONE);
                textViewDataIdaVoltaSelecione.setVisibility(View.GONE);
                updateLayoutDaysIdaAlert();
                buttonCancel.setVisibility(View.VISIBLE);
                buttonBack.setVisibility(View.GONE);
                buttonOk.setVisibility(View.GONE);

                buttonCancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = null;
                        if (isOpenByAlert) {
                            i = new Intent();
                            if (isIda) {
                                Date date = AppContext.getInstance().getDiaIdaAlerta();
                                String dateStr = null;
                                if (date != null)
                                    dateStr = Util.getFormatedDate(date);
                                i.putExtra("date", dateStr);
                            } else {
                                Date date = AppContext.getInstance().getDiaVoltaAlerta();
                                String dateStr = null;
                                if (date != null)
                                    dateStr = Util.getFormatedDate(date);
                                i.putExtra("date", dateStr);
                            }
                        }
                        setResult(RESULT_OK, i);
                        finish();
                    }
                });
            } else {
                textViewTitle.setText(R.string.datas_ida);
                textViewDataIdaVolta.setVisibility(View.VISIBLE);
                textViewDataIdaVoltaDatas.setVisibility(View.VISIBLE);
                textViewDataIdaVoltaSelecione.setVisibility(View.VISIBLE);
                textViewDataIdaVolta.setText(R.string.de_ida);
                updateLayoutDaysIda();
            }

            if (!isOpenByAlert) {
//                Util.Log.i("CalendarHost - DiaVolta > 0");
//                Date lastDate = CalendarUtils.firstDateInList(filter.getListaDiaVolta());
//
//                caldroidFragment.setMaxDate(lastDate);
//                updateLayoutDaysIda();
            } else if (isOpenByAlert) {
                setCurrentMonth();
                Date lastDate = AppContext.getInstance().getDiaVoltaAlerta();

                if (caldroidFragment != null) {
                    caldroidFragment.setMaxDate(lastDate);
                    caldroidFragment.refreshView();
                }
                updateLayoutDaysIdaAlert();
            }

        } else {
            if (isOpenByAlert) {
                textViewTitle.setText(R.string.datas_volta_alerta);
                textViewDataIdaVolta.setVisibility(View.GONE);
                textViewDataIdaVoltaDatas.setVisibility(View.GONE);
                textViewDataIdaVoltaSelecione.setVisibility(View.GONE);

                buttonCancel.setVisibility(View.VISIBLE);
                buttonBack.setVisibility(View.GONE);
                buttonOk.setVisibility(View.GONE);

                buttonCancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = null;
                        if (isOpenByAlert) {
                            i = new Intent();
                            if (!isIda) {
                                Date date = AppContext.getInstance().getDiaVoltaAlerta();
                                String dateStr = null;
                                if (date != null)
                                    dateStr = Util.getFormatedDate(date);
                                i.putExtra("date", dateStr);
                            }
                            setResult(RESULT_OK, i);
                        }
                        finish();
                    }
                });
            } else {
                textViewTitle.setText(R.string.datas_volta);

                textViewDataIdaVolta.setVisibility(View.VISIBLE);
                textViewDataIdaVoltaDatas.setVisibility(View.VISIBLE);
                textViewDataIdaVoltaSelecione.setVisibility(View.VISIBLE);
                textViewDataIdaVolta.setText(R.string.de_volta);
                updateLayoutDaysVolta();
            }

            // seta como o prmeiro dia selecionado de ida como sendo o primeiro
            // da volta
//            if (!isOpenByAlert && filter.getListaDiaIda().size() > 0) {
//
//                Util.Log.i("CalendarHost - DiaIda > 0");
//
//                Date lastDate = CalendarUtils
//                        .firstDateInList(filter.getListaDiaIda());
//
//                caldroidFragment.setMinDate(lastDate);
//                updateLayoutDaysVolta();
//            } else if (isOpenByAlert) {
//                setCurrentMonth();
//                Date lastDate = AppContext.getInstance().getDiaIdaAlerta();
//                if (lastDate == null)
//                    lastDate = new Date();
//                if (caldroidFragment != null) {
//                    caldroidFragment.setMinDate(lastDate);
//                    caldroidFragment.refreshView();
//                }
//                updateLayoutDaysVoltaAlert();
//            }

        }

//        final CaldroidListener listener = new CaldroidListener() {
//
//            @Override
//            public void onSelectDate(Date date, View view) {
//                Util.Log.i("Date: " + Util.getFormatedDate(date));
//                if (!isOpenByAlert) {
//
//                    if (isIda) {
//                        if (filter.getListaDiaIda().contains(date)) {
//                            filter.getListaDiaIda().remove(date);
//                            caldroidFragment.clearBackgroundDrawableForDate(date);
//                            caldroidFragment.clearTextColorForDate(date);
//                            updateLayoutDaysIda();
//                        } else {
//                            if ((filter.getListaDiaIda().size() <= 3 && !isSingleDate) || (isSingleDate && filter.getListaDiaIda().size() <= 0)) {
//                                filter.getListaDiaIda().add(date);
//                                Collections.sort(filter.getListaDiaIda());
//                                updateLayoutDaysIda();
//                            }
//                        }
//                        saveDateIdaPreferences();
//                    } else {
//                        if (filter.getListaDiaVolta().contains(date)) {
//                            filter.getListaDiaVolta().remove(date);
//                            caldroidFragment.clearBackgroundDrawableForDate(date);
//                            caldroidFragment.clearTextColorForDate(date);
//                            updateLayoutDaysVolta();
//                        } else {
//                            if ((filter.getListaDiaVolta().size() <= 3 && !isSingleDate) || (isSingleDate && filter.getListaDiaVolta().size() <= 0)) {
//                                filter.getListaDiaVolta().add(date);
//                                Collections.sort(filter.getListaDiaVolta());
//                                updateLayoutDaysVolta();
//                            }
//                        }
//                        saveDateVoltaPreferences();
//                    }
//
//                } else {  //  se foi aberto pelo alerta
//                    if (isIda) {
//                        if (AppContext.getInstance().getDiaIdaAlerta() != null && Util.getFormatedDate(AppContext.getInstance().getDiaIdaAlerta()).equals(Util.getFormatedDate(date))) {
//                            AppContext.getInstance().setDiaIdaAlerta(null);
//                            caldroidFragment.clearBackgroundDrawableForDate(date);
//                            caldroidFragment.clearTextColorForDate(date);
//                            updateLayoutDaysIdaAlert();
//                        } else {
//                            if (AppContext.getInstance().getDiaIdaAlerta() == null) {
//                                AppContext.getInstance().setDiaIdaAlerta(date);
//                                updateLayoutDaysIdaAlert();
//                            }
//                            String dateStr = Util.getFormatedDate(date);
//                            caldroidFragment.clearBackgroundDrawableForDate(date);
//                            caldroidFragment.clearTextColorForDate(date);
//                            updateLayoutDaysIdaAlert();
//                            Intent intent = new Intent();
//                            intent.putExtra("date", dateStr);
//                            setResult(RESULT_OK, intent);
//                            finish();
//                        }
//                    } else {
//                        if (AppContext.getInstance().getDiaVoltaAlerta() != null && Util.getFormatedDate(AppContext.getInstance().getDiaVoltaAlerta()).equals(Util.getFormatedDate(date))) {
//                            AppContext.getInstance().setDiaVoltaAlerta(null);
//                            caldroidFragment.clearBackgroundDrawableForDate(date);
//                            caldroidFragment.clearTextColorForDate(date);
//                            updateLayoutDaysVoltaAlert();
//                        } else {
//                            if (AppContext.getInstance().getDiaVoltaAlerta() == null) {
//                                AppContext.getInstance().setDiaVoltaAlerta(date);
//                                updateLayoutDaysVoltaAlert();
//                            }
//                            String dateStr = Util.getFormatedDate(date);
//                            updateLayoutDaysVoltaAlert();
//                            Intent intent = new Intent();
//                            intent.putExtra("date", dateStr);
//                            setResult(RESULT_OK, intent);
//                            finish();
//                        }
//                    }
//
//                }
//            }
//
//            @Override
//            public void onChangeMonth(int month, int year) {
//
//            }
//
//            @Override
//            public void onLongClickDate(Date date, View view) {
//
//            }
//
//            @Override
//            public void onCaldroidViewCreated() {
//                Resources res = getApplicationContext().getResources();
//                DisplayMetrics dm = res.getDisplayMetrics();
//                Locale locale = new Locale("pt-BR", "BR");
//                Locale.setDefault(locale);
//                Configuration config = new Configuration();
//                config.locale = locale;
//                res.updateConfiguration(config, dm);
//            }
//
//        };

//        caldroidFragment.setCaldroidListener(listener);

        if (savedInstaceState != null) {

            caldroidFragment.restoreStatesFromKey(savedInstaceState,
                    "CALDROID_SAVED_STATE");

        } else {

            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());

            if (!isOpenByAlert) {
//                if (isIda) {
//                    if (filter.getListaDiaIda().size() > 0) {
//                        Date dt = filter.getListaDiaIda().get(0);
//                        cal.setTime(dt);
//                    }
//                } else {
//                    if (filter.getListaDiaVolta().size() > 0) {
//                        Date dt = filter.getListaDiaVolta().get(0);
//                        cal.setTime(dt);
//                    }
//                }


                if (lastDateSelected == null) {
                    args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
                    args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
                    args.putInt(CaldroidFragment.THEME_RESOURCE, R.style.CaldroidDefault);
                    args.putString(LANGUAGE, "pt-BR");
                    try {
                        caldroidFragment.setArguments(args);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

            } else {

                if (isIda) {
                    if (AppContext.getInstance().getDiaIdaAlerta() != null) {
                        cal.setTime(AppContext.getInstance().getDiaIdaAlerta());
                    }
                } else {
                    if (AppContext.getInstance().getDiaVoltaAlerta() != null) {
                        cal.setTime(AppContext.getInstance().getDiaVoltaAlerta());
                    }
                    if (AppContext.getInstance().getDiaIdaAlerta() != null) {
                        cal.setTime(AppContext.getInstance().getDiaIdaAlerta());
                    }
                }


                args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
                args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
                args.putInt(CaldroidFragment.THEME_RESOURCE, R.style.CaldroidDefault);
                args.putString(LANGUAGE, "pt-BR");
                try {
                    caldroidFragment.setArguments(args);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }

        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(mFrameLayout.getId(), caldroidFragment)
                    .commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        unloadMeReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                finish();
//            }
//        };
//
//        registerReceiver(unloadMeReceiver, new IntentFilter(
//                CalendarHost.CLOSE_CALENDAR));

        if (!isIda) {
            buttonBack.setImageResource(R.drawable.seta_back_blue);

            buttonBack.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        buttonOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!isIda || isOnlyIda) {
                    Intent intent = new Intent();
                    setResult(KEY_VOLTA_OK, intent);
                    finish();

                } else {
                    Intent intent = new Intent();
                    intent.putExtra("openBack", true);
//                    FilterManager.getInstance().setDaysSource(filter.getListaDiaIda());
                    setResult(KEY_IDA_OK, intent);
                    finish();
//                    Intent i = new Intent(CalendarHost.this, CalendarHost.class);
//                    i.putExtra(CalendarHost.IDA, false);
//                    if (!isOpenByAlert) {
//                        if (filter.getListaDiaIda() != null && filter.getListaDiaIda().size() > 0) {
//                            ArrayList<Date> list = filter.getListaDiaIda();
//                            Date firstDate = null;
//                            for (Date dt : list) {
//                                if (firstDate != null && dt.before(dt)) {
//                                    firstDate = dt;
//                                } else if (firstDate == null) {
//                                    firstDate = dt;
//                                }
//                            }
//                            String dtString = Util.getFormatedDate(firstDate);
//                            i.putExtra(CalendarHost.LAST_DATE_SELECT, dtString);
//                        }
//                    } else {
//                        if (AppContext.getInstance().getDiaIdaAlerta() != null) {
//                            Date dt = AppContext.getInstance().getDiaIdaAlerta();
//                            String dtString = Util.getFormatedDate(dt);
//                            i.putExtra(CalendarHost.LAST_DATE_SELECT, dtString);
//                        }
//                    }
//                    FilterManager.getInstance().setDaysSource(filter.getListaDiaIda());
//                    i.putExtra(CalendarHost.KEY_OPEN_BY_ALERTS, isOpenByAlert);
//                    startActivityForResult(i, CalendarHost.KEY_VOLTA_OK);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KEY_VOLTA_OK) {
            setResult(RESULT_OK, null);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent() != null && getIntent().hasExtra(KEY_OPEN_BY_ALERTS)) {
            isOpenByAlert = getIntent().getBooleanExtra(KEY_OPEN_BY_ALERTS, false);
        }
        if (getIntent() != null && getIntent().hasExtra(IDA)) {
            isIda = getIntent().getBooleanExtra(IDA, true);
        }
//        if (controller != null)
        init();
    }

    protected void updateLayoutDaysIda() {
//        if (filter.getListaDiaIda().size() == 0) {
//            linearData1.setVisibility(View.GONE);
//            linearData2.setVisibility(View.GONE);
//            linearData3.setVisibility(View.GONE);
//            linearData4.setVisibility(View.GONE);
//        } else if (filter.getListaDiaIda().size() == 1) {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(0)).substring(
//                            0, 2));
//            textViewMes1.setText(
//                    getStringMonth(filter.getListaDiaIda().get(0)));
//            linearData2.setVisibility(View.GONE);
//            linearData3.setVisibility(View.GONE);
//            linearData4.setVisibility(View.GONE);
//        } else if (filter.getListaDiaIda().size() == 2) {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(0)).substring(
//                            0, 2));
//            textViewMes1.setText(
//                    getStringMonth(filter.getListaDiaIda().get(0)));
//            linearData2.setVisibility(View.VISIBLE);
//            textViewData2.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(1)).substring(
//                            0, 2));
//            textViewMes2.setText(
//                    getStringMonth(filter.getListaDiaIda().get(1)));
//            linearData3.setVisibility(View.GONE);
//            linearData4.setVisibility(View.GONE);
//        } else if (filter.getListaDiaIda().size() == 3) {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(0)).substring(
//                            0, 2));
//            textViewMes1.setText(
//                    getStringMonth(filter.getListaDiaIda().get(0)));
//            linearData2.setVisibility(View.VISIBLE);
//            textViewData2.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(1)).substring(
//                            0, 2));
//            textViewMes2.setText(
//                    getStringMonth(filter.getListaDiaIda().get(1)));
//            linearData3.setVisibility(View.VISIBLE);
//            textViewData3.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(2)).substring(
//                            0, 2));
//            textViewMes3.setText(
//                    getStringMonth(filter.getListaDiaIda().get(2)));
//            linearData4.setVisibility(View.GONE);
//        } else if (filter.getListaDiaIda().size() == 4) {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(0)).substring(
//                            0, 2));
//            textViewMes1.setText(
//                    getStringMonth(filter.getListaDiaIda().get(0)));
//            linearData2.setVisibility(View.VISIBLE);
//            textViewData2.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(1)).substring(
//                            0, 2));
//            textViewMes2.setText(
//                    getStringMonth(filter.getListaDiaIda().get(1)));
//            linearData3.setVisibility(View.VISIBLE);
//            textViewData3.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(2)).substring(
//                            0, 2));
//            textViewMes3.setText(
//                    getStringMonth(filter.getListaDiaIda().get(2)));
//            linearData4.setVisibility(View.VISIBLE);
//            textViewData4.setText(
//                    Util.getFormatedDate(filter.getListaDiaIda().get(3)).substring(
//                            0, 2));
//            textViewMes4.setText(
//                    getStringMonth(filter.getListaDiaIda().get(3)));
//
//        }
//        ColorDrawable blue = new ColorDrawable(ResourcesCompat.getColor(VoopterApplication.getAppContext().getResources(), R.color.blue, VoopterApplication.getAppContext().getTheme()));
//        HashMap<Date, Drawable> backgroundForDateMap = new HashMap<>();
//        HashMap<Date, Integer> fontsForDateMap = new HashMap<>();
//
//
//        if (filter.getListaDiaIda().size() > 0) {
//            for (Date dt : filter.getListaDiaIda()) {
//
//                Util.Log.i("Data carregada: " + dt.toString());
//
//                backgroundForDateMap
//                        .put(dt, blue);
//                fontsForDateMap.put(dt, R.color.white);
//            }
//        }
//
//
//        if (caldroidFragment != null) {
//            caldroidFragment.setBackgroundDrawableForDates(backgroundForDateMap);
//            caldroidFragment.setTextColorForDates(fontsForDateMap);
//            caldroidFragment.refreshView();
//        }
    }


    protected void updateLayoutDaysIdaAlert() {
//        if (AppContext.getInstance().getDiaIdaAlerta() == null) {
//            linearData1.setVisibility(View.GONE);
//            linearData2.setVisibility(View.GONE);
//            linearData3.setVisibility(View.GONE);
//            linearData4.setVisibility(View.GONE);
//        } else {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(AppContext.getInstance().getDiaIdaAlerta()).substring(
//                            0, 2));
//            textViewMes1.setText(
//                    getStringMonth(AppContext.getInstance().getDiaIdaAlerta()));
//            linearData2.setVisibility(View.GONE);
//            linearData3.setVisibility(View.GONE);
//            linearData4.setVisibility(View.GONE);
//        }
//
//        ColorDrawable blue = new ColorDrawable(ResourcesCompat.getColor(VoopterApplication.getAppContext().getResources(), R.color.blue, VoopterApplication.getAppContext().getTheme()));
//        HashMap<Date, Drawable> backgroundForDateMap = new HashMap<>();
//        HashMap<Date, Integer> fontsForDateMap = new HashMap<>();
//
//
//        if (AppContext.getInstance().getDiaIdaAlerta() != null) {
//            Util.Log.i("Data carregada: " + AppContext.getInstance().getDiaIdaAlerta().toString());
//
//            backgroundForDateMap
//                    .put(AppContext.getInstance().getDiaIdaAlerta(), blue);
//            fontsForDateMap.put(AppContext.getInstance().getDiaIdaAlerta(), R.color.white);
//        }
//
//        if (caldroidFragment != null) {
//            caldroidFragment.setBackgroundDrawableForDates(backgroundForDateMap);
//            caldroidFragment.setTextColorForDates(fontsForDateMap);
//            caldroidFragment.refreshView();
//        }
    }

    protected void updateLayoutDaysVoltaAlert() {
        if (AppContext.getInstance().getDiaVoltaAlerta() == null) {
            linearData1.setVisibility(View.GONE);
            linearData2.setVisibility(View.GONE);
            linearData3.setVisibility(View.GONE);
            linearData4.setVisibility(View.GONE);
        } else {
            linearData1.setVisibility(View.VISIBLE);
            textViewData1.setText(
                    Util.getFormatedDate(AppContext.getInstance().getDiaVoltaAlerta())
                            .substring(0, 2));
            textViewMes1.setText(
                    getStringMonth(AppContext.getInstance().getDiaVoltaAlerta()));
            linearData2.setVisibility(View.GONE);
            linearData3.setVisibility(View.GONE);
            linearData4.setVisibility(View.GONE);
        }

        Util.Log.i("enter here");
        //ColorDrawable blue = new ColorDrawable(ResourcesCompat.getColor(VoopterApplication.getAppContext().getResources(), R.color.blue, VoopterApplication.getAppContext().getTheme()));
        HashMap<Date, Drawable> backgroundForDateMap = new HashMap<>();
        HashMap<Date, Integer> fontsForDateMap = new HashMap<>();
        if (AppContext.getInstance().getDiaVoltaAlerta() != null) {
            Util.Log.i("maior");

            Util.Log.i("Data volta carregada : " + AppContext.getInstance().getDiaVoltaAlerta().toString());
            Util.Log.i("1");

//            backgroundForDateMap
//                    .put(AppContext.getInstance().getDiaVoltaAlerta(), blue);
//            fontsForDateMap.put(AppContext.getInstance().getDiaVoltaAlerta(), R.color.white);
        }

        if (caldroidFragment != null) {
            Util.Log.i("2");
            caldroidFragment.setBackgroundDrawableForDates(backgroundForDateMap);
            caldroidFragment.setTextColorForDates(fontsForDateMap);

            caldroidFragment.refreshView();
        }
    }

    public void setCurrentMonth() {
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        if (isIda) {
            if (AppContext.getInstance().getDiaIdaAlerta() != null) {
                cal.setTime(AppContext.getInstance().getDiaIdaAlerta());
            }
        } else {
            if (AppContext.getInstance().getDiaVoltaAlerta() != null) {
                cal.setTime(AppContext.getInstance().getDiaVoltaAlerta());
            }
            if (AppContext.getInstance().getDiaIdaAlerta() != null) {
                cal.setTime(AppContext.getInstance().getDiaIdaAlerta());
            }
        }

        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH));
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.THEME_RESOURCE, R.style.CaldroidDefault);
        args.putString(LANGUAGE, "pt-BR");
        caldroidFragment.setArguments(args);
    }

    private String getStringMonth(Date dt) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        String mes = CalendarUtils.getMonthOfDate(cal, this);

        return mes.toUpperCase();

    }

    protected void updateLayoutDaysVolta() {
//        if (filter.getListaDiaVolta().size() == 0) {
//            linearData1.setVisibility(View.GONE);
//            linearData2.setVisibility(View.GONE);
//            linearData3.setVisibility(View.GONE);
//            linearData4.setVisibility(View.GONE);
//        } else if (filter.getListaDiaVolta().size() == 1) {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(0))
//                            .substring(0, 2));
//            textViewMes1.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(0)));
//            linearData2.setVisibility(View.GONE);
//            linearData3.setVisibility(View.GONE);
//            linearData4.setVisibility(View.GONE);
//        } else if (filter.getListaDiaVolta().size() == 2) {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(0))
//                            .substring(0, 2));
//            textViewMes1.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(0)));
//            linearData2.setVisibility(View.VISIBLE);
//            textViewData2.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(1))
//                            .substring(0, 2));
//            textViewMes2.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(1)));
//            linearData3.setVisibility(View.GONE);
//            linearData4.setVisibility(View.GONE);
//        } else if (filter.getListaDiaVolta().size() == 3) {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(0))
//                            .substring(0, 2));
//            textViewMes1.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(0)));
//            linearData2.setVisibility(View.VISIBLE);
//            textViewData2.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(1))
//                            .substring(0, 2));
//            textViewMes2.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(1)));
//            linearData3.setVisibility(View.VISIBLE);
//            textViewData3.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(2))
//                            .substring(0, 2));
//            textViewMes3.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(2)));
//            linearData4.setVisibility(View.GONE);
//        } else if (filter.getListaDiaVolta().size() == 4) {
//            linearData1.setVisibility(View.VISIBLE);
//            textViewData1.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(0))
//                            .substring(0, 2));
//            textViewMes1.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(0)));
//            linearData2.setVisibility(View.VISIBLE);
//            textViewData2.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(1))
//                            .substring(0, 2));
//            textViewMes2.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(1)));
//            linearData3.setVisibility(View.VISIBLE);
//            textViewData3.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(2))
//                            .substring(0, 2));
//            textViewMes3.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(2)));
//            linearData4.setVisibility(View.VISIBLE);
//            textViewData4.setText(
//                    Util.getFormatedDate(filter.getListaDiaVolta().get(3))
//                            .substring(0, 2));
//            textViewMes4.setText(
//                    getStringMonth(filter.getListaDiaVolta().get(3)));
//
//        }
//        Util.Log.i("enter here");
//        ColorDrawable blue = new ColorDrawable(ResourcesCompat.getColor(VoopterApplication.getAppContext().getResources(), R.color.blue, VoopterApplication.getAppContext().getTheme()));
//        HashMap<Date, Drawable> backgroundForDateMap = new HashMap<>();
//        HashMap<Date, Integer> fontsForDateMap = new HashMap<>();
//        if (filter.getListaDiaVolta().size() > 0) {
//            Util.Log.i("maior");
//
//            for (Date dt : filter.getListaDiaVolta()) {
//                Util.Log.i("Data volta carregada : " + dt.toString());
//                Util.Log.i("1");
//
//                backgroundForDateMap
//                        .put(dt, blue);
//                fontsForDateMap.put(dt, R.color.white);
//            }
//        }
//
//        if (caldroidFragment != null) {
//            Util.Log.i("2");
//            caldroidFragment.setBackgroundDrawableForDates(backgroundForDateMap);
//            caldroidFragment.setTextColorForDates(fontsForDateMap);
//
//            caldroidFragment.refreshView();
//        }
    }
}
