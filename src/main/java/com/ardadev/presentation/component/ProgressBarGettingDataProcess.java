package com.ardadev.presentation.component;

import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.uses_cases.currency_converted.CurrencyConvertedUseCase;
import com.ardadev.infrastructure.driven_adapter.api.currency_converted_api.CurrencyConvertedApi;

import javax.swing.*;
import java.util.List;

public class ProgressBarGettingDataProcess extends JProgressBar {
    private Boolean isTerminatedTasks;
    private CurrencyConverted currencyConverted;
    public ProgressBarGettingDataProcess() {
        this.isTerminatedTasks = false;
        setMinimum(0);
        setMaximum(100);
        setStringPainted(false);
    }

    public void executeTasks(List<Runnable> tasks) {
        this.isTerminatedTasks = false;
        SwingUtilities.invokeLater(() -> {
            for (int i = 1; i <= tasks.size(); i++) {
                try {
                    double percentage = (((double) i / tasks.size()) * 100);
                    setValue((int) percentage);
                    Thread.sleep(200);
                    tasks.get(i - 1).run();
                }catch (InterruptedException e) {
                    this.isTerminatedTasks = true;
                    e.printStackTrace();
                }
            }
            this.isTerminatedTasks = true;
        });
        setValueDefault();
    }
    public void executeTaskCurrencyConverted(String currency) {
        this.isTerminatedTasks = false;
        SwingUtilities.invokeLater(() -> {
            try {
                setValue(100);
                Thread.sleep(200);
                this.currencyConverted = new CurrencyConvertedUseCase(new CurrencyConvertedApi()).getCurrencyConverted(currency);
            }catch (InterruptedException e) {
                this.isTerminatedTasks = true;
                e.printStackTrace();
            }
            this.isTerminatedTasks = true;
        });
        setValueDefault();
    }
    private void setValueDefault() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                    if (this.isTerminatedTasks) {
                        setValue(0);
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
    public CurrencyConverted getCurrencyConverted() {
        return currencyConverted;
    }
    public Boolean getTerminatedTasks() {
        return isTerminatedTasks;
    }
}
